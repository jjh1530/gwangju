package com.example.testre.Resturant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testre.Item.AcitivityItem
import com.example.testre.R
import com.example.testre.databinding.FragmentResturantBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FragmentResturant : Fragment(R.layout.fragment_resturant) {

    private lateinit var resturantDB: DatabaseReference
    private lateinit var resturantAdapter: ResturantAdapter
    private val resturantList = mutableListOf<Resturant>()

    private val listener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val resturant = snapshot.getValue(Resturant::class.java)
            resturant ?: return

            resturantList.add(resturant)
            resturantAdapter.submitList(resturantList)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }
    private var binding: FragmentResturantBinding? = null
    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentResturantBinding = FragmentResturantBinding.bind(view)
        binding = fragmentResturantBinding

        resturantList.clear()
        resturantDB = Firebase.database.reference.child("Resturant")

        resturantAdapter = ResturantAdapter(
            onItemClicked = {bar ->
                val intent = Intent(context, AcitivityItem::class.java)
                intent.putExtra("name",bar.name)
                intent.putExtra("info",bar.info)
                intent.putExtra("picture",bar.picture)
                startActivity(intent)
            }
        )


        fragmentResturantBinding.rvResturant.layoutManager = LinearLayoutManager(context)
        fragmentResturantBinding.rvResturant.adapter = resturantAdapter

        resturantDB.addChildEventListener(listener)
    }

    override fun onResume() {
        super.onResume()
        resturantAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        resturantDB.removeEventListener(listener)
    }
}
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_resturant, container, false)
//
//        val resturantList = arrayListOf(
//            Resturant("1",R.drawable.rest1,"오미뚝배기","시골밥상 style 숯불향 나는 매콤달콤 돼지불고기 집"),
//            Resturant("2",R.drawable.rest2,"가원","여름되면 무조건 생각나는 냉면맛집! tv 출현한 찐맛집"),
//            Resturant("3",R.drawable.rest3,"나주곰탕","동구청 근처에 있는 맑은 국물에 깊은 맛까지 나는 국밥집!"),
//            Resturant("4",R.drawable.rest4,"솔밥솥밥","한국인은 밥심!! 정갈하고 깔끔한 솥밥집"),
//            Resturant("5",R.drawable.rest5,"미미원","즉석에서 구워주는 육전 광주 3대육전맛집으로 꼽힌다는 곳!"),
//            Resturant("6",R.drawable.rest6,"산수쌈밥","상추에 수육 + 우럭된장까지 싸먹어야 찐맛"),
//            Resturant("7",R.drawable.rest7,"청미장","한옥에서 먹는 깔끔한 맛의 곱창전골에 소주~"),
//            Resturant("8",R.drawable.rest8,"금관꽃돼지","야들야들한 꽃삼겹살에 냉명까지 먹자!"),
//            Resturant("9",R.drawable.rest9,"동명사랑채","우렁강된장 쌈밥 전문점! 푸짐한 한상차림")
//        )
//        val adapter = ResturantAdapter(resturantList)
//        view.rv_resturant.adapter = adapter
//        view.rv_resturant.layoutManager = LinearLayoutManager(context)
//
//        return view
//    }
//}
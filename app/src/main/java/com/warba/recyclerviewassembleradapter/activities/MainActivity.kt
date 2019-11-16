package com.warba.recyclerviewassembleradapter.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.warba.recyclerviewassembleradapter.DataProvider
import com.warba.recyclerviewassembleradapter.R
import com.warba.recyclerviewassembleradapter.adapters.ClientProductRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvClients.layoutManager = LinearLayoutManager(this)
        var adapter = ClientProductRecyclerViewAdapter()
        rvClients.adapter = adapter
        adapter.setData(DataProvider.getClientsAndProductsList())
        btnAddAds.setOnClickListener {
            var intent = Intent(this, CombinedListActivity::class.java)
            startActivity(intent)
        }
    }
}

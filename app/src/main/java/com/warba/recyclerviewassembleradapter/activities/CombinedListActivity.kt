package com.warba.recyclerviewassembleradapter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.appro.recyclerviewassembler.RecyclerViewAssemblerAdapter
import com.appro.recyclerviewassembler.RecyclerViewAssemblerElement
import com.warba.recyclerviewassembleradapter.DataProvider
import com.warba.recyclerviewassembleradapter.R
import com.warba.recyclerviewassembleradapter.adapters.AdsRecyclerViewAdapter
import com.warba.recyclerviewassembleradapter.adapters.ClientProductRecyclerViewAdapter
import com.warba.recyclerviewassembleradapter.models.Adv
import kotlinx.android.synthetic.main.activity_combined_list.*

class CombinedListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combined_list)
        rvCombined.layoutManager = LinearLayoutManager(this)

        var lstEntries = ArrayList<Any>()

        var clientProductRecyclerView = ClientProductRecyclerViewAdapter()
        clientProductRecyclerView.setData(DataProvider.getClientsAndProductsList())

        var advAdapter = AdsRecyclerViewAdapter()
        advAdapter.setData(DataProvider.getAds())


        lstEntries.addAll(DataProvider.getClientsAndProductsList())
        lstEntries.add(0, DataProvider.getAds()[0])
        lstEntries.add(5, DataProvider.getAds()[1])

        var assembler = RecyclerViewAssemblerAdapter.Builder()
            .addAdapter(
                clientProductRecyclerView,
                R.layout.row_client,
                R.layout.row_product
            )
            .addAdapter(
                advAdapter,
                R.layout.row_ads
            ).build(object : RecyclerViewAssemblerAdapter.PositionAdapterMapper{
                override fun map(position: Int): RecyclerViewAssemblerElement {
                    return when {
                        lstEntries[position] is Adv -> advAdapter
                        else -> clientProductRecyclerView
                    }
                }
            })

        rvCombined.adapter = assembler

        assembler.setData(lstEntries)
    }
}

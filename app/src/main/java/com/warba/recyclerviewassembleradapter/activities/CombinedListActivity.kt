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

        //All Entries
        var lstEntries = ArrayList<Any>()

        //Prepare First Adapter
        var clientProductRecyclerView = ClientProductRecyclerViewAdapter()
        clientProductRecyclerView.setData(DataProvider.getClientsAndProductsList())

        //Prepare Second Adapter
        var advAdapter = AdsRecyclerViewAdapter()
        advAdapter.setData(DataProvider.getAds())


        //Add All entries combined
        lstEntries.addAll(DataProvider.getClientsAndProductsList())
        lstEntries.add(0, DataProvider.getAds()[0])
        lstEntries.add(5, DataProvider.getAds()[1])

        //Build the Assembler adapter
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
                        //Map Adapters
                        lstEntries[position] is Adv -> advAdapter
                        else -> clientProductRecyclerView
                    }
                }
            })

        //Attach Assembler adapter to recyclerview
        rvCombined.adapter = assembler

        //Add All data to assembler adapter
        assembler.setData(lstEntries)
    }
}

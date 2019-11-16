# RecyclerViewAssemblerAdapter
This library allows you to combine any types of Recyclerview adapters easilly without the need to modify or create any new Recycler view adapter

Implmentation:
```
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
```

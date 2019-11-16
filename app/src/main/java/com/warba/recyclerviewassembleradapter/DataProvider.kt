package com.warba.recyclerviewassembleradapter

import com.warba.recyclerviewassembleradapter.models.Adv
import com.warba.recyclerviewassembleradapter.models.Client
import com.warba.recyclerviewassembleradapter.models.Product

object DataProvider {

    fun getClientsAndProductsList(): List<Any>{
        var data = ArrayList<Any>()

        data.add(Client("Georges", "0098711112"))
        data.add(Product("S10", "Smartphone"))
        data.add(Product("IPhone", "Smartphone"))
        data.add(Product("Toshiba", "Laptop"))

        data.add(Client("Mark", "0098717712"))
        data.add(Product("Toshiba", "Charger"))

        data.add(Client("Chris", "0098237712"))
        data.add(Product("Toshiba", "Charger"))
        data.add(Product("Toshiba", "Laptop"))
        data.add(Product("Macbook Pro", "Laptop"))
        data.add(Product("Macbook Pro", "Laptop"))

        data.add(Client("Arnold", "0228237712"))
        data.add(Product("Lenovo", "Tablet"))

        return data
    }

    fun getAds(): ArrayList<Adv> {
        var ads = ArrayList<Adv>()
        ads.add(Adv("Hello this is our first Ads", "First Ads Desc"))
        ads.add(Adv("Hello this is our Second Ads", "Second Ads Desc"))
        return ads
    }

}
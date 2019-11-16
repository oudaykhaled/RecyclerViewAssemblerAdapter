package com.appro.recyclerviewassembler

interface NotifyableAdapter {
    val listener: OnNotifyDatasetChangeListener?
        get() = null

    fun setOnNotifyDataSetChangeListener(onNotifyDataSetChangeListener: OnNotifyDatasetChangeListener)
}

package com.appro.recyclerviewassembler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import java.util.HashMap

class RecyclerViewAssemblerAdapter private constructor(private val builder: Builder) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), NotifyableAdapter {
    private var data: List<Any>? = null

    private var onNotifyDataSetChangeListener: OnNotifyDatasetChangeListener? = null

    fun setData(data: List<Any>) {
        this.data = data
        if (onNotifyDataSetChangeListener != null) onNotifyDataSetChangeListener!!.onNotifyDataSetChange()
    }

    override fun getItemViewType(position: Int): Int {
        val positionInChildAdapter =
            builder.positionAdapterMapper!!.map(position).getData().indexOf(data!![position])
        return (builder.positionAdapterMapper!!.map(position) as RecyclerView.Adapter<RecyclerView.ViewHolder>).getItemViewType(
            positionInChildAdapter
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return builder.viewTypesAdapterMap[viewType]!!.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val positionInChildAdapter =
            builder.positionAdapterMapper!!.map(position).getData().indexOf(data!![position])
        (builder.positionAdapterMapper!!.map(position) as RecyclerView.Adapter<RecyclerView.ViewHolder>).onBindViewHolder(
            holder,
            positionInChildAdapter
        )
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    override fun setOnNotifyDataSetChangeListener(onNotifyDataSetChangeListener: OnNotifyDatasetChangeListener) {
        this.onNotifyDataSetChangeListener = onNotifyDataSetChangeListener
    }

    class Builder {
        internal val viewTypesAdapterMap = HashMap<Int, RecyclerView.Adapter<*>>()
        internal var positionAdapterMapper: PositionAdapterMapper? = null

        fun addAdapter(adapter: RecyclerView.Adapter<*>, vararg viewTypes: Int): Builder {
            for (viewType in viewTypes) {
                viewTypesAdapterMap[viewType] = adapter
            }
            return this
        }

        fun build(positionAdapterMapper: PositionAdapterMapper): RecyclerViewAssemblerAdapter {
            this.positionAdapterMapper = positionAdapterMapper
            return RecyclerViewAssemblerAdapter(this)
        }
    }


    interface PositionAdapterMapper {
        fun map(position: Int): RecyclerViewAssemblerElement
    }

}

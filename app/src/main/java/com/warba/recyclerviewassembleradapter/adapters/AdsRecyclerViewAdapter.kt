package com.warba.recyclerviewassembleradapter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appro.recyclerviewassembler.NotifyableAdapter
import com.appro.recyclerviewassembler.OnNotifyDatasetChangeListener
import com.appro.recyclerviewassembler.RecyclerViewAssemblerElement
import com.warba.recyclerviewassembleradapter.R
import com.warba.recyclerviewassembleradapter.models.Adv
import kotlinx.android.synthetic.main.row_ads.view.*

class AdsRecyclerViewAdapter: RecyclerView.Adapter<AdsRecyclerViewAdapter.ViewHolder>(),
     RecyclerViewAssemblerElement, NotifyableAdapter {

    private val data = ArrayList<Adv>()

    var notifyListener: OnNotifyDatasetChangeListener? = null

    override fun setOnNotifyDataSetChangeListener(onNotifyDataSetChangeListener: OnNotifyDatasetChangeListener) {
        this.notifyListener = onNotifyDataSetChangeListener
    }

    override fun getData(): List<*> {
        return data
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_ads
    }

    fun setData(advs: List<Adv>){
        data.clear()
        data.addAll(advs)
        notifyListener?.onNotifyDataSetChange()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind (adv: Adv){
            itemView.tvAdsTitle.text = adv.title
            itemView.tvAdsDesc.text = adv.description
        }
    }

}
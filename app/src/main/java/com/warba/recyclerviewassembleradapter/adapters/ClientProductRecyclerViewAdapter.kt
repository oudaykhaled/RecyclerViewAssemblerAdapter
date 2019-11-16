package com.warba.recyclerviewassembleradapter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appro.recyclerviewassembler.NotifyableAdapter
import com.appro.recyclerviewassembler.OnNotifyDatasetChangeListener
import com.appro.recyclerviewassembler.RecyclerViewAssemblerElement
import com.warba.recyclerviewassembleradapter.R
import com.warba.recyclerviewassembleradapter.models.Client
import com.warba.recyclerviewassembleradapter.models.Product
import kotlinx.android.synthetic.main.row_client.view.*
import kotlinx.android.synthetic.main.row_product.view.*

class ClientProductRecyclerViewAdapter(): RecyclerView.Adapter<ClientProductRecyclerViewAdapter.AbstractViewHolder>() ,
    NotifyableAdapter, RecyclerViewAssemblerElement {
    val data = ArrayList<Any>()

    var notifyListener: OnNotifyDatasetChangeListener? = null

    override fun setOnNotifyDataSetChangeListener(onNotifyDataSetChangeListener: OnNotifyDatasetChangeListener) {
        this.notifyListener = onNotifyDataSetChangeListener
    }

    override fun getData(): List<*> {
        return data
    }

    fun setData(arr: List<Any>){
        data.clear()
        data.addAll(arr)
        notifyListener?.onNotifyDataSetChange()
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int {
        return if (data[position] is Client) R.layout.row_client
        else R.layout.row_product
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return if (viewType == R.layout.row_client) ClientViewHolder(view)
        else ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    abstract class AbstractViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        abstract fun bind(obj: Any)
    }

    class ClientViewHolder(itemView: View): AbstractViewHolder(itemView) {
        override fun bind(obj: Any){
            var client = obj as Client
            itemView.tvPhoneNumber.text = client.name
            itemView.tvClientName.text = client.phoneNumber
        }
    }

    class ProductViewHolder(itemView: View): AbstractViewHolder(itemView) {
        override fun bind(obj: Any){
            var product = obj as Product
            itemView.tvProductName.text = product.productName
            itemView.tvProductDescription.text = product.productDescription
        }
    }

}
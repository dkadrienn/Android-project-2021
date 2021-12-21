package com.example.bazaar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.R
import com.example.bazaar.model.Order
import java.text.SimpleDateFormat

class OrderRecyclerViewAdapter(
    private var list: ArrayList<Order>,
    private val context: Context,
    private val listener: OnItemClickListener
//    private val longListener: OnItemLongClickListener
) :
    RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderRecycleViewViewHolder>() {

    // 1. user defined ViewHolder type - Embedded class!
    inner class OrderRecycleViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val imageView_seller: ImageView = itemView.findViewById(R.id.orderSellerProfile)
        val textView_seller: TextView = itemView.findViewById(R.id.orderSellerName)
        val textView_status: TextView = itemView.findViewById(R.id.orderStatus)
        val textView_name: TextView = itemView.findViewById(R.id.orderItemName)
        val textView_description: TextView = itemView.findViewById(R.id.orderDetail)
        val textView_date: TextView = itemView.findViewById(R.id.orderDate)
        val textView_amount: TextView = itemView.findViewById(R.id.orderItemAmount)
        val textView_price: TextView = itemView.findViewById(R.id.orderItemPrice)
        val imageView_item: ImageView = itemView.findViewById(R.id.orderItemImage)
        val imageView_decline: ImageView = itemView.findViewById(R.id.declineOrder)
        val imageView_accept: ImageView = itemView.findViewById(R.id.acceptOrder)
        val dropDownArrow: ImageView = itemView.findViewById(R.id.orderDetailArrow)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val current = list[bindingAdapterPosition]
            listener.onItemClick(current)
//            longListener.onItemLongClick(current)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(order: Order)
    }

//    interface OnItemLongClickListener {
//        fun onItemLongClick(order: Order)
//    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderRecycleViewViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.single_item_fare, parent, false)
        return OrderRecycleViewViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: OrderRecycleViewViewHolder, position: Int) {
        val currentItem = list[position]
        holder.imageView_seller.setImageResource(R.drawable.ic_avatar)
        holder.textView_seller.text = currentItem.username.replace("\"", "")
        holder.textView_date.text = (" " + SimpleDateFormat("yyyy.MM.dd").format(currentItem.creation_time))
        holder.textView_status.text = currentItem.status
        holder.textView_name.text = currentItem.title.replace("\"", "")
        holder.textView_description.text = currentItem.description.replace("\"", "")
        holder.textView_amount.text = " " + currentItem.units.replace("\"", "") + currentItem.amount_type.replace("\"", "")
        holder.textView_price.text = " " + currentItem.price_per_unit.replace("\"", "") + currentItem.price_type.replace("\"", "")
        holder.imageView_item.setImageResource(R.drawable.bg)
        holder.imageView_decline.setImageResource(R.drawable.ic_decline)
        holder.imageView_accept.setImageResource(R.drawable.ic_accept)

        if(currentItem.status.contains("Incoming", ignoreCase = true) || currentItem.status.contains("open", ignoreCase = true)){
            holder.imageView_decline.visibility = View.VISIBLE
            holder.imageView_accept.visibility = View.VISIBLE
        }

        if(currentItem.status.contains("Accepted", ignoreCase = true)){
            holder.imageView_decline.visibility = View.GONE
            holder.imageView_accept.visibility = View.VISIBLE
        }

        if(currentItem.status.contains("Declined", ignoreCase = true)){
            holder.imageView_decline.visibility = View.VISIBLE
            holder.imageView_accept.visibility = View.GONE
        }

        if(currentItem.status.contains("Delivering", ignoreCase = true) || currentItem.status.contains("Delivered", ignoreCase = true)){
            holder.imageView_decline.visibility = View.GONE
            holder.imageView_accept.visibility = View.GONE
        }

        //drop down for description
        holder.dropDownArrow.setOnClickListener {
            if (holder.textView_description.visibility == View.GONE){
                holder.dropDownArrow.setImageResource(R.drawable.ic_arrow_down)
                holder.textView_description.visibility = View.VISIBLE
            }
            else{
                holder.dropDownArrow.setImageResource(R.drawable.ic_arrow_up)
                holder.textView_description.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = list.size

    // Update the list
    fun setData(newlist: ArrayList<Order>) {
        list = newlist
    }
}
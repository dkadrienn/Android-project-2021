package com.example.bazaar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.R
import com.example.bazaar.model.Product

class RecycleViewAdapter(
    private var list: ArrayList<Product>,
    private val context: Context,
    private val listener: OnItemClickListener,
//    private val listenerLong: OnItemLongClickListener
) :
    RecyclerView.Adapter<RecycleViewAdapter.RecycleViewViewHolder>() {

    // 1. user defined ViewHolder type - Embedded class!
    inner class RecycleViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val textView_name: TextView = itemView.findViewById(R.id.itemName)
        val textView_price: TextView = itemView.findViewById(R.id.itemPrice)
        val textView_seller: TextView = itemView.findViewById(R.id.sellerName)
        val imageView_item: ImageView = itemView.findViewById(R.id.itemImage)
        val imageView_seller: ImageView = itemView.findViewById(R.id.sellerProfile)
        val imageView_state: ImageView = itemView.findViewById(R.id.itemState)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val current = list[bindingAdapterPosition]
            listener.onItemClick(current)
        }


    }

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(position: Int)
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        return RecycleViewViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: RecycleViewViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textView_name.text = currentItem.title.replace("\"", "")
        holder.textView_price.text = currentItem.price_per_unit.replace("\"", "")
        holder.textView_seller.text = currentItem.username.replace("\"", "")
        holder.imageView_item.setImageResource(R.drawable.bg)
        holder.imageView_seller.setImageResource(R.drawable.ic_avatar)

        if (currentItem.is_active){
            holder.imageView_state.setImageResource(R.drawable.ic_button_order_now)
        } else{
            holder.imageView_state.setImageResource(R.drawable.ic_inactive)
        }

//        val images = currentItem.images
//        if(images.isNotEmpty()) {
//            Log.d("xxx", "#num_images: ${images.size}")
//        }
//        Glide.with(this.context)
//            .load(R.drawable.ic_avatar)
//            .override(200, 200)
//            .into(holder.imageView_item);
    }

    override fun getItemCount(): Int = list.size

    // Update the list
    fun setData(newlist: ArrayList<Product>) {
        list = newlist
    }
}
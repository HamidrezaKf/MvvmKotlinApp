package com.hamidreza.newsapp.data.adapters

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.hamidreza.newsapp.R
import kotlinx.android.synthetic.main.category_recycler_itmes.view.*

class CategoryRecyclerAdapter(val list: List<String>) : RecyclerView.Adapter<CategoryRecyclerAdapter.MyViewHolder>()  {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    var row_index =-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_recycler_itmes,parent,false)
        return MyViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        holder.itemView.tv_category.text = current
        holder.itemView.setOnClickListener {
            row_index = position
            notifyDataSetChanged()
        }
        if(row_index==position){
            holder.itemView.background = holder.itemView.context.getDrawable(R.drawable.category_item_background)
            holder.itemView.tv_category.setTextColor(holder.itemView.context.getColor(R.color.white))
        }else if (position == 0 && row_index ==-1){
            holder.itemView.background = holder.itemView.context.getDrawable(R.drawable.category_item_background)
            holder.itemView.tv_category.setTextColor(holder.itemView.context.getColor(R.color.white))
        }
        else if (position == 0 && row_index != -1){
            holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.white))
            holder.itemView.tv_category.setTextColor(holder.itemView.context.getColor(R.color.category_onselected_text))

        }
        else
        {
            holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.white))
           // holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.itemView.tv_category.setTextColor(holder.itemView.context.getColor(R.color.category_onselected_text))

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    private var onItemClickListener: (() -> Unit)? = null

    fun setOnItemClickListener(listener:() -> Unit){
        onItemClickListener = listener
    }
}
package com.example.kmtest.ui

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kmtest.databinding.ItemUserBinding
import com.example.kmtest.service.response.DataItem
import com.example.kmtest.ui.second.SecondScreenActivity

class UserAdapter : PagingDataAdapter<DataItem, UserAdapter.ListViewHolder>(DIFF_ITEM_CALLBACK) {
    class ListViewHolder(private val itemUser: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUser.root) {
        fun bind(user: DataItem) {
            itemUser.apply {
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .dontAnimate()
                    .into(imgUser)
                fullName.text = "${user.firstName} ${user.lastName}"
                emailUser.text = user.email

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, SecondScreenActivity::class.java)
                    intent.putExtra(NAME, fullName.text.toString())
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemUser = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemUser)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    companion object {
        const val NAME = "NAME"
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.email == newItem.email &&
                        oldItem.avatar == newItem.avatar &&
                        oldItem.firstName == newItem.firstName &&
                        oldItem.lastName == newItem.lastName 
            }

        }
    }
}
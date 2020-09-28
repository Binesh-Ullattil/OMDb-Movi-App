package com.example.imdbmoviapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbmoviapp.BR
import com.example.imdbmoviapp.R
import com.example.imdbmoviapp.databinding.RowItemMovieListBinding
import com.example.imdbmoviapp.model.Search

class MovieListAdapter(
    private val _list: MutableList<Search>,
    private var listener: OnItemClickListener
) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val list = _list

    interface OnItemClickListener {
        fun onItemClicked(position: Int,item: Search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind:RowItemMovieListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_item_movie_list,
            parent,
            false
        )
        return ViewHolder(bind)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
        holder.itemView.setOnClickListener {
            listener.onItemClicked(position,list[position])
        }
    }

    class ViewHolder(private val _bind: RowItemMovieListBinding) : RecyclerView.ViewHolder(_bind.root) {
        public fun bindItems(
            item: Search
        ) {
            _bind.setVariable(BR.item, item)
            _bind.executePendingBindings()
        }
    }

    fun addItems(dataList: MutableList<Search>) {
        list.addAll(dataList)
        notifyDataSetChanged()
    }


    fun clearAllItems() {
        list.clear()
        notifyDataSetChanged()
    }
}




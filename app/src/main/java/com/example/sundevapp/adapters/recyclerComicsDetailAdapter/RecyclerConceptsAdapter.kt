package com.example.sundevapp.adapters.recyclerComicsDetailAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sundevapp.R
import com.example.sundevapp.util.comicDetailResponse.Concepts


class RecyclerConceptsAdapter:
    RecyclerView.Adapter<RecyclerConceptsAdapter.ViewHolder>() {
    //vars
    private var concepts: List<Concepts> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comic_detail, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return concepts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txvName.text = concepts[position].name
    }

    fun submitConceptsDetail(concepts: List<Concepts>) {
        val oldList = this.concepts
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            UserItemDiffCallBack(
                oldList,
                concepts
            )
        )

        this.concepts = concepts
        diffResult.dispatchUpdatesTo(this)
    }


    class UserItemDiffCallBack(
        private var oldConcepts: List<Concepts>,
        private var newConcepts: List<Concepts>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldConcepts[oldItemPosition].name == newConcepts[newItemPosition].name)
        }

        override fun getOldListSize(): Int {
            return oldConcepts.size
        }

        override fun getNewListSize(): Int {
            return newConcepts.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldConcepts[oldItemPosition] == newConcepts[newItemPosition]
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txvName: TextView = itemView.findViewById(R.id.txv_name)
    }

}
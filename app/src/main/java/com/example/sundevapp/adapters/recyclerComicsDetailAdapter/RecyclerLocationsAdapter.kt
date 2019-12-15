package com.example.sundevapp.adapters.recyclerComicsDetailAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sundevapp.R
import com.example.sundevapp.util.comicDetailResponse.Location


class RecyclerLocationsAdapter:
    RecyclerView.Adapter<RecyclerLocationsAdapter.ViewHolder>() {
    //vars
    private var locations: List<Location> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comic_detail, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txvName.text = locations[position].name

    }

    fun submitLocations(locations: List<Location>) {
        val oldList = this.locations
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            UserItemDiffCallBack(
                oldList,
                locations
            )
        )

        this.locations = locations
        diffResult.dispatchUpdatesTo(this)
    }


    class UserItemDiffCallBack(
        private var oldLocation: List<Location>,
        private var newLocation: List<Location>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldLocation[oldItemPosition].name == newLocation[newItemPosition].name)
        }

        override fun getOldListSize(): Int {
            return oldLocation.size
        }

        override fun getNewListSize(): Int {
            return newLocation.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldLocation[oldItemPosition] == newLocation[newItemPosition]
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txvName: TextView = itemView.findViewById(R.id.txv_name)
    }

}
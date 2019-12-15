package com.example.sundevapp.adapters.recyclerComicsDetailAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sundevapp.R
import com.example.sundevapp.util.comicDetailResponse.Team


class RecyclerTeamsAdapter:
    RecyclerView.Adapter<RecyclerTeamsAdapter.ViewHolder>() {
    //vars
    private var teams: List<Team> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comic_detail, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txvName.text = teams[position].name
    }

    fun submitTeamsDetail(teams: List<Team>) {
        val oldList = this.teams
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            UserItemDiffCallBack(
                oldList,
                teams
            )
        )

        this.teams = teams
        diffResult.dispatchUpdatesTo(this)
    }


    class UserItemDiffCallBack(
        private var oldTeam: List<Team>,
        private var newTeam: List<Team>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldTeam[oldItemPosition].name == newTeam[newItemPosition].name)
        }

        override fun getOldListSize(): Int {
            return oldTeam.size
        }

        override fun getNewListSize(): Int {
            return newTeam.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldTeam[oldItemPosition] == newTeam[newItemPosition]
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txvName: TextView = itemView.findViewById(R.id.txv_name)
    }

}
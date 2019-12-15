package com.example.sundevapp.adapters.recyclerComicsDetailAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sundevapp.R
import com.example.sundevapp.util.comicDetailResponse.Characters


class RecyclerCharactersAdapter:
    RecyclerView.Adapter<RecyclerCharactersAdapter.ViewHolder>() {
    //vars
    private var characters: List<Characters> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comic_detail, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txvName.text = characters[position].name
    }

    fun submitCharacters(characters: List<Characters>) {
        val oldList = this.characters
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            UserItemDiffCallBack(
                oldList,
                characters
            )
        )

        this.characters = characters
        diffResult.dispatchUpdatesTo(this)
    }


    class UserItemDiffCallBack(
        private var oldCharacters: List<Characters>,
        private var newCharacters: List<Characters>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldCharacters[oldItemPosition].name == newCharacters[newItemPosition].name)
        }

        override fun getOldListSize(): Int {
            return oldCharacters.size
        }

        override fun getNewListSize(): Int {
            return newCharacters.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCharacters[oldItemPosition] == newCharacters[newItemPosition]
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txvName: TextView = itemView.findViewById(R.id.txv_name)
    }

}
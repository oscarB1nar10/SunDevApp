package com.example.sundevapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sundevapp.R
import com.example.sundevapp.ui.comicBook.ComicBook
import com.example.sundevapp.util.DateUtil
import com.example.sundevapp.util.comicResponse.Result
import com.squareup.picasso.Picasso

class RecyclerComicsAdapter constructor(private val view: ComicBook) :
    RecyclerView.Adapter<RecyclerComicsAdapter.ViewHolder>() {
    //const
    private val TAG = "RecyclerComicsAdapter"
    //vars
    private var comicsDetail: List<Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comic_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comicsDetail.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txvComicTitle.text = comicsDetail[position].name
        holder.txvComicPublicationDate.text = DateUtil.getDateInCustomFormat(comicsDetail[position].date_last_updated)
        Picasso.get()
            .load(comicsDetail[position].image.small_url)
            .into(holder.imvComicImg)
        holder.imvComicImg.setOnClickListener {
            val bundle = bundleOf("comic_detail" to comicsDetail[position].api_detail_url)
            view.findNavController().navigate(R.id.action_comicBook_to_comicDetail, bundle)
        }
    }

    fun submitComicsDetail(comicsDetail: List<Result>) {
        val oldList = this.comicsDetail
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            UserItemDiffCallBack(
                oldList,
                comicsDetail
            )
        )

        this.comicsDetail = comicsDetail
        diffResult.dispatchUpdatesTo(this)
    }



    class UserItemDiffCallBack(
        private var oldComicsDetail: List<Result>,
        private var newComicsDetail: List<Result>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldComicsDetail[oldItemPosition].id == newComicsDetail[newItemPosition].id)
        }

        override fun getOldListSize(): Int {
            return oldComicsDetail.size
        }

        override fun getNewListSize(): Int {
            return newComicsDetail.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldComicsDetail[oldItemPosition] == newComicsDetail[newItemPosition]
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imvComicImg: ImageView = itemView.findViewById(R.id.imv_comic_img)
        val txvComicTitle: TextView = itemView.findViewById(R.id.txv_comic_title)
        val txvComicPublicationDate: TextView = itemView.findViewById(R.id.txv_comic_publication_date)
    }

}
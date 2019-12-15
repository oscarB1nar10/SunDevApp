package com.example.sundevapp.ui.comicBook


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sundevapp.R
import com.example.sundevapp.adapters.RecyclerComicsAdapter
import com.example.sundevapp.viewModelFactory.ViewModelProvFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_comic_book.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ComicBook : DaggerFragment() {
    //const
    private val TAG = "ComicBook"
    //vars
    private lateinit var comicBookViewModel: ComicBookViewModel
    private lateinit var pgMain: ProgressBar
    private lateinit var recyclerComicsAdapter: RecyclerComicsAdapter
    private lateinit var srlMain: SwipeRefreshLayout
    @Inject
    lateinit var viewModelProvFactory: ViewModelProvFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic_book, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        comicBookViewModel = ViewModelProvider(this,viewModelProvFactory).get(ComicBookViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewComponents()
        getComics()
    }

    private fun initViewComponents() {
        pgMain = activity!!.findViewById(R.id.pg_main)
        srlMain = activity!!.findViewById(R.id.srl_main_swipe)
        srlMain.setOnRefreshListener { getComics() }
        rv_comics.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerComicsAdapter = RecyclerComicsAdapter(this)
        rv_comics.adapter = recyclerComicsAdapter

    }

    private fun getComics() {
        pgMain.visibility = View.VISIBLE
        observeComics()
        observeErrors()
        comicBookViewModel.getComics()
    }

    private fun observeComics(){
        comicBookViewModel.comicResponse.removeObservers(viewLifecycleOwner)
        comicBookViewModel.comicResponse.observe(viewLifecycleOwner, Observer {
            pgMain.visibility = View.GONE
            srlMain.isRefreshing = false
            it?.let {
                Log.i(TAG, "comics: $it")
            }
            recyclerComicsAdapter.submitComicsDetail(it.results)
            recyclerComicsAdapter.notifyDataSetChanged()
        })
    }

    private fun observeErrors(){
        comicBookViewModel.handlerExceptions.removeObservers(viewLifecycleOwner)
        comicBookViewModel.handlerExceptions.observe(viewLifecycleOwner, Observer {
            pgMain.visibility = View.GONE
            srlMain.isRefreshing = false
            it?.let {
                Snackbar.make(this!!.view!!, it.exceptionName, Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }
        })
    }
}

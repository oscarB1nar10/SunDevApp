package com.example.sundevapp.ui.comicDetail


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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sundevapp.R
import com.example.sundevapp.adapters.recyclerComicsDetailAdapter.RecyclerCharactersAdapter
import com.example.sundevapp.adapters.recyclerComicsDetailAdapter.RecyclerConceptsAdapter
import com.example.sundevapp.adapters.recyclerComicsDetailAdapter.RecyclerLocationsAdapter
import com.example.sundevapp.adapters.recyclerComicsDetailAdapter.RecyclerTeamsAdapter
import com.example.sundevapp.viewModelFactory.ViewModelProvFactory
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_comic_detail.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ComicDetail : DaggerFragment() {

    //const
    private val TAG = "ComicBook"
    //vars
    private lateinit var comicDetailViewModel: ComicDetailViewModel
    private lateinit var pgMain: ProgressBar
    private lateinit var srlMain: SwipeRefreshLayout
    private var comicDetail:String = ""
    @Inject
    lateinit var viewModelProvFactory: ViewModelProvFactory
    @Inject
    lateinit var recyclerCharactersAdapter: RecyclerCharactersAdapter
    @Inject
    lateinit var recyclerTeamsAdapter: RecyclerTeamsAdapter
    @Inject
    lateinit var recyclerLocationsAdapter: RecyclerLocationsAdapter
    @Inject
    lateinit var recyclerConceptsAdapter: RecyclerConceptsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        comicDetail = arguments!!.getString("comic_detail").toString()
        return inflater.inflate(R.layout.fragment_comic_detail, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        comicDetailViewModel = ViewModelProvider(this,viewModelProvFactory).get(ComicDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewComponents()
        getComicDetail()
    }

    private fun initViewComponents() {
        pgMain = activity!!.findViewById(R.id.pg_main)
        srlMain = activity!!.findViewById(R.id.srl_main_swipe)
        srlMain.setOnRefreshListener { getComicDetail() }
        rv_comics_characters.adapter = recyclerCharactersAdapter
        rv_comics_teams.adapter = recyclerTeamsAdapter
        rv_comics_location.adapter = recyclerLocationsAdapter
        rv_comics_concepts.adapter = recyclerConceptsAdapter
    }

    private fun getComicDetail() {
        pgMain.visibility = View.VISIBLE
        observeComicDetail()
        observeErrors()
        comicDetailViewModel.getComicDetail(comicDetail)
    }

    private fun observeComicDetail(){
        comicDetailViewModel.comicDetailResponse.removeObservers(viewLifecycleOwner)
        comicDetailViewModel.comicDetailResponse.observe(viewLifecycleOwner, Observer {
            pgMain.visibility = View.GONE
            srlMain.isRefreshing = false
            it?.let {
                Log.i(TAG, "comics: $it")
                attachComicImage(it.results.image.medium_url)
                recyclerCharactersAdapter.submitCharacters(it.results.character_credits)
                recyclerCharactersAdapter.notifyDataSetChanged()
                recyclerTeamsAdapter.submitTeamsDetail(it.results.team_credits)
                recyclerTeamsAdapter.notifyDataSetChanged()
                recyclerLocationsAdapter.submitLocations(it.results.location_credits)
                recyclerLocationsAdapter.notifyDataSetChanged()
                recyclerConceptsAdapter.submitConceptsDetail(it.results.concept_credits)
                recyclerConceptsAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun observeErrors(){
        comicDetailViewModel.handlerExceptions.removeObservers(viewLifecycleOwner)
        comicDetailViewModel.handlerExceptions.observe(viewLifecycleOwner, Observer {
            pgMain.visibility = View.GONE
            srlMain.isRefreshing = false
            it?.let {
                Snackbar.make(this.view!!, it.exceptionName, Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }
        })
    }

    private fun attachComicImage(comicImage: String){
        Picasso.get()
            .load(comicImage)
            .into(imv_comic)
    }


}

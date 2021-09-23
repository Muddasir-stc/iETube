package `in`.muddasir.ietube

import `in`.muddasir.ietube.Modals.MovieModel
import `in`.muddasir.ietube.adapters.RecyclerItemClickListenr
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val movieList = ArrayList<MovieModel>()
    private val movieList1 = ArrayList<MovieModel>()
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var moviesAdapter1: MoviesAdapter

    private val movieList2 = ArrayList<MovieModel>()
    private lateinit var moviesAdapter3: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        moviesAdapter = MoviesAdapter(movieList)
        val layoutManager = LinearLayoutManager(applicationContext)
        //recyclerView.layoutManager = layoutManager
        recyclerView.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                true
            )
        )
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = moviesAdapter
        prepareMovieData()



        val recyclerView1: RecyclerView = findViewById(R.id.recyclerView2)
        moviesAdapter1 = MoviesAdapter(movieList1)
        val layoutManager1 = LinearLayoutManager(applicationContext)
        //recyclerView.layoutManager = layoutManager
        recyclerView1.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                true
            )
        )
        recyclerView1.itemAnimator = DefaultItemAnimator()
        recyclerView1.adapter = moviesAdapter1

        prepareMovieData1()

        val recyclerView3: RecyclerView = findViewById(R.id.recyclerView3)
        moviesAdapter3 = MoviesAdapter(movieList2)
        val layoutManager2 = LinearLayoutManager(applicationContext)
        //recyclerView.layoutManager = layoutManager
        recyclerView3.setLayoutManager(
                LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        true
                )
        )
        recyclerView3.itemAnimator = DefaultItemAnimator()
        recyclerView3.adapter = moviesAdapter3


        prepareMovieData1()
        prepareMovieData2()

        var search:ImageButton = findViewById(R.id.search)
           search.setOnClickListener(View.OnClickListener {

               startActivity(Intent(this@MainActivity, YouPageWatch::class.java))

           })



        recyclerView.addOnItemTouchListener(RecyclerItemClickListenr(this, recyclerView, object : RecyclerItemClickListenr.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                //do your work here..
                Log.e("MuddasirClicked","cliekd")
            }
            override fun onItemLongClick(view: View?, position: Int) {
                TODO("do nothing")
            }
        }))

    }

    private fun prepareMovieData1() {
        var movie = MovieModel("Mad Max: Fury Road", "Action & Adventure", "2015")
        movieList1.add(movie)
        movie = MovieModel("Inside Out", "Animation, Kids & Family", "2015")
        movieList1.add(movie)
        movie = MovieModel("Star Wars: Episode VII - The Force Awakens", "Action", "2015")

        moviesAdapter.notifyDataSetChanged()




    }


    private fun prepareMovieData2() {
        var movie = MovieModel("Mad Max: Fury Road", "Action & Adventure", "2015")
        movieList2.add(movie)
        movie = MovieModel("Inside Out", "Animation, Kids & Family", "2015")
        movieList2.add(movie)
        movie = MovieModel("Star Wars: Episode VII - The Force Awakens", "Action", "2015")

        moviesAdapter3.notifyDataSetChanged()
    }

    private fun prepareMovieData() {
        var movie = MovieModel("Mad Max: Fury Road", "Action & Adventure", "2015")
        movieList.add(movie)
        movie = MovieModel("Inside Out", "Animation, Kids & Family", "2015")
        movieList.add(movie)
        movie = MovieModel("Star Wars: Episode VII - The Force Awakens", "Action", "2015")
        movieList.add(movie)
        movie = MovieModel("Shaun the Sheep", "Animation", "2015")
        movieList.add(movie)
        movie = MovieModel("The Martian", "Science Fiction & Fantasy", "2015")
        movieList.add(movie)
        movie = MovieModel("Mission: Impossible Rogue Nation", "Action", "2015")
        movieList.add(movie)
        movie = MovieModel("Up", "Animation", "2009")
        movieList.add(movie)
        movie = MovieModel("Star Trek", "Science Fiction", "2009")
        movieList.add(movie)
        movie = MovieModel("The LEGO MovieModel", "Animation", "2014")
        movieList.add(movie)
        movie = MovieModel("Iron Man", "Action & Adventure", "2008")
        movieList.add(movie)
        movie = MovieModel("Aliens", "Science Fiction", "1986")
        movieList.add(movie)
        movie = MovieModel("Chicken Run", "Animation", "2000")
        movieList.add(movie)
        movie = MovieModel("Back to the Future", "Science Fiction", "1985")
        movieList.add(movie)
        movie = MovieModel("Raiders of the Lost Ark", "Action & Adventure", "1981")
        movieList.add(movie)
        movie = MovieModel("Goldfinger", "Action & Adventure", "1965")
        movieList.add(movie)
        movie = MovieModel("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014")
        movieList.add(movie)
        moviesAdapter.notifyDataSetChanged()
    }

    fun showview(view: View) {

        print("clicked by user asdf")

    }
}

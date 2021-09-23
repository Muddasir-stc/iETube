package `in`.muddasir.ietube

import `in`.muddasir.ietube.Modals.MovieModel
import `in`.muddasir.ietube.retrofitinterface.RetrofitClient
import `in`.muddasir.ietube.retrofitinterface.UserDetail
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class YouPageWatch : AppCompatActivity() {
    private val movieList = ArrayList<MovieModel>()
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit  var textsearchvalue:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_page_watch)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        moviesAdapter = MoviesAdapter(movieList)
        val layoutManager = LinearLayoutManager(applicationContext)

        val cross:AppCompatImageButton = findViewById(R.id.cross);
        cross.setOnClickListener(View.OnClickListener {

            finish();
        })
        val searchbtn:AppCompatImageButton = findViewById(R.id.searchbtn)
        searchbtn.setOnClickListener(View.OnClickListener {
            val url =
                    "https://youtube.googleapis.com/"
            movieList.clear()
            moviesAdapter.notifyDataSetChanged()
            getYouTubePlaylist(url, textsearchvalue)
        })
        //recyclerView.layoutManager = layoutManager
        recyclerView.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                true
            )
        )
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = moviesAdapter
        val searchView:SearchView = findViewById(R.id.searchview)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@YouPageWatch, query, Toast.LENGTH_SHORT).show()

                if (movieList.contains(query)) {
                    // moviesAdapter.getFilter().filter(query)
                } else {
                    Toast.makeText(this@YouPageWatch, "No Match found", Toast.LENGTH_LONG).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Toast.makeText(this@YouPageWatch, newText, Toast.LENGTH_SHORT).show()
                //    adapter.getFilter().filter(newText);

                Log.e("value", newText)
                val url =
                    "https://youtube.googleapis.com/" //youtube/v3/playlists?part=snippet&id="+pl+"&maxResults=5&key=AIzaSyAKmUf4rGRbtvhSDWxFRm8shJRp5ey28oM";

                Log.e("url92", url)
                textsearchvalue = newText

                return false
            }
        })


       // prepareMovieData()
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
    var map: Map<String, String>? = null
    var playlist: List<String>? = null
    fun getYouTubePlaylist(url: String?, pl: String?) {

            //https://youtube.googleapis.com/youtube/v3/playlists?part=snippet&id="+pl+"&maxResults=5&key=AIzaSyAKmUf4rGRbtvhSDWxFRm8shJRp5ey28oM";

            playlist = java.util.ArrayList<String>()


                RetrofitClient.instance.youTubeList(pl,23,
                        "snippet",  "AIzaSyAKmUf4rGRbtvhSDWxFRm8shJRp5ey28oM"
                )
                        ?.enqueue(object : Callback<ResponseBody?> {
                            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                            }

                            override fun onResponse(
                                    call: Call<ResponseBody?>,
                                    response: Response<ResponseBody?>
                            ) {
                               print(response.code())

                                Log.e("cleed","called"+response.code());
                                try {
                                    val json  = JSONObject(response.body().toString());
                                    Log.e("cleed","called"+json);
                                }
                                catch (ex:Exception)
                                {

                                }


                                if (response?.code() == 200) {

                                    try {
                                        movieList.clear()
                                        val jsonObject = JSONObject(response.body()?.string())
                                        Log.e("error168", jsonObject.toString())

                                        val jsonArray = jsonObject.getJSONArray("items");

                                        for (i in 0 until jsonArray.length()) {
                                            val jsonObjectItem = jsonArray.get(i) as JSONObject
                                            val jsonObjectYoutubeVideoIdJsonObject = jsonObjectItem.getJSONObject("id")
                                            val videoId = jsonObjectYoutubeVideoIdJsonObject.getString("videoId")
                                            Log.e("error173", videoId)

                                            val snippet = jsonObjectItem.getJSONObject("snippet")
                                            val title = snippet.getString("title")
                                            val description = snippet.getString("description")
                                            val thumbnails = snippet.getJSONObject("thumbnails")
                                            val default = thumbnails.getJSONObject("default")
                                            val url = default.getString("url")

                                            var movie  = MovieModel(title, description, videoId)
                                            movieList.add(movie)

                                        }

                                        moviesAdapter.notifyDataSetChanged()
                                    } catch (err: JSONException) {
                                        Log.e("error", err.toString())
                                    }




                                } else {
                                    Toast.makeText(
                                            applicationContext,
                                            "Please Enter Valid NRIC ",
                                            Toast.LENGTH_LONG
                                    ).show()


                                }

                            }

                        })
    }

  fun   showview(view: View)
    {
        startActivity(Intent(this@YouPageWatch, YouPlayActivity::class.java))
    }
}



package com.codepath.cs388_project4

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale
import com.codepath.cs388_project4.util.GenreConverter
import com.codepath.cs388_project4.util.LanguageConverter

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var genreTextView: TextView
    private lateinit var abstractTextView: TextView
    private lateinit var voteTextView: TextView
    private lateinit var releaseDateTextView: TextView
    private lateinit var languageTextView: TextView
    private lateinit var voteCountTextView: TextView
    private lateinit var popularityTextView: TextView
    private lateinit var adultTextView: TextView

    //
    private val genreConverter = GenreConverter()
    private val languageConverter = LanguageConverter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        genreTextView = findViewById(R.id.mediaGenre)
        abstractTextView = findViewById(R.id.mediaAbstract)
        voteTextView = findViewById(R.id.mediaVote)
        releaseDateTextView = findViewById(R.id.mediaRelease)
        languageTextView = findViewById(R.id.mediaLanguage)
        voteCountTextView = findViewById(R.id.mediaVoteCount)
        popularityTextView = findViewById(R.id.mediaPopularity)
        adultTextView = findViewById(R.id.mediaAdult)


        // TODO: Get the extra from the Intent
        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

        val date = inputFormat.parse(movie.releaseDate)
        val formattedDate = outputFormat.format(date)
        val genres = movie.genreIds.joinToString(", ") { id -> genreConverter.getGenreName(id) ?: "Unknown" }
        val languages = movie.originalLanguage.let { id -> languageConverter.getLanguageName(id) ?: "Unknown" }

        // TODO: Set the title, byline, and abstract information from the movie
        titleTextView.text = movie.title
        genreTextView.text = "Genre: $genres"
        abstractTextView.text = movie.overview
        voteTextView.text = "Rating: " + String.format("%.1f", movie.voteAverage)
        releaseDateTextView.text = "Release Date: $formattedDate"
        languageTextView.text = "Language: " + languages
        voteCountTextView.text = "Vote Count: ${movie.voteCount}"
        popularityTextView.text = "Popularity: ${movie.popularity}"
        adultTextView.text = "Adult: " + String.format("%b", movie.adult).toString().uppercase()

        // TODO: Load the media image
        Glide.with(this)
            .load(movie.mediaImageUrl)
            .into(mediaImageView)

    }
}
package com.dicoding.visitcampus.ui.university

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.database.UnivEntity
import com.dicoding.visitcampus.databinding.ActivityFilterUniversityBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.main.MainActivity
import com.google.android.material.appbar.MaterialToolbar

class FilterUniversityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterUniversityBinding
    private val viewModel by viewModels<UniversityViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterUniversityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val univType = intent.getStringExtra(EXTRA_UNIV_TYPE).toString()
        val scienceScope = intent.getStringExtra(EXTRA_SCIENCE_SCOPE).toString()
        val major = intent.getStringExtra(EXTRA_MAJOR).toString()
        val majorAcd = intent.getStringExtra(EXTRA_MAJOR_ACD).toString()

        val toolbar : MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val formattedText = getString(R.string.filter_univ_result, univType, scienceScope, major, majorAcd)

        binding.tvFilterUniv.text = formattedText


        val layoutManager = LinearLayoutManager(this)
        binding.rvUniversities.layoutManager = layoutManager
        viewModel.filterUniversities(univType, "Unggul", scienceScope, major, majorAcd).observe(this) {result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {

                    showListUniversity(result.data)
                    showLoading(false)
                }
                is Result.Error -> {
                    showAlert(result.error)
                    showLoading(false)
                }
                else -> {}
            }
        }
    }

    private fun showListUniversity(items: List<UnivEntity>) {
        val adapter = ListUniversityAdapter()
        adapter.submitListAlphabetically(items)
        binding.rvUniversities.adapter = adapter
    }

    private fun showLoading(isLoading:Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this).apply {
            setTitle("Failed!")
            setMessage("No universities found for the specified filters")
            setPositiveButton("Continue") { _, _ ->
                moveMain()
            }
            create()
            show()
        }
    }

    private fun moveMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_UNIV_TYPE = "UNIV_TYPE"
        const val EXTRA_SCIENCE_SCOPE = "SCIENCE_SCOPE"
        const val EXTRA_MAJOR = "MAJOR"
        const val EXTRA_MAJOR_ACD = "MAJOR_ACD"
    }
}
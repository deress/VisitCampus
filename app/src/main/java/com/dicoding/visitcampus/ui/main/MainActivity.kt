package com.dicoding.visitcampus.ui.main



import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.ActivityMainBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.login.LoginActivity
import com.dicoding.visitcampus.ui.sidemenu.SideMenuActivity
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

            }
        }

        val navView = binding.bottomNavigationView

        val navController = findNavController(R.id.container)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_university, R.id.navigation_chatbot, R.id.navigation_exam_practice
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView . setupWithNavController (navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu -> {
                val intent = Intent(this, SideMenuActivity::class.java)
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}



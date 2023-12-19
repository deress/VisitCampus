package com.dicoding.visitcampus.ui.sidemenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.ActivityMainBinding
import com.dicoding.visitcampus.databinding.ActivitySideMenuBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.main.MainViewModel

class SideMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySideMenuBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySideMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this){user ->
            binding.tvEmail.text = user.email
            binding.tvName.text = user.name
        }

        with(binding) {
            ivClose.setOnClickListener{
                finish()
            }

            setting.setOnClickListener{
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
            logout.setOnClickListener{
                viewModel.logout()
                finish()
            }
        }

    }

}
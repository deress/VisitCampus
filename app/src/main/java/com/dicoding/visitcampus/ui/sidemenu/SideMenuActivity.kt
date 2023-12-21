package com.dicoding.visitcampus.ui.sidemenu

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
            Log.e("SideMenuActivity", "user: ${user.name}")
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
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
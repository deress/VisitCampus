package com.dicoding.visitcampus.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.pref.UserModel
import com.dicoding.visitcampus.databinding.ActivityLoginBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.main.MainActivity
import com.dicoding.visitcampus.ui.main.MainViewModel
import com.dicoding.visitcampus.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        playAnimation()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.edLoginEmail
            val password = binding.edLoginPassword

            if (email.text.toString().isEmpty()) {
                email.error = getString(R.string.input_empty)
            } else if (password.text.toString().isEmpty()) {
                password.error = getString(R.string.input_empty)
            }else if (email.text.toString().isNotEmpty() && password.text.toString()
                    .isNotEmpty()) {
                postForm()
            }
        }

        binding.tvButtonRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun postForm() {
        val email = binding.edLoginEmail.text.toString()
        val password = binding.edLoginPassword.text.toString()

        viewModel.postLogin(email, password).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        showLoading(true)

                    }
                    is Result.Success -> {
                        viewModel.saveSession(UserModel(email, result.data.loginResult.token, result.data.loginResult.userId, result.data.loginResult.name))
                        moveMain()

                    }
                    is Result.Error -> {
                        showLoading(false)

                    }
                }
            }
        }

    }

    private fun showLoading(isLoading:Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val message = ObjectAnimator.ofFloat(binding.subTitleTextView, View.ALPHA, 1f).setDuration(100)
        val email = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val emailEdit = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val password = ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEdit = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val loginButton = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)



        AnimatorSet().apply {
            startDelay = 100
            playSequentially(title, message, email, emailEdit, password, passwordEdit, loginButton)
            start()
        }
    }

    private fun moveMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
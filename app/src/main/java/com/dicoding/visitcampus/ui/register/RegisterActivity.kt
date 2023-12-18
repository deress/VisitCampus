package com.dicoding.visitcampus.ui.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.databinding.ActivityRegisterBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.login.LoginActivity
import com.dicoding.visitcampus.ui.login.LoginViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        playAnimation()
    }


    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.edRegisterName
            val email = binding.edRegisterEmail
            val password = binding.edRegisterPassword

            if (name.text.toString().isEmpty()) {
                name.error = getString(R.string.input_empty)
            } else if (email.text.toString().isEmpty()) {
                email.error = getString(R.string.input_empty)
            } else if (password.text.toString().isEmpty()) {
                password.error = getString(R.string.input_empty)
            }else if (name.text.toString().isNotEmpty() && email.text.toString().isNotEmpty() && password.text.toString()
                    .isNotEmpty()) {
                postForm()
            }
        }
    }

    private fun postForm() {
        viewModel.postRegister(
            binding.edRegisterName.text.toString(),
            binding.edRegisterEmail.text.toString(),
            binding.edRegisterPassword.text.toString()
        ).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showAlert(result.data.message)
                        showLoading(false)
                    }
                    is Result.Error -> {
                        showAlert(result.error)
                        showLoading(false)
                    }

                }
            }
        }
    }

    private fun showLoading(isLoading:Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showAlert(message: String) {
        when (message) {
            "User created" -> {
                AlertDialog.Builder(this).apply {
                    setTitle("Registration Successful")
                    setMessage(message)
                    setPositiveButton("Continue") {_, _ ->
                        moveLogin()
                    }
                    create()
                    show()
                }
            }
            else -> {
                AlertDialog.Builder(this).apply {
                    setTitle("Registration Failed")
                    setMessage(message)
                    setPositiveButton("Continue") {_, _ ->
                    }
                    create()
                    show()
                }
            }
        }
    }



    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val subTitle = ObjectAnimator.ofFloat(binding.subTitleTextView, View.ALPHA, 1f).setDuration(100)
        val name = ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(100)
        val nameEdit = ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val email = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val emailEdit = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val password = ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEdit = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            startDelay = 100
            playSequentially(title, subTitle, name, nameEdit, email, emailEdit, password, passwordEdit, signup)
            start()
        }
    }

    private fun moveLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
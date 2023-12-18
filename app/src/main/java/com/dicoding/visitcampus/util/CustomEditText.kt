package com.dicoding.visitcampus.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.dicoding.visitcampus.R

class CustomEditText :  AppCompatEditText, View.OnTouchListener {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                when (inputType) {
                    EMAIL -> {
                        if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                            setError(context.getString(R.string.email_validation), null)
                        } else {
                            error = null
                        }
                    }
                    PASSWORD -> {
                        if (s.toString().length < 8) {
                            setError(context.getString(R.string.password_validation), null)
                        } else {
                            error = null
                        }
                    }
                }

            }
            override fun afterTextChanged(s: Editable) {
                when (inputType) {
                    EMAIL -> {
                        if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                            setError(context.getString(R.string.email_validation), null)
                        } else {
                            error = null
                        }
                    }
                    PASSWORD -> {
                        if (s.toString().length < 8) {
                            setError(context.getString(R.string.password_validation), null)
                        } else {
                            error = null
                        }
                    }
                }
            }
        })
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        return false
    }

    companion object {
        const val EMAIL = 0x00000021
        const val PASSWORD = 0x00000081
    }

}
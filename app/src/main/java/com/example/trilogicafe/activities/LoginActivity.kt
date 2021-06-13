package com.example.trilogicafe.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.trilogicafe.R
import com.example.trilogicafe.databinding.ActivityLoginBinding
import com.example.trilogicafe.databinding.ActivitySplashBinding
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.tasks.OnCanceledListener

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        binding.tvRegist.setOnClickListener { startActivity(
            Intent(
                this@LoginActivity,
                RegisterActivity::class.java
            )
        ) }
        binding.btnLgn.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.etEmail.text.toString().trim { it<=' ' })->{
                    Toast.makeText(
                        this@LoginActivity,
                        "please enter email!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(binding.etPassword.text.toString().trim { it<=' ' })->{
                    Toast.makeText(
                        this@LoginActivity,
                        "please enter password!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            else -> {
              val email =binding.etEmail.text.toString().trim { it<=' ' }
            val password = binding.etPassword.text.toString().trim { it<=' ' }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
                task->if (task.isSuccessful){
                startActivity(
                    Intent(
                        this@LoginActivity,
                        MainActivity::class.java
                    )
                )
            }else{
                Toast.makeText(
                    this@LoginActivity,
                    "Email atau Password Salah!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            }
        }
            }
            }

    }
}
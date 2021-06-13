package com.example.trilogicafe.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trilogicafe.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backtologin.setOnClickListener {
            Intent(
            this@RegisterActivity,
            LoginActivity::class.java
        ) }
        binding.btnLgn.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.etEmail.text.toString().trim { it<=' ' })->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "please enter email!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(binding.etPassword.text.toString().trim { it<=' ' })->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "please enter password!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(binding.etName.text.toString().trim { it<=' ' })->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "please enter name!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(binding.etNumber.text.toString().trim { it<=' ' })->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "please enter phone number!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(binding.etAddress.text.toString().trim { it<=' ' })->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "please enter address!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val email =binding.etEmail.text.toString().trim { it<=' ' }
                    val password = binding.etPassword.text.toString().trim { it<=' ' }
                    val name = binding.etName.text.toString().trim { it<=' ' }
                    val address = binding.etAddress.text.toString().trim { it<=' ' }
                    val number = binding.etNumber.text.toString().trim { it<=' ' }
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                            task->if (task.isSuccessful){
                        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
                        val user: MutableMap<String, Any> = HashMap()
                                user["name"] = name
                                user["number"] = number
                                user["address"] = address
                        db.collection("users")
                            .add(user)
                            .addOnSuccessListener { documentReference ->
                                startActivity(
                                    Intent(
                                        this@RegisterActivity,
                                        LoginActivity::class.java
                                    )
                                )
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                    }
                    }
                }
            }
        }
    }


}
package com.example.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instagramclone.Models.User
import com.example.instagramclone.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class Login_Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener {
            if (binding.email.editText?.text.toString().equals("") ||
                binding.pass.editText?.text.toString().equals("")
            ) {
                Toast.makeText(
                    this@Login_Activity,
                    "please fill all the details",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var user = User(
                    binding.email.editText?.text.toString(),
                    binding.pass.editText?.text.toString()
                )

                Firebase.auth.signInWithEmailAndPassword(user.email!!, user.password!!)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this@Login_Activity,HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@Login_Activity,
                                it.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}
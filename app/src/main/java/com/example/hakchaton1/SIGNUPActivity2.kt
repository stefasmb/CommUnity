package com.example.hakchaton1

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hakchaton1.databinding.ActivityLoginBinding
import com.example.hakchaton1.databinding.ActivitySignupactivity2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SIGNUPActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySignupactivity2Binding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding = ActivitySignupactivity2Binding(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        auth = Firebase.auth

        binding.continueBtn.setOnClickListener {
            auth.createUserWithEmailAndPassword(binding.email.getText().toString().trim(), binding.password.getText().toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        val intent= Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fun onStart() {
            super.onStart()
            // Check if user is signed in (non-null) and update UI accordingly.
            val currentUser = auth.currentUser
            if (currentUser != null) {
               val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }

        binding.move.setOnClickListener{
            val intent= Intent(this,LOGIN::class.java)
            startActivity(intent)
        }

    }
}
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        binding.btnRegister.setOnClickListener {
            registerUser()
        }


        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun registerUser() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        when {
            name.isEmpty() -> showError("Введіть ім'я")
            email.isEmpty() -> showError("Введіть email")
            password.isEmpty() -> showError("Введіть пароль")
            password != confirmPassword -> showError("Паролі не збігаються")
            password.length < 6 -> showError("Пароль має бути від 6 символів")
            else -> {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUserToFirestore(name, email)
                        } else {
                            showError("Помилка реєстрації: ${task.exception?.message}")
                        }
                    }
            }
        }
    }

    private fun saveUserToFirestore(name: String, email: String) {
        val userId = auth.currentUser?.uid ?: return

        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "userId" to userId
        )

        db.collection("users").document(userId)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Реєстрація успішна!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                showError("Помилка збереження даних: ${e.message}")
            }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
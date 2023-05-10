package com.example.newproject

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newproject.model.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)


        val btnbckLogin: Button = findViewById(R.id.rgisetrbtn)
        btnbckLogin.setOnClickListener {
            val i = Intent(this@RegisterPage, LoginPage::class.java)
            startActivity(i)
        }
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser() {
        val usernameEditText = findViewById<EditText>(R.id.loginusername)
        val emailEditText = findViewById<EditText>(R.id.loginEmail)
        val passwordEditText = findViewById<EditText>(R.id.loginPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPassword)
        val name = usernameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        val registerModelUser = Register(name, email, password, confirmPassword)

        if (email.isBlank()) {
            Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show()
            return
        }
        if (name.isBlank()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.length < 8) {
            Toast.makeText(this, "Minimum password should be 8 character", Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (password != confirmPassword) {
            Toast.makeText(this, "password should be same", Toast.LENGTH_SHORT).show()
            return
        }
        var progressDialog = ProgressDialog(this@RegisterPage)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        RetroChatBotInstance.myInterface!!.registeruser(registerModelUser)
            .enqueue(object : Callback<Register?> {
                override fun onResponse(call: Call<Register?>, response: Response<Register?>) {
                    if (response.isSuccessful) {
                        val a = response.body()
                        println("$a")
                        val reg = Intent(this@RegisterPage, LoginPage::class.java)
                        startActivity(reg)
                        progressDialog.dismiss()
                    } else {
                        Toast.makeText(this@RegisterPage, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Register?>, t: Throwable) {
                    Toast.makeText(applicationContext, "something went wrong", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.show()
                    progressDialog.dismiss()
                }
            })


    }
}






package com.example.newproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.newproject.model.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)





        val   btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
           val i =Intent(this@LoginPage,Dashboard::class.java)
            startActivity(i)
//            loginuser()
        }
        val btnregistpage:Button = findViewById(R.id.rgisetruserbtn)
        btnregistpage.setOnClickListener {
            val i = Intent(this@LoginPage,RegisterPage::class.java)
            startActivity(i)
        }
    }

//    private fun loginuser() {
//        val emailEditText = findViewById<EditText>(R.id.loginEmail)
//        val loginEditText = findViewById<EditText>(R.id.loginPassword)
//        val email = emailEditText.text.toString()
//        val password = loginEditText.text.toString()
//        val loginmodeluser = Login(email,password)
//
//        fun isPasswordValid(password: String): Boolean {
//            val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"
//            return password.matches(passwordRegex.toRegex())
//        }
//        if(email.isBlank()){
//            Toast.makeText(this, "email should not be blank",Toast.LENGTH_SHORT).show()
//            return
//        }
//            if (isPasswordValid(password)) {
//               Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
//            } else {
//                // Password is invalid, show error message
//               Toast.makeText(this, "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and be at least 8 characters long.",Toast.LENGTH_SHORT).show()
//                return
//            }
//
//
////        val progressDialog = ProgressDialog(this@LoginPage)
////        progressDialog.setTitle("Loading")
////        progressDialog.setMessage("Please wait")
////        progressDialog.show()
//
//      RetroChatBotInstance.myInterface!!.loginuser(loginmodeluser).enqueue(object : Callback<Login?> {
//          override fun onResponse(call: Call<Login?>, response: Response<Login?>) {
//
//              if(response.isSuccessful){
//                  val a = response.body()
//                  println("$a")
//
//                  val i = Intent(this@LoginPage,Dashboard::class.java)
//                  startActivity(i)
////                  progressDialog.dismiss()
//              }else{
//
//                  Toast.makeText(applicationContext,"failure",Toast.LENGTH_SHORT).show()
//              }
//          }
//
//          override fun onFailure(call: Call<Login?>, t: Throwable) {
//            Toast.makeText(applicationContext,"unsuccesfull",Toast.LENGTH_SHORT).show()
////              progressDialog.show()
////              progressDialog.dismiss()
//
//          }
//      })
//
//
//
//    }
}
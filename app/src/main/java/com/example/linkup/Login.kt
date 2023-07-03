package com.example.linkup


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var login: Button
    private lateinit var signup: TextView

   private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        mAuth =FirebaseAuth.getInstance()

        email=findViewById(R.id.email)
        pass=findViewById(R.id.pass)
        login=findViewById(R.id.button)
        signup=findViewById(R.id.login_sign)


        signup.setOnClickListener{
           val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            val email=email.text.toString()
            val password=pass.text.toString()
            login(email,password)

        }

    }
    private fun login(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent=Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                        val intent=Intent(this@Login,SignUp::class.java)
                    startActivity(intent)
                    finish()
//                   Toast.makeText(this@Login,"User does not exist",Toast.LENGTH_SHORT).show()
                }
            }
    }

}

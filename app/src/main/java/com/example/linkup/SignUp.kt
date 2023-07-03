 package com.example.linkup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


 class  SignUp : AppCompatActivity() {
     private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var login: TextView
    private lateinit var signup: Button
     private lateinit var mAuth: FirebaseAuth
     private lateinit var mDbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        email=findViewById(R.id.sign_email)
        name =findViewById(R.id.name)
        pass=findViewById(R.id.sign_pass)
        login=findViewById(R.id.logotxt)

        mAuth = Firebase.auth

        signup=findViewById(R.id.sign_button)

        login.setOnClickListener{
            val intent= Intent(this, Login::class.java)
            startActivity(intent)
        }
        signup.setOnClickListener {
            val name=name.text.toString()
             val email=email.text.toString()
            val password=pass.text.toString()
            signup(name,email, password)
        }
    }

     private fun signup(name:String, email: String,password: String)
     {
         mAuth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener(this) {
                 if (it.isSuccessful) {
                     addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                     val intent=Intent(this@SignUp,MainActivity::class.java)
                     finish()
                     startActivity(intent)

                 } else {
                     // If sign in fails, display a message to the user.
                            Toast.makeText(this@SignUp,"Authentication failed",Toast.LENGTH_SHORT).show()
                 }
             }
     }
     private fun addUserToDatabase(name:String,email:String, uid:String){
            mDbRef=FirebaseDatabase.getInstance().reference
         mDbRef.child("user").child(uid).setValue(User(name,email,uid))
     }

 }
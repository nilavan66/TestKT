package com.example.testkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    lateinit var myJio : MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val num1 = findViewById<TextInputEditText>(R.id.num1)
        val num2 = findViewById<TextInputEditText>(R.id.num2)
        val result = findViewById<MaterialTextView>(R.id.result)
        val button = findViewById<MaterialButton>(R.id.button)
        val api = findViewById<MaterialButton>(R.id.api)



        myJio = findViewById(R.id.MyJio)


        button.setOnClickListener {

            val n1 = num1.text.toString().toDoubleOrNull()
            val n2 = num2.text.toString().toDoubleOrNull()
            val sum = n1?.plus(n2!!)
            result.text = "Result: ${sum ?: "Invalid Input"}"
            Toast.makeText(applicationContext, "you have clicked", Toast.LENGTH_SHORT).show()

        }

        api.setOnClickListener{
            startActivity(Intent(this, Api::class.java))
        }

        myJio.setOnClickListener{
            startActivity(Intent(this,MyJio::class.java))
        }




    }
}
package com.example.prototipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prototipe.databinding.ActivityLoginBinding
import com.example.prototipe.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets }

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val flag = 1

        var personal_id = findViewById<EditText>(R.id.inres_id)
        var name = findViewById<EditText>(R.id.inres_nombre)
        var lastname = findViewById<EditText>(R.id.inres_apellido)
        var adress = findViewById<EditText>(R.id.adress)
        //date
        var day = findViewById<EditText>(R.id.inres_day)
        var month = findViewById<EditText>(R.id.inres_month)
        var year = findViewById<EditText>(R.id.inres_year)

        var phone = findViewById<EditText>(R.id.inres_phonenum)
        var email = findViewById<EditText>(R.id.inres_email)
        var password = findViewById<EditText>(R.id.inres_password)
        var confirm_password = findViewById<EditText>(R.id.inres_confirmpass)

        val data_register = listOf(personal_id,name,lastname,adress,day,month,year,phone,email,password,confirm_password)

        fun showMsg(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        binding.btnresCreate.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            val msgError = VerifyData.validateRegister(data_register)
            if(!msgError.isEmpty()){
                for(msg in msgError){
                    showMsg(this,msg)
                }
            }else{
                startActivity(intent)
            }

        }

    }
}
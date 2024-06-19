package com.example.bmicalc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val weightText=findViewById<EditText>(R.id.etWeight)
        val heightText=findViewById<EditText>(R.id.etHeight)
        val calcButton=findViewById<Button>(R.id.btn)


        calcButton.setOnClickListener(){
            val weight=weightText.text.toString()
            val height=heightText.text.toString()


                val bmi=weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                val bmi2Digits=String.format("%.2f",bmi).toFloat()
                displayResult(bmi2Digits)



        }
    }


    private fun displayResult(bmi:Float){
        val result=findViewById<TextView>(R.id.tvResult)
        val info=findViewById<TextView>(R.id.tvInfo)

        result.text=bmi.toString()


        var resultText=""
        var color=0

        when{

            bmi < 18.50 ->{
                resultText="Underweight"
                color=R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resultText="Healthy"
                color=R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText="Overweight"
                color=R.color.over_weight
            }
            bmi > 29.99->{
                resultText="Obese"
                color=R.color.obese
            }
        }
        info.setTextColor(ContextCompat.getColor(this,color))
        info.text=resultText

        val resetBtn=findViewById<Button>(R.id.resetBtn)
        resetBtn.setOnClickListener(){

            result.text=""  //color is given
            info.text=""


        }

    }
}
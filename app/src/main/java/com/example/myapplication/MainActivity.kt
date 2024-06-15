package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weighttext = findViewById<EditText>(R.id.etweight)
        val heighttext = findViewById<EditText>(R.id.etheight)
        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            val weight = weighttext.text.toString()
            val height = heighttext.text.toString()
            if(valid(weight,height)) {
                val bmi = (weight.toFloat()) / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displaytheresult(bmi2digits)
            }
        }

    }
    private fun valid(weigh:String?,height:String?):Boolean{
        return when{
            weigh.isNullOrEmpty()->{
                Toast.makeText(this@MainActivity, "please! Enter the Weight", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this@MainActivity, "please! Enter the Height", Toast.LENGTH_SHORT).show()
                return false
            }
            else->{
                return true
            }
        }
    }
    private fun displaytheresult(bmi:Float){
        val resultnum = findViewById<TextView>(R.id.resultnum)
        val resultstat = findViewById<TextView>(R.id.statement)
        val info = findViewById<TextView>(R.id.info)
        resultnum.text = bmi.toString()
        info.text = "(Normal range is 18.5 - 24.9)"
        var resultvalue = ""
        var colorval = 0
        when{
            bmi<18.50 ->{
                resultvalue = "UnderWeight"
                colorval = R.color.Under_weight
            }
            bmi in 18.50..24.9 ->{
                resultvalue = "Healthy"
                colorval = R.color.normal
            }
            bmi in 25.00..29.99 ->{
                resultvalue= "Overweight"
                colorval = R.color.over_weight
            }
            bmi>29.99 ->{
                resultvalue= "Obese"
                colorval = R.color.obese
            }
        }
        resultstat.setTextColor(ContextCompat.getColor(this,colorval))
        resultstat.text = resultvalue

    }
}
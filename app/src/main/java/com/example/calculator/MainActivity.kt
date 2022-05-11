package com.example.calculator

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class
MainActivity : AppCompatActivity() {
    var lastNumerical= false
    var lastDot= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
     tvInput.append((view as Button).text)
        lastNumerical=true
    }

    fun onClear(view: View) {
        tvInput.text=""
        lastNumerical= false
        lastDot= false
    }

    fun onDecimal(view: View) {
        if (lastNumerical && !lastDot) {
            tvInput.append(".")
            lastNumerical = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
            if (lastNumerical && !isOperatorAdded (tvInput.text.toString())){
                tvInput.append((view as Button).text)
                lastNumerical=false
                lastDot=false
         }
        }

    private fun isOperatorAdded(value: String):Boolean{
            return if (value.startsWith("-")){
                false
            }else{
                value.contains("/")|| value.contains("*")|| value.contains("-")|| value.contains("+")|| value.contains("%")
            }
        }

    fun onEqual(view: View) {
        if (lastNumerical){
            var tvValue = tvInput.text.toString()
            try {
                if (tvValue.contains("-")){
                    val splitValue= tvValue.split("-")

                    var oneValue = splitValue[0]
                    var twoValue = splitValue[1]

                    tvInput.text= removeZero((oneValue.toDouble()-twoValue.toDouble()).toString())
                }
   else if (tvValue.contains("+")){
                    val splitValue= tvValue.split("+")

                    var oneValue = splitValue[0]
                    var twoValue = splitValue[1]

                    tvInput.text= removeZero((oneValue.toDouble()+twoValue.toDouble()).toString())                }
  else if (tvValue.contains("*")){
                    val splitValue= tvValue.split("*")

                    var oneValue = splitValue[0]
                    var twoValue = splitValue[1]

                    tvInput.text= removeZero((oneValue.toDouble()*twoValue.toDouble()).toString())                }
  else if (tvValue.contains("/")){
                    val splitValue= tvValue.split("/")

                    var oneValue = splitValue[0]
                    var twoValue = splitValue[1]

                    tvInput.text= removeZero((oneValue.toDouble()/twoValue.toDouble()).toString())                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }

     private fun removeZero(result:String):String{
         var value= result
         if (result.contains(".0")){
             value= result.substring(0,result.length-2)
         }
         return value

     }


}
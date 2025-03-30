package com.example.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput : TextView? = null
    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvText)

    }

    fun onDecimalPoint (view: View)
    {
        tvInput?.append(".")
        lastDot = true
        lastNumeric = false

    }

    fun onClick(view: View)
    {
        tvInput?.append((view as Button).text)

        lastDot = false
        lastNumeric = true

    }

    fun onClr (view: View){
        tvInput?.text = ""
    }

    fun onEquals (view: View) {
        if (lastNumeric)
        {
            var tvValue = tvInput?.text.toString()

            var prefix = ""

            try {

                if (tvValue.startsWith("-"))
                {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if ( tvValue.contains("-"))
                {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot( (one.toDouble() - two.toDouble()).toString() )


                }
                else if ( tvValue.contains("+"))
                {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot( (one.toDouble() + two.toDouble()).toString() )


                }
                else if ( tvValue.contains("*"))
                {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot( (one.toDouble() * two.toDouble()).toString() )


                }
                else if ( tvValue.contains("/"))
                {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot( (one.toDouble() / two.toDouble()).toString() )


                }


            }
            catch (e: ArithmeticException)
            {
                e.printStackTrace()
            }

        }
    }

    fun onOperator (view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString()))
            {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded (value : String) : Boolean {
        return if ( value.startsWith(".")){
            false
        }
        else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

    private fun removeZeroAfterDot ( result : String ) : String {
        var value = result
        if (result.contains(".0"))
        value = result.substring(0,result.length - 2)

        return value
    }


}



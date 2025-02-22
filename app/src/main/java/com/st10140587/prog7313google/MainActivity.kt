package com.st10140587.prog7313google

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    private lateinit var etValOne: EditText
    private lateinit var etValTwo: EditText
    private lateinit var etResult: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        etValOne = findViewById(R.id.etValOne)
        etValTwo = findViewById(R.id.etValTwo)
        etResult = findViewById(R.id.etResult)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)

        // Set onClick listeners for buttons
        btnAdd.setOnClickListener { calculate(Operation.ADD) }
        btnSubtract.setOnClickListener { calculate(Operation.SUBTRACT) }
        btnMultiply.setOnClickListener { calculate(Operation.MULTIPLY) }
        btnDivide.setOnClickListener { calculate(Operation.DIVIDE) }
    }

    private fun inputIsNotEmpty(): Boolean {
        var valid = true
        if (etValOne.text.toString().trim().isEmpty()) {
            etValOne.error = "Required"
            etValOne.requestFocus()
            valid = false
        }
        if (etValTwo.text.toString().trim().isEmpty()) {
            etValTwo.error = "Required"
            etValTwo.requestFocus()
            valid = false
        }
        return valid
    }

    private fun calculate(operation: Operation) {
        if (!inputIsNotEmpty()) return

        val num1 = etValOne.text.toString().trim().toBigDecimal()
        val num2 = etValTwo.text.toString().trim().toBigDecimal()
        val result: BigDecimal = when (operation) {
            Operation.ADD -> num1.add(num2)
            Operation.SUBTRACT -> num1.subtract(num2)
            Operation.MULTIPLY -> num1.multiply(num2)
            Operation.DIVIDE -> {
                if (num2 == BigDecimal.ZERO) {
                    etValTwo.error = "Cannot divide by zero"
                    return
                } else {
                    num1.divide(num2, 2, RoundingMode.HALF_UP)
                }
            }
        }
        etResult.setText(result.toString())
    }

    private enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}

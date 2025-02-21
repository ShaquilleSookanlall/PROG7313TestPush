package com.st10140587.prog7313google

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    private lateinit var btnOne: Button
    private lateinit var etValOne: EditText
    private lateinit var etValTwo: EditText
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

            btn.setOnClickListener {
                add()
            }

            btnMinus.setOnClickListener {
                subtract()
            }

            multiply.setOnClickListener {
                multiply()
            }

            divide.setOnClickListener {
                divide()
            }
        }

        btnOne = findViewById(R.id.btnAdd)
        etValOne = findViewById(R.id.etValOne)
        etValTwo = findViewById(R.id.etValTwo)
        tvResult = findViewById(R.id.etResult)

        fun inputIsNotEmpty(): Boolean {
            var b = true;
            if (etValOne.text.toString().trim().isEmpty()) {
                etValOne.error = "Required"
                etValOne.requestFocus()
                b = false;
            }
            if (etValTwo.text.toString().trim().isEmpty()) {
                etValTwo.error = "Required"
                etValTwo.requestFocus()
                b = false;
            }
            return b;
        }

        fun add() {
            if (inputIsNotEmpty()) {
                var inputdata1 = etValOne.text.toString().trim().toBigDecimal()
                var inputdata2 = etValTwo.text.toString().trim().toBigDecimal()
                tvResult.text = inputdata1.add(inputdata2).toString()
            }
        }

        fun subtract() {
            if (inputIsNotEmpty()) {
                var inputdata1 = etValOne.text.toString().trim().toBigDecimal()
                var inputdata2 = etValTwo.text.toString().trim().toBigDecimal()
                tvResult.text = inputdata1.subtract(inputdata2).toString();
            }
        }

        fun multiply() {
            if (inputIsNotEmpty()) {
                var inputdata1 = etValOne.text.toString().trim().toBigDecimal()
                var inputdata2 = etValTwo.text.toString().trim().toBigDecimal()
                tvResult.text = inputdata1.multiply(inputdata2).toString()
            }
        }


        fun divide() {
            if (inputIsNotEmpty()) {
                var inputdata1 = etValOne.text.toString().trim().toBigDecimal()
                var inputdata2 = etValTwo.text.toString().trim().toBigDecimal()

                if (inputdata2.compareTo(BigDecimal.ZERO) == 0) {
                    etValTwo.error = "invalid input"
                } else {
                    tvResult.text =
                        inputdata1.divide(inputdata2, 2, RoundingMode.HALF_UP).toString();
                }
            }
        }
    }
}


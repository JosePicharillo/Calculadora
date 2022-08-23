package br.edu.ifsp.calculadora

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var expression: TextView
    private lateinit var result: TextView
    private lateinit var clear: TextView
    private lateinit var division: TextView
    private lateinit var multiplication: TextView
    private lateinit var subtraction: TextView
    private lateinit var addition: TextView
    private lateinit var equal: TextView
    private lateinit var point: TextView
    private lateinit var backspace: ImageView
    private lateinit var sqrt: TextView
    private lateinit var percent: TextView
    private lateinit var exp: TextView
    private lateinit var zero: TextView
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        componentsView()
        actionButtons()
    }

    private fun componentsView() {
        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result)
        clear = findViewById(R.id.clear)
        division = findViewById(R.id.division)
        multiplication = findViewById(R.id.multiplication)
        subtraction = findViewById(R.id.subtraction)
        addition = findViewById(R.id.addition)
        equal = findViewById(R.id.equal)
        point = findViewById(R.id.point)
        backspace = findViewById(R.id.backspace)
        sqrt = findViewById(R.id.sqrt)
        percent = findViewById(R.id.percentage)
        exp = findViewById(R.id.exp)
        zero = findViewById(R.id.zero)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
    }

    private fun addExpression(string: String, clear: Boolean) {
        if (result.text.isNotEmpty()) {
            expression.text = ""
        }

        if (clear) {
            result.text = ""
            expression.append(string)
        } else {
            expression.append(result.text)
            expression.append(string)
            result.text = ""
        }
    }

    private fun actionButtons() {
        //Numbers
        zero.setOnClickListener { addExpression("0", true) }
        one.setOnClickListener { addExpression("1", true) }
        two.setOnClickListener { addExpression("2", true) }
        three.setOnClickListener { addExpression("3", true) }
        four.setOnClickListener { addExpression("4", true) }
        five.setOnClickListener { addExpression("5", true) }
        six.setOnClickListener { addExpression("6", true) }
        seven.setOnClickListener { addExpression("7", true) }
        eight.setOnClickListener { addExpression("8", true) }
        nine.setOnClickListener { addExpression("9", true) }
        point.setOnClickListener { addExpression(".", true) }

        //Operations
        addition.setOnClickListener { addExpression("+", false) }
        subtraction.setOnClickListener { addExpression("-", false) }
        division.setOnClickListener { addExpression("/", false) }
        multiplication.setOnClickListener { addExpression("*", false) }

        //Other
        clear.setOnClickListener {
            expression.text = ""
            result.text = ""
        }
        backspace.setOnClickListener {
            val string = expression.text.toString()
            if (string.isNotBlank()) {
                expression.text = string.substring(0, string.length - 1)
            }
            result.text = ""
        }
        equal.setOnClickListener {
            try {
                val expression = ExpressionBuilder(expression.text.toString()).build()
                val res = expression.evaluate()
                if (res == expressionComplete().toDouble()) {
                    result.text = expressionComplete().toString()
                } else {
                    result.text = res.toString()
                }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        }


        /**
         * Operações Avançadas
         */
        sqrt.setOnClickListener {
            val resultRaiz = sqrt(expressionComplete().toDouble())
            result.text = resultRaiz.toString()
        }
        percent.setOnClickListener {
            val resultPorcent = expressionComplete() / 100
            result.text = resultPorcent.toString()
        }
        exp.setOnClickListener { addExpression("^", false) }
    }

    /**
     * Retorna a expressão completa
     */
    private fun expressionComplete(): Long {
        val expression = ExpressionBuilder(expression.text.toString()).build()
        val res = expression.evaluate()
        return res.toLong()
    }

}
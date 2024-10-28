package br.edu.ifsp.dmo1.conversormonetario

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), OnClickListener{

    private val dolar = 5.50
    //Como a parte dos atributos geralmente são criadas antes de chamar o método onCreate, como ele ainda não existe
    // e é por isso que você não consegue recuperar as views que estão no layout.xml, utilizando o lateinit, você consegue salvar essas
    //referencias de view após a chamada do onCreate, que vai pegar no set Content View, você tem acesso a essas views
    private lateinit var vEditText: EditText
    private lateinit var vText: TextView
    private  lateinit var vButtonDolar: Button
    private  lateinit var vButtonReal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Depois dessa linha que você consegue acesso dessas views

        vEditText = findViewById(R.id.edit_text)
        vButtonDolar = findViewById(R.id.converterDolar_button)
        vButtonReal = findViewById(R.id.converterReal_button)
        vText = findViewById(R.id.mostra_resultado)

        vButtonDolar.setOnClickListener(this)
        vButtonReal.setOnClickListener(this)
    }

    override fun onClick(viewButton: View?) {
        //Se o objeto View que eu recebi é do tipo Button]
        val decimal = DecimalFormat("#.##")

        if (viewButton == vButtonDolar) {
            val guardaValorDolar = vEditText.text.toString()
            val doubleDolar = guardaValorDolar.toDoubleOrNull() ?: 0.0
            val resultado =  doubleDolar / dolar
            val formatoDecimal = decimal.format(resultado)

            vText.text = "U$ $formatoDecimal"
        } else if (viewButton == vButtonReal) {
            val guardaValorReal = vEditText.text.toString()
            var doubleReal = guardaValorReal.toDoubleOrNull() ?: 0.0
            doubleReal *= dolar

            vText.text = "R$ $doubleReal"
        }
    }
}
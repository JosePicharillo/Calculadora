package br.edu.ifsp.calculadora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.calculadora.settings.Configuration

class SettingsActivity : AppCompatActivity() {

    private lateinit var radioSimples: RadioButton
    private lateinit var radioAvancado: RadioButton
    private lateinit var btnSave: Button

    private val configSettings = Configuration.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        componentsView()
        showInfoView()
        saveSettings()
    }

    private fun componentsView() {
        radioSimples = findViewById(R.id.radio_simples)
        radioAvancado = findViewById(R.id.radio_avancado)
        btnSave = findViewById(R.id.btn_save_settings)
    }

    /**
     * Verificar Tipo Calculadora Selecionado
     * 0 - SIMPLES | 1 - AVANÇADO
     */
    private fun checkTypeCalc(): Int {
        var op = 0
        if (radioSimples.isChecked) op = 0
        if (radioAvancado.isChecked) op = 1
        return op
    }

    /**
     * Salva alterações configurações e retorna para a tela principal
     */
    private fun saveSettings () {
        btnSave.setOnClickListener {
            configSettings.setCheckType(checkTypeCalc())

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Exibir Informações na VIew
     */
    private fun showInfoView() {
        when (configSettings.getCheckType()) {
            0 -> radioSimples.isChecked = true
            1 -> radioAvancado.isChecked = true
            else -> {}
        }
    }
}
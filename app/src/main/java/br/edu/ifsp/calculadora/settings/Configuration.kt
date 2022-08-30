package br.edu.ifsp.calculadora.settings

import android.content.Context

class Configuration(context: Context) : AppConfig(context, "Configuration") {

    companion object {
        private var instance: Configuration? = null

        fun getInstance(context: Context? = null): Configuration {
            if (instance == null)
                if (context == null)
                    throw  ExceptionInInitializerError("O contexto deve ser enviado pelo menos na primeira vez que o método getInstance(contexto) for chamado")
                else instance = Configuration(context.applicationContext)
            return instance!!
        }

        private const val TIPO_CALCULADORA = "TipoCalc"
        private const val calcDefault = 0   // 0 - Simples | 1 - Avançada
    }

    fun getCheckType(): Int = getInt(TIPO_CALCULADORA, calcDefault)
    fun setCheckType(value: Int) = putInt(TIPO_CALCULADORA, value)

}
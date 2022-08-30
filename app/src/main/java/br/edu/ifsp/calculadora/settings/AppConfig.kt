package br.edu.ifsp.calculadora.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

open class AppConfig(context: Context, fileName: String = "AppConfig") {

    private val preferenceFirstExecution = "First Execution"

    companion object {
        private var instance: AppConfig? = null

        fun getInstance(context: Context? = null): AppConfig {
            if (instance == null)
                if (context == null)
                    throw  ExceptionInInitializerError("O contexto deve ser enviado pelo menos na primeira vez que o método getInstance(contexto) for chamado")
                else {
                    instance = AppConfig(context.applicationContext)
                    Configuration.getInstance(context)
                }
            return instance!!
        }
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(fileName,
        AppCompatActivity.MODE_PRIVATE)

    private fun edit(): SharedPreferences.Editor = sharedPreferences.edit()

    fun putInt(key: String, value: Int) = edit().putInt(key, value).apply()
    fun getInt(key: String, defaultValue: Int) = sharedPreferences.getInt(key, defaultValue)

    /**
     * Indica se o app foi executado pela 1a vez
     */
    fun setFirstExecution(firstExecution: Boolean) {
        edit().putBoolean(preferenceFirstExecution, firstExecution).apply()
    }

    fun firstExecution(): Boolean {
        return sharedPreferences.getBoolean(preferenceFirstExecution, true)
    }

}
package uz.eloving.practise

import android.content.Context
import android.graphics.Bitmap

class MySharedPreferences(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    var name: String
        get() = sharedPreferences.getString("name", "") ?: ""
        set(value) {
            sharedPreferences.edit().putString("name", value).apply()
        }

    var surname: String
        get() = sharedPreferences.getString("surname", "") ?: ""
        set(value) {
            sharedPreferences.edit().putString("surname", value).apply()
       }

    var country: String
        get() = sharedPreferences.getString("country", "") ?: ""
        set(value) {
            sharedPreferences.edit().putString("country", value).apply()
        }
}

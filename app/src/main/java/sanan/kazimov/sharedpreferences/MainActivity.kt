package sanan.kazimov.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a SharedPreferences instance
        val nameAgeSharedPref = getSharedPreferences("nameAge", Context.MODE_PRIVATE)

        btnSave.setOnClickListener {
            // get values entered by user from the widgets
            val name = edtName.text.toString()
            val age = edtAge.text.toString().toInt()
            val isAdult = cbxAdult.isChecked

            // whenever we want to write something in shared preferences we call editor, and then put that values inside the shared preferences
            nameAgeSharedPref.edit().apply {
                putString("NAME", name)
                putInt("AGE", age)
                putBoolean("IS_ADULT", isAdult)
                apply() // means, we end putting data in shared preferences,and applying edits asynchronously.
            }
        }

        btnLoad.setOnClickListener {
            val name = nameAgeSharedPref.getString("NAME", null)
            val age = nameAgeSharedPref.getInt("AGE", 0)
            val isAdult = nameAgeSharedPref.getBoolean("IS_ADULT", false)

            edtName.setText(name)
            edtAge.setText(age.toString())
            cbxAdult.isChecked = isAdult
        }
    }
}
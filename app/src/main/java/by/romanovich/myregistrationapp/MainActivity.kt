package by.romanovich.myregistrationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import by.romanovich.myregistrationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var vb: ActivityMainBinding? = null
    val counters = mutableListOf(0, 0, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

    }
}

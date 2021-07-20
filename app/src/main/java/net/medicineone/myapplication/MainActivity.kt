package net.medicineone.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHILD_ACTIVITY_REQUEST_CODE = 12345
    }

    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        Log.d("MainActivity", "registerForActivityResult")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStartActivityForResult = findViewById<Button>(R.id.buttonStartActivityForResult)
        buttonStartActivityForResult.setOnClickListener {
            val intent = Intent(this, ChildActivity::class.java)
            startActivityForResult(intent, CHILD_ACTIVITY_REQUEST_CODE)
            Handler(Looper.getMainLooper()).postDelayed({
                finishActivity(CHILD_ACTIVITY_REQUEST_CODE)
            }, 5000)
        }

        val buttonActivityResultAPI = findViewById<Button>(R.id.buttonActivityResultAPI)
        buttonActivityResultAPI.setOnClickListener {
            val intent = Intent(this, ChildActivity::class.java)
            activityResultLauncher.launch(intent)
            Handler(Looper.getMainLooper()).postDelayed({
                finishActivity(CHILD_ACTIVITY_REQUEST_CODE)
            }, 5000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("MainActivity", "onActivityResult")
    }
}
package edu.temple.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts

class DisplayActivity : AppCompatActivity() {

    private lateinit var lyricsDisplayTextView: TextView
    private lateinit var textSizeSelectorButton: Button

    // Step 1: Launch TextSizeActivity when button clicked
    private val textSizeActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // Step 3: Use returned value for lyricsDisplayTextView text size
            val selectedTextSize = result.data?.getIntExtra("SELECTED_TEXT_SIZE", 16) ?: 16
            lyricsDisplayTextView.textSize = selectedTextSize.toFloat()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        lyricsDisplayTextView = findViewById(R.id.lyricsDisplayTextView)
        textSizeSelectorButton = findViewById(R.id.textSizeSelectorButton)

        // Step 1: Launch TextSizeActivity when button clicked
        textSizeSelectorButton.setOnClickListener {
            val intent = Intent(this, TextSizeActivity::class.java)
            textSizeActivityLauncher.launch(intent)
        }
    }
}
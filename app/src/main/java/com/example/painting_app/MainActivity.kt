package com.example.painting_app

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.painting_app.PaintView.Companion.colorList
import com.example.painting_app.PaintView.Companion.currentBrush
import com.example.painting_app.PaintView.Companion.pathList

class MainActivity : AppCompatActivity() {

    // Objects that PaintView need, so we make them available.
    companion object {
        var path = Path()
        var paintBrush = Paint()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Storing the color on variables
        val redButton: ImageButton = findViewById(R.id.redColor)
        val blueButton: ImageButton = findViewById(R.id.blueColor)
        val yellowButton: ImageButton = findViewById(R.id.yellowColor)
        val blackButton: ImageButton = findViewById(R.id.blackColor)
        val eraser: ImageButton = findViewById(R.id.whiteColor)

        // Add events when the colors are pressed.
        redButton.setOnClickListener {
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }

        blueButton.setOnClickListener {
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }

        yellowButton.setOnClickListener {
            paintBrush.color = Color.YELLOW
            currentColor(paintBrush.color)
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }

        blackButton.setOnClickListener {
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }

        eraser.setOnClickListener {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            pathList.clear()
            colorList.clear()
            path.reset()
        }
    }

    // This method allows to change color
    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }
}

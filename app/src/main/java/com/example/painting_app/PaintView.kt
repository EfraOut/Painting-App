package com.example.painting_app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.painting_app.MainActivity.Companion.paintBrush
import com.example.painting_app.MainActivity.Companion.path

class PaintView : View {
    // Responsible for matching width and height with parent layout.
    private var params: ViewGroup.LayoutParams? = null

    // Global variables for MainActivity.kt
    companion object {
        var pathList = ArrayList<Path>() // Keeps track of the path we are painting
        var colorList = ArrayList<Int>() // All the colors we use on the program
        var currentBrush = Color.BLACK
    }

    constructor(context: Context) : this(context, null) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init()
    }

    // Initialize the brush
    private fun init() {
        paintBrush.isAntiAlias = true // Make the texture smooth.
        paintBrush.color = currentBrush // Color
        paintBrush.style = Paint.Style.STROKE // How it's going to look like
        paintBrush.strokeJoin = Paint.Join.ROUND // THe stroke is connected
        paintBrush.strokeWidth = 8f // How thick is going to be

        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    // Register the movement of the finger on the screen
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.x
        var y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                pathList.add(path)
                colorList.add(currentBrush)
            }
            else -> return false
        }
        postInvalidate()
        return false
    }

    // Draw on the canvas now that we can register the finger movement.
    override fun onDraw(canvas: Canvas) {
        for (i in pathList.indices) {
            paintBrush.color = colorList[i]
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()
        }
    }
}

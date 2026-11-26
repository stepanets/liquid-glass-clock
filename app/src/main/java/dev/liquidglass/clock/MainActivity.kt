package dev.liquidglass.clock

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.WindowInsets

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        window.setDecorFitsSystemWindows(false)
        setContentView(R.layout.activity_main)
        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            val bars = insets.getInsets(WindowInsets.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
    }
}

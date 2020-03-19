package com.eosr14.motionlayoutexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eosr14.motionlayoutexample.codelab.CodeLabActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_code_lab.setOnClickListener { CodeLabActivity.startActivity(this) }
    }
}
package com.yagmurgocmen.lab3_sols

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.ImageViewCompat

class MainActivity : AppCompatActivity() {
    lateinit var radioGroupGames: RadioGroup
    lateinit var radioButtonFortnite: RadioButton
    lateinit var radioButtonLegend: RadioButton
    lateinit var radioButtonMinecraft: RadioButton
    lateinit var gameImageView: ImageView
    lateinit var favcheckbox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        radioButtonFortnite = findViewById(R.id.radioButtonFortnite)
        radioButtonLegend = findViewById(R.id.radioButtonLegend)
        radioButtonMinecraft = findViewById(R.id.radioButtonMinecraft)
        radioGroupGames = findViewById(R.id.radioGroup)
        gameImageView = findViewById(R.id.gameImageView)
        favcheckbox=findViewById(R.id.checkBox)

        radioButtonFortnite.setOnClickListener {
            Toast.makeText(this,"Fortnite is selected", Toast.LENGTH_SHORT).show() //toast: ekranda bi sure gozuken mesajlar
            gameImageView.setImageResource(R.drawable.fortnite)
        }

        radioButtonLegend.setOnClickListener {
            Toast.makeText(this,"LoL is selected",Toast.LENGTH_SHORT).show()
            gameImageView.setImageResource(R.drawable.legend)
        }

        radioButtonMinecraft.setOnClickListener {
            Toast.makeText(this,"Minecraft is selected",Toast.LENGTH_SHORT).show()
            gameImageView.setImageResource(R.drawable.minecraft)
        }
        favcheckbox.setOnClickListener {
            Toast.makeText(this,"Checkbox is selected", Toast.LENGTH_SHORT).show()
        }
    }
}
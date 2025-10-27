package com.yagmurgocmen.lab4_sols

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ctis487.lab.myapplication.LocaleHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var titleTextView: TextView
    lateinit var title: String
    lateinit var partyType: String
    lateinit var spinType: Spinner
    lateinit var btnLeft: Button
    lateinit var btnRight: Button
    lateinit var backGround: Button
    lateinit var btnLanguage: Button
    lateinit var img1: ImageView
    lateinit var img2: ImageView
    lateinit var img3: ImageView
    lateinit var floatingActionButton: FloatingActionButton

    var imgNum = 3
    lateinit var layout: LinearLayout
    var dimTextSize = 0f
    var clr = 0

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getLanguage(newBase)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge() tam ekrana sigdirmak icin daha cok statusbar ya da navigasyon bar falan kullanirken bunu yaziyoruz ekranin kenarlarina kadar icerigi uzatmaya yariyo

        setContentView(R.layout.activity_main)

        img1 = findViewById<View>(R.id.img1) as ImageView  //Imgview old icin view dondurmesini istiyoruz o yuzden findViewById<View>
        img2 =findViewById<View>(R.id.img2) as ImageView
        img3 = findViewById<View>(R.id.img3) as ImageView

        btnLeft = findViewById(R.id.arrow_left)
        btnRight = findViewById(R.id.arrow_right)
        backGround = findViewById(R.id.btnBackGround) // eger burda degisken atamayi unutursan calismaz buna dikkat et
        btnLanguage = findViewById(R.id.btnLanguage)
        floatingActionButton = findViewById(R.id.floating_action_button)

        titleTextView= findViewById<View>(R.id.tv1) as TextView
        title = resources.getString(R.string.txtParty) //xml fileinda kullansak bile bunu mainde kullanmak icin bu sekilde cagiriyoruz
        titleTextView.text = title
        clr= ContextCompat.getColor(this,R.color.teal_700)
        titleTextView.setTextColor(clr)

        spinType = findViewById<View>(R.id.spinType) as Spinner
        layout= findViewById<View>(R.id.LinearLayout1) as LinearLayout

        spinType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    partyType = parent?.getItemAtPosition(position).toString()
                    val message = resources.getString(R.string.dialog_you_have, partyType)
                    makeAndShowDialogBox(message)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        floatingActionButton.setOnClickListener { displayToast(resources.getString(R.string.toast_fab_clicked)) }
        btnLeft.setOnClickListener {
            imgNum--
            img1.visibility= View.INVISIBLE
            img2.visibility= View.INVISIBLE
            img3.visibility= View.INVISIBLE
            if(imgNum %3 ==0) img3.visibility = View.VISIBLE
            else if(imgNum %3 ==1) img1.visibility = View.VISIBLE
            else img2.visibility = View.VISIBLE
        }
        btnRight.setOnClickListener {
            imgNum++
            img1.visibility= View.INVISIBLE
            img2.visibility= View.INVISIBLE
            img3.visibility= View.INVISIBLE
            if(imgNum %3 ==0) img3.visibility = View.VISIBLE
            else if(imgNum %3 ==1) img1.visibility = View.VISIBLE
            else img2.visibility = View.VISIBLE

        }
        backGround.setOnClickListener {
            changeBackgroundcolor()
        }

        btnLanguage.setOnClickListener {
            changeLanguage()
        }


    }

    fun changeBackgroundcolor() {
        val rand1: Int
        val rand2: Int
        val rand3: Int
        clr= ContextCompat.getColor(this,R.color.teal_200)
        rand1 = (Math.random() *255).toInt()
        rand2 = (Math.random() *255).toInt()
        rand3 = (Math.random() *255).toInt()
        layout.setBackgroundColor(Color.rgb(rand1,rand2,rand3))
        backGround.setBackgroundColor(clr)
    }

    private fun makeAndShowDialogBox(message: String) {
        val myDialogBox= AlertDialog.Builder(this) //popup uyari penceresi olusturmak icin builder
        //alertdialog => emin misiniz, kaydedilsin mi vs icin kullanilan pencere turu

        myDialogBox.setTitle(resources.getString(R.string.dialog_party_type_title))
        myDialogBox.setMessage(message)
        myDialogBox.setIcon(R.drawable.ic_android)

        //set three options button
        myDialogBox.setPositiveButton(resources.getString(R.string.dialog_close)
        ){dialog, which ->

        }
        myDialogBox.create()
        myDialogBox.show()
    }

    private fun displayToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun changeLanguage() {
        val currentLang = LocaleHelper.getLanguage(this)
        val newLang = if(currentLang =="tr") "en" else "tr"

        LocaleHelper.setLocale(this, newLang)
        displayToast(resources.getString(R.string.toast_language_changed))

        recreate() //UI yeniden ciziliyo
    }
}
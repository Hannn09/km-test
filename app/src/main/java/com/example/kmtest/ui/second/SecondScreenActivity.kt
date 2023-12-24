package com.example.kmtest.ui.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmtest.ui.third.MainActivity
import com.example.kmtest.ui.first.FirstScreenActivity.Companion.USERNAME
import com.example.kmtest.ui.UserAdapter.Companion.NAME
import com.example.kmtest.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var secondScreenBinding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondScreenBinding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(secondScreenBinding.root)

        val username = intent.getStringExtra(USERNAME)

        val name = intent.getStringExtra(NAME)

        secondScreenBinding.apply {
            icBack.setOnClickListener {
                onBackPressed()
            }

            tvUsername.text = username.toString()

            tvSelectedUserName.text = name.toString()

            btnChooseUser.setOnClickListener {
                startActivity(Intent(this@SecondScreenActivity, MainActivity::class.java))
            }
        }
    }
}
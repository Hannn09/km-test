package com.example.kmtest.ui.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.kmtest.databinding.ActivityFirstScreenBinding
import com.example.kmtest.ui.second.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var firstScreenBinding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstScreenBinding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(firstScreenBinding.root)

        firstScreenBinding.apply {
            val inputedName = nameEditText.text
            val palindromeText = palindromeEditText.text

            btnNext.setOnClickListener {
                val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                intent.putExtra(USERNAME, inputedName.toString())
                Log.d(TAG, "username: $inputedName")
                startActivity(intent)
            }

           btnCheck.setOnClickListener {
                if (isPalindrome(palindromeText.toString())) {
                    buildDialog("The Text is Palindrome!")
                    Log.d(TAG, "palindrome : ${isPalindrome(palindromeText.toString())}")
                } else {
                    buildDialog("The Text is Not Palindrome!")
                    Log.d(TAG, "palindrome : ${isPalindrome(palindromeText.toString())}")
                }
            }
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val inputText = text.lowercase().replace("[^a-zA-Z0-9]".toRegex(), "")
        val reversedText = inputText.reversed()

        return inputText == reversedText
    }

    private fun buildDialog(message: String) {
        val builder = AlertDialog.Builder(this@FirstScreenActivity)
        builder.setMessage(message)
            .setTitle("Check Palindrome")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    companion object {
        const val USERNAME = "username"
        private const val TAG = "FirstScreenActivity"
    }
}
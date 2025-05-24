package ru.lion.netmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomappbar.BottomAppBar
import ru.lion.netmedia.R
import ru.lion.netmedia.databinding.EditPostActivityBinding

class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = EditPostActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomAppBar = findViewById<BottomAppBar>(R.id.bottomAppBar)

        val content = intent.getStringExtra(Intent.EXTRA_TEXT)
        binding.edit.setText(content)
        binding.edit.requestFocus()


        binding.ok.setOnClickListener {
            val intent = Intent()
            if (binding.edit.text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                val newContent = binding.edit.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, newContent)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
        bottomAppBar.setNavigationOnClickListener {
            finish()
        }


    }
}
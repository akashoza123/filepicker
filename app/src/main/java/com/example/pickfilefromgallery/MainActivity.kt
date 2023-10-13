package com.example.pickfilefromgallery

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pickfilefromgallery.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPickfile.setOnClickListener {
            showFileChooser()
        }
    }

    private fun showFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            startActivityForResult(Intent.createChooser(intent,"Select a file"),100)
        }
        catch (exception : Exception){
            Toast.makeText(this, "Please install a file manager.", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE  && resultCode == RESULT_OK){

            val uri: Uri? = data?.data
            val path : String = uri?.path.toString()
            var file = File(path)

            binding.tvFile.text = "Path: $path File name: ${file.name}".trimIndent()

        }
    }
}
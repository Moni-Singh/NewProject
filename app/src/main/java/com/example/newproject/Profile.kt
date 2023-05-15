package com.example.newproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {


    lateinit var imageView: ImageView
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null
     var radioGroup:RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

//       radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
//        val genderTextInputLayout = findViewById<TextInputLayout>(R.id.birthday)
//        genderTextInputLayout.setOnClickListener{
//
//        }
        val genderTextInputLayout: TextInputLayout = findViewById(R.id.gender)
        val radioButtonMale: RadioButton = findViewById(R.id.radioButtonMale)
        val radioButtonFemale: RadioButton = findViewById(R.id.radioButtonFemale)
        val basicInfoEditText: EditText = findViewById(R.id.Basic_info)

        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedGender: String = when (checkedId) {
                R.id.radioButtonMale -> radioButtonMale.text.toString()
                R.id.radioButtonFemale -> radioButtonFemale.text.toString()
                else -> ""
            }
            basicInfoEditText.setText(selectedGender)
        }




        title = "profile"
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.buttonLoadPicture)
        button.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
}
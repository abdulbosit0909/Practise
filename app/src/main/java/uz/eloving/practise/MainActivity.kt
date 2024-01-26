package uz.eloving.practise

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import uz.eloving.practise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imgType: ImgType
    private var state = false

    enum class ImgType {
        Gallery, Camera
    }

    private val dialog by lazy { MyDialog() }
    private val mySharedPreferences by lazy { MySharedPreferences(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val countries = listOf(
            mySharedPreferences.country,
            "America",
            "Russian",
            "Pakistan",
            "India",
            "Dubai",
            "Uzbekistan"
        )

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val bitmap: Bitmap? = if (imgType == ImgType.Gallery) {
                        val imageUri: Uri? = data?.data
                        MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                    } else data?.extras?.get("data") as? Bitmap
                    binding.ivAvatar.setImageBitmap(bitmap)
                } else {
                    Toast.makeText(this, "Failed to retrieve image", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivAvatar.setOnClickListener {
            dialog.show(supportFragmentManager, "dialog")
        }

        dialog.onCameraClicked = {
            imgType = ImgType.Camera
            resultLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            dialog.dismiss()
        }
        dialog.onGalleryClicked = {
            imgType = ImgType.Gallery
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            resultLauncher.launch(galleryIntent)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.etRegion.adapter = adapter

        binding.etName.setText(mySharedPreferences.name)
        binding.etSurname.setText(mySharedPreferences.surname)


        binding.btnSave.setOnClickListener {
            mySharedPreferences.name = binding.etName.text.toString()
            mySharedPreferences.surname = binding.etSurname.text.toString()
            mySharedPreferences.country = binding.etRegion.selectedItem.toString()
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }
        binding.toolbar.backArrow.setOnClickListener {
            finish()
        }
    }

    private fun changeButtonState(state: Boolean) {
        binding.btnSave.isEnabled = state
        binding.btnSave.isClickable = state

        binding.btnSave.setBackgroundColor(
            ContextCompat.getColor(
                this, if (state) R.color.stateEnabled else R.color.stateDisabled
            )
        )
        binding.btnSave.setTextColor(
            ContextCompat.getColor(
                this, if (state) R.color.white else R.color.black
            )
        )
    }

}

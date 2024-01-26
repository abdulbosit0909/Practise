package uz.eloving.practise

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import uz.eloving.practise.databinding.CustomDialogBinding

class MyDialog : DialogFragment() {

    private lateinit var binding: CustomDialogBinding

    var onGalleryClicked: (() -> Unit)? = null
    var onCameraClicked: (() -> Unit)? = null
    

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomDialogBinding.inflate(inflater, container, false)

        binding.btnGallery.setOnClickListener {
            onGalleryClicked?.invoke()
        }

        binding.btnCamera.setOnClickListener {
            onCameraClicked?.invoke()
        }

        binding.ivCancel.setOnClickListener { dismiss() }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root

    }
}

package com.example.instagramclone.utils

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


fun uploadImage(uri: Uri, folderName: String, callback: (String?) -> Unit) {
    var imageUri: String? = null
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageUri = it.toString()

                callback(imageUri)
            }
        }


}

fun uploadVideo(
    uri: Uri,
    folderName: String,
    progressDialog: ProgressDialog,
    callback: (String?) -> Unit
) {
    var imageUri: String? = null
    progressDialog.setTitle("Please Wait. . .")
    progressDialog.show()

    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageUri = it.toString()
                progressDialog.dismiss()

                callback(imageUri)
            }
        }
        .addOnProgressListener {
            val uploadedValue: Long = (it.bytesTransferred / it.totalByteCount)*100
            progressDialog.setMessage("Uploaded $uploadedValue %")
            progressDialog.setCancelable(false)
        }


}
package com.breadone.PermissionTestApp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import com.unity3d.player.UnityPlayer
import com.unity3d.player.UnityPlayerActivity

class UnityGalleryActivity : UnityPlayerActivity() {

    fun Open(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        UnityPlayer.currentActivity.startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 0 && resultCode == Activity.RESULT_OK)
        {
            val UPath = data!!.data
            val path = abs_path(UPath)
            //유니티에 값을 전달
            UnityPlayer.UnitySendMessage("AndroidPlugin", "getImage", path)
        }
    }

    fun abs_path(uri:Uri?): String?
    {
        var m_data = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = managedQuery(uri, m_data, null, null, null)
        startManagingCursor(cursor)
        val columIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columIndex)

    }
}

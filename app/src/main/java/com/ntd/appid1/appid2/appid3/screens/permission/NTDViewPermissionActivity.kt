package com.ntd.appid1.appid2.appid3.screens.permission

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import com.ntd.appid1.appid2.appid3.NTDViewMainActivity
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.databinding.ActivityPermissionBinding
import com.ntd.appid1.appid2.appid3.extensions.onAvoidDoubleClick

class NTDViewPermissionActivity : NTDOtherBaseActivityAppntd<ActivityPermissionBinding>(),
    OnClickListener {

    private var checkPermissionStorage: Boolean = false
    private var checkPermissionRecord: Boolean = false
    private var enableNotification: Boolean = false


    override fun setViewBinding(): ActivityPermissionBinding {
        return ActivityPermissionBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        init()
        onClick()
    }

    override fun initData() {}

    override fun initListener() {
        binding.btnGo.onAvoidDoubleClick {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (enableNotification && checkPermissionRecord) {
                    NTDHelperSharePrefUtils.putBoolean(NTDHelperSharePrefUtils.SHARE_PREF_OPEN_FIRST_APP, false)
                    startActivity(Intent(this@NTDViewPermissionActivity, NTDViewMainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, R.string.str_permission_message_denied, Toast.LENGTH_SHORT).show()
                }
            } else {
                if (checkPermissionRecord && checkPermissionStorage) {
                    NTDHelperSharePrefUtils.putBoolean(NTDHelperSharePrefUtils.SHARE_PREF_OPEN_FIRST_APP, false)
                    startActivity(Intent(this@NTDViewPermissionActivity, NTDViewMainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, R.string.str_permission_message_denied, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun init() {
        checkPermissionRecord =
            NTDHelperPermissionHelper.checkPermissionRecordAudio(this@NTDViewPermissionActivity, binding.btnAllowRecordFile)
        checkPermissionStorage =
            NTDHelperPermissionHelper.checkPermissionStorage(this@NTDViewPermissionActivity, binding.btnAllowStorage)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            enableNotification = PermissionChecker.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PermissionChecker.PERMISSION_GRANTED

            binding.layoutStorage.visibility = View.GONE  // has no effect with sdk > 32
            binding.layoutNotification.visibility = View.VISIBLE
            binding.btnSwitchNotification.isActivated = enableNotification
            binding.tvDescribePer.text = getString(R.string.string_allow_translate_1)
        } else {
            binding.layoutNotification.visibility = View.GONE
            binding.layoutStorage.visibility = View.VISIBLE
            binding.tvDescribePer.text = getString(R.string.string_allow_translate)
            val params: ViewGroup.MarginLayoutParams = binding.layoutRecordFileAudio.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(0, 0, 0, 0)
            binding.layoutRecordFileAudio.requestLayout()
        }
    }

    private fun onClick() {
        binding.btnAllowStorage.setOnClickListener(this)
        binding.btnAllowRecordFile.setOnClickListener(this)
        binding.btnGo.setOnClickListener(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            binding.btnSwitchNotification.setOnClickListener {

                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1999)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnAllowStorage.id -> {
                if (!binding.btnAllowStorage.isActivated) {
                    NTDHelperPermissionHelper.requestPermissionStorage(
                        this@NTDViewPermissionActivity,
                        this@NTDViewPermissionActivity,
                        storageActivityResultLauncher
                    )
                }
            }

            binding.btnAllowRecordFile.id -> {
                if (!binding.btnAllowRecordFile.isActivated) {
                    NTDHelperPermissionHelper.requestPermissionRecordAudio(this@NTDViewPermissionActivity)
                }
            }
        }
    }

    private fun enableGo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (enableNotification && checkPermissionRecord) {
                binding.btnGo.setTextColor(resources.getColor(R.color.text_high_light, resources.newTheme()))
                binding.imgPermission.setImageResource(R.drawable.img_permission_unlock)
            } else {
                binding.btnGo.setTextColor(resources.getColor(R.color.gray_81, resources.newTheme()))
                binding.imgPermission.setImageResource(R.drawable.img_permission_lock)
            }
        } else {
            if (checkPermissionRecord && checkPermissionStorage) {
                binding.btnGo.setTextColor(resources.getColor(R.color.text_high_light, resources.newTheme()))
                binding.imgPermission.setImageResource(R.drawable.img_permission_unlock)
            } else {
                binding.btnGo.setTextColor(resources.getColor(R.color.gray_81, resources.newTheme()))
                binding.imgPermission.setImageResource(R.drawable.img_permission_lock)
            }
        }
    }

    private val storageActivityResultLauncher =
        (this as ComponentActivity).registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setCancelable(false)

            alertDialog.setMessage(getString(R.string.noti_need_permission))
            alertDialog.setButton(
                -1, getString(R.string.go_to_setting)
            ) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", (this as ComponentActivity).packageName, null)
                intent.data = uri

                startActivityForResult(intent, NTDHelperPermissionHelper.STORAGE_PERMISSION_CODE)
                alertDialog.dismiss()
            }

            alertDialog.show()
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == NTDHelperPermissionHelper.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty()) {
                val write = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val read = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (write && read) {
                    checkPermissionStorage = true
                    binding.btnAllowStorage.isActivated = true
                    binding.btnAllowStorage.isEnabled = false

                } else {
                    checkPermissionStorage = false
                    binding.btnAllowStorage.isActivated = false
                    binding.btnAllowStorage.isEnabled = true

                    val alertDialog = AlertDialog.Builder(this).create()
                    alertDialog.setCancelable(false)

                    alertDialog.setMessage(getString(R.string.noti_need_permission))
                    alertDialog.setButton(
                        -1,
                        getString(R.string.go_to_setting)
                    ) { _, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri

                        startActivityForResult(intent, 1234)

                        alertDialog.dismiss()
                    }

                    alertDialog.show()

                }
            }
        }

        if (requestCode == NTDHelperPermissionHelper.RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.isNotEmpty()) {
                val record = grantResults[0] == PackageManager.PERMISSION_GRANTED

                if (record) {
                    checkPermissionRecord = true
                    binding.btnAllowRecordFile.isActivated = true
                    binding.btnAllowRecordFile.isEnabled = false


                } else {
                    checkPermissionRecord = false
                    binding.btnAllowRecordFile.isActivated = false
                    binding.btnAllowRecordFile.isEnabled = true


                    val alertDialog = AlertDialog.Builder(this).create()
                    alertDialog.setCancelable(false)

                    alertDialog.setMessage(getString(R.string.noti_need_permission))
                    alertDialog.setButton(
                        -1,
                        getString(R.string.go_to_setting)
                    ) { _, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri

                        startActivityForResult(intent, 1234)
                        alertDialog.dismiss()
                    }

                    alertDialog.show()
                }
            }
        }

        if (requestCode == NTDHelperPermissionHelper.NOTIFICATION_ACCESS) {
            if (grantResults.isNotEmpty()) {
                val notificationAccess = grantResults[0] == PackageManager.PERMISSION_GRANTED

                if (notificationAccess) {
                    enableNotification = true
                    binding.btnSwitchNotification.isActivated = true
                    binding.btnSwitchNotification.isEnabled = false

                } else {
                    enableNotification = false
                    binding.btnSwitchNotification.isActivated = false
                    binding.btnSwitchNotification.isEnabled = true

                    val alertDialog = AlertDialog.Builder(this).create()
                    alertDialog.setCancelable(false)

                    alertDialog.setMessage(getString(R.string.noti_need_permission))
                    alertDialog.setButton(
                        -1,
                        getString(R.string.go_to_setting)
                    ) { _, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri

                        startActivityForResult(intent, 1234)
                        alertDialog.dismiss()
                    }

                    alertDialog.show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        enableGo()
    }
}

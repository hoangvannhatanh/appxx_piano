package com.ntd.appid1.appid2.appid3.screens.language

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntd.appid1.appid2.appid3.NTDViewMainActivity
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.adapter.NTDOtherLanguageAdapter
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.local.NTDHelperPreferencesHelper
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.databinding.LanguageActivityBinding
import com.ntd.appid1.appid2.appid3.extensions.onAvoidDoubleClick
import com.ntd.appid1.appid2.appid3.model.NTDModelLanguage
import com.ntd.appid1.appid2.appid3.screens.intro.NTDViewIntroActivity
import com.ntd.appid1.appid2.appid3.utils.NTDOtherUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NTDViewLanguageActivity : NTDOtherBaseActivityAppntd<LanguageActivityBinding>() {
    private var languageCode = ""
    private var adapter: NTDOtherLanguageAdapter? = null
    private var listLanguage = arrayListOf<NTDModelLanguage>()
    private var isOpenFirst: Boolean = false
    override fun setViewBinding(): LanguageActivityBinding {
        return LanguageActivityBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        isOpenFirst = NTDHelperSharePrefUtils.getBoolean(
            NTDHelperSharePrefUtils.SHARE_PREF_OPEN_FIRST_APP, true)

        if (isOpenFirst) {
            binding.imgBack.visibility = View.GONE
        } else binding.imgBack.visibility = View.VISIBLE

        languageCode = NTDHelperPreferencesHelper.getLanguage()
        listLanguage.add(NTDModelLanguage("English", "en", false, R.drawable.ic_english))
        listLanguage.add(NTDModelLanguage("Portuguese", "pt", false, R.drawable.ic_portugal))
        listLanguage.add(NTDModelLanguage("French", "fr", false, R.drawable.ic_france))
        listLanguage.add(NTDModelLanguage("Spanish", "es", false, R.drawable.ic_spanish))
        listLanguage.add(NTDModelLanguage("Hindi", "hi", false, R.drawable.ic_india))
        binding.rvLanguage.layoutManager = LinearLayoutManager(this)
        adapter = NTDOtherLanguageAdapter(this) {
            languageCode = it.isoLanguage
        }
        binding.rvLanguage.adapter = adapter
        adapter?.setData(listLanguage)
        val position =
            if (listLanguage.indexOfFirst { it.isoLanguage == NTDHelperPreferencesHelper.getLanguage() } == -1) {
                0
            } else {
                listLanguage.indexOfFirst { it.isoLanguage == NTDHelperPreferencesHelper.getLanguage() }
            }
        adapter?.setCurrentPosition(position)
        binding.imgDone.onAvoidDoubleClick {
            NTDHelperPreferencesHelper.setLanguage(languageCode)
            NTDOtherUtils.setLocale(this, languageCode)
            if (isOpenFirst) {
                startActivity(Intent(this@NTDViewLanguageActivity, NTDViewIntroActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@NTDViewLanguageActivity, NTDViewMainActivity::class.java))
                finishAffinity()
            }

        }
        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    override fun initData() {}

    override fun initListener() {}
}
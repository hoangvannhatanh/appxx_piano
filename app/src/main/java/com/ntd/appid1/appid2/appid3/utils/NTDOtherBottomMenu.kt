package com.ntd.appid1.appid2.appid3.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.extensions.setTextColorGradient

class NTDOtherBottomMenu : ConstraintLayout {
    private var onMenuClick: OnMenuClick? = null
    private var context: Context
    private var ivHome: ImageView? = null
    private var ivRecord: ImageView? = null
    private var ivSetting: ImageView? = null
    private var btnHome: View? = null
    private var btnRecord: View? = null
    private var btnSetting: View? = null
    private var tvHome: TextView? = null
    private var tvRecord: TextView? = null
    private var tvSetting: TextView? = null

    constructor(context: Context) : super(context) {
        this.context = context
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.context = context
        initView(context)
    }

    private fun initView(context: Context) {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.layout_bottom_menu, this)
        ivHome = findViewById(R.id.ivHome)
        ivRecord = findViewById(R.id.ivRecord)
        ivSetting = findViewById(R.id.ivSetting)
        btnHome = findViewById(R.id.btnHome)
        btnRecord = findViewById(R.id.btnRecord)
        btnSetting = findViewById(R.id.btnSetting)
        tvHome = findViewById(R.id.tvHome)
        tvRecord = findViewById(R.id.tvRecord)
        tvSetting = findViewById(R.id.tvSetting)
        tvHome?.setTextColorGradient("#30274E", "#803253")
        listenOnClickItem()
    }

    private fun selectScreen(view: ScreenTag?) {
        when (view) {
            ScreenTag.HOME -> {
                ivHome?.setImageResource(R.drawable.ic_home_selected)
                tvHome?.setTextColorGradient("#30274E", "#803253")
                ivRecord?.setImageResource(R.drawable.ic_record_unselected)
                tvRecord?.setTextColorGradient("#B0B0B0", "#B0B0B0")
                ivSetting?.setImageResource(R.drawable.ic_setting_unselected)
                tvSetting?.setTextColorGradient("#B0B0B0", "#B0B0B0")
            }

            ScreenTag.TRACK -> {
                ivHome?.setImageResource(R.drawable.ic_home_unselected)
                tvHome?.setTextColorGradient("#B0B0B0", "#B0B0B0")
                ivRecord?.setImageResource(R.drawable.ic_record_selected)
                tvRecord?.setTextColorGradient("#30274E", "#803253")
                ivSetting?.setImageResource(R.drawable.ic_setting_unselected)
                tvSetting?.setTextColorGradient("#B0B0B0", "#B0B0B0")
            }

            ScreenTag.SETTING -> {
                ivHome?.setImageResource(R.drawable.ic_home_unselected)
                tvHome?.setTextColorGradient("#B0B0B0", "#B0B0B0")
                ivRecord?.setImageResource(R.drawable.ic_record_unselected)
                tvRecord?.setTextColorGradient("#B0B0B0", "#B0B0B0")
                ivSetting?.setImageResource(R.drawable.ic_setting_selected)
                tvSetting?.setTextColorGradient("#30274E", "#803253")
            }

            else -> {}
        }
    }

    private fun listenOnClickItem() {
        btnHome?.setOnClickListener {
            selectScreen(ScreenTag.HOME)
            onMenuClick?.onClick(Action.OPEN_HOME)
        }
        btnRecord?.setOnClickListener {
            selectScreen(ScreenTag.TRACK)
            onMenuClick?.onClick(Action.OPEN_TRACK)
        }
        btnSetting?.setOnClickListener {
            selectScreen(ScreenTag.SETTING)
            onMenuClick?.onClick(Action.OPEN_SETTING)
        }
    }

    fun addListener(context: Context, onMenuClickObject: OnMenuClick?) {
        this.context = context
        onMenuClick = onMenuClickObject
    }

    enum class Action {
        OPEN_HOME, OPEN_TRACK, OPEN_SETTING
    }

    enum class ScreenTag {
        HOME, TRACK, SETTING
    }

    interface OnMenuClick {
        fun onClick(action: Action?)
    }
}
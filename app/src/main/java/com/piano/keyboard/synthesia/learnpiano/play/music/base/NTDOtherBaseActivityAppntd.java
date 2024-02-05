package com.piano.keyboard.synthesia.learnpiano.play.music.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.viewbinding.ViewBinding;

import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperPreferencesHelper;
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperSharePrefUtils;
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.NTDOtherUtils;

public abstract class NTDOtherBaseActivityAppntd<T extends ViewBinding> extends AppCompatActivity {
    protected T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NTDHelperSharePrefUtils.init(this);
        NTDHelperPreferencesHelper.init(this);
        NTDOtherUtils.INSTANCE.init(this);
        NTDOtherUtils.INSTANCE.setLocale(this, NTDHelperPreferencesHelper.getLanguage());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        binding = setViewBinding();
        setContentView(binding.getRoot());

        //init
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        WindowInsetsControllerCompat windowInsetsController;
        if (Build.VERSION.SDK_INT >= 30) {
            windowInsetsController = ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        } else {
            windowInsetsController = new WindowInsetsControllerCompat(getWindow(), binding.getRoot());
        }

        if (windowInsetsController == null) {
            return;
        }

        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemGestures());

        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(i -> {
            if (i == 0) {
                new Handler().postDelayed(() -> {
                    WindowInsetsControllerCompat windowInsetsController1;
                    if (Build.VERSION.SDK_INT >= 30) {
                        windowInsetsController1 = ViewCompat.getWindowInsetsController(getWindow().getDecorView());
                    } else {
                        windowInsetsController1 = new WindowInsetsControllerCompat(getWindow(), binding.getRoot());
                    }
                    if (windowInsetsController1 != null) {
                        windowInsetsController1.hide(WindowInsetsCompat.Type.navigationBars());
                        windowInsetsController1.hide(WindowInsetsCompat.Type.systemGestures());
                    }
                }, 3000);
            }
        });
    }

    protected abstract T setViewBinding();
    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();
}

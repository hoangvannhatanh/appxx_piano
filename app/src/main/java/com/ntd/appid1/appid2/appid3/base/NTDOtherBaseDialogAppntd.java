package com.ntd.appid1.appid2.appid3.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

public abstract class NTDOtherBaseDialogAppntd<T extends ViewBinding> extends Dialog {
    public T binding;
    protected abstract T setViewBinding(Context context);
    protected abstract void initView(Context context);
    public NTDOtherBaseDialogAppntd(@NonNull Context context) {
        super(context);
        binding = setViewBinding(context);
        setContentView(binding.getRoot());
        getWindow().setLayout(AbsListView.LayoutParams.WRAP_CONTENT,
                AbsListView.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);

        initView(context);

    }

    @Override
    public void show() {
        // Set the dialog to not focusable.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Show the dialog with NavBar hidden.
        super.show();

        // Set the dialog to focusable again.
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }
}

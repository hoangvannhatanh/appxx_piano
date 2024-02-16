package com.ntd.appid1.appid2.appid3.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH$J\b\u0010\r\u001a\u00020\fH$J\b\u0010\u000e\u001a\u00020\fH$J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\fH\u0014J\r\u0010\u0014\u001a\u00028\u0000H$\u00a2\u0006\u0002\u0010\u0007R\u001c\u0010\u0005\u001a\u00028\u0000X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u0015"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/base/NTDOtherBaseActivityAppntd;", "T", "Landroidx/viewbinding/ViewBinding;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "setBinding", "(Landroidx/viewbinding/ViewBinding;)V", "Landroidx/viewbinding/ViewBinding;", "initData", "", "initListener", "initView", "initWindow", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setViewBinding", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public abstract class NTDOtherBaseActivityAppntd<T extends androidx.viewbinding.ViewBinding> extends androidx.appcompat.app.AppCompatActivity {
    protected T binding;
    
    public NTDOtherBaseActivityAppntd() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final T getBinding() {
        return null;
    }
    
    protected final void setBinding(@org.jetbrains.annotations.NotNull()
    T p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public void initWindow() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected abstract T setViewBinding();
    
    protected abstract void initView();
    
    protected abstract void initData();
    
    protected abstract void initListener();
}
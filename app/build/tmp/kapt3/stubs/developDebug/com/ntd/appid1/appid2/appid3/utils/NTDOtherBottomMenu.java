package com.ntd.appid1.appid2.appid3.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003\u001e\u001f B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\u0012\u0010\u001b\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/utils/NTDOtherBottomMenu;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "btnHome", "Landroid/view/View;", "btnRecord", "btnSetting", "ivHome", "Landroid/widget/ImageView;", "ivRecord", "ivSetting", "onMenuClick", "Lcom/ntd/appid1/appid2/appid3/utils/NTDOtherBottomMenu$OnMenuClick;", "tvHome", "Landroid/widget/TextView;", "tvRecord", "tvSetting", "addListener", "", "onMenuClickObject", "initView", "listenOnClickItem", "selectScreen", "view", "Lcom/ntd/appid1/appid2/appid3/utils/NTDOtherBottomMenu$ScreenTag;", "Action", "OnMenuClick", "ScreenTag", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class NTDOtherBottomMenu extends androidx.constraintlayout.widget.ConstraintLayout {
    private com.ntd.appid1.appid2.appid3.utils.NTDOtherBottomMenu.OnMenuClick onMenuClick;
    private android.content.Context context;
    private android.widget.ImageView ivHome;
    private android.widget.ImageView ivRecord;
    private android.widget.ImageView ivSetting;
    private android.view.View btnHome;
    private android.view.View btnRecord;
    private android.view.View btnSetting;
    private android.widget.TextView tvHome;
    private android.widget.TextView tvRecord;
    private android.widget.TextView tvSetting;
    
    public NTDOtherBottomMenu(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public NTDOtherBottomMenu(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    private final void initView(android.content.Context context) {
    }
    
    private final void selectScreen(com.ntd.appid1.appid2.appid3.utils.NTDOtherBottomMenu.ScreenTag view) {
    }
    
    private final void listenOnClickItem() {
    }
    
    public final void addListener(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    com.ntd.appid1.appid2.appid3.utils.NTDOtherBottomMenu.OnMenuClick onMenuClickObject) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/utils/NTDOtherBottomMenu$Action;", "", "(Ljava/lang/String;I)V", "OPEN_HOME", "OPEN_TRACK", "OPEN_SETTING", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
    public static enum Action {
        /*public static final*/ OPEN_HOME /* = new OPEN_HOME() */,
        /*public static final*/ OPEN_TRACK /* = new OPEN_TRACK() */,
        /*public static final*/ OPEN_SETTING /* = new OPEN_SETTING() */;
        
        Action() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/utils/NTDOtherBottomMenu$ScreenTag;", "", "(Ljava/lang/String;I)V", "HOME", "TRACK", "SETTING", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
    public static enum ScreenTag {
        /*public static final*/ HOME /* = new HOME() */,
        /*public static final*/ TRACK /* = new TRACK() */,
        /*public static final*/ SETTING /* = new SETTING() */;
        
        ScreenTag() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/utils/NTDOtherBottomMenu$OnMenuClick;", "", "onClick", "", "action", "Lcom/ntd/appid1/appid2/appid3/utils/NTDOtherBottomMenu$Action;", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
    public static abstract interface OnMenuClick {
        
        public abstract void onClick(@org.jetbrains.annotations.Nullable()
        com.ntd.appid1.appid2.appid3.utils.NTDOtherBottomMenu.Action action);
    }
}
// Generated by view binder compiler. Do not edit!
package com.ntd.appid1.appid2.appid3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ntd.appid1.appid2.appid3.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPermissionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView btnAllowRecordFile;

  @NonNull
  public final ImageView btnAllowStorage;

  @NonNull
  public final TextView btnGo;

  @NonNull
  public final ImageView btnSwitchNotification;

  @NonNull
  public final ImageView imgPermission;

  @NonNull
  public final ConstraintLayout layoutNotification;

  @NonNull
  public final ConstraintLayout layoutRecordFileAudio;

  @NonNull
  public final ConstraintLayout layoutStorage;

  @NonNull
  public final TextView tvAppName;

  @NonNull
  public final TextView tvDescribePer;

  private ActivityPermissionBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView btnAllowRecordFile, @NonNull ImageView btnAllowStorage,
      @NonNull TextView btnGo, @NonNull ImageView btnSwitchNotification,
      @NonNull ImageView imgPermission, @NonNull ConstraintLayout layoutNotification,
      @NonNull ConstraintLayout layoutRecordFileAudio, @NonNull ConstraintLayout layoutStorage,
      @NonNull TextView tvAppName, @NonNull TextView tvDescribePer) {
    this.rootView = rootView;
    this.btnAllowRecordFile = btnAllowRecordFile;
    this.btnAllowStorage = btnAllowStorage;
    this.btnGo = btnGo;
    this.btnSwitchNotification = btnSwitchNotification;
    this.imgPermission = imgPermission;
    this.layoutNotification = layoutNotification;
    this.layoutRecordFileAudio = layoutRecordFileAudio;
    this.layoutStorage = layoutStorage;
    this.tvAppName = tvAppName;
    this.tvDescribePer = tvDescribePer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPermissionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPermissionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_permission, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPermissionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_allow_record_file;
      ImageView btnAllowRecordFile = ViewBindings.findChildViewById(rootView, id);
      if (btnAllowRecordFile == null) {
        break missingId;
      }

      id = R.id.btn_allow_storage;
      ImageView btnAllowStorage = ViewBindings.findChildViewById(rootView, id);
      if (btnAllowStorage == null) {
        break missingId;
      }

      id = R.id.btn_go;
      TextView btnGo = ViewBindings.findChildViewById(rootView, id);
      if (btnGo == null) {
        break missingId;
      }

      id = R.id.btn_switch_notification;
      ImageView btnSwitchNotification = ViewBindings.findChildViewById(rootView, id);
      if (btnSwitchNotification == null) {
        break missingId;
      }

      id = R.id.img_permission;
      ImageView imgPermission = ViewBindings.findChildViewById(rootView, id);
      if (imgPermission == null) {
        break missingId;
      }

      id = R.id.layout_notification;
      ConstraintLayout layoutNotification = ViewBindings.findChildViewById(rootView, id);
      if (layoutNotification == null) {
        break missingId;
      }

      id = R.id.layout_record_file_audio;
      ConstraintLayout layoutRecordFileAudio = ViewBindings.findChildViewById(rootView, id);
      if (layoutRecordFileAudio == null) {
        break missingId;
      }

      id = R.id.layout_storage;
      ConstraintLayout layoutStorage = ViewBindings.findChildViewById(rootView, id);
      if (layoutStorage == null) {
        break missingId;
      }

      id = R.id.tv_app_name;
      TextView tvAppName = ViewBindings.findChildViewById(rootView, id);
      if (tvAppName == null) {
        break missingId;
      }

      id = R.id.tv_describe_per;
      TextView tvDescribePer = ViewBindings.findChildViewById(rootView, id);
      if (tvDescribePer == null) {
        break missingId;
      }

      return new ActivityPermissionBinding((ConstraintLayout) rootView, btnAllowRecordFile,
          btnAllowStorage, btnGo, btnSwitchNotification, imgPermission, layoutNotification,
          layoutRecordFileAudio, layoutStorage, tvAppName, tvDescribePer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
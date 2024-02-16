// Generated by view binder compiler. Do not edit!
package com.ntd.appid1.appid2.appid3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ntd.appid1.appid2.appid3.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogDeleteRecordBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView btnCancel;

  @NonNull
  public final TextView btnDelete;

  @NonNull
  public final ImageView ivDelete;

  @NonNull
  public final TextView tvContent;

  @NonNull
  public final TextView tvTitle;

  private DialogDeleteRecordBinding(@NonNull CardView rootView, @NonNull TextView btnCancel,
      @NonNull TextView btnDelete, @NonNull ImageView ivDelete, @NonNull TextView tvContent,
      @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.btnCancel = btnCancel;
    this.btnDelete = btnDelete;
    this.ivDelete = ivDelete;
    this.tvContent = tvContent;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogDeleteRecordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogDeleteRecordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_delete_record, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogDeleteRecordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnCancel;
      TextView btnCancel = ViewBindings.findChildViewById(rootView, id);
      if (btnCancel == null) {
        break missingId;
      }

      id = R.id.btnDelete;
      TextView btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.ivDelete;
      ImageView ivDelete = ViewBindings.findChildViewById(rootView, id);
      if (ivDelete == null) {
        break missingId;
      }

      id = R.id.tvContent;
      TextView tvContent = ViewBindings.findChildViewById(rootView, id);
      if (tvContent == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new DialogDeleteRecordBinding((CardView) rootView, btnCancel, btnDelete, ivDelete,
          tvContent, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
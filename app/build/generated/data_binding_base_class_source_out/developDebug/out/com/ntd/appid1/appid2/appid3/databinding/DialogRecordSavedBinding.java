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

public final class DialogRecordSavedBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imgRecordSaved;

  @NonNull
  public final ConstraintLayout layoutRecordSaved;

  @NonNull
  public final TextView tvRecordSaved;

  private DialogRecordSavedBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView imgRecordSaved, @NonNull ConstraintLayout layoutRecordSaved,
      @NonNull TextView tvRecordSaved) {
    this.rootView = rootView;
    this.imgRecordSaved = imgRecordSaved;
    this.layoutRecordSaved = layoutRecordSaved;
    this.tvRecordSaved = tvRecordSaved;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogRecordSavedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogRecordSavedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_record_saved, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogRecordSavedBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_record_saved;
      ImageView imgRecordSaved = ViewBindings.findChildViewById(rootView, id);
      if (imgRecordSaved == null) {
        break missingId;
      }

      id = R.id.layout_record_saved;
      ConstraintLayout layoutRecordSaved = ViewBindings.findChildViewById(rootView, id);
      if (layoutRecordSaved == null) {
        break missingId;
      }

      id = R.id.tv_record_saved;
      TextView tvRecordSaved = ViewBindings.findChildViewById(rootView, id);
      if (tvRecordSaved == null) {
        break missingId;
      }

      return new DialogRecordSavedBinding((ConstraintLayout) rootView, imgRecordSaved,
          layoutRecordSaved, tvRecordSaved);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ntd.appid1.appid2.appid3.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogSongListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imgClose;

  @NonNull
  public final RecyclerView rcvSongList;

  @NonNull
  public final TextView tvTitle;

  private DialogSongListBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView imgClose,
      @NonNull RecyclerView rcvSongList, @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.imgClose = imgClose;
    this.rcvSongList = rcvSongList;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogSongListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogSongListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_song_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogSongListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_close;
      ImageView imgClose = ViewBindings.findChildViewById(rootView, id);
      if (imgClose == null) {
        break missingId;
      }

      id = R.id.rcv_song_list;
      RecyclerView rcvSongList = ViewBindings.findChildViewById(rootView, id);
      if (rcvSongList == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new DialogSongListBinding((ConstraintLayout) rootView, imgClose, rcvSongList, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
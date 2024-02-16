// Generated by view binder compiler. Do not edit!
package com.ntd.appid1.appid2.appid3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ntd.appid1.appid2.appid3.R;
import com.ntd.appid1.appid2.appid3.piano_lib.view.NTDOtherPianoView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPianoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView btnBack;

  @NonNull
  public final ImageView btnDecrease;

  @NonNull
  public final ConstraintLayout btnDoubleKey;

  @NonNull
  public final ConstraintLayout btnHide;

  @NonNull
  public final ImageView btnIncrease;

  @NonNull
  public final ConstraintLayout btnListSong;

  @NonNull
  public final ImageView btnPlaySong;

  @NonNull
  public final ImageView btnRecord;

  @NonNull
  public final ImageView btnSetting;

  @NonNull
  public final ConstraintLayout btnSize;

  @NonNull
  public final ConstraintLayout btnStyle;

  @NonNull
  public final ConstraintLayout btnTwoPlayer;

  @NonNull
  public final ImageView icDoubleKey;

  @NonNull
  public final ImageView icDropdown;

  @NonNull
  public final ImageView icHide;

  @NonNull
  public final ImageView icSong;

  @NonNull
  public final ImageView icStyle;

  @NonNull
  public final ImageView icTwoPlayer;

  @NonNull
  public final ImageView imgSb;

  @NonNull
  public final RelativeLayout layoutFunction;

  @NonNull
  public final ConstraintLayout layoutLoading;

  @NonNull
  public final ConstraintLayout layoutParent;

  @NonNull
  public final ConstraintLayout layoutPianoControl;

  @NonNull
  public final ConstraintLayout layoutTop;

  @NonNull
  public final View line;

  @NonNull
  public final View lineDivide;

  @NonNull
  public final LinearLayout llTimeRecord;

  @NonNull
  public final ProgressBar progressPiano;

  @NonNull
  public final NTDOtherPianoView pv;

  @NonNull
  public final SeekBar sb;

  @NonNull
  public final SeekBar seekBarSpeed;

  @NonNull
  public final TextView tvSongName;

  @NonNull
  public final TextView tvTimeRecord;

  private ActivityPianoBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView btnBack,
      @NonNull ImageView btnDecrease, @NonNull ConstraintLayout btnDoubleKey,
      @NonNull ConstraintLayout btnHide, @NonNull ImageView btnIncrease,
      @NonNull ConstraintLayout btnListSong, @NonNull ImageView btnPlaySong,
      @NonNull ImageView btnRecord, @NonNull ImageView btnSetting,
      @NonNull ConstraintLayout btnSize, @NonNull ConstraintLayout btnStyle,
      @NonNull ConstraintLayout btnTwoPlayer, @NonNull ImageView icDoubleKey,
      @NonNull ImageView icDropdown, @NonNull ImageView icHide, @NonNull ImageView icSong,
      @NonNull ImageView icStyle, @NonNull ImageView icTwoPlayer, @NonNull ImageView imgSb,
      @NonNull RelativeLayout layoutFunction, @NonNull ConstraintLayout layoutLoading,
      @NonNull ConstraintLayout layoutParent, @NonNull ConstraintLayout layoutPianoControl,
      @NonNull ConstraintLayout layoutTop, @NonNull View line, @NonNull View lineDivide,
      @NonNull LinearLayout llTimeRecord, @NonNull ProgressBar progressPiano,
      @NonNull NTDOtherPianoView pv, @NonNull SeekBar sb, @NonNull SeekBar seekBarSpeed,
      @NonNull TextView tvSongName, @NonNull TextView tvTimeRecord) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.btnDecrease = btnDecrease;
    this.btnDoubleKey = btnDoubleKey;
    this.btnHide = btnHide;
    this.btnIncrease = btnIncrease;
    this.btnListSong = btnListSong;
    this.btnPlaySong = btnPlaySong;
    this.btnRecord = btnRecord;
    this.btnSetting = btnSetting;
    this.btnSize = btnSize;
    this.btnStyle = btnStyle;
    this.btnTwoPlayer = btnTwoPlayer;
    this.icDoubleKey = icDoubleKey;
    this.icDropdown = icDropdown;
    this.icHide = icHide;
    this.icSong = icSong;
    this.icStyle = icStyle;
    this.icTwoPlayer = icTwoPlayer;
    this.imgSb = imgSb;
    this.layoutFunction = layoutFunction;
    this.layoutLoading = layoutLoading;
    this.layoutParent = layoutParent;
    this.layoutPianoControl = layoutPianoControl;
    this.layoutTop = layoutTop;
    this.line = line;
    this.lineDivide = lineDivide;
    this.llTimeRecord = llTimeRecord;
    this.progressPiano = progressPiano;
    this.pv = pv;
    this.sb = sb;
    this.seekBarSpeed = seekBarSpeed;
    this.tvSongName = tvSongName;
    this.tvTimeRecord = tvTimeRecord;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPianoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPianoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_piano, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPianoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_back;
      ImageView btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.btn_decrease;
      ImageView btnDecrease = ViewBindings.findChildViewById(rootView, id);
      if (btnDecrease == null) {
        break missingId;
      }

      id = R.id.btn_double_key;
      ConstraintLayout btnDoubleKey = ViewBindings.findChildViewById(rootView, id);
      if (btnDoubleKey == null) {
        break missingId;
      }

      id = R.id.btn_hide;
      ConstraintLayout btnHide = ViewBindings.findChildViewById(rootView, id);
      if (btnHide == null) {
        break missingId;
      }

      id = R.id.btn_increase;
      ImageView btnIncrease = ViewBindings.findChildViewById(rootView, id);
      if (btnIncrease == null) {
        break missingId;
      }

      id = R.id.btn_list_song;
      ConstraintLayout btnListSong = ViewBindings.findChildViewById(rootView, id);
      if (btnListSong == null) {
        break missingId;
      }

      id = R.id.btn_play_song;
      ImageView btnPlaySong = ViewBindings.findChildViewById(rootView, id);
      if (btnPlaySong == null) {
        break missingId;
      }

      id = R.id.btn_record;
      ImageView btnRecord = ViewBindings.findChildViewById(rootView, id);
      if (btnRecord == null) {
        break missingId;
      }

      id = R.id.btn_setting;
      ImageView btnSetting = ViewBindings.findChildViewById(rootView, id);
      if (btnSetting == null) {
        break missingId;
      }

      id = R.id.btn_size;
      ConstraintLayout btnSize = ViewBindings.findChildViewById(rootView, id);
      if (btnSize == null) {
        break missingId;
      }

      id = R.id.btn_style;
      ConstraintLayout btnStyle = ViewBindings.findChildViewById(rootView, id);
      if (btnStyle == null) {
        break missingId;
      }

      id = R.id.btn_two_player;
      ConstraintLayout btnTwoPlayer = ViewBindings.findChildViewById(rootView, id);
      if (btnTwoPlayer == null) {
        break missingId;
      }

      id = R.id.ic_double_key;
      ImageView icDoubleKey = ViewBindings.findChildViewById(rootView, id);
      if (icDoubleKey == null) {
        break missingId;
      }

      id = R.id.ic_dropdown;
      ImageView icDropdown = ViewBindings.findChildViewById(rootView, id);
      if (icDropdown == null) {
        break missingId;
      }

      id = R.id.ic_hide;
      ImageView icHide = ViewBindings.findChildViewById(rootView, id);
      if (icHide == null) {
        break missingId;
      }

      id = R.id.ic_song;
      ImageView icSong = ViewBindings.findChildViewById(rootView, id);
      if (icSong == null) {
        break missingId;
      }

      id = R.id.ic_style;
      ImageView icStyle = ViewBindings.findChildViewById(rootView, id);
      if (icStyle == null) {
        break missingId;
      }

      id = R.id.ic_two_player;
      ImageView icTwoPlayer = ViewBindings.findChildViewById(rootView, id);
      if (icTwoPlayer == null) {
        break missingId;
      }

      id = R.id.img_sb;
      ImageView imgSb = ViewBindings.findChildViewById(rootView, id);
      if (imgSb == null) {
        break missingId;
      }

      id = R.id.layout_function;
      RelativeLayout layoutFunction = ViewBindings.findChildViewById(rootView, id);
      if (layoutFunction == null) {
        break missingId;
      }

      id = R.id.layout_loading;
      ConstraintLayout layoutLoading = ViewBindings.findChildViewById(rootView, id);
      if (layoutLoading == null) {
        break missingId;
      }

      ConstraintLayout layoutParent = (ConstraintLayout) rootView;

      id = R.id.layout_piano_control;
      ConstraintLayout layoutPianoControl = ViewBindings.findChildViewById(rootView, id);
      if (layoutPianoControl == null) {
        break missingId;
      }

      id = R.id.layout_top;
      ConstraintLayout layoutTop = ViewBindings.findChildViewById(rootView, id);
      if (layoutTop == null) {
        break missingId;
      }

      id = R.id.line;
      View line = ViewBindings.findChildViewById(rootView, id);
      if (line == null) {
        break missingId;
      }

      id = R.id.line_divide;
      View lineDivide = ViewBindings.findChildViewById(rootView, id);
      if (lineDivide == null) {
        break missingId;
      }

      id = R.id.ll_time_record;
      LinearLayout llTimeRecord = ViewBindings.findChildViewById(rootView, id);
      if (llTimeRecord == null) {
        break missingId;
      }

      id = R.id.progress_piano;
      ProgressBar progressPiano = ViewBindings.findChildViewById(rootView, id);
      if (progressPiano == null) {
        break missingId;
      }

      id = R.id.pv;
      NTDOtherPianoView pv = ViewBindings.findChildViewById(rootView, id);
      if (pv == null) {
        break missingId;
      }

      id = R.id.sb;
      SeekBar sb = ViewBindings.findChildViewById(rootView, id);
      if (sb == null) {
        break missingId;
      }

      id = R.id.seekBarSpeed;
      SeekBar seekBarSpeed = ViewBindings.findChildViewById(rootView, id);
      if (seekBarSpeed == null) {
        break missingId;
      }

      id = R.id.tv_song_name;
      TextView tvSongName = ViewBindings.findChildViewById(rootView, id);
      if (tvSongName == null) {
        break missingId;
      }

      id = R.id.tv_time_record;
      TextView tvTimeRecord = ViewBindings.findChildViewById(rootView, id);
      if (tvTimeRecord == null) {
        break missingId;
      }

      return new ActivityPianoBinding((ConstraintLayout) rootView, btnBack, btnDecrease,
          btnDoubleKey, btnHide, btnIncrease, btnListSong, btnPlaySong, btnRecord, btnSetting,
          btnSize, btnStyle, btnTwoPlayer, icDoubleKey, icDropdown, icHide, icSong, icStyle,
          icTwoPlayer, imgSb, layoutFunction, layoutLoading, layoutParent, layoutPianoControl,
          layoutTop, line, lineDivide, llTimeRecord, progressPiano, pv, sb, seekBarSpeed,
          tvSongName, tvTimeRecord);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
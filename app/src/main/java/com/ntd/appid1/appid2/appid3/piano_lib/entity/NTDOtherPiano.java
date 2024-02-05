package com.ntd.appid1.appid2.appid3.piano_lib.entity;

/**
 * Created by ChengTao on 2016-11-25.
 */

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.util.Log;
import android.view.Gravity;

import androidx.core.content.ContextCompat;

import com.google.gson.annotations.SerializedName;
import com.ntd.appid1.appid2.appid3.R;
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils;
import com.ntd.appid1.appid2.appid3.utils.Constants;

import java.util.ArrayList;

/**
 * 钢琴实体
 */
public class NTDOtherPiano {
  //钢琴键数目
  public final static int PIANO_NUMS = 88;
  //黑白键的组数
  private final static int BLACK_PIANO_KEY_GROUPS = 8;
  private final static int WHITE_PIANO_KEY_GROUPS = 9;
  //黑白键集合
  private ArrayList<NTDOtherPianoKey[]> blackPianoKeys = new ArrayList<>(BLACK_PIANO_KEY_GROUPS);
  private ArrayList<NTDOtherPianoKey[]> whitePianoKeys = new ArrayList<>(WHITE_PIANO_KEY_GROUPS);
  //黑白键高度和宽度
  private int blackKeyWidth;
  private int blackKeyHeight;
  private int whiteKeyWidth;
  private int whiteKeyHeight;
  //钢琴总宽度
  private int pianoWith = 0;
  /**
   * 承载钢琴的布局的高度,用于初始化黑白键的高度和宽度
   */
  private float scale = 0;
  //上下文
  private Context context;
  public static int blackDrawable = R.drawable.black_piano_key;
  public static int whiteDrawable = R.drawable.white_piano_key;
  private boolean cOrD0 = false;

  public boolean iscOrD0() {
    return cOrD0;
  }

  public void setcOrD0(boolean cOrD0) {
    this.cOrD0 = cOrD0;
  }

  //构造函数
  public NTDOtherPiano(Context context, float scale, boolean cOrD0) {
    this.context = context;
    this.scale = scale;
    this.cOrD0 = cOrD0;
    initPiano();
  }

  public void initPiano() {
    if (scale > 0) {
      //获取黑键和白键的高度和宽度
      Drawable blackDrawable = ContextCompat.getDrawable(context, this.blackDrawable);
      Drawable whiteDrawable = ContextCompat.getDrawable(context, this.whiteDrawable);

      int pianoKeySize = NTDHelperSharePrefUtils.getInt(NTDHelperSharePrefUtils.PIANO_KEY_SIZE, 0);
      double scaleAdditional = 0;

      switch (pianoKeySize) {
        case Constants.DEFAULT_SIZE:
          scaleAdditional = 1;
          break;
        case Constants.BIG_SIZE_1:
          scaleAdditional = 1.1;
          break;
        case Constants.BIG_SIZE_2:
          scaleAdditional = 1.2;
          break;
        case Constants.BIG_SIZE_3:
          scaleAdditional = 1.3;
          break;
        case Constants.BIG_SIZE_4:
          scaleAdditional = 1.4;
          break;
        case Constants.BIG_SIZE_5:
          scaleAdditional = 1.5;
          break;
      }
      blackKeyWidth = (int) ((double) blackDrawable.getIntrinsicWidth() * scaleAdditional);
      blackKeyHeight = (int) ((float) blackDrawable.getIntrinsicHeight() * scale);
      whiteKeyWidth = (int) ((double) whiteDrawable.getIntrinsicWidth() * scaleAdditional);
      whiteKeyHeight = (int) ((float) whiteDrawable.getIntrinsicHeight() * scale);

      //初始化黑键
      for (int i = 0; i < BLACK_PIANO_KEY_GROUPS; i++) {
        NTDOtherPianoKey keys[];
        switch (i) {
          case 0:
            keys = new NTDOtherPianoKey[1];
            break;
          default:
            keys = new NTDOtherPianoKey[5];
            break;
        }
        for (int j = 0; j < keys.length; j++) {
          keys[j] = new NTDOtherPianoKey();
          Rect areaOfKey[] = new Rect[1];
          keys[j].setType(PianoKeyType.BLACK);
          keys[j].setGroup(i);
          keys[j].setPositionOfGroup(j);
          keys[j].setVoiceId(getVoiceFromResources("b" + i + j));
          keys[j].setPressed(false);
          keys[j].setKeyDrawable(
              new ScaleDrawable(ContextCompat.getDrawable(context, this.blackDrawable),
                  Gravity.NO_GRAVITY, 1, scale).getDrawable());
          setBlackKeyDrawableBounds(i, j, keys[j].getKeyDrawable());
          areaOfKey[0] = keys[j].getKeyDrawable().getBounds();
          keys[j].setAreaOfKey(areaOfKey);
          if (i == 0) {
            keys[j].setVoice(PianoVoice.LA);
            break;
          }
          switch (j) {
            case 0:
              keys[j].setVoice(PianoVoice.DO);
              break;
            case 1:
              keys[j].setVoice(PianoVoice.RE);
              break;
            case 2:
              keys[j].setVoice(PianoVoice.FA);
              break;
            case 3:
              keys[j].setVoice(PianoVoice.SO);
              break;
            case 4:
              keys[j].setVoice(PianoVoice.LA);
              break;
          }
        }
        blackPianoKeys.add(keys);
      }
      //初始化白键
      if (!iscOrD0()) {
        whitePianoKeys.clear();
        for (int i = 0; i < WHITE_PIANO_KEY_GROUPS; i++) {
          NTDOtherPianoKey[] mKeys;
          switch (i) {
            case 0:
              mKeys = new NTDOtherPianoKey[2];
              break;
            case 8:
              mKeys = new NTDOtherPianoKey[1];
              break;
            default:
              mKeys = new NTDOtherPianoKey[7];
              break;
          }
          for (int j = 0; j < mKeys.length; j++) {
            mKeys[j] = new NTDOtherPianoKey();
            //固定属性
            mKeys[j].setType(PianoKeyType.WHITE);
            mKeys[j].setGroup(i);
            mKeys[j].setPositionOfGroup(j);
            mKeys[j].setVoiceId(getVoiceFromResources("w" + i + j));
            mKeys[j].setPressed(false);
            mKeys[j].setKeyDrawable(
                    new ScaleDrawable(ContextCompat.getDrawable(context, this.whiteDrawable),
                            Gravity.NO_GRAVITY, 1, scale).getDrawable());
            setWhiteKeyDrawableBounds(i, j, mKeys[j].getKeyDrawable());
            pianoWith += whiteKeyWidth;
            if (i == 0) {
              switch (j) {
                case 0:
                  mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.RIGHT));
                  mKeys[j].setVoice(PianoVoice.LA);
                  mKeys[j].setLetterName("A0");
                  break;
                case 1:
                  mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT));
                  mKeys[j].setVoice(PianoVoice.SI);
                  mKeys[j].setLetterName("B0");
                  break;
              }
              continue;
            }
            if (i == 8) {
              Rect areaOfKey[] = new Rect[1];
              areaOfKey[0] = mKeys[j].getKeyDrawable().getBounds();
              mKeys[j].setAreaOfKey(areaOfKey);
              mKeys[j].setVoice(PianoVoice.DO);
              mKeys[j].setLetterName("C8");
              break;
            }
            //非固定属性
            switch (j) {
              case 0:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.RIGHT));
                mKeys[j].setVoice(PianoVoice.DO);
                mKeys[j].setLetterName("C" + i);
                break;
              case 1:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT_RIGHT));
                mKeys[j].setVoice(PianoVoice.RE);
                mKeys[j].setLetterName("D" + i);
                break;
              case 2:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT));
                mKeys[j].setVoice(PianoVoice.MI);
                mKeys[j].setLetterName("E" + i);
                break;
              case 3:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.RIGHT));
                mKeys[j].setVoice(PianoVoice.FA);
                mKeys[j].setLetterName("F" + i);
                break;
              case 4:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT_RIGHT));
                mKeys[j].setVoice(PianoVoice.SO);
                mKeys[j].setLetterName("G" + i);
                break;
              case 5:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT_RIGHT));
                mKeys[j].setVoice(PianoVoice.LA);
                mKeys[j].setLetterName("A" + i);
                break;
              case 6:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT));
                mKeys[j].setVoice(PianoVoice.SI);
                mKeys[j].setLetterName("B" + i);
                break;
            }
          }
          whitePianoKeys.add(mKeys);
        }
      } else {
        Log.e("TAG", "here: ");
        whitePianoKeys.clear();
        for (int i = 0; i < WHITE_PIANO_KEY_GROUPS; i++) {
          NTDOtherPianoKey[] mKeys;
          switch (i) {
            case 0:
              mKeys = new NTDOtherPianoKey[2];
              break;
            case 8:
              mKeys = new NTDOtherPianoKey[1];
              break;
            default:
              mKeys = new NTDOtherPianoKey[7];
              break;
          }
          for (int j = 0; j < mKeys.length; j++) {
            mKeys[j] = new NTDOtherPianoKey();
            //固定属性
            mKeys[j].setType(PianoKeyType.WHITE);
            mKeys[j].setGroup(i);
            mKeys[j].setPositionOfGroup(j);
            mKeys[j].setVoiceId(getVoiceFromResources("w" + i + j));
            mKeys[j].setPressed(false);
            mKeys[j].setKeyDrawable(
                    new ScaleDrawable(ContextCompat.getDrawable(context, this.whiteDrawable),
                            Gravity.NO_GRAVITY, 1, scale).getDrawable());
            setWhiteKeyDrawableBounds(i, j, mKeys[j].getKeyDrawable());
            pianoWith += whiteKeyWidth;
            if (i == 0) {
              switch (j) {
                case 0:
                  mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.RIGHT));
                  mKeys[j].setVoice(PianoVoice.LA);
                  mKeys[j].setLetterName("LA0");
                  break;
                case 1:
                  mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT));
                  mKeys[j].setVoice(PianoVoice.SI);
                  mKeys[j].setLetterName("Si0");
                  break;
              }
              continue;
            }
            if (i == 8) {
              Rect areaOfKey[] = new Rect[1];
              areaOfKey[0] = mKeys[j].getKeyDrawable().getBounds();
              mKeys[j].setAreaOfKey(areaOfKey);
              mKeys[j].setVoice(PianoVoice.DO);
              mKeys[j].setLetterName("Do8");
              break;
            }
            //非固定属性
            switch (j) {
              case 0:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.RIGHT));
                mKeys[j].setVoice(PianoVoice.DO);
                mKeys[j].setLetterName("Do" + i);
                break;
              case 1:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT_RIGHT));
                mKeys[j].setVoice(PianoVoice.RE);
                mKeys[j].setLetterName("Re" + i);
                break;
              case 2:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT));
                mKeys[j].setVoice(PianoVoice.MI);
                mKeys[j].setLetterName("Mi" + i);
                break;
              case 3:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.RIGHT));
                mKeys[j].setVoice(PianoVoice.FA);
                mKeys[j].setLetterName("Fa" + i);
                break;
              case 4:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT_RIGHT));
                mKeys[j].setVoice(PianoVoice.SO);
                mKeys[j].setLetterName("Sol" + i);
                break;
              case 5:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT_RIGHT));
                mKeys[j].setVoice(PianoVoice.LA);
                mKeys[j].setLetterName("La" + i);
                break;
              case 6:
                mKeys[j].setAreaOfKey(getWhitePianoKeyArea(i, j, BlackKeyPosition.LEFT));
                mKeys[j].setVoice(PianoVoice.SI);
                mKeys[j].setLetterName("Si" + i);
                break;
            }
          }
          whitePianoKeys.add(mKeys);
        }
        for (int i = 0; i < whitePianoKeys.size(); i++) {
          Log.d("ABS", "list  w piano :  "+whitePianoKeys.get(i).length);
          for (int j = 0; j < whitePianoKeys.get(i).length; j++) {
            Log.d("ABS", "data w piano :  "+whitePianoKeys.get(i)[j].getLetterName());
          }
        }
      }
    }
  }

  public enum PianoVoice {
    DO, RE, MI, FA, SO, LA, SI
  }

  public enum PianoKeyType {
    @SerializedName("0")
    BLACK(0), @SerializedName("1")
    WHITE(1);
    private int value;

    PianoKeyType(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    @Override public String toString() {
      return "PianoKeyType{" + "value=" + value + '}';
    }
  }

  private enum BlackKeyPosition {
    LEFT, LEFT_RIGHT, RIGHT
  }

  /**
   * 从资源文件中获取声音ID
   *
   * @param voiceName 声音的文件名
   * @return 声音ID
   */
  private int getVoiceFromResources(String voiceName) {
    int voiceResource = R.raw.b00;
    switch (voiceName) {
      case "b00":
        voiceResource = R.raw.b00;
        break;
      case "b10":
        voiceResource = R.raw.b10;
        break;
      case "b11":
        voiceResource = R.raw.b11;
        break;
      case "b12":
        voiceResource = R.raw.b12;
        break;
      case "b13":
        voiceResource = R.raw.b13;
        break;
      case "b14":
        voiceResource = R.raw.b14;
        break;
      case "b20":
        voiceResource = R.raw.b20;
        break;
      case "b21":
        voiceResource = R.raw.b21;
        break;
      case "b22":
        voiceResource = R.raw.b22;
        break;
      case "b23":
        voiceResource = R.raw.b23;
        break;
      case "b24":
        voiceResource = R.raw.b24;
        break;
      case "b30":
        voiceResource = R.raw.b30;
        break;
      case "b31":
        voiceResource = R.raw.b31;
        break;
      case "b32":
        voiceResource = R.raw.b32;
        break;
      case "b33":
        voiceResource = R.raw.b33;
        break;
      case "b34":
        voiceResource = R.raw.b34;
        break;
      case "b40":
        voiceResource = R.raw.b40;
        break;
      case "b41":
        voiceResource = R.raw.b41;
        break;
      case "b42":
        voiceResource = R.raw.b42;
        break;
      case "b43":
        voiceResource = R.raw.b43;
        break;
      case "b44":
        voiceResource = R.raw.b44;
        break;
      case "b50":
        voiceResource = R.raw.b50;
        break;
      case "b51":
        voiceResource = R.raw.b51;
        break;
      case "b52":
        voiceResource = R.raw.b52;
        break;
      case "b53":
        voiceResource = R.raw.b53;
        break;
      case "b54":
        voiceResource = R.raw.b54;
        break;
      case "b60":
        voiceResource = R.raw.b60;
        break;
      case "b61":
        voiceResource = R.raw.b61;
        break;
      case "b62":
        voiceResource = R.raw.b62;
        break;
      case "b63":
        voiceResource = R.raw.b63;
        break;
      case "b64":
        voiceResource = R.raw.b64;
        break;
      case "b70":
        voiceResource = R.raw.b70;
        break;
      case "b71":
        voiceResource = R.raw.b71;
        break;
      case "b72":
        voiceResource = R.raw.b72;
        break;
      case "b73":
        voiceResource = R.raw.b73;
        break;
      case "b74":
        voiceResource = R.raw.b74;
        break;
      case "w00":
        voiceResource = R.raw.w00;
        break;
      case "w01":
        voiceResource = R.raw.w01;
        break;
      case "w10":
        voiceResource = R.raw.w10;
        break;
      case "w11":
        voiceResource = R.raw.w11;
        break;
      case "w12":
        voiceResource = R.raw.w12;
        break;
      case "w13":
        voiceResource = R.raw.w13;
        break;
      case "w14":
        voiceResource = R.raw.w14;
        break;
      case "w15":
        voiceResource = R.raw.w15;
        break;
      case "w16":
        voiceResource = R.raw.w16;
        break;
      case "w20":
        voiceResource = R.raw.w20;
        break;
      case "w21":
        voiceResource = R.raw.w21;
        break;
      case "w22":
        voiceResource = R.raw.w22;
        break;
      case "w23":
        voiceResource = R.raw.w23;
        break;
      case "w24":
        voiceResource = R.raw.w24;
        break;
      case "w25":
        voiceResource = R.raw.w25;
        break;
      case "w26":
        voiceResource = R.raw.w26;
        break;
      case "w30":
        voiceResource = R.raw.w30;
        break;
      case "w31":
        voiceResource = R.raw.w31;
        break;
      case "w32":
        voiceResource = R.raw.w32;
        break;
      case "w33":
        voiceResource = R.raw.w33;
        break;
      case "w34":
        voiceResource = R.raw.w34;
        break;
      case "w35":
        voiceResource = R.raw.w35;
        break;
      case "w36":
        voiceResource = R.raw.w36;
        break;
      case "w40":
        voiceResource = R.raw.w40;
        break;
      case "w41":
        voiceResource = R.raw.w41;
        break;
      case "w42":
        voiceResource = R.raw.w42;
        break;
      case "w43":
        voiceResource = R.raw.w43;
        break;
      case "w44":
        voiceResource = R.raw.w44;
        break;
      case "w45":
        voiceResource = R.raw.w45;
        break;
      case "w46":
        voiceResource = R.raw.w46;
        break;
      case "w50":
        voiceResource = R.raw.w50;
        break;
      case "w51":
        voiceResource = R.raw.w51;
        break;
      case "w52":
        voiceResource = R.raw.w52;
        break;
      case "w53":
        voiceResource = R.raw.w53;
        break;
      case "w54":
        voiceResource = R.raw.w54;
        break;
      case "w55":
        voiceResource = R.raw.w55;
        break;
      case "w56":
        voiceResource = R.raw.w56;
        break;
      case "w60":
        voiceResource = R.raw.w60;
        break;
      case "w61":
        voiceResource = R.raw.w61;
        break;
      case "w62":
        voiceResource = R.raw.w62;
        break;
      case "w63":
        voiceResource = R.raw.w63;
        break;
      case "w64":
        voiceResource = R.raw.w64;
        break;
      case "w65":
        voiceResource = R.raw.w65;
        break;
      case "w66":
        voiceResource = R.raw.w66;
        break;
      case "w70":
        voiceResource = R.raw.w70;
        break;
      case "w71":
        voiceResource = R.raw.w71;
        break;
      case "w72":
        voiceResource = R.raw.w72;
        break;
      case "w73":
        voiceResource = R.raw.w73;
        break;
      case "w74":
        voiceResource = R.raw.w74;
        break;
      case "w75":
        voiceResource = R.raw.w75;
        break;
      case "w76":
        voiceResource = R.raw.w76;
        break;
      case "w80":
        voiceResource = R.raw.w80;
        break;
    }
    return voiceResource;
  }

  /**
   * 设置白色键的点击区域
   *
   * @param group 组数，从0开始
   * @param positionOfGroup 本组数内的位置
   * @param blackKeyPosition 黑键占白键的位置
   * @return 矩形数组
   */
  private Rect[] getWhitePianoKeyArea(int group, int positionOfGroup,
      BlackKeyPosition blackKeyPosition) {
    int offset = 0;
    if (group == 0) {
      offset = 5;
    }
    switch (blackKeyPosition) {
      case LEFT:
        Rect left[] = new Rect[2];
        left[0] =
            new Rect((7 * group - 5 + offset + positionOfGroup) * whiteKeyWidth, blackKeyHeight,
                (7 * group - 5 + offset + positionOfGroup) * whiteKeyWidth + blackKeyWidth / 2,
                whiteKeyHeight);
        left[1] =
            new Rect((7 * group - 5 + offset + positionOfGroup) * whiteKeyWidth + blackKeyWidth / 2,
                0, (7 * group - 4 + offset + positionOfGroup) * whiteKeyWidth, whiteKeyHeight);
        return left;
      case LEFT_RIGHT:
        Rect leftRight[] = new Rect[3];
        leftRight[0] =
            new Rect((7 * group - 5 + offset + positionOfGroup) * whiteKeyWidth, blackKeyHeight,
                (7 * group - 5 + offset + positionOfGroup) * whiteKeyWidth + blackKeyWidth / 2,
                whiteKeyHeight);
        leftRight[1] =
            new Rect((7 * group - 5 + offset + positionOfGroup) * whiteKeyWidth + blackKeyWidth / 2,
                0, (7 * group - 4 + offset + positionOfGroup) * whiteKeyWidth - blackKeyWidth / 2,
                whiteKeyHeight);
        leftRight[2] =
            new Rect((7 * group - 4 + offset + positionOfGroup) * whiteKeyWidth - blackKeyWidth / 2,
                blackKeyHeight, (7 * group - 4 + offset + positionOfGroup) * whiteKeyWidth,
                whiteKeyHeight);
        return leftRight;
      case RIGHT:
        Rect right[] = new Rect[2];
        right[0] = new Rect((7 * group - 5 + offset + positionOfGroup) * whiteKeyWidth, 0,
            (7 * group - 4 + offset + positionOfGroup) * whiteKeyWidth - blackKeyWidth / 2,
            whiteKeyHeight);
        right[1] =
            new Rect((7 * group - 4 + offset + positionOfGroup) * whiteKeyWidth - blackKeyWidth / 2,
                blackKeyHeight, (7 * group - 4 + offset + positionOfGroup) * whiteKeyWidth,
                whiteKeyHeight);
        return right;
    }
    return null;
  }

  /**
   * 设置白色键图案的位置
   *
   * @param group 组数，从0开始
   * @param positionOfGroup 在本组中的位置
   * @param drawable 要设置的Drawale对象
   */
  private void setWhiteKeyDrawableBounds(int group, int positionOfGroup, Drawable drawable) {
    int offset = 0;
    if (group == 0) {
      offset = 5;
    }
    drawable.setBounds((7 * group - 5 + offset + positionOfGroup) * whiteKeyWidth, 0,
        (7 * group - 4 + offset + positionOfGroup) * whiteKeyWidth, whiteKeyHeight);
  }

  /**
   * 设置黑色键图案的位置
   *
   * @param group 组数，从0开始
   * @param positionOfGroup 组内的位置
   * @param drawable 要设置的Drawale对象
   */
  private void setBlackKeyDrawableBounds(int group, int positionOfGroup, Drawable drawable) {
    int whiteOffset = 0;
    int blackOffset = 0;
    if (group == 0) {
      whiteOffset = 5;
    }
    if (positionOfGroup == 2 || positionOfGroup == 3 || positionOfGroup == 4) {
      blackOffset = 1;
    }
    drawable.setBounds((7 * group - 4 + whiteOffset + blackOffset + positionOfGroup) * whiteKeyWidth
            - blackKeyWidth / 2, 0,
        (7 * group - 4 + whiteOffset + blackOffset + positionOfGroup) * whiteKeyWidth
            + blackKeyWidth / 2, blackKeyHeight);
  }

  public ArrayList<NTDOtherPianoKey[]> getWhitePianoKeys() {
    return whitePianoKeys;
  }

  public ArrayList<NTDOtherPianoKey[]> getBlackPianoKeys() {
    return blackPianoKeys;
  }

  public int getPianoWith() {
    return pianoWith;
  }
}

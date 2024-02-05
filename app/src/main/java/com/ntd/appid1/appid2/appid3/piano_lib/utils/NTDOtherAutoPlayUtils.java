package com.ntd.appid1.appid2.appid3.piano_lib.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ntd.appid1.appid2.appid3.piano_lib.entity.NTDOtherAutoPlayEntity;
import com.ntd.appid1.appid2.appid3.piano_lib.entity.NTDOtherPiano;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 11/3/17
 * Time : 10:17 AM
 * Description :
 */

@SuppressWarnings("unchecked") public class NTDOtherAutoPlayUtils {
  public static final Gson gson = new GsonBuilder().create();

  public static ArrayList<NTDOtherAutoPlayEntity> getAutoPlayEntityListByJsonString(
      String configJsonString) {
    if (!TextUtils.isEmpty(configJsonString)) {
      try {
        return gson.fromJson(configJsonString, new TypeToken<List<NTDOtherAutoPlayEntity>>() {
        }.getType());
      } catch (Exception e) {
        Log.e("TAG", "AutoPlayUtils-->" + e.getMessage());
      }
    }
    return null;
  }

  public static ArrayList<NTDOtherAutoPlayEntity> getAutoPlayEntityListJsonStream(
      InputStream configJsonStream) {
    if (configJsonStream != null) {
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(configJsonStream));
        ArrayList<NTDOtherAutoPlayEntity> list =
            gson.fromJson(reader, new TypeToken<ArrayList<NTDOtherAutoPlayEntity>>() {
            }.getType());
        reader.close();
        return list;
      } catch (Exception e) {
        Log.e("TAG", "AutoPlayUtils-->" + e.getMessage());
      }
    }
    return null;
  }

  public static ArrayList<NTDOtherAutoPlayEntity> getAutoPlayEntityListByCustomConfigString(
      String customConfigString) {
    try {
      Object[] result = NTDOtherPianoConvertUtils.convertByConfigString(customConfigString);
      return convertToAutoPlayEntityList((List<NTDOtherPianoConvertUtils.PianoKey>) result[2]);
    } catch (Throwable throwable) {
      Log.e("TAG", throwable.getMessage());
    }
    return null;
  }

  public static ArrayList<NTDOtherAutoPlayEntity> getAutoPlayEntityListByCustomConfigInputStream(
      InputStream customConfigInputStream) {
    try {
      Object[] result = NTDOtherPianoConvertUtils.convertByInputStream(customConfigInputStream);
      return convertToAutoPlayEntityList((List<NTDOtherPianoConvertUtils.PianoKey>) result[2]);
    } catch (Throwable throwable) {
      Log.e("TAG", throwable.getMessage());
    }
    return null;
  }

  public static ArrayList<NTDOtherAutoPlayEntity> convertToAutoPlayEntityList(
      List<NTDOtherPianoConvertUtils.PianoKey> keyList) {
    if (keyList != null && keyList.size() > 0) {
      ArrayList<NTDOtherAutoPlayEntity> list = new ArrayList<>();
      for (NTDOtherPianoConvertUtils.PianoKey key : keyList) {
        if (key != null) {
          NTDOtherAutoPlayEntity entity = new NTDOtherAutoPlayEntity();
          if (key.getType() == NTDOtherPianoConvertUtils.PianoKey.BLACK_KEY) {
            entity.setType(NTDOtherPiano.PianoKeyType.BLACK);
          } else if (key.getType() == NTDOtherPianoConvertUtils.PianoKey.WHITE_KEY) {
            entity.setType(NTDOtherPiano.PianoKeyType.WHITE);
          }
          entity.setCurrentBreakTime(key.getFrequency());
          entity.setGroup(key.getGroup());
          entity.setPosition(key.getPosition());
          list.add(entity);
        }
      }
      return list;
    }
    return null;
  }
}

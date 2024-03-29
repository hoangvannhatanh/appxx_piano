package com.ntd.appid1.appid2.appid3.piano_lib.utils;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseIntArray;

import com.ntd.appid1.appid2.appid3.piano_lib.entity.NTDOtherPiano;
import com.ntd.appid1.appid2.appid3.piano_lib.entity.NTDOtherPianoKey;
import com.ntd.appid1.appid2.appid3.piano_lib.listener.NTDOtherLoadAudioMessage;
import com.ntd.appid1.appid2.appid3.piano_lib.listener.NTDOtherOnLoadAudioListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ChengTao on 2016-11-26.
 */

/**
 * 音频工具类
 */
public class NTDOtherAudioUtils implements NTDOtherLoadAudioMessage {
  //线程池,用于加载和播放音频
  private ExecutorService service = Executors.newCachedThreadPool();
  //最大音频数目
  private final static int MAX_STREAM = 11;
  private static NTDOtherAudioUtils instance = null;
  //消息ID
  private final static int LOAD_START = 1;
  private final static int LOAD_FINISH = 2;
  private final static int LOAD_ERROR = 3;
  private final static int LOAD_PROGRESS = 4;
  //发送进度的间隙时间
  private final static int SEND_PROGRESS_MESSAGE_BREAK_TIME = 500;
  //音频池，用于播放音频
  private SoundPool pool;
  //上下文
  private Context context;
  //加载音频接口
  private NTDOtherOnLoadAudioListener loadAudioListener;
  //存放黑键和白键的音频加载后的ID的集合
  private SparseIntArray whiteKeyMusics = new SparseIntArray();
  private SparseIntArray blackKeyMusics = new SparseIntArray();
  //是否加载成功
  private boolean isLoadFinish = false;
  //是否正在加载
  private boolean isLoading = false;
  //用于处理进度消息
  private Handler handler;
  private AudioManager audioManager;
  private long currentTime;
  private int loadNum;
  private int minSoundId = -1;
  private int maxSoundId = -1;

  private NTDOtherAudioUtils(Context context, NTDOtherOnLoadAudioListener loadAudioListener, int maxStream) {
    this.context = context;
    this.loadAudioListener = loadAudioListener;
    handler = new AudioStatusHandler(context.getMainLooper());
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      pool = new SoundPool.Builder().setMaxStreams(maxStream)
          .setAudioAttributes(
              new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                  .setUsage(AudioAttributes.USAGE_MEDIA)
                  .build())
          .build();
    } else {
      pool = new SoundPool(maxStream, AudioManager.STREAM_MUSIC, 1);
    }
    audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
  }

  //单例模式，只返回一个工具实例
  public static NTDOtherAudioUtils getInstance(Context context, NTDOtherOnLoadAudioListener listener) {
    return getInstance(context, listener, MAX_STREAM);
  }

  public static NTDOtherAudioUtils getInstance(Context context, NTDOtherOnLoadAudioListener listener,
                                               int maxStream) {
    if (instance == null || instance.pool == null) {
      synchronized (NTDOtherAudioUtils.class) {
        if (instance == null || instance.pool == null) {
          instance = new NTDOtherAudioUtils(context, listener, maxStream);
        }
      }
    }
    return instance;
  }

  /**
   * 加载音乐
   *
   * @param piano 钢琴实体
   * @throws Exception 异常
   */
  public void loadMusic(final NTDOtherPiano piano) throws Exception {
    if (pool == null) {
      throw new Exception("请初始化SoundPool");
    }
    if (piano != null) {
      if (!isLoading && !isLoadFinish) {
        isLoading = true;
        pool.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
          loadNum++;
          if (loadNum == NTDOtherPiano.PIANO_NUMS) {
            isLoadFinish = true;
            sendProgressMessage(100);
            sendFinishMessage();
            //静音播放一个音频,防止延时
            pool.play(whiteKeyMusics.get(0), 0, 0, 1, -1, 2f);
          } else {
            if (System.currentTimeMillis() - currentTime >= SEND_PROGRESS_MESSAGE_BREAK_TIME) {
              sendProgressMessage((int) (((float) loadNum / (float) NTDOtherPiano.PIANO_NUMS) * 100f));
              currentTime = System.currentTimeMillis();
            }
          }
        });
        service.execute(() -> {
          sendStartMessage();
          ArrayList<NTDOtherPianoKey[]> whiteKeys = piano.getWhitePianoKeys();
          int whiteKeyPos = 0;
          for (int i = 0; i < whiteKeys.size(); i++) {
            for (NTDOtherPianoKey key : whiteKeys.get(i)) {
              try {
                int soundID = pool.load(context, key.getVoiceId(), 1);
                whiteKeyMusics.put(whiteKeyPos, soundID);
                if (minSoundId == -1) {
                  minSoundId = soundID;
                }
                whiteKeyPos++;
              } catch (Exception e) {
                isLoading = false;
                sendErrorMessage(e);
                return;
              }
            }
          }
          ArrayList<NTDOtherPianoKey[]> blackKeys = piano.getBlackPianoKeys();
          int blackKeyPos = 0;
          for (int i = 0; i < blackKeys.size(); i++) {
            for (NTDOtherPianoKey key : blackKeys.get(i)) {
              try {
                int soundID = pool.load(context, key.getVoiceId(), 1);
                blackKeyMusics.put(blackKeyPos, soundID);
                blackKeyPos++;
                if (soundID > maxSoundId) {
                  maxSoundId = soundID;
                }
              } catch (Exception e) {
                isLoading = false;
                sendErrorMessage(e);
                return;
              }
            }
          }
        });
      }
    }
  }

  /**
   * 播放音乐
   *
   * @param key 钢琴键
   */
  public void playMusic(final NTDOtherPianoKey key) {
    if (key != null) {
      if (isLoadFinish) {
        if (key.getType() != null) {
          service.execute(() -> {
            switch (key.getType()) {
              case BLACK:
                playBlackKeyMusic(key.getGroup(), key.getPositionOfGroup());
                break;
              case WHITE:
                playWhiteKeyMusic(key.getGroup(), key.getPositionOfGroup());
                break;
            }
          });
        }
      }
    }
  }

  /**
   * 播放白键音乐
   *
   * @param group 组数，从0开始
   * @param positionOfGroup 组内位置
   */
  private void playWhiteKeyMusic(int group, int positionOfGroup) {
    int position;
    if (group == 0) {
      position = positionOfGroup;
    } else {
      position = (group - 1) * 7 + 2 + positionOfGroup;
    }
    if (whiteKeyMusics != null) {
      play(whiteKeyMusics.get(position));
    }
  }

  /**
   * 播放黑键音乐
   *
   * @param group 组数，从0开始
   * @param positionOfGroup 组内位置
   */
  private void playBlackKeyMusic(int group, int positionOfGroup) {
    int position;
    if (group == 0) {
      position = positionOfGroup;
    } else {
      position = (group - 1) * 5 + 1 + positionOfGroup;
    }
    if (blackKeyMusics != null) {
      play(blackKeyMusics.get(position));
    }
  }

  private void play(int soundId) {
    float volume = 1;
    if (audioManager != null) {
      float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
      float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
      volume = actualVolume / maxVolume;
    }
    if (volume <= 0) {
      volume = 1f;
    }
    pool.play(soundId, volume, volume, 1, 0, 1f);
  }

  /**
   * 结束
   */
  public void stop() {
    context = null;
    pool.release();
    pool = null;
    whiteKeyMusics.clear();
    whiteKeyMusics = null;
    blackKeyMusics.clear();
    blackKeyMusics = null;
  }

  public void pause() {
    pool.autoPause();
  }


  @Override public void sendStartMessage() {
    handler.sendEmptyMessage(LOAD_START);
  }

  @Override public void sendFinishMessage() {
    handler.sendEmptyMessage(LOAD_FINISH);
  }

  @Override public void sendErrorMessage(Exception e) {
    handler.sendMessage(Message.obtain(handler, LOAD_ERROR, e));
  }

  @Override public void sendProgressMessage(int progress) {
    handler.sendMessage(Message.obtain(handler, LOAD_PROGRESS, progress));
  }

  /**
   * 自定义handler,处理加载状态
   */
  private class AudioStatusHandler extends Handler {
    AudioStatusHandler(Looper looper) {
      super(looper);
    }

    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      handleAudioStatusMessage(msg);
    }
  }

  private void handleAudioStatusMessage(Message msg) {
    if (loadAudioListener != null) {
      switch (msg.what) {
        case LOAD_START:
          loadAudioListener.loadPianoAudioStart();
          break;
        case LOAD_FINISH:
          loadAudioListener.loadPianoAudioFinish();
          break;
        case LOAD_ERROR:
          loadAudioListener.loadPianoAudioError((Exception) msg.obj);
          break;
        case LOAD_PROGRESS:
          loadAudioListener.loadPianoAudioProgress((int) msg.obj);
          break;
        default:
          break;
      }
    }
  }
}

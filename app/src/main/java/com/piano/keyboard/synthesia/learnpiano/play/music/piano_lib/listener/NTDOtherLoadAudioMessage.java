package com.piano.keyboard.synthesia.learnpiano.play.music.piano_lib.listener;

/**
 * Created by ChengTao on 2016-11-27.
 */

/**
 * 加载音频消息接口
 */
public interface NTDOtherLoadAudioMessage {
  /**
   * 开始
   */
  void sendStartMessage();

  /**
   * 完成
   */
  void sendFinishMessage();

  /**
   * 失败
   *
   * @param e 异常
   */
  void sendErrorMessage(Exception e);

  /**
   * 进度
   *
   * @param progress 进度值
   */
  void sendProgressMessage(int progress);
}

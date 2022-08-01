package com.watayouxiang.android_audio_plugin;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class MethodCallHandlerImpl implements MethodCallHandler {
    private AudioManager audioManager;

    public MethodCallHandlerImpl(@NonNull Context appContext) {
        this.audioManager = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("enableSpeakerphone")) {
            if (audioManager == null) {
                result.error("enableSpeakerphone", "audioManager 为空", null);
                return;
            }
            Boolean enable = call.argument("enable");
            if (enable == null) {
                result.error("enableSpeakerphone", "enable 为空", null);
                return;
            }
            enableSpeakerphone(audioManager, enable);
            result.success(null);
        } else {
            result.notImplemented();
        }
    }

    private void enableSpeakerphone(@NonNull AudioManager audioManager, boolean enable) {
        // 设置mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        } else {
            audioManager.setMode(AudioManager.MODE_IN_CALL);
        }
        if (enable) {
            // 设置音量为最大
            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                    audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL),
                    AudioManager.FX_KEY_CLICK);
            // 打开外放
            audioManager.setSpeakerphoneOn(true);
        } else {
            // 设置音量，解决有些机型切换后没声音或者声音突然变大的问题
            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                    audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL),
                    AudioManager.FX_KEY_CLICK);
            // 关闭外放
            audioManager.setSpeakerphoneOn(false);
        }
    }

    public void release() {
        if (audioManager != null) {
            audioManager.setMode(AudioManager.MODE_NORMAL);
            audioManager = null;
        }
    }
}

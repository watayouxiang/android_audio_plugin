package com.watayouxiang.android_audio_plugin;

import android.content.Context;
import android.media.AudioManager;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class MethodCallHandlerImpl implements MethodCallHandler {
    private final Context appContext;

    public MethodCallHandlerImpl(@NonNull Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("enableSpeakerphone")) {
            Boolean enable = call.argument("enable");
            if (enable == null) {
                result.error("enableSpeakerphone", "enable 为空", null);
                return;
            }
            enableSpeakerphone(appContext, enable);
            result.success(null);
        } else {
            result.notImplemented();
        }
    }

    private void enableSpeakerphone(@NonNull Context context, boolean enable) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (enable) {
            // 外放
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            audioManager.setSpeakerphoneOn(true);
        } else {
            // 听筒
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            audioManager.setSpeakerphoneOn(false);
        }
    }
}

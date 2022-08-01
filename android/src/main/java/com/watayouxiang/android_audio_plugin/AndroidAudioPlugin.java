package com.watayouxiang.android_audio_plugin;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodChannel;

/**
 * AndroidAudioPlugin
 */
public class AndroidAudioPlugin implements FlutterPlugin {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    private MethodCallHandlerImpl handler;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        handler = new MethodCallHandlerImpl(binding.getApplicationContext());
        channel = new MethodChannel(binding.getBinaryMessenger(), "android_audio_plugin");
        channel.setMethodCallHandler(handler);
    }


    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        handler.release();
        channel.setMethodCallHandler(null);
    }


}

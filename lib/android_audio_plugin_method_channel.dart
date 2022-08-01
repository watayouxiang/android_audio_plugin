import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:platform/platform.dart';

import 'android_audio_plugin_platform_interface.dart';

/// An implementation of [AndroidAudioPluginPlatform] that uses method channels.
class MethodChannelAndroidAudioPlugin extends AndroidAudioPluginPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('android_audio_plugin');
  final Platform _platform = const LocalPlatform();

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<void> enableSpeakerphone(bool enable) async {
    if (!_platform.isAndroid) {
      return;
    }
    await methodChannel.invokeMethod<void>(
      'enableSpeakerphone',
      <String, dynamic>{
        'enable': enable,
      },
    );
  }
}

import 'android_audio_plugin_platform_interface.dart';

class AndroidAudioPlugin {
  Future<String?> getPlatformVersion() {
    return AndroidAudioPluginPlatform.instance.getPlatformVersion();
  }

  Future<void> enableSpeakerphone(bool enable) {
    return AndroidAudioPluginPlatform.instance.enableSpeakerphone(enable);
  }
}

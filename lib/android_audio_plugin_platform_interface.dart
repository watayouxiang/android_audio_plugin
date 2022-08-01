import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'android_audio_plugin_method_channel.dart';

abstract class AndroidAudioPluginPlatform extends PlatformInterface {
  /// Constructs a AndroidAudioPluginPlatform.
  AndroidAudioPluginPlatform() : super(token: _token);

  static final Object _token = Object();

  static AndroidAudioPluginPlatform _instance =
      MethodChannelAndroidAudioPlugin();

  /// The default instance of [AndroidAudioPluginPlatform] to use.
  ///
  /// Defaults to [MethodChannelAndroidAudioPlugin].
  static AndroidAudioPluginPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AndroidAudioPluginPlatform] when
  /// they register themselves.
  static set instance(AndroidAudioPluginPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<void> enableSpeakerphone(bool enable) {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}

import 'package:android_audio_plugin/android_audio_plugin.dart';
import 'package:android_audio_plugin/android_audio_plugin_method_channel.dart';
import 'package:android_audio_plugin/android_audio_plugin_platform_interface.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAndroidAudioPluginPlatform
    with MockPlatformInterfaceMixin
    implements AndroidAudioPluginPlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<void> enableSpeakerphone(bool enable) => Future.value();
}

void main() {
  final AndroidAudioPluginPlatform initialPlatform =
      AndroidAudioPluginPlatform.instance;

  test('$MethodChannelAndroidAudioPlugin is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAndroidAudioPlugin>());
  });

  test('getPlatformVersion', () async {
    AndroidAudioPlugin androidAudioPlugin = AndroidAudioPlugin();
    MockAndroidAudioPluginPlatform fakePlatform =
        MockAndroidAudioPluginPlatform();
    AndroidAudioPluginPlatform.instance = fakePlatform;

    expect(await androidAudioPlugin.getPlatformVersion(), '42');
  });
}

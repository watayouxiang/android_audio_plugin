import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:android_audio_plugin/android_audio_plugin_method_channel.dart';

void main() {
  MethodChannelAndroidAudioPlugin platform = MethodChannelAndroidAudioPlugin();
  const MethodChannel channel = MethodChannel('android_audio_plugin');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}

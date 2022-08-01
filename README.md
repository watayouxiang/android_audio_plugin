# android_audio_plugin

安卓音频控制相关的Flutter插件。该插件只提供安卓端，用于开启和关闭安卓声音的外放。

## Features

| Feature                  | Platform |
| ------------------------ | -------- |
| 开启和关闭安卓声音的外放 | Andriod  |

## Usage

```dart
import 'dart:io';
import 'package:android_audio_plugin/android_audio_plugin_platform_interface.dart';
```

```dart
if (Platform.isAndroid) {
  await AndroidAudioPluginPlatform.instance.enableSpeakerphone(enable);
}
```


# 电脑通知 - PC Notify

> 通过 ADB 从电脑向手机发送系统通知（带声音+震动）

## 📱 效果

```
电脑发命令  →  ADB  →  手机弹出系统通知横幅（带声音+震动）
```

## 📦 下载 APK

👉 **[app/电脑通知.apk](./app/电脑通知.apk)**

## 🚀 快速开始

### 第1步：手机安装 App

1. 把 `电脑通知.apk` 传给手机（微信/QQ/数据线都行）
2. 安装后 **点开一次**（创建通知渠道，然后会自动关闭）
3. 去手机 **设置 → 通知与应用 → 电脑通知 → 允许通知**（打开横幅+声音）

### 第2步：电脑安装 ADB

**Windows：**
```bash
# 下载 Platform Tools
https://developer.android.com/studio/releases/platform-tools

# 解压后，把文件夹路径添加到系统 PATH
# 或者直接在文件夹里打开命令行
```

**macOS：**
```bash
brew install android-platform-tools
```

**验证安装：**
```bash
adb --version
```

### 第3步：手机开启无线调试

```
设置 → 开发者选项 → 无线调试 → 开启
```

> 如果找不到开发者选项：设置 → 关于手机 → 版本号 → 连续点击7次

### 第4步：电脑连接手机

```bash
# 查看手机的 IP 地址（设置 → WLAN → 当前网络）
adb connect 手机IP:5555

# 验证连接
adb devices
# 应显示: 192.168.x.x:5555  device
```

### 第5步：发一条通知试试！

```bash
adb shell am start -n com.reasonix.notify/.MainActivity \
  --es title "通知标题" \
  --es message "通知内容"
```

## 📝 日常使用

连接成功后，每次发消息只需要：

```bash
# 1. 如果之前断开过，先连接
adb connect 手机IP:5555

# 2. 发通知
adb shell am start -n com.reasonix.notify/.MainActivity \
  --es title "标题" \
  --es message "内容"
```

### 一键脚本（Windows）

新建 `send.bat`：
```bat
@echo off
adb connect %1
adb shell am start -n com.reasonix.notify/.MainActivity --es title "%2" --es message "%3"
```

使用方法：
```bash
send.bat 192.168.0.103:5555 "标题" "内容"
```

## ⚙️ 从源码编译

如果你有 Android 开发环境：

```bash
git clone https://github.com/你的用户名/notify-app
cd notify-app
./gradlew assembleDebug
# APK 在 app/build/outputs/apk/debug/app-debug.apk
```

或者直接用 [Android Studio](https://developer.android.com/studio) 打开项目编译。

## 📁 项目结构

```
notify-app/
├── app/
│   ├── src/main/
│   │   ├── java/com/reasonix/notify/
│   │   │   └── MainActivity.kt    # 核心代码（40行）
│   │   ├── AndroidManifest.xml    # App 配置
│   │   └── res/values/            # 资源文件
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## 📄 许可证

MIT

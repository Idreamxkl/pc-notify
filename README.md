# 手机弹窗

> 从电脑发通知到手机，支持 WiFi 连接，带声音+震动弹窗

## 📱 效果

电脑发命令 → ADB → 手机弹出系统通知（带声音+震动）

## 📦 下载 APK

👉 **app/手机弹窗.apk**（约 3.3MB）

## 🚀 快速开始

### 第1步：手机安装 App
1. 把手机弹窗.apk 传给手机安装
2. 安装后点开一次（静默创建通知渠道，自动关闭）
3. 去手机设置 → 通知与应用 → 手机弹窗 → 允许通知

### 第2步：电脑安装 ADB
Windows: 下载 Platform Tools 并添加到 PATH
macOS: brew install android-platform-tools

### 第3步：手机开启无线调试
设置 → 开发者选项 → 无线调试 → 开启

### 第4步：电脑连接手机
adb connect 手机IP:5555
adb devices  # 验证连接

### 第5步：发一条通知试试！
adb shell am start -n com.reasonix.notify/.MainActivity --es title "标题" --es message "内容"
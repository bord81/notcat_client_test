# notcat_examples — Example Clients for notcatd

This repository contains example projects demonstrating how to use [`notcat_lib`](https://github.com/bord81/notcat_lib) to communicate with the [`notcatd`](https://github.com/bord81/notcatd) logging daemon. It showcases client implementations in **C++**, **Rust**, and **Kotlin/Android**, all integrated into the AOSP build system.

---

## 📁 Project Structure

### 🔹 `notcat_client` — C++ Binary

A simple native client that sends logs to `notcatd` using the C FFI interface provided by `notcat_lib`.

---

### 🦀 `notcat_client_rust` — Rust Binary

A Rust client that uses the native Rust library interface of `notcat_lib`.

---

### 📱 `NotCatTest` — Android Kotlin App

A minimal Android app written in Kotlin using Jetpack Compose. It logs messages to `notcatd` via JNI bindings from `notcat_lib`.

---

## 🔗 Related Projects

- [`notcatd`](https://github.com/bord81/notcatd): Logging daemon for Android
- [`notcat_lib`](https://github.com/bord81/notcat_lib): Cross-language client library

---

## 🛡️ License

This project is licensed under the [MIT License](LICENSE).

© 2025 Borys Zakaliuk

---

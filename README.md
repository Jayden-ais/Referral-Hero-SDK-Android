Referral Hero SDK-Android
==================
## Introducation

The Referral Hero SDK is a powerful tool for integrating referral functionality into your application. It provides a seamless way for users to refer friends, track referrals, and incentivize user engagement. This repository contains all the necessary code and documentation to help you get started with integrating the Referral Hero SDK into your project.

## Getting Started

To begin using the Referral Hero SDK, follow these steps:

## Setup
### 1. Import JitPack Android Library
Add `maven { url 'https://jitpack.io' }` in
<details open>
  <summary>groovy - settings.gradle</summary>

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven { url 'https://jitpack.io' }
    }
}
```
</details>

<details open>
  <summary>kotlin - settings.gradle.kts</summary>

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven ("https://jitpack.io")
    }
}
```
</details>

### 2. Add dependency
<details open>
  <summary>groovy - build.gradle</summary>

```gradle
dependencies {
    implementation "com.github.Jayden-ais:Referral-Hero-SDK-Android:0.0.0"
}
```
</details>
<details open>
  <summary>kotlin - build.gradle.kts</summary>

```gradle
dependencies {
    implementation("com.github.Jayden-ais:Referral-Hero-SDK-Android:0.0.0")
}
```
</details>

## Usage
### Import
```kotlin
import com.github.Jayden-ais:Referral-Hero-SDK-Android:0.0.0
```

### Examples
| APIs | App Examples |
| --- | --- |
| All AP is demo | [MainScreen.kt](https://github.com/Jayden-ais/Referral-Hero-SDK-Android/blob/master/app/src/main/java/com/example/referralsdk/MainActivity.kt) - Show all API usages|

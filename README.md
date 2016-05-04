# iSight

This is an Android APP to protect users' eyesight which is also a team project for 18641 - Java Smart Phone

## Features

1. User activities: Sign up, Sign in.
2. Tests I: Astigmatism, Color Blind, Motion Acuity, Amsler Grid by recognizing patterns in the images.
3. Tests II: Visual Acuity, Presbyopic by calling Android API to implement speech recognition.
4. Tests III: Pupil Distance by importing OpenCV 3.1.0 to implement face recognition.
5. Quiz: Random quiz questions for protecting eyes.
6. History: Record users' results for tests to monitor health condition.
6. Setting:Show account info, Change password, Clear history, Share and Log out.

## Run instructions

1. Download source code from [github](https://github.com/MandyChen0114/iSight)
2. Import source code to Platform like Android Studio
3. Install Android SDK, JDK, Android NDK ( native methods are used in Face recognition)
4. Modify NDK path to your local one in build.gradle file of APP MODULE( NOT for the whole project or openCVLibrary310).

        task ndkBuild(type: Exec) {
             if (Os.isFamily(Os.FAMILY_WINDOWS)) {
                 commandLine '**/Users/Mandy/Library/Android/sdk/ndk-bundle**/ndk-build.cmd', '-C', file('src/main/jni').absolutePath
             } else {
                 commandLine '**/Users/Mandy/Library/Android/sdk/ndk-bundle/**ndk-build', '-C', file('src/main/jni').absolutePath
             }
         } 

5. Run app in two ways: Android phone or tablet by using a USB to connect , emulator in Android studio or Genymotion(Recommended, but emulators' microphone can't be reached)
6. We deployed a servlet in AWS, which can be called by our app via the RESTful APIs. We also created a simple example for [server testing](http://ec2-54-235-239-186.compute-1.amazonaws.com:8080/ISightServer/ISightQuery?queryId=1&id=0)


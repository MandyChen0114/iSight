# iSight

This is an Android APP to protect users' eyesight which is also a team project for 18641 - Java Smart Phone

## Features

1. USER ACTIVITY including sign up, sign in and log out.
2. TEST including Visual Acuity, Astigmatism, Color Blind, Motion Acuity, Amsler Grid, Presbyopic tests.
3. TEST user's pupillary distance by Face Recognition.
4. QUIZ for protecting eyes.
5. RECORD users' history.
6. SETTING including show account info, change password, check standards and tools used for testing.

## Coding Progress

1. Feature 1 has been done. We made strict rules for signing up like username and email must be unique, password and password confirmed must match. User can use either username or email address with correct password to login.
    _**Related Codes:**_ 
        `Activities in package ui`: LoginActivity.java, SignupActivity.java, MainActivity.java
        `Models in package model`: User.java
        `DB in package util`: DBHelper.java, UserDAO.java
        `Layout`: activity_sign_up.xml, activity_login.xml, activity_main.xml

2. Feature 2 has been 70% done. Ways to implement all 6 tests are very similar, and we've fully implement color blind one. The other ones whose related recourse has been collected are easy to implement if we just follow the code structure of color blind test one.
 _**Related Codes:**_ 
        `Activities in package ui`: Fragment_Test.java, Activity_Test_[TestName].java (6)
        `Models in package model`: Test.java (abstract class), Rule.java (Interface), [TestName].java (6)
        `Layout`: fragment_test.xml, list_test_item.xml, activity_[testName].xml(6)

3. Feature 3 has been 30% done. We've tried to use the Android FaceDetector.Face API. We got some progress but haven't implemented it to our app.
 _**Related Codes:**_ 
        `Activities in package ui`: Activity_Test_PD.java (6)
        `Models in package model`: TestPD.java (6)
        `Layout`: activity_PD.xml(6)
4. Feature 4 has been 30% done. We've finished collected resource for quiz, and designed the logic flow of this feature. It shouldn't be hard to implement.

5. Feature 5 hasn't been done.

6. Feature 6 has been 30% done. We can check standards and tools used for testing now.


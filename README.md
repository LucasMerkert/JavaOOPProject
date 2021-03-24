# JavaOOPProject
Java OOP Project Ws20/21
Made by Lucas Merkert, Ana Erlenkötter, Friedrich Just, and Tarik Hammad.

## How to Build

Requirements:
 - git (to clone from repository)
 - Android Studio

Step 1: Clone project and open in Android Studio
If you are opening Android Studio with an active Project:
File -> New -> Project from Version Control...
Enter <https://github.com/LucasMerkert/JavaOOPProject.git> as URL
Choose your own Directory
Click 'Clone'

If you are opening Android Studio without an active Project:
Click 'Get from Version Control'
Enter <https://github.com/LucasMerkert/JavaOOPProject.git> as URL
Choose your own Directory
Click 'Clone'

Step 2: Generate APK
Build -> Generate Signed Bundle / APK
Select 'APK'
Click 'Next'
Click 'Choose existing...'
Select 'key.jks' from project folder
Click 'OK'
Passwords for Key and Key store are both 'game2048'
Enter key0 as key alias
Click 'Next'
Choose Destination Path
Select 'Release' Build Variant
Select both V1 and V2 Signature Versions
Click 'Finish'
This can take some time.
Open 'release' folder at Destination

## How to install

Locate/Build APK

To build the APK, follow BUILD.txt instruction
Otherwise, an already built APK is located in ./app/release/app-release.apk

1. Move APK to android environment
	a. Connect your android device to your computer
	b. Move APK to a shared folder with an android emulator
	c. You are reading this on an android device

2. Install APK
2.1. Click on the APK file using a file manager
2.2. You may need to adjust settings to accept outside APKs
2.3. Ingore security warning from Play Protect
2.4. Do not upload app for security review by Play Protect
2.5. The app is now installing. This may take a while.

# Bework
Small Android Framework


1. Top-level. 
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.2'
    }
}

allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io'}
    }
}
...



2. On your Module:App (gradle)
dependencies {
    compile 'com.github.opannapo:Bework:Beta-1.0'
}



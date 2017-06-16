# Bework
Small Android Framework


# Gradle
1. Top-level.<br /> 
buildscript {<br />
    repositories {<br />
        jcenter()<br />
    }<br />
    dependencies {<br />
        classpath 'com.android.tools.build:gradle:2.1.2'<br />
    }<br />
}<br />
<br />
allprojects {<br />
    repositories {<br />
        jcenter()<br />
        maven { url 'https://jitpack.io'}<br />
    }<br />
}<br />
...
<br />
<br />
<br />
2. On your Module:App (gradle)<br />
dependencies {<br />
    compile 'com.github.opannapo:Bework:Beta-1.0'<br />
}<br />



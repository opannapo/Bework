# Bework
Small Android Framework


# Gradle
1. Top-level.<br /> 
buildscript {<br />
    _repositories {<br />
        _jcenter()<br />
    _}<br />
    _dependencies {<br />
        _classpath 'com.android.tools.build:gradle:2.1.2'<br />
    _}<br />
}<br />
<br />
allprojects {<br />
    _repositories {<br />
        _jcenter()<br />
        _maven { url 'https://jitpack.io'}<br />
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



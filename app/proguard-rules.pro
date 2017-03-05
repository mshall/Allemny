# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Mohamed\fcih\work\Android\Android-SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keep class com.zzoome.android.Views.TagGroup { *; }
-keepattributes Exceptions
-keepattributes EnclosingMethod
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes Deprecated
-keepattributes SourceFile
-keepattributes LineNumberTable
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;}
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.pojo.** { *; }
-keep class         org.apache.commons.beanutils.** { *; }
-keep interface     org.apache.commons.beanutils.** { *; }
-keep enum          org.apache.commons.beanutils.** { *; }
-dontoptimize
-ignorewarnings


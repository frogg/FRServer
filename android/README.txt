Don't forget to add the following line in your AndroidManifest.xml
<uses-permission android:name="android.permission.INTERNET" />

If not, the following error will occur, as your app is not allowed to connect with the Internet
12-07 10:55:24.533  19886-19908/project.server E/LogLari﹕ Error creating JSON, try  jObj= new JSONObject(json);java.lang.SecurityException: Permission denied (missing INTERNET permission?)
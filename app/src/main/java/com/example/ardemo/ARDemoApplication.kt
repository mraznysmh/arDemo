package com.example.ardemo

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class ARDemoApplication: Application(){

    override fun onCreate(){
        super.onCreate()
        Fabric.with(this,Crashlytics()) //spytac co to daje wlasciwie
    }
}
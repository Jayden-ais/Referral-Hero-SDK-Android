package com.example.referralsdk

import android.app.Application
import com.sdk.referral.RH

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RH.getAutoInstance(this)

        RH.getAutoInstance()
    }
}
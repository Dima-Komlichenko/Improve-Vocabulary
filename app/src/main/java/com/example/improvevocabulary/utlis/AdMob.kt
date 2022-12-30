package com.example.improvevocabulary.utlis

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdMob {

    var mInterstitialAd: InterstitialAd? = null
    private var TAG = "AdMob"

    var isAdFinished: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isAdFinished.value = false
    }

    fun loadAds(context: Context) {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(context,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError.toString())
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })
    }


    fun show(activity: Activity): Boolean {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(activity)
            return true
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
            return false
        }
    }
}
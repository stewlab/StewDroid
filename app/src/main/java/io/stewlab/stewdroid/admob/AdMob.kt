package io.stewlab.stewdroid.admob

import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class AdMob {

    private var rewardAdUnitId: String = "ca-app-pub-3940256099942544/5224354917"

    private var admobInitialized: Boolean = false

    private var rewardedAd: RewardedAd? = null
    private var rewardedAdListener: RewardedAdListener? = null


    fun initialize(currentActivity: FragmentActivity?, adMobInitListener: AdMobInitListener) {

        MobileAds.initialize(currentActivity!!){
            adMobInitListener.onInitializationComplete()
            admobInitialized = true
            Log.i(TAG, "AdMob Initialization Status: ".plus(it.toString()))
        }

    }

    private fun getAdRequest(): AdRequest {
        return AdRequest.Builder().build()
    }

    fun loadRewardedAd(currentActivity: FragmentActivity?, ral: RewardedAdListener) {

        rewardedAdListener = ral
        val adRequest: AdRequest = getAdRequest()

        RewardedAd.load(currentActivity!!, rewardAdUnitId, adRequest, object : RewardedAdLoadCallback() {

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.d(TAG, loadAdError.message)
                rewardedAd = null

                rewardedAdListener!!.onAdFailedToLoad(loadAdError.code)
            }

            override fun onAdLoaded(newRewardedAd: RewardedAd) {

                rewardedAd = newRewardedAd
                rewardedAdListener!!.onAdLoaded()

                rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdClicked() {
                        rewardedAdListener!!.onAdClicked()
                    }

                    override fun onAdDismissedFullScreenContent() {
                        rewardedAdListener!!.onAdDismissedFullScreenContent()
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        rewardedAdListener!!.onAdFailedToShowFullScreenContent()
                    }

                    override fun onAdImpression() {
                        rewardedAdListener!!.onAdImpression()
                    }

                    override fun onAdShowedFullScreenContent() {
                        rewardedAdListener!!.onAdShowedFullScreenContent()
                    }
                }
            }
        })

    }

    fun showRewardAd(currentActivity: FragmentActivity?) {
        rewardedAd?.show(currentActivity!!) {

            val rewardAmount = it.amount
            val rewardType = it.type

            rewardedAdListener?.onUserEarnedReward(rewardType, rewardAmount)
        }
    }

    companion object {
        private const val TAG = "AdMob"
    }
}
package io.stewlab.stewdroid.admob

import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class AdMob {

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

    fun loadRewardedAd(adUnitId: String = DefaultAdUnitId.REWARDED.id, currentActivity: FragmentActivity?, ral: RewardedAdListener) {
        rewardedAdListener = ral
        val adRequest: AdRequest = getAdRequest()

        RewardedAd.load(currentActivity!!, adUnitId, adRequest, object : RewardedAdLoadCallback() {
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

    enum class DefaultAdUnitId(val id: String) {
        APP_OPEN("ca-app-pub-3940256099942544/3419835294"),
        BANNER("ca-app-pub-3940256099942544/6300978111"),
        INTERSTITIAL("ca-app-pub-3940256099942544/1033173712"),
        INTERSTITIAL_VIDEO("ca-app-pub-3940256099942544/8691691433"),
        REWARDED("ca-app-pub-3940256099942544/5224354917"),
        REWARDED_INTERSTITIAL("ca-app-pub-3940256099942544/5354046379"),
        NATIVE_ADVANCED("ca-app-pub-3940256099942544/2247696110"),
        NATIVE_ADVANCED_VIDEO("ca-app-pub-3940256099942544/1044960115")
    }
    companion object {
        private const val TAG = "AdMob"
    }
}
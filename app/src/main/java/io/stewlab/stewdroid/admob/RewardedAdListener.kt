package io.stewlab.stewdroid.admob

interface RewardedAdListener {

    fun onAdLoaded();
    fun onAdFailedToLoad(errorCode: Int);
    fun onAdShowedFullScreenContent();
    fun onAdFailedToShowFullScreenContent();
    fun onAdDismissedFullScreenContent();
    fun onAdClicked();
    fun onAdImpression();
    fun onUserEarnedReward(type: String, amount: Int);

}
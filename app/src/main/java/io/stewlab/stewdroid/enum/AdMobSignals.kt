package io.stewlab.stewdroid.enum

enum class AdMobSignals(val signalName : String) {
    ADMOB_INIT("admob_initialized"),
    REWARDED_AD_LOADED("rewarded_ad_loaded"),
    REWARDED_AD_FAILED_TO_LOAD("rewarded_ad_failed_to_load"),
    REWARDED_AD_USER_EARNED_REWARD("rewarded_ad_user_earned_reward")
}
package io.stewlab.stewdroid

import android.app.Activity
import android.content.Intent
import android.util.ArraySet
import android.view.View
import androidx.fragment.app.FragmentActivity
import io.stewlab.stewdroid.admob.AdMob
import io.stewlab.stewdroid.admob.RewardedAdListener
import io.stewlab.stewdroid.enum.AdMobSignals
import io.stewlab.stewdroid.enum.Signals
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo
import org.godotengine.godot.plugin.UsedByGodot
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class StewDroid(godot: Godot?) : GodotPlugin(godot) {

    private val godotActivity: FragmentActivity? = godot?.activity
    private val adMob: AdMob = AdMob()

    init {

    }

    // AdMob Functions
    @UsedByGodot
    fun initializeAdmob() {
        adMob.initialize(godotActivity)
    }

    @UsedByGodot
    fun loadRewardedAd() {
        adMob.loadRewardedAd(godotActivity, object: RewardedAdListener {
            override fun onAdLoaded() {
                emitSignal(AdMobSignals.REWARDED_AD_LOADED.signalName, "")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                emitSignal(AdMobSignals.REWARDED_AD_FAILED_TO_LOAD.signalName, "")
            }

            override fun onAdShowedFullScreenContent() {
                TODO("Not yet implemented")
            }

            override fun onAdFailedToShowFullScreenContent() {
                TODO("Not yet implemented")
            }

            override fun onAdDismissedFullScreenContent() {
                TODO("Not yet implemented")
            }

            override fun onAdClicked() {
                TODO("Not yet implemented")
            }

            override fun onAdImpression() {
                TODO("Not yet implemented")
            }

            override fun onUserEarnedReward(type: String, amount: Int) {
                emitSignal(AdMobSignals.REWARDED_AD_USER_EARNED_REWARD.signalName, "")
            }
        })
    }

    @UsedByGodot
    fun showRewardAd() {
        adMob.showRewardAd(godotActivity)
    }

    @UsedByGodot
    fun shareText(text : String) {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        godotActivity?.startActivity(shareIntent)

    }

    @UsedByGodot
    fun getHello(): String {
        return "Hello from Kotlin StewDroid!"
    }

    override fun getPluginName(): String {
        return "StewDroid"
    }

    override fun getPluginSignals(): MutableSet<SignalInfo> {

        val signals = ArraySet<SignalInfo>()

        signals.add(SignalInfo(
            Signals.STEWDROID_INIT.signalName,
            String::class.java
        ))

        signals.add(SignalInfo(
            AdMobSignals.ADMOB_INIT.signalName,
            String::class.java
        ))

        signals.add(SignalInfo(
            AdMobSignals.REWARDED_AD_LOADED.signalName,
            String::class.java
        ))

        signals.add(SignalInfo(
            AdMobSignals.REWARDED_AD_FAILED_TO_LOAD.signalName,
            String::class.java
        ))

        signals.add(SignalInfo(
            AdMobSignals.REWARDED_AD_USER_EARNED_REWARD.signalName,
            String::class.java
        ))

        return signals
    }

    override fun getGodot(): Godot {
        return super.getGodot()
    }

    override fun getActivity(): Activity? {
        return super.getActivity()
    }

    override fun onMainCreate(activity: Activity?): View? {
        return super.onMainCreate(activity)
    }

    override fun onMainActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onMainActivityResult(requestCode, resultCode, data)
    }

    override fun onMainRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>?,
        grantResults: IntArray?
    ) {
        super.onMainRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMainPause() {
        super.onMainPause()
    }

    override fun onMainResume() {
        super.onMainResume()
    }

    override fun onMainDestroy() {
        super.onMainDestroy()
    }

    override fun onMainBackPressed(): Boolean {
        return super.onMainBackPressed()
    }

    override fun onGodotSetupCompleted() {
        super.onGodotSetupCompleted()
        emitSignal(Signals.STEWDROID_INIT.signalName, "StewDroid has been initialized!")
    }

    override fun onGodotMainLoopStarted() {
        super.onGodotMainLoopStarted()
    }

    override fun onGLDrawFrame(gl: GL10?) {
        super.onGLDrawFrame(gl)
    }

    override fun onGLSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onGLSurfaceChanged(gl, width, height)
    }

    override fun onGLSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onGLSurfaceCreated(gl, config)
    }

//    override fun getPluginMethods(): MutableList<String> {
//        return super.getPluginMethods()
//    }

    override fun getPluginGDNativeLibrariesPaths(): MutableSet<String> {
        return super.getPluginGDNativeLibrariesPaths()
    }

    override fun shouldBeOnTop(): Boolean {
        return super.shouldBeOnTop()
    }

    override fun runOnUiThread(action: Runnable?) {
        super.runOnUiThread(action)
    }

    override fun runOnRenderThread(action: Runnable?) {
        super.runOnRenderThread(action)
    }

    override fun emitSignal(signalName: String?, vararg signalArgs: Any?) {
        super.emitSignal(signalName, *signalArgs)
    }
}
package io.stewlab.stewdroid;

import android.app.Activity;
import android.content.Intent;
import android.util.ArraySet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

import java.util.List;
import java.util.Set;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


// StewDroid.gdap below

//[config]
//
//name="StewDroid"
//binary_type="local"
//binary="app-debug.aar"
//
//[dependencies]
//
//local=[]
//remote=[]
//custom_maven_repos=[]


public class StewDroid extends GodotPlugin {

    public StewDroid(Godot godot) {
        super(godot);
    }

    @UsedByGodot
    public String getHello() {
        return "Hello from StewDroid!";
    }

    @UsedByGodot
    public void getHelloSignal(String string) {
        emitSignal("testSignal", string);
    }

    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals() {
        Set<SignalInfo> signals = new ArraySet<>();
        signals.add(new SignalInfo("testSignal", String.class));
        return signals;
    }

    @NonNull
    @Override
    public String getPluginName() {
        return "StewDroid";
    }

    @Override
    protected Godot getGodot() {
        return super.getGodot();
    }

    @Nullable
    @Override
    protected Activity getActivity() {
        return super.getActivity();
    }

    @Nullable
    @Override
    public View onMainCreate(Activity activity) {
        return super.onMainCreate(activity);
    }

    @Override
    public void onMainActivityResult(int requestCode, int resultCode, Intent data) {
        super.onMainActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onMainRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onMainRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMainPause() {
        super.onMainPause();
    }

    @Override
    public void onMainResume() {
        super.onMainResume();
    }

    @Override
    public void onMainDestroy() {
        super.onMainDestroy();
    }

    @Override
    public boolean onMainBackPressed() {
        return super.onMainBackPressed();
    }

    @Override
    public void onGodotSetupCompleted() {
        super.onGodotSetupCompleted();
    }

    @Override
    public void onGodotMainLoopStarted() {
        super.onGodotMainLoopStarted();
    }

    @Override
    public void onGLDrawFrame(GL10 gl) {
        super.onGLDrawFrame(gl);
    }

    @Override
    public void onGLSurfaceChanged(GL10 gl, int width, int height) {
        super.onGLSurfaceChanged(gl, width, height);
    }

    @Override
    public void onGLSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onGLSurfaceCreated(gl, config);
    }

//    @NonNull
//    @Override
//    public List<String> getPluginMethods() {
//        return super.getPluginMethods();
//    }

    @NonNull
    @Override
    protected Set<String> getPluginGDNativeLibrariesPaths() {
        return super.getPluginGDNativeLibrariesPaths();
    }

    @Override
    public boolean shouldBeOnTop() {
        return super.shouldBeOnTop();
    }

    @Override
    protected void runOnUiThread(Runnable action) {
        super.runOnUiThread(action);
    }

    @Override
    protected void runOnRenderThread(Runnable action) {
        super.runOnRenderThread(action);
    }

    @Override
    protected void emitSignal(String signalName, Object... signalArgs) {
        super.emitSignal(signalName, signalArgs);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

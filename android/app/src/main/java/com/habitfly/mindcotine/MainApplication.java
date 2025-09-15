package com.habitfly.mindcotine;
import com.intercom.reactnative.IntercomModule;
import expo.modules.ApplicationLifecycleDispatcher;
import expo.modules.ReactNativeHostWrapper;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;  // Add this import
import android.os.Looper;   // Add this import

import androidx.annotation.NonNull;

import com.facebook.react.ReactApplication;
import com.reactnativeutilsscale.UtilsScalePackage;
import com.zoontek.rnpermissions.RNPermissionsPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import io.invertase.firebase.app.ReactNativeFirebaseAppPackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage; // ✅ Required import
import com.facebook.soloader.SoLoader;
import com.reactnativecommunity.webview.RNCWebViewPackage;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import io.intercom.android.sdk.Intercom;
import io.invertase.firebase.app.ReactNativeFirebaseAppPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.swmansion.reanimated.ReanimatedPackage;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHostWrapper(this, new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new UtilsScalePackage(),
            new RNPermissionsPackage(),
            new RNGestureHandlerPackage(),
            new AsyncStoragePackage(),
            new ReactNativeFirebaseAppPackage(),
            new RNCWebViewPackage(),
            new ReanimatedPackage()
          );
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      });

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);

    // Initialize Intercom with delay to ensure app is fully initialized
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
      @Override
      public void run() {
        // Use MainApplication.this for the correct Application context
        Intercom.initialize(
          MainApplication.this,  // Use MainApplication.this for Application context
          "android_sdk-89531c4aac524ddf1cf3cfb58d3822c14535f394",
          "cvhtb46t"
        );
      }
    }, 3000); // Delay in milliseconds (3 seconds)

    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
    ApplicationLifecycleDispatcher.onApplicationCreate(this);
  IntercomModule.initialize(this, "android_sdk-89531c4aac524ddf1cf3cfb58d3822c14535f394", "cvhtb46t");
}

  @Override
  public void onConfigurationChanged(@NonNull Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    ApplicationLifecycleDispatcher.onConfigurationChanged(this, newConfig);
  }

  private static void initializeFlipper(
      Context context, ReactInstanceManager reactInstanceManager) {
    if (BuildConfig.DEBUG) {
      try {
        Class<?> aClass = Class.forName("com.habitfly.mindcotine.ReactNativeFlipper");
        aClass
            .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
            .invoke(null, context, reactInstanceManager);
      } catch (ClassNotFoundException
              | NoSuchMethodException
              | IllegalAccessException
              | InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}

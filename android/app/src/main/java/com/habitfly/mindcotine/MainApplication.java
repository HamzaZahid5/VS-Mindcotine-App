package com.habitfly.mindcotine;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage; // ✅ Required import
import com.facebook.soloader.SoLoader;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import io.intercom.android.sdk.Intercom;
import io.invertase.firebase.app.ReactNativeFirebaseAppPackage;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new ReactNativeFirebaseAppPackage()
            // new ReactNativeFirebaseAppPackage(),
            // new ReactNativeFirebaseCrashlyticsPackage() // Add this line
          );
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);

    // Initialize Intercom (if necessary)
    Intercom.initialize(
        this,
        "android_sdk-89531c4aac524ddf1cf3cfb58d3822c14535f394",
        "cvhtb46t"
    );

    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
  }

  @Override
  public void onConfigurationChanged(@NonNull Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
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

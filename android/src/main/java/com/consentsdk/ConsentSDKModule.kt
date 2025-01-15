package com.consentsdk

import ai.securiti.cmpsdkcore.main.CmpSDKLoggerLevel
import ai.securiti.cmpsdkcore.main.CmpSDKOptions
import android.app.Activity
import android.app.Application
import android.util.Log
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.modules.core.DeviceEventManagerModule


@ReactModule(name = ConsentSDKModule.NAME)
class ConsentSDKModule(val reactContext: ReactApplicationContext) :
  NativeConsentSDKSpec(reactContext) {

  override fun getName(): String {
    return NAME
  }

  private val wrapper = ConsentSDKWrapper()

  override fun initialize(options: ReadableMap?) {
    val app = reactContext.applicationContext as Application
    options?.let {
      val sdkOptions = CmpSDKOptions(
        appURL = options.getString("appURL") ?: "",
        cdnURL = options.getString("cdnURL") ?: "",
        tenantID = options.getString("tenantID") ?: "",
        appID = options.getString("appID") ?: "",
        testingMode = options.getBoolean("testingMode"),
        loggerLevel = CmpSDKLoggerLevel.valueOf(options.getString("loggerLevel") ?: ""),
        consentsCheckInterval = options.getInt("consentsCheckInterval"),
        subjectId = options.getString("subjectId") ?: "",
        languageCode = options.getString("languageCode") ?: "",
        locationCode = options.getString("locationCode") ?: ""
      )
      wrapper.initialize(app, sdkOptions)

      wrapper.isReady {
        sendEvent(
          "isConsentSDKReady",
          it
        )
      }
    }
  }

  override fun resetConsents() {
    wrapper.resetConsents()
  }

  private fun sendEvent(eventName: String, params: Any) {
    reactApplicationContext
      .getJSModule<DeviceEventManagerModule.RCTDeviceEventEmitter>(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
      .emit(eventName, params)
  }

  override fun isReactNativeSDKReady(callback: Callback?) {}

  override fun isSdkReady(): Boolean {
    return wrapper.isReady()
  }

  fun getActivity(): Activity? {
    return currentActivity
  }

  override fun presentConsentBanner() {
    wrapper.presentConsentBanner(getActivity())
  }

  override fun presentPreferenceCenter() {
    wrapper.presentPreferenceCenter(getActivity())
  }

  companion object {
    const val NAME = "ConsentSDK"
  }
}

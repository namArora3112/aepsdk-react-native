package com.additionalpackage

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.adobe.marketing.mobile.Places
import android.util.Log

class AdditionalpackageModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  // Example method
  // Multiplies two numbers and returns the result
  @ReactMethod
  fun multiply(a: Double, b: Double, promise: Promise) {
    try {
      promise.resolve(a * b)
    } catch (e: Exception) {
      promise.reject("MULTIPLY_ERROR", "Error multiplying $a and $b", e)
    }
  }
  
  // Fetches the extension version of the Adobe Places SDK without handling null values
  @ReactMethod
  fun extensionVersion(promise: Promise) {
    try {
      Log.d(TAG, "Fetching Places Extension Version")
      val version = Places.extensionVersion()
      promise.resolve(version)
    } catch (e: Exception) {
      promise.reject("EXTENSION_VERSION_ERROR", "Failed to fetch Places extension version", e)
    }
  }

  companion object {
    const val NAME = "Additionalpackage"
    private const val TAG = "AdditionalpackageModule"
  }
}

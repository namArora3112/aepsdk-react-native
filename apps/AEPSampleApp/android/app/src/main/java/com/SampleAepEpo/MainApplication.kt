/*
Copyright 2024 Adobe. All rights reserved.
This file is licensed to you under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License. You may obtain a copy
of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
OF ANY KIND, either express or implied. See the License for the specific language
governing permissions and limitations under the License.
*/
package com.SampleAepEpo

import android.app.Application
import com.adobe.marketing.mobile.AdobeCallback
import com.adobe.marketing.mobile.Assurance
import com.adobe.marketing.mobile.Edge
import com.adobe.marketing.mobile.Extension
// Adobe SDKs imports start
import com.adobe.marketing.mobile.Lifecycle
import com.adobe.marketing.mobile.Signal
import com.adobe.marketing.mobile.Identity
import android.util.Log
import com.adobe.marketing.mobile.LoggingMode
import com.adobe.marketing.mobile.Assurance
import com.adobe.marketing.mobile.CampaignClassic
import com.adobe.marketing.mobile.Edge
import com.adobe.marketing.mobile.EdgeBridge
import com.adobe.marketing.mobile.edge.consent.Consent
import com.adobe.marketing.mobile.Messaging
import com.adobe.marketing.mobile.Optimize
import com.adobe.marketing.mobile.Places
import com.adobe.marketing.mobile.Target
import com.adobe.marketing.mobile.UserProfile
import com.adobe.marketing.mobile.MobileCore
// Adobe SDKs imports end
import com.adobe.marketing.mobile.Places
import com.adobe.marketing.mobile.Signal
import com.adobe.marketing.mobile.Target
import com.adobe.marketing.mobile.UserProfile
import com.adobe.marketing.mobile.edge.bridge.EdgeBridge
import com.adobe.marketing.mobile.edge.consent.Consent
import com.adobe.marketing.mobile.edge.identity.Identity
import com.adobe.marketing.mobile.optimize.Optimize
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.load
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost
import com.facebook.react.defaults.DefaultReactNativeHost
//import com.facebook.react.flipper.ReactNativeFlipper
import com.facebook.soloader.SoLoader
import java.util.Arrays


class MainApplication : Application(), ReactApplication {

  override val reactNativeHost: ReactNativeHost =
      object : DefaultReactNativeHost(this) {
        override fun getPackages(): List<ReactPackage> =
            PackageList(this).packages.apply {
              // Packages that cannot be autolinked yet can be added manually here, for example:
              // add(MyReactNativePackage())
            }

        override fun getJSMainModuleName(): String = "index"

        override fun getUseDeveloperSupport(): Boolean = BuildConfig.DEBUG

        override val isNewArchEnabled: Boolean = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
        override val isHermesEnabled: Boolean = BuildConfig.IS_HERMES_ENABLED
      }

  override val reactHost: ReactHost
    get() = getDefaultReactHost(this.applicationContext, reactNativeHost)

  override fun onCreate() {
    super.onCreate()
         
      MobileCore.setApplication(this);
      MobileCore.configureWithAppID("example");
      MobileCore.setLogLevel(LoggingMode.DEBUG);
    
        
      val extensions = listOf(Lifecycle.EXTENSION, Signal.EXTENSION, Identity.EXTENSION, Assurance.EXTENSION, CampaignClassic.EXTENSION, Edge.EXTENSION, EdgeBridge.EXTENSION, Consent.EXTENSION, com.adobe.marketing.mobile.edge.identity.Identity.EXTENSION, Messaging.EXTENSION, Optimize.EXTENSION, Places.EXTENSION, Target.EXTENSION, UserProfile.EXTENSION)
      MobileCore.registerExtensions(extensions) {
        // Use the extensions in your app
        Log.d("CoreExtensions", "Extensions registered successfully");
      }
    
    SoLoader.init(this, false)
    if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      // If you opted-in for the New Architecture, we load the native entry point for this app.
      load()
    }


    MobileCore.setApplication(this)
    MobileCore.setLogLevel(LoggingMode.VERBOSE)
    MobileCore.configureWithAppID("3149c49c3910/60b93a735420/launch-d6d39b41b8fa")
    val extensions: List<Class<out Extension?>> = Arrays.asList(
      Lifecycle.EXTENSION,
      Signal.EXTENSION,
      Edge.EXTENSION,
      Identity.EXTENSION,
      Consent.EXTENSION,
      EdgeBridge.EXTENSION,
      Messaging.EXTENSION,
      UserProfile.EXTENSION,
      Assurance.EXTENSION,
      Places.EXTENSION,
      Target.EXTENSION,
      Optimize.EXTENSION,
      com.adobe.marketing.mobile.Identity.EXTENSION
    )
    MobileCore.registerExtensions(extensions,
      AdobeCallback { o: Any? ->
        MobileCore.lifecycleStart(
          null
        )
      })
  }
}

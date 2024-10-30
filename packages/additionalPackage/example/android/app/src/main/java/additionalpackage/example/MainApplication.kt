package additionalpackage.example

// Adobe SDKs imports start
import android.util.Log
import com.adobe.marketing.mobile.LoggingMode
import com.adobe.marketing.mobile.Assurance

import com.adobe.marketing.mobile.Edge
import com.adobe.marketing.mobile.edge.bridge.EdgeBridge
import com.adobe.marketing.mobile.edge.consent.Consent
import com.adobe.marketing.mobile.edge.identity.Identity

import com.adobe.marketing.mobile.MobileCore
// Adobe SDKs imports end









import android.app.Application
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.load
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost
import com.facebook.react.defaults.DefaultReactNativeHost
import com.facebook.react.soloader.OpenSourceMergedSoMapping
import com.facebook.soloader.SoLoader

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
    get() = getDefaultReactHost(applicationContext, reactNativeHost)

  override fun onCreate() {
    super.onCreate()


    
      MobileCore.setApplication(this);
      MobileCore.configureWithAppID("example");
      MobileCore.setLogLevel(LoggingMode.DEBUG);
    
        val extensions = listOf( Assurance.EXTENSION,Edge.EXTENSION, EdgeBridge.EXTENSION, Consent.EXTENSION, Identity.EXTENSION)
      MobileCore.registerExtensions(extensions) {
        // Use the extensions in your app
        Log.d("CoreExtensions", "Extensions registered successfully");
      }



    SoLoader.init(this, OpenSourceMergedSoMapping)
    if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      // If you opted-in for the New Architecture, we load the native entry point for this app.
      load()
    }
  }
}

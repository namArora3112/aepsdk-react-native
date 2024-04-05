/*
Copyright 2022 Adobe. All rights reserved.
This file is licensed to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance with the License. You
may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
REPRESENTATIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.
*/

package com.reactnative.aepsampleapp;

import android.app.Application;
import com.adobe.marketing.mobile.Assurance;
import com.adobe.marketing.mobile.Edge;
import com.adobe.marketing.mobile.Extension;
import com.adobe.marketing.mobile.Lifecycle;
import com.adobe.marketing.mobile.LoggingMode;
//import com.adobe.marketing.mobile.Messaging;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.Places;
import com.adobe.marketing.mobile.Signal;
import com.adobe.marketing.mobile.Target;
import com.adobe.marketing.mobile.UserProfile;
import com.adobe.marketing.mobile.edge.bridge.EdgeBridge;
import com.adobe.marketing.mobile.edge.consent.Consent;
//import com.adobe.marketing.mobile.optimize.Optimize;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactNativeHost;
import com.facebook.soloader.SoLoader;
import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new DefaultReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here,
          // for example: packages.add(new MyReactNativePackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }

        @Override
        protected boolean isNewArchEnabled() {
          return BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
        }

        @Override
        protected Boolean isHermesEnabled() {
          return BuildConfig.IS_HERMES_ENABLED;
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
    if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      // If you opted-in for the New Architecture, we load the native entry
      // point for this app.
      DefaultNewArchitectureEntryPoint.load();
    }
    ReactNativeFlipper.initializeFlipper(
        this, getReactNativeHost().getReactInstanceManager());
    MobileCore.setApplication(this);
    MobileCore.setLogLevel(LoggingMode.VERBOSE);
    MobileCore.configureWithAppID("YOUR-APP-ID");
    List<Class<? extends Extension>> extensions = Arrays.asList(
            Lifecycle.EXTENSION,
            Signal.EXTENSION,
            Edge.EXTENSION,
            com.adobe.marketing.mobile.edge.identity.Identity.EXTENSION,
            Consent.EXTENSION,
            EdgeBridge.EXTENSION,
            //Messaging.EXTENSION,
            UserProfile.EXTENSION,
            Assurance.EXTENSION,
            Places.EXTENSION,
            Target.EXTENSION,
            //Optimize.EXTENSION,
            com.adobe.marketing.mobile.Identity.EXTENSION);
    MobileCore.registerExtensions(extensions,
                                  o -> { MobileCore.lifecycleStart(null); });
  }
}

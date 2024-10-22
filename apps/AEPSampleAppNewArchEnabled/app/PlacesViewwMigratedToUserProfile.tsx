/*
Copyright 2022 Adobe. All rights reserved.
This file is licensed to you under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License. You may obtain a copy
of the License at http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
OF ANY KIND, either express or implied. See the License for the specific language
governing permissions and limitations under the License.
*/

import React from 'react';
import {Button, ScrollView, Text, View} from 'react-native';
import {
  //Places,
  // PlacesAuthStatus,
  // PlacesGeofence,
  // PlacesGeofenceTransitionType,
  // PlacesLocation,
} from '@adobe/react-native-aepplaces';
import {NavigationProps} from '../types/props';
import { UserProfile ,
 PlacesAuthStatus,
  PlacesGeofence,
  PlacesGeofenceTransitionType,
  PlacesLocation,


} from '@adobe/react-native-aepuserprofile';
import styles from '../styles/styles';

const EXAMPLE_LATITUDE = 37.3325958;
const EXAMPLE_LONGITUDE = -121.8910217;
const EXAMPLE_GEOFENCE_ID = '82e2eb52-e925-41a3-9d50-418a2e015608';
const EXAMPLE_RADIUS = 50;

const extensionVersion = async () => {

  UserProfile.extensionVersionPlaces().then(version =>
    console.log('AdobeExperienceSDK: UserProfile version: ' + version),
  );


  // Places.extensionVersion().then(version =>
  //   console.log('AdobeExperienceSDK: places extension version: ' + version),
  // );
};

const getNearbyPointsOfInterest = async () => {
  const location = new PlacesLocation(EXAMPLE_LONGITUDE, EXAMPLE_LATITUDE);
  try {
    const pois = await UserProfile.getNearbyPointsOfInterest(location, 2);
    console.log(
      `AdobeExperienceSDK: Places pois: ${pois[0]?.['name'] || '[]'}`,
    );
  } catch (e) {
    console.log(`AdobeExperienceSDK: Places error: ${e}`);
  }
};

const processGeofence = () => {
  const geofence = new PlacesGeofence(
    EXAMPLE_GEOFENCE_ID,
    EXAMPLE_LATITUDE,
    EXAMPLE_LONGITUDE,
    EXAMPLE_RADIUS,
    10,
  );
  UserProfile.processGeofence(geofence, PlacesGeofenceTransitionType.EXIT);
  console.log('Geofence processed');
};

const getCurrentPointsOfInterest = async () => {
  const pois = await UserProfile.getCurrentPointsOfInterest();
  console.log(`AdobeExperienceSDK: Places pois: ${pois[0]?.['name'] || '[]'}`);
};

const getLastKnownLocation = async () => {
  const location = await UserProfile.getLastKnownLocation();
  console.log(
    `AdobeExperienceSDK: Places location: ${JSON.stringify(location)}`,
  );
};

const clear = () => {
  UserProfile.clear();
  console.log('cleared');
};

const setAuthorizationStatus = () => {
  UserProfile.setAuthorizationStatus(PlacesAuthStatus.ALWAYS);
  console.log('Authorization status set');
};

const PlacesView = () => {
  return (
    <View style={styles.container}>
      <ScrollView contentContainerStyle={{marginTop: 75}}>
        <Button onPress={()=>{}} title="Go to main page" />
        <Text style={styles.welcome}>Places</Text>
        <Button title="extensionVersion()" onPress={extensionVersion} />
        <Button
          title="getNearbyPointsOfInterest()"
          onPress={getNearbyPointsOfInterest}
        />
        <Button title="processGeofence()" onPress={processGeofence} />
        <Button
          title="getCurrentPointsOfInterest()"
          onPress={getCurrentPointsOfInterest}
        />
        <Button title="getLastKnownLocation()" onPress={getLastKnownLocation} />
        <Button
          title="setAuthorizationStatus()"
          onPress={setAuthorizationStatus}
        />
        <Button title="clear" onPress={clear} />
      </ScrollView>
    </View>
  );
};

export default PlacesView;

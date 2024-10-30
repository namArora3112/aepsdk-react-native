import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'additionalpackage' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

// Define the expected methods of the native module
interface AdditionalpackageType {
  multiply(a: number, b: number): Promise<number>;
  extensionVersion(): Promise<string>;
}

// Check if the native module is properly linked
const Additionalpackage: AdditionalpackageType = NativeModules.Additionalpackage
  ? NativeModules.Additionalpackage
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

// Export the multiply function
export function multiply(a: number, b: number): Promise<number> {
  return Additionalpackage.multiply(a, b);
}

// Export the extensionVersion function
export function extensionVersion(): Promise<string> {
  return Additionalpackage.extensionVersion();
}

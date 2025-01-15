import ConsentSDK from './NativeConsentSDK';

// export function multiply(a: number, b: number): number {
//   return ConsentSDK.multiply(a, b);
// }

export interface CmpSDKOptions {
  appURL: string;
  cdnURL: string;
  tenantID: string;
  appID: string;
  testingMode: boolean;
  loggerLevel: string; // Assuming CmpSDKLoggerLevel is a string, adjust if necessary
  consentsCheckInterval: number;
  subjectId?: string;
  languageCode?: string;
  locationCode?: string;
}

export function initialize(options: CmpSDKOptions) {
  ConsentSDK.initialize(options);
}

export function resetConsents() {
  ConsentSDK.resetConsents();
}

export function isReactNativeSDKReady(callback: (status: boolean) => void) {
  ConsentSDK.isReactNativeSDKReady(callback);
}

export function isSdkReady(): Boolean {
  return ConsentSDK.isSdkReady();
}

export function presentConsentBanner() {
  ConsentSDK.presentConsentBanner();
}

export function presentPreferenceCenter() {
  ConsentSDK.presentPreferenceCenter();
}

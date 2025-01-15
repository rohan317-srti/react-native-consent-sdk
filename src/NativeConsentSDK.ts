import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface ConsentStatus {
  status: string;
}

export interface AppPermission {
  permissionId: string;
  name: string;
  description: string;
}

export interface Purpose {
  purposeId: number;
  name: string;
  description: string;
}

export interface BannerConfig {
  bannerText: string;
  bannerColor: string;
}

export interface SettingsPrompt {
  promptText: string;
  promptOptions: string[];
}

export interface PostConsentsRequest {
  uuid: string;
  appUUID: string;
  device: string;
  implicitConsent: boolean;
  version: number;
  purposeConsents: string[];
}

// CmpSDKOptions.ts

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

export interface Spec extends TurboModule {
  initialize(options: CmpSDKOptions): void;
  //   getConsent(purposeId: number, callback: (result: ConsentStatus) => void): void;
  //   getConsent(permissionId: string, callback: (result: ConsentStatus) => void): void;
  //   getPermissions(callback: (result: AppPermission[]) => void): void;
  //   getPurposes(callback: (result: Purpose[]) => void): void;
  //   getSdksInPurpose(purposeId: number, callback: (result: unknown[]) => void): void;
  //   setPurposeConsent(request: Purpose, consent: ConsentStatus): void;
  //   setPermissionConsent(request: AppPermission, consent: ConsentStatus): void;
  resetConsents(): void;
  isReactNativeSDKReady(callback: (status: boolean) => void): void;
  isSdkReady(): boolean;
  //   getBannerConfig(callback: (result: BannerConfig | null) => void): void;
  //   options(): CmpSDKOptions | null;
  //   getSettingsPrompt(callback: (result: SettingsPrompt | null) => void): void;
  //   uploadConsents(request: PostConsentsRequest, callback: (result: boolean) => void): void;
  presentConsentBanner(): void;
  presentPreferenceCenter(): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('ConsentSDK');

import {
  View,
  StyleSheet,
  Button,
  NativeModules,
  NativeEventEmitter,
} from 'react-native';
import { useEffect } from 'react';
import {
  initialize,
  presentConsentBanner,
  presentPreferenceCenter,
} from 'consent-sdk';

const { ConsentSDK } = NativeModules;
const consentSDKEmitter = new NativeEventEmitter(ConsentSDK);

export default function App() {
  useEffect(() => {
    const options = {
      appURL: 'https://dev-intg-2.securiti.xyz',
      cdnURL: 'https://cdn-dev-intg-2.securiti.xyz/consent',
      tenantID: 'd525a59e-896e-4a85-ad1a-84921e9acce0',
      appID: '175f4dbd-ba1c-4821-9792-d5cc29237db2',
      testingMode: false,
      loggerLevel: 'DEBUG',
      consentsCheckInterval: 60,
      subjectId: 'reactNativeSubject',
      languageCode: 'en',
      locationCode: 'PK',
    };

    initialize(options);

    const consentSDKListener = consentSDKEmitter.addListener(
      'isConsentSDKReady',
      (status) => {
        if (status) {
          presentConsentBanner();
        }
      }
    );

    return () => {
      consentSDKListener.remove();
    };
  }, []);

  const handlePress = () => {
    presentPreferenceCenter();
  };

  return (
    <View style={styles.container}>
      <Button onPress={handlePress} title="Open Preference Center" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

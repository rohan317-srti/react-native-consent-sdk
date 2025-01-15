package com.consentsdk

import ai.securiti.cmpsdkcore.main.*
import ai.securiti.cmpsdkcore.network.models.common.*
import ai.securiti.cmpsdkcore.network.models.request.PostConsentsRequest
import ai.securiti.cmpsdkcore.ui.SecuritiMobileCmpExtensions.Companion.presentConsentBanner
import ai.securiti.cmpsdkcore.ui.SecuritiMobileCmpExtensions.Companion.presentPreferenceCenter
import android.app.Activity
import android.app.Application
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.facebook.react.bridge.Callback

class ConsentSDKWrapper {

    private val scope = CoroutineScope(Dispatchers.IO)

    fun initialize(application: Application, options: CmpSDKOptions) {
        SecuritiMobileCmp.initialize(application, options)
    }

    fun getConsent(purposeId: Int, callback: (ConsentStatus) -> Unit) {
        scope.launch {
            val result = SecuritiMobileCmp.getConsent(purposeId)
            callback(result)
        }
    }

    fun getConsent(permissionId: String, callback: (ConsentStatus) -> Unit) {
        scope.launch {
            val result = SecuritiMobileCmp.getConsent(permissionId)
            callback(result)
        }
    }

    fun getPermissions(callback: (ArrayList<AppPermission>) -> Unit) {
        scope.launch {
            val result = SecuritiMobileCmp.getPermissions()
            callback(result)
        }
    }

    fun getPurposes(callback: (ArrayList<Purpose>) -> Unit) {
        scope.launch {
            val result = SecuritiMobileCmp.getPurposes()
            callback(result)
        }
    }

    fun getSdksInPurpose(purposeId: Int, callback: (List<Any>) -> Unit) {
        scope.launch {
            val result = SecuritiMobileCmp.getSdksInPurpose(purposeId)
            callback(result)
        }
    }

    fun setConsent(purpose: Purpose, consent: ConsentStatus) {
        SecuritiMobileCmp.setConsent(purpose, consent)
    }

    fun setConsent(appPermission: AppPermission, consent: ConsentStatus) {
        SecuritiMobileCmp.setConsent(appPermission, consent)
    }

    fun resetConsents() {
        SecuritiMobileCmp.resetConsents()
    }

    fun addListener(listener: ConsentSdkListener) {
        SecuritiMobileCmp.addListener(listener)
    }

    fun isReady(callback: (Boolean) -> Unit) {
        SecuritiMobileCmp.isReady {
          try {
            Log.d("customTag", "from Wrapper: value: ${it}")
          } catch (e: Exception) {
            Log.d("customTag", "from Module: error: ${e.message}")
          }
          callback.invoke(it)
        }
    }

    fun isReady(): Boolean {
        return SecuritiMobileCmp.isReady()
    }

    fun getBannerConfig(cdn: MainConfiguration? = null, callback: (BannerConfig?) -> Unit) {
        scope.launch {
            val result = SecuritiMobileCmp.getBannerConfig(cdn)
            callback(result)
        }
    }

    fun options(): CmpSDKOptions? {
        return SecuritiMobileCmp.options()
    }

    fun getSettingsPrompt(callback: (SettingsPrompt?) -> Unit) {
        scope.launch {
            val result = SecuritiMobileCmp.getSettingsPrompt()
            callback(result)
        }
    }

    fun uploadConsents(request: PostConsentsRequest, callback: (Boolean) -> Unit) {
        scope.launch {
            val result = SecuritiMobileCmp.uploadConsents(request)
            callback(result)
        }
    }

    fun presentPreferenceCenter(activity: Activity?) {
        if (activity != null) {
            SecuritiMobileCmp.presentPreferenceCenter(activity)
        }
    }

    fun presentConsentBanner(activity: Activity?) {
        if (activity != null) {
            SecuritiMobileCmp.presentConsentBanner(activity)
        }
    }
}

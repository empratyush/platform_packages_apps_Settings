package com.android.settings.location

import android.content.Context
import com.android.settings.ext.IntSettingPrefController
import androidx.preference.PreferenceScreen
import com.android.settings.R
import com.android.settings.ext.RadioButtonPickerFragment2
import android.ext.settings.GeocodingSettings

class GeocodingPreferenceController(ctx: Context, key: String?)  :
    IntSettingPrefController(ctx, key, GeocodingSettings.GEOCODING_SETTINGS) {

    override fun addPrefsAfterList(fragment: RadioButtonPickerFragment2, screen: PreferenceScreen) {
        addFooterPreference(screen, R.string.geocoding_footer)
    }

    override fun getEntries(entries: Entries) {
        entries.add(
            R.string.geocoding_enabled_grapheneos_proxy,
            GeocodingSettings.GEOCODING_SERVER_GRAPHENEOS_PROXY
        )
        entries.add(
            R.string.geocoding_enabled_nominatim_server,
            GeocodingSettings.GEOCODING_SERVER_NOMINATIM
        )
        entries.add(
            R.string.geocoding_disabled,
            GeocodingSettings.GEOCODING_DISABLED
        )
    }

}
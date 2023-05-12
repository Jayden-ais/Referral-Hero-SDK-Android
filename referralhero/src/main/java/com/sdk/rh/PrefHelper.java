package com.sdk.rh;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;


/**
 *  we are using this class for to save data into SharedPreferences and  for display log in SDK classes
 */

public class PrefHelper {
    /**
     * A {@link String} value used where no string value is available.
     */
    public static final String NO_STRING_VALUE = "rh_no_value";
    private static final String TAG = "ReferralHeroSDK";
    private static final String SHARED_PREF_FILE = "rh_referral_shared_pref";
    private static final String KEY_GOOGLE_PLAY_INSTALL_REFERRER_EXTRA = "rh_google_play_install_referrer_extras";
    private static final String KEY_APP_STORE_SOURCE = "rh_app_store_source";
    private static final String KEY_APP_SHORT_LINK = "rh_app_short_link";
    /**
     * Internal static variable of own type {@link PrefHelper}. This variable holds the single
     * instance used when the class is instantiated via the Singleton pattern.
     */
    private static PrefHelper prefHelper_;
    private static boolean enableLogging_ = false;
    /**
     * A single variable that holds a reference to the application's {@link SharedPreferences}
     * object for use whenever {@link SharedPreferences} values are read or written via this helper
     * class.
     */
    private final SharedPreferences appSharedPrefs_;
    /**
     * A single variable that holds a reference to an {@link SharedPreferences.Editor} object that is used by the
     * helper class whenever the preferences for the application are changed.
     */
    private SharedPreferences.Editor prefsEditor_;

    /**
     * <p>Constructor with context passed from calling {@link android.app.Activity}.</p>
     *
     * @param context A reference to the {@link Context} that the application is operating
     *                within. This is normally the base context of the application.
     */
    private PrefHelper(Context context) {
        this.appSharedPrefs_ = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        this.prefsEditor_ = this.appSharedPrefs_.edit();
    }

    /**
     * <p>Singleton method to return the pre-initialised, or newly initialise and return, a singleton
     * object of the type {@link PrefHelper}.</p>
     *
     * @param context The {@link Context} within which the object should be instantiated; this
     *                parameter is passed to the private {@link #PrefHelper(Context)}
     *                constructor method.
     * @return A {@link PrefHelper} object instance.
     */
    public static PrefHelper getInstance(Context context) {
        if (prefHelper_ == null) {
            prefHelper_ = new PrefHelper(context);
        }
        return prefHelper_;
    }

    // Package Private
    static void shutDown() {
        if (prefHelper_ != null) {
            prefHelper_.prefsEditor_ = null;
        }
        prefHelper_ = null;
    }

    /**
     * <p>Creates a <b>Debug</b> message in the debugger. If debugging is disabled, this will fail silently.</p>
     *
     * @param message A {@link String} value containing the debug message to record.
     */
    public static void Debug(String message) {
        if (enableLogging_ && !TextUtils.isEmpty(message)) {
            Log.i(TAG, message);
        }
    }

    public static void LogException(String message, Exception t) {
        if (!TextUtils.isEmpty(message)) {
            Log.e(TAG, message, t);
        }
    }

    public static void LogAlways(String message) {
        if (!TextUtils.isEmpty(message)) {
            Log.i(TAG, message);
        }
    }

    static void enableLogging(boolean fEnable) {
        enableLogging_ = fEnable;
    }

    /**
     * <p>Clears all the RH referral shared preferences related .
     * </p>
     */
    private void clearAllPref() {
        prefsEditor_.clear();
        prefsEditor_.apply();
    }

    /**
     * Gets the app store install referrer string
     *
     * @return {@link String}  App store install referrer string
     */
    public String getAppStoreReferrer() {
        return getString(KEY_GOOGLE_PLAY_INSTALL_REFERRER_EXTRA);
    }

    // ALL GENERIC CALLS

    /**
     * Sets the app store install referrer string
     *
     * @param referrer App store install referrer string
     */
    public void setAppStoreReferrer(String referrer) {
        setString(KEY_GOOGLE_PLAY_INSTALL_REFERRER_EXTRA, referrer);
    }

    /**
     * Gets the short link
     *
     * @return {@link String}  App store install referrer string
     */
    public String getShortLink() {
        return getString(KEY_APP_SHORT_LINK);
    }

    public void setShortLink(String store) {
        if (!TextUtils.isEmpty(store)) {
            setString(KEY_APP_SHORT_LINK, store);
        }
    }

    public String getAppStoreSource() {
        return getString(KEY_APP_STORE_SOURCE);
    }

    public void setAppStoreSource(String store) {
        if (!TextUtils.isEmpty(store)) {
            setString(KEY_APP_STORE_SOURCE, store);
        }
    }

    private String serializeArrayList(ArrayList<String> strings) {
        String retString = "";
        for (String value : strings) {
            retString = retString + value + ",";
        }
        retString = retString.substring(0, retString.length() - 1);
        return retString;
    }

    private ArrayList<String> deserializeString(String list) {
        ArrayList<String> strings = new ArrayList<>();
        String[] stringArr = list.split(",");
        Collections.addAll(strings, stringArr);
        return strings;
    }

    /**
     * <p>A basic method that returns a {@link Boolean} indicating whether some preference exists.</p>
     *
     * @param key A {@link String} value containing the key to reference.
     * @return A {@link Boolean} indicating whether some preference exists.
     */
    public boolean hasPrefValue(String key) {
        return appSharedPrefs_.contains(key);
    }

    /**
     * <p>A basic method to remove some preference value.</p>
     *
     * @param key A {@link String} value containing the key to the value that's to be deleted.
     */
    public void removePrefValue(String key) {
        prefsEditor_.remove(key).apply();
    }

    /**
     * <p>A basic method that returns an integer value from a specified preferences Key.</p>
     *
     * @param key A {@link String} value containing the key to reference.
     * @return An {@link Integer} value of the specified key as stored in preferences.
     */
    public int getInteger(String key) {
        return getInteger(key, 0);
    }

    /**
     * <p>A basic method that returns an {@link Integer} value from a specified preferences Key, with a
     * default value supplied in case the value is null.</p>
     *
     * @param key          A {@link String} value containing the key to reference.
     * @param defaultValue An {@link Integer} specifying the value to use if the preferences value
     *                     is null.
     * @return An {@link Integer} value containing the value of the specified key, or the supplied
     * default value if null.
     */
    public int getInteger(String key, int defaultValue) {
        return appSharedPrefs_.getInt(key, defaultValue);
    }

    /**
     * <p>A basic method that returns a {@link Long} value from a specified preferences Key.</p>
     *
     * @param key A {@link String} value containing the key to reference.
     * @return A {@link Long} value of the specified key as stored in preferences.
     */
    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        return appSharedPrefs_.getLong(key, defaultValue);
    }

    /**
     * <p>A basic method that returns a {@link Float} value from a specified preferences Key.</p>
     *
     * @param key A {@link String} value containing the key to reference.
     * @return A {@link Float} value of the specified key as stored in preferences.
     */
    public float getFloat(String key) {
        return appSharedPrefs_.getFloat(key, 0);
    }

    /**
     * <p>A basic method that returns a {@link String} value from a specified preferences Key.</p>
     *
     * @param key A {@link String} value containing the key to reference.
     * @return A {@link String} value of the specified key as stored in preferences.
     */
    public String getString(String key) {
        return appSharedPrefs_.getString(key, NO_STRING_VALUE);
    }

    /**
     * <p>A basic method that returns a {@link Boolean} value from a specified preferences Key.</p>
     *
     * @param key A {@link String} value containing the key to reference.
     * @return An {@link Boolean} value of the specified key as stored in preferences.
     */
    public boolean getBool(String key) {
        return appSharedPrefs_.getBoolean(key, false);
    }

    /**
     * <p>Sets the value of the {@link String} key value supplied in preferences.</p>
     *
     * @param key   A {@link String} value containing the key to reference.
     * @param value An {@link Integer} value to set the preference record to.
     */
    public void setInteger(String key, int value) {
        prefsEditor_.putInt(key, value).apply();
    }

    /**
     * <p>Sets the value of the {@link String} key value supplied in preferences.</p>
     *
     * @param key   A {@link String} value containing the key to reference.
     * @param value A {@link Long} value to set the preference record to.
     */
    public void setLong(String key, long value) {
        prefsEditor_.putLong(key, value).apply();
    }

    /**
     * <p>Sets the value of the {@link String} key value supplied in preferences.</p>
     *
     * @param key   A {@link String} value containing the key to reference.
     * @param value A {@link Float} value to set the preference record to.
     */
    public void setFloat(String key, float value) {
        prefsEditor_.putFloat(key, value).apply();
    }

    /**
     * <p>Sets the value of the {@link String} key value supplied in preferences.</p>
     *
     * @param key   A {@link String} value containing the key to reference.
     * @param value A {@link String} value to set the preference record to.
     */
    public void setString(String key, String value) {
        prefsEditor_.putString(key, value).apply();
    }

    /**
     * <p>Sets the value of the {@link String} key value supplied in preferences.</p>
     *
     * @param key   A {@link String} value containing the key to reference.
     * @param value A {@link Boolean} value to set the preference record to.
     */
    public void setBool(String key, Boolean value) {
        prefsEditor_.putBoolean(key, value).apply();
    }
}

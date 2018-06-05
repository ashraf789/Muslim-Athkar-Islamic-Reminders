package net.a6te.lazycoder.muslim_pro_islamicremainders;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class LocaleManager {

    public static List<String > languagesCode;
    public static SavedData savedData;
    public static Context setLocale(Context c) {
        return setNewLocale(c, getLanguage(c));
    }

    public static Context setNewLocale(Context c, String language) {
        persistLanguage(c, language);
        return updateResources(c, language);
    }

    public static String getLanguage(Context c) {
        savedData = new SavedData(c);

        languagesCode = Arrays.asList(c.getResources().getStringArray(R.array.app_languages_code));
        return languagesCode.get(savedData.getAppLanguageSelectedId());
    }

    private static void persistLanguage(Context c, String language) {

    }

    private static Context updateResources(Context context, String language) {


        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

}

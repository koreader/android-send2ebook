package org.koreader.send2ebook.android.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    private static String filename = "ftp.properties";

    public static String getProperty(String key, Context context) throws IOException {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();

        properties.load(assetManager.open(filename));
        return properties.getProperty(key);

    }


}

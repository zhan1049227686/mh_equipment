package com.xiaow.mh.framework.common;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertyResources {
    private static final String BUNDLE_NAME = "AppResources"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME, Locale.ROOT);

    private PropertyResources() {
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}

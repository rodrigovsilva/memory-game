package com.rodrigovsilva.memorygame.common.message;

import com.rodrigovsilva.memorygame.common.constant.AppConstants;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Application global messages.
 *
 * @author Rodrigo Silva
 */
public enum AppMessages {

    ACTION_SUCCESS("app.action.success");

    private static ResourceBundle bundle;

    private String key;

    AppMessages(String key) {
        this.key = key;
    }

    public String getMessage() {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(AppConstants.RESOURCE_BUNDLE_BASENAME, Locale.getDefault());
        }
        return bundle.getString(this.key);
    }

    public String getMessage(Object... arguments) {
        return MessageFormat.format(getMessage(key), arguments);
    }
}

package com.rodrigovsilva.memorygame.common.message;

import com.rodrigovsilva.memorygame.common.constant.AppConstants;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Exception messages.
 *
 * @author Rodrigo Silva
 */
public enum ExceptionMessages {

    GENERAL_ERROR("exception.general_error"),

    INVALID_ARGUMENTS("exception.invalid_arguments"),

    PARAMETER_IS_MISSING("exception.parameter_is_missing"),

    NO_HANDLER_FOUND_ERROR("exception.no_handler_found_error"),

    PLAYER_ALREADY_EXISTS("business.exception.player_already_exists");

    private static ResourceBundle bundle;

    private String key;

    ExceptionMessages(String key) {
        this.key = key;
    }

    public String getMessage() {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(AppConstants.RESOURCE_BUNDLE_EXCEPTIONS, Locale.getDefault());
        }
        return bundle.getString(this.key);
    }

    public String getMessage(Object... arguments) {
        return MessageFormat.format(getMessage(), arguments);
    }
}

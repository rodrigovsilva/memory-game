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

    GENERAL_ERROR("ERR-001", "exception.general_error"),

    INVALID_ARGUMENTS("ERR-002", "exception.invalid_arguments"),

    PARAMETER_IS_MISSING("ERR-003", "exception.parameter_is_missing"),

    NO_HANDLER_FOOUND_ERROR("ERR-004", "exception.no_handler_found_error");

    private String code;

    private String message;

    private static ResourceBundle bundle;

    private String key;

    ExceptionMessages(String code, String message) {
        this.code = code;
        this.message = message;
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


    public String getCode() {
        return code;
    }


}

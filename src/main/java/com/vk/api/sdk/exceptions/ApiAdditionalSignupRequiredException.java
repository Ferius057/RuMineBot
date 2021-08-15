package com.vk.api.sdk.exceptions;

public class ApiAdditionalSignupRequiredException extends ApiException {
    public ApiAdditionalSignupRequiredException(String message) {
        super(41, "Additional signup required", message);
    }
}

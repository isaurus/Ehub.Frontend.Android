package com.isaac.ehub.ui.auth;

import com.isaac.ehub.core.Resource;

/**
 * Clase que representa el estado de la vista para el RegistrationFragment
 * Utiliza la clase Resource para gestionar estados de la operación asíncrona
 */
public class RegistrationViewState {

    private final Resource<?> resource;
    private final boolean isNameValid;
    private final boolean isEmailValid;
    private final boolean isPasswordValid;
    private final boolean isConfirmPasswordValid;

    private RegistrationViewState(Resource<?> resource, boolean isNameValid, boolean isEmailValid,
                                  boolean isPasswordValid, boolean isConfirmPasswordValid) {
        this.resource = resource;
        this.isNameValid = isNameValid;
        this.isEmailValid = isEmailValid;
        this.isPasswordValid = isPasswordValid;
        this.isConfirmPasswordValid = isConfirmPasswordValid;
    }

    public static RegistrationViewState idle() {
        return new RegistrationViewState(Resource.success(null), true, true, true, true);
    }

    public static RegistrationViewState validating(boolean isNameValid, boolean isEmailValid,
                                                   boolean isPasswordValid, boolean isConfirmPasswordValid) {
        return new RegistrationViewState(Resource.success(null), isNameValid, isEmailValid,
                isPasswordValid, isConfirmPasswordValid);
    }

    public static RegistrationViewState loading() {
        return new RegistrationViewState(Resource.loading(), true, true, true, true);
    }

    public static <T> RegistrationViewState success(T data) {
        return new RegistrationViewState(Resource.success(data), true, true, true, true);
    }

    public static RegistrationViewState error(String message) {
        return new RegistrationViewState(Resource.error(message, null), true, true, true, true);
    }

    public Resource<?> getResource() {
        return resource;
    }

    public Resource.Status getStatus() {
        return resource.getStatus();
    }

    public String getMessage() {
        return resource.getMessage();
    }

    public <T> T getData() {
        return (T) resource.getData();
    }

    public boolean isNameValid() {
        return isNameValid;
    }

    public boolean isEmailValid() {
        return isEmailValid;
    }

    public boolean isPasswordValid() {
        return isPasswordValid;
    }

    public boolean isConfirmPasswordValid() {
        return isConfirmPasswordValid;
    }

    public boolean isLoading() {
        return resource.getStatus() == Resource.Status.LOADING;
    }

    public boolean isSuccess() {
        return resource.getStatus() == Resource.Status.SUCCESS;
    }

    public boolean isError() {
        return resource.getStatus() == Resource.Status.ERROR;
    }
}
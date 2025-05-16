package com.isaac.ehub.ui.complete_profile;

import com.isaac.ehub.core.Resource;

public class CompleteProfileViewState {

    private final Resource<?> resource;
    private final boolean isFirstNameValid;
    private final boolean isLastNameValid;
    private final boolean isBirthDateValid;
    private final boolean isCountryValid;
    private final boolean isAvatarSelected;

    public CompleteProfileViewState(Resource<?> resource,
                                    boolean isFirstNameValid,
                                    boolean isLastNameValid,
                                    boolean isBirthDateValid,
                                    boolean isCountryValid,
                                    boolean isAvatarSelected) {
        this.resource = resource;
        this.isFirstNameValid = isFirstNameValid;
        this.isLastNameValid = isLastNameValid;
        this.isBirthDateValid = isBirthDateValid;
        this.isCountryValid = isCountryValid;
        this.isAvatarSelected = isAvatarSelected;
    }

    public static CompleteProfileViewState validating(boolean isAvatarSelected,
                                                      boolean isFirstNameValid,
                                                      boolean isLastNameValid,
                                                      boolean isBirthDateValid,
                                                      boolean isNationalityValid) {
        return new CompleteProfileViewState(Resource.validating(),
                isAvatarSelected,
                isFirstNameValid,
                isLastNameValid,
                isBirthDateValid,
                isNationalityValid);
    }

    public static CompleteProfileViewState loading() {
        return new CompleteProfileViewState(Resource.loading(),
                true,
                true,
                true,
                true,
                true);
    }

    public static CompleteProfileViewState success() {
        return new CompleteProfileViewState(Resource.success(true),
                true,
                true,
                true,
                true,
                true);
    }

    public static CompleteProfileViewState error(String message) {
        return new CompleteProfileViewState(Resource.error(message),
                true,
                true,
                true,
                true,
                true);
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


    public boolean isFirstNameValid() {
        return isFirstNameValid;
    }

    public boolean isLastNameValid() {
        return isLastNameValid;
    }

    public boolean isBirthDateValid() {
        return isBirthDateValid;
    }

    public boolean isCountryValid() {
        return isCountryValid;
    }

    public boolean isAvatarSelected() {
        return isAvatarSelected;
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

package com.isaac.ehub.ui.complete_profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.isaac.ehub.domain.model.UserModel;
import com.isaac.ehub.domain.usecase.profile.CompleteUserProfileUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CompleteProfileViewModel extends ViewModel {

    private final CompleteUserProfileUseCase completeUserProfileUseCase;

    public final MutableLiveData<CompleteProfileViewState> completeUserProfileViewState = new MutableLiveData<>();

    @Inject
    public CompleteProfileViewModel(CompleteUserProfileUseCase completeUserProfileUseCase) {
        this.completeUserProfileUseCase = completeUserProfileUseCase;
    }

    public LiveData<CompleteProfileViewState> getCompleteProfileViewState(){
        return completeUserProfileViewState;
    }

    public void completeUserProfile(UserModel userModel){
        completeUserProfileViewState.setValue(CompleteProfileViewState.loading());

        completeUserProfileUseCase.execute(userModel).observeForever(resource -> {
            switch (resource.getStatus()){
                case SUCCESS:
                    if (resource.getData() != null){
                        completeUserProfileViewState.setValue(CompleteProfileViewState.success());
                    } else {
                        completeUserProfileViewState.setValue(CompleteProfileViewState.error("Error inesperado"));
                    }
                    break;
                case ERROR:
                    completeUserProfileViewState.setValue(CompleteProfileViewState.error(resource.getMessage()));
                    break;
                case LOADING:
                    completeUserProfileViewState.setValue(CompleteProfileViewState.loading());
                    break;
            }
        });
    }

    /**
     * Lógica para validar los campos una vez introducidos.
     *
     * @param avatarUrl
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param country
     */
    public void validateFields(String avatarUrl, String firstName, String lastName, String birthDate, String country) {
        boolean isAvatarUrlValid = isValidAvatarUrl(avatarUrl); // NO SE USA HASTA IMPLEMENTAR EL GET
        boolean isFirstNameValid = isValidFirstName(firstName);
        boolean isLastNameValid = isValidLastName(lastName);
        boolean isBirthDateValid = isValidBirthDate(birthDate);
        boolean isCountryValid = isValidCountry(country);

        completeUserProfileViewState.setValue(
                CompleteProfileViewState.validating(
                        true, // ASUMIMOS QUE ESTÁ PRESELECCIONADO HASTA IMPLEMENTARLO
                        isFirstNameValid,
                        isLastNameValid,
                        isBirthDateValid,
                        isCountryValid
                )
        );

        // Si todos los campos son válidos, proceder con el registro
        if (isFirstNameValid && isLastNameValid && isBirthDateValid && isCountryValid) {
            // Crear UserModel y llamar al caso de uso
            UserModel userModel = new UserModel(
                    "", // avatarUrl
                    firstName,
                    lastName,
                    birthDate,
                    country
            );
            completeUserProfile(userModel);
        }
    }

    // ¡MEJORAR LA LÓGICA DE LAS VALIDACIONES PARA TEMA DE CARACTERES, ETC.

    private boolean isValidAvatarUrl(String avatarUrl){
        return !avatarUrl.isEmpty();
    }

    private boolean isValidFirstName(String firstName){
        return !firstName.isEmpty();
    }

    private boolean isValidLastName(String lastName){
        return !lastName.isEmpty();
    }

    private boolean isValidBirthDate(String birthDate){
        return !birthDate.isEmpty();
    }

    private boolean isValidCountry(String country){
        return !country.isEmpty();
    }

}

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

    // FALTARÍAN AÑADIR VALIDACIONES PARA LOS CAMPOS
}

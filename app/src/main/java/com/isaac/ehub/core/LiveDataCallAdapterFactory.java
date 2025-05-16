package com.isaac.ehub.core;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        // Verifica si el tipo de retorno es LiveData<Resource<T>>
        if (getRawType(returnType) != LiveData.class) {
            return null;
        }
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Type resourceType = getParameterUpperBound(0, (ParameterizedType) observableType);
        return new LiveDataCallAdapter<>(resourceType);
    }

    private static class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<Resource<R>>> {
        private final Type responseType;

        LiveDataCallAdapter(Type responseType) {
            this.responseType = responseType;
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public LiveData<Resource<R>> adapt(Call<R> call) {
            MutableLiveData<Resource<R>> result = new MutableLiveData<>();
            result.postValue(Resource.loading());
            call.enqueue(new Callback<R>() {
                @Override
                public void onResponse(Call<R> call, Response<R> response) {
                    if (response.isSuccessful()) {
                        result.postValue(Resource.success(response.body()));
                    } else {
                        result.postValue(Resource.error("Error en la API"));
                    }
                }

                @Override
                public void onFailure(Call<R> call, Throwable t) {
                    result.postValue(Resource.error("Error de red: " + t.getMessage()));
                }
            });
            return result;
        }
    }
}
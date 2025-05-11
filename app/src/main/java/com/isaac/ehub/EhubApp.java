package com.isaac.ehub;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Clase para la inyección de dependencias (DI) de la aplicación. Crea la infraestructura necesaria
 * para la DI.
 */
@HiltAndroidApp
public class EhubApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
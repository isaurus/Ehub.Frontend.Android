package com.isaac.ehub.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// Patrón de diseño útil para manejar eventos o datos que solo deben procesarse una vez.
// Su objetivo principal es evitar que un evento (como un mensaje de error, una notificación o una acción de navegación) se consuma múltiples veces
public class Event<T> {
    private final T content;    // El dato que queremos proteger (mensaje, objeto, etc.).
    private boolean hasBeenHandled;     // Bandera que indica si el evento ya fue consumido.

    public Event(T content) {
        this.content = content;
        this.hasBeenHandled = false;
    }

    @Nullable
    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;    // Si ya se consumió, retorna null.
        } else {
            hasBeenHandled = true;      // Marca como consumido.
            return content;     // Retorna el dato.
        }
    }

    @Nullable
    public T getContent() {
        return content;     // Retorna el dato (nullable, pero no afecta hasBeenHandled).
    }

    @NonNull
    public T peekContent() {
        return content;     // Retorna el dato siempre, sin marcar como consumido.
    }

    public boolean hasBeenHandled() {
        return hasBeenHandled;      // Getter para verificar el estado.
    }
}
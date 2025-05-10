package com.isaac.ehub.core.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.annotation.NonNull;

public final class EditTextUtils {

    // Listener de cambio de texto
    public interface TextChangeListener {
        void onTextChanged(String newText);
    }

    public static void addTextChangedListener(
            @NonNull EditText editText,
            @NonNull TextChangeListener listener
    ) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                listener.onTextChanged(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    // Perder foco después de una acción (ej: botón "Done" del teclado)
    public static void loseFocusAfterAction(
            @NonNull EditText editText,
            int imeAction,
            @NonNull Runnable onFocusLost
    ) {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == imeAction) {
                ViewUtils.dismissKeyboard(v);
                v.clearFocus();
                onFocusLost.run();
                return true;
            }
            return false;
        });
    }
}
package com.isaac.ehub.core.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.Toast;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public final class ActivityUtils {

    // Mostrar Toast con duración predeterminada
    public static void showToast(@NonNull Activity activity, @NonNull String text) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    // Crear texto con formato (negrita y color)
    public static SpannableStringBuilder createStyledText(
            @NonNull Activity activity,
            @NonNull String unselectedPart,
            @NonNull String selectedPart,
            @ColorRes int colorRes
    ) {
        String fullText = unselectedPart + " " + selectedPart;
        SpannableStringBuilder spannable = new SpannableStringBuilder(fullText);

        // Negrita en la parte seleccionada
        spannable.setSpan(
                new StyleSpan(Typeface.BOLD),
                unselectedPart.length() + 1,
                fullText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        // Color en la parte seleccionada
        int color = ContextCompat.getColor(activity, colorRes);
        spannable.setSpan(
                new ForegroundColorSpan(color),
                unselectedPart.length() + 1,
                fullText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        return spannable;
    }
}

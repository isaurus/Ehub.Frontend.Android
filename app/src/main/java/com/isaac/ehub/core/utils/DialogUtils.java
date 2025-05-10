package com.isaac.ehub.core.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public final class DialogUtils {

    public static void showDialogSafely(
            @NonNull DialogFragment dialog,
            @NonNull FragmentActivity activity,
            @NonNull String tag
    ) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return; // Evita crash si la Activity está destruida
        }

        FragmentManager manager = activity.getSupportFragmentManager();
        dialog.show(manager, tag);
    }
}
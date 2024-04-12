package io.mouadoumous.permission;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;


/**
 * Provides access to the current Activity and its Context throughout the application.
 * Implements Application.ActivityLifecycleCallbacks to track the current Activity.
 */
public class AndroidActivityProvider implements Application.ActivityLifecycleCallbacks {

    private WeakReference<Activity> currentActivity;

    /**
     * Constructor to initialize the AndroidActivityProvider with the current Activity.
     *
     * @param activity The current Activity to be provided by this provider.
     */
    public AndroidActivityProvider(Activity activity) {
        currentActivity = new WeakReference<>(activity);
    }

    /**
     * Get the current Activity provided by this provider.
     *
     * @return The current Activity instance, or null if not available.
     */
    public Activity get() {
        return currentActivity.get();
    }

    /**
     * Get the Context of the current Activity provided by this provider.
     *
     * @return The Context of the current Activity, or null if not available.
     */
    public Context getContext() {
        return currentActivity.get();
    }

    @Override
    public void onActivityPreCreated(@NonNull Activity activity, Bundle savedInstanceState) {
        currentActivity = new WeakReference<>(activity);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
        // Update the currentActivity reference only if it's different from the current one
        if ((currentActivity == null || activity != currentActivity.get())) {
            currentActivity = new WeakReference<>(activity);
        }
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        // Clear the currentActivity reference if the destroyed activity is the current one
        if (currentActivity != null && activity == currentActivity.get()) {
            currentActivity.clear();
        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

}
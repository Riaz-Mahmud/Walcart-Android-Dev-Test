package com.backdoor.walcartandroidtest.viewModel;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.backdoor.walcartandroidtest.R;
import com.backdoor.walcartandroidtest.View.Activity.MainActivity;

public class MainViewModel extends ViewModel {
    private Activity activity;


    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    public void checkNightMode() {
        int nightModeFlags = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, R.color.black_status_bar));
                View decorView = activity.getWindow().getDecorView(); //set status background black
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
                activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.white_status_bar));// set status background white
                break;
        }
    }
}

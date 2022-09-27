package com.backdoor.walcartandroidtest.viewModel;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {
    private Activity activity;

    public void onBackBtnPress() {
        activity.onBackPressed();
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }
}

package com.assignment.nytimes;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.assignment.nytimes.utils.ProgressDialogUtils;


public class BaseFragment extends Fragment {
    private ProgressDialog mBar;
    private ProgressDialogUtils progressUtil;


    public void showProgressDialog(Context context) {
        progressUtil = new ProgressDialogUtils();
        mBar = progressUtil.getDialog(context);
        progressUtil.showDialog(mBar);
    }

    public void dismissProgressDialog() {
        if (progressUtil != null) {
            progressUtil.onDismiss(mBar);
        }
        progressUtil = null;
        mBar = null;
    }
}

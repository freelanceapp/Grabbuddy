package grabbuddy.infobite.grabbuddy.utils;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitApiClient;

public class BaseFragment extends Fragment {

    public RetrofitApiClient retrofitApiClient;
    public RetrofitApiClient retrofitRxClient;
    public Context mContext;
    public Activity activity;

    public BaseFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mContext = getActivity();
        return null;
    }
}
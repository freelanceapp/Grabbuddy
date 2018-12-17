package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;

public class FeedbackFragment extends BaseFragment implements FragmentChangeListener {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        mContext = getActivity();
        return rootView;
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }
}

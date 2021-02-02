package com.example.myapplication;

import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    public void onBegin() {
        Toast.makeText(getActivity(), "onBegin", Toast.LENGTH_SHORT).show();
    }

    public void onBack() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getActivity().getSupportFragmentManager().popBackStack();
            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.frame);
            if (fragment != null) {
                if (fragment instanceof FirstFragment) {
                    getActivity().getSupportFragmentManager().beginTransaction().remove(fragment);
                }
            }
        } else {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this);
        }
    }
}

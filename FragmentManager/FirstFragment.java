package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
    
    public static FirstFragment newInstance(String text, int index) {
        Bundle args = new Bundle();
        args.putString("text", text);
        args.putInt("index", index);
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

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

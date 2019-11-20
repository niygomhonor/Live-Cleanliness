package com.moringaschool.live_cleanliness.tabs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moringaschool.live_cleanliness.R;

public class FragmentQuiz extends Fragment {
    View view;

    public  FragmentQuiz(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.quiz_fragment,container,false);
        return view;
    }
}

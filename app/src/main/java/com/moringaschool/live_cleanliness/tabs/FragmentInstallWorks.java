package com.moringaschool.live_cleanliness.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moringaschool.live_cleanliness.InstallationDetails;
import com.moringaschool.live_cleanliness.R;
import com.moringaschool.live_cleanliness.adapters.InstallAdapter;

import java.util.ArrayList;

public class FragmentInstallWorks extends Fragment {
    View v;
    ListView listView;
    ArrayAdapter<InstallationDetails> mAdapter;
    private String[] sName = {"GREAT BRIGHT CLEANERS CO. LTD","Kigali Dry Cleaners","Diamond Sparkle Cleaning Services","Pickup Laundry Service"};
    private String[] sName2 = {"GREAT BRIGHT CLEANERS CO. LTD","Kigali Dry Cleaners","Diamond Sparkle Cleaning Services","Pickup Laundry Service"};
    private int[] bssImage = {R.drawable.bullet,R.drawable.clean,R.drawable.painter,R.drawable.wellness};
    public FragmentInstallWorks(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){
        v=inflater.inflate(R.layout.installworks,container,false);
        listView =(ListView) v.findViewById(R.id.lv);

        InstallAdapter adapter = new InstallAdapter(getActivity(),sName,sName2,bssImage);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), sName[position], Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }
}

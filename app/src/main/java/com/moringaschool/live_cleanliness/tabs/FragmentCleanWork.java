package com.moringaschool.live_cleanliness.tabs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moringaschool.live_cleanliness.CleanersDetails;
import com.moringaschool.live_cleanliness.InstallationDetails;
import com.moringaschool.live_cleanliness.R;
import com.moringaschool.live_cleanliness.adapters.HowworksAdapter;

public class FragmentCleanWork extends Fragment {
    View view;
    ListView listView;
    ArrayAdapter<CleanersDetails> mAdapter;
    private String[] sName = {"Browse & Select","Diagnosis","Job Delivery"};
    private String[] sName2 = {"Find the product that fits your current need","Select the date and time you like a site visit to take place","Book quote provided by the pro, sit back and let us fix your problem"};
    private int[] bssImage = {R.drawable.bullet,R.drawable.time,R.drawable.right};

    public FragmentCleanWork(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cleanerwork_fragment,container,false);
        listView =(ListView) view.findViewById(R.id.listv);

        HowworksAdapter adapter = new HowworksAdapter(getActivity(),sName,sName2,bssImage);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });
        return view;
    }
}

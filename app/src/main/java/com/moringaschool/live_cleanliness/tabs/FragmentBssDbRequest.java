package com.moringaschool.live_cleanliness.tabs;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.live_cleanliness.InstallationDetailsOne;
import com.moringaschool.live_cleanliness.Models.InstallationCustomer;
import com.moringaschool.live_cleanliness.R;

import com.moringaschool.live_cleanliness.adapters.UserRequestAdapter;

import java.util.ArrayList;
import java.util.List;


public class FragmentBssDbRequest extends Fragment {


    ListView listUserRequest;
    List<InstallationCustomer> customers = new ArrayList<>();
    List<String> customersRetrieve = new ArrayList<>();
    DatabaseReference databaseRef;
    FirebaseAuth mAuth;
    View v;
    String [] name;
    int [] phone;
    String [] email;
    String [] location;
    String [] time;
    String [] service;
    private String currentUserId;

    public FragmentBssDbRequest() {
        // Required empty public constructor
    }

//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        databaseRef= FirebaseDatabase.getInstance().getReference(" Request from users");
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)   {
  View v=inflater.inflate(R.layout.fragment_fragment_bss_db_request,container,false);

        listUserRequest =(ListView) v.findViewById(R.id.UserRequest);

//        UserRequestAdapter adapter = new UserRequestAdapter(getActivity(),0,customers,name,phone,email,location,time,service);
//        listUserRequest.setAdapter(adapter);
        databaseRef= FirebaseDatabase.getInstance().getReference(" Request from users");
//        listUserRequest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            Intent intent = new Intent(getContext(), InstallationDetailsOne.class);
//            startActivity(intent);

//        }
//    });
        mAuth=FirebaseAuth.getInstance();
        currentUserId=mAuth.getCurrentUser().getUid();
        databaseRef= FirebaseDatabase.getInstance().getReference(" Request from users");
         return v;
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
this.v=view;
loaddata();

    }

    @Override
    public void onStart() {
        super.onStart();

    }
    private  void loaddata(){

        databaseRef=FirebaseDatabase.getInstance().getReference(" Request from users");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customers.clear();

                for (DataSnapshot userSnap : dataSnapshot.getChildren()) {

customersRetrieve.add(userSnap.getKey().toLowerCase());
                    customers.add(userSnap.getValue(InstallationCustomer.class));
                }
                UserRequestAdapter adapter = new UserRequestAdapter(getContext(), 0,customers,name,phone,email,location,time,service);

                listUserRequest.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}





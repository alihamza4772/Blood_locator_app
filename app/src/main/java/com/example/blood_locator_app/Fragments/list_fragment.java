package com.example.blood_locator_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blood_locator_app.Activities.RecycleViewAdapter;
import com.example.blood_locator_app.JavaClasses.Users;
import com.example.blood_locator_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class list_fragment extends Fragment {
    ArrayList<Users> list = new ArrayList<>();
    RecycleViewAdapter myAdapter;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Bundle bun;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listfragmentlayout,container,false);
         bun = getArguments();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView =  (RecyclerView) getActivity().findViewById(R.id.recvie);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        myAdapter = new RecycleViewAdapter(getContext(), list);
        myAdapter.setHasStableIds(true);
        recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot keynode : dataSnapshot.getChildren())
                {
                    if(dataSnapshot.getChildrenCount() > 0) {
                        Users std = new Users();
                        int aplus = bun.getInt("aplus");
                        int oplus = bun.getInt("oplus");
                        int bplus = bun.getInt("bplus");
                        int abplus = bun.getInt("abplus");
                        int aneg = bun.getInt("aneg");
                        int oneg = bun.getInt("oneg");
                        int abneg = bun.getInt("abneg");
                        int bneg = bun.getInt("bneg");
                        int b = bun.getInt("b");
                        std = keynode.getValue(Users.class);
                        if(aplus==1) {
                            if (std.getBloodgroup().equals("A+")) {
                                list.add(std);
                            }
                        }
                        else if(aneg==1)
                        {
                            if (std.getBloodgroup().equals("A-")) {
                                list.add(std);
                            }
                        }
                        else if(oplus==1)
                        {
                            if (std.getBloodgroup().equals("O+")) {
                                list.add(std);
                            }
                        }
                        else if(abplus==1)
                        {
                            if (std.getBloodgroup().equals("AB+")) {
                                list.add(std);
                            }
                        }
                        else if(oneg==1)
                        {
                            if (std.getBloodgroup().equals("O-")) {
                                list.add(std);
                            }
                        }
                        else if(bplus==1)
                        {
                            if (std.getBloodgroup().equals("B+")) {
                                list.add(std);
                            }
                        }
                        else if(bneg==1)
                        {
                            if (std.getBloodgroup().equals("B-")) {
                                list.add(std);
                            }
                        }
                        else if(abneg==1)
                        {
                            if (std.getBloodgroup().equals("AB-")) {
                                list.add(std);
                            }
                        }
                        else if(b==1)
                        {
                            if (std.getBloodgroup().equals("B")) {
                                list.add(std);
                            }
                        }
                        else
                        {
                            list.add(std);
                        }
                    }
                    else
                        return;
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}


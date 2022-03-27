package com.example.toserveyou.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.device_class;
import com.example.toserveyou.admin.admin_class.adapter_device_search;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class find_device extends Fragment {
    private RecyclerView admin_search;
    private ArrayList<device_class> arrayList;
    private DatabaseReference database;
    private SearchView search;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_find_device, container, false);
        intialize_recycler(v);
        firebase_tools();
        get_device_fromdatabase();
        searchview_method(v);
        return v;
    }
    private void firebase_tools()
    {
        database= FirebaseDatabase.getInstance().getReference();
    }
    private void intialize_recycler(View v)
    {
        admin_search=v.findViewById(R.id.admin_search_device);
        admin_search.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList=new ArrayList<>();
    }
    private void searchview_method(View v)
    {
         search=v.findViewById(R.id.search_view);
         search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String s) {
                 //get_device_fromdatabase(s);
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String s) {
                 get_device_searched(s);
                 return false;
             }
         });
    }

    private void get_device_searched(String s) {
        ArrayList<device_class>arr=new ArrayList<>();
         for(int i=0;i<arrayList.size();i++) {
             if(arrayList.get(i).getPhysical_address().contains(s))
             {
                 arr.add(arrayList.get(i));
             }
         }
         if(arr.size()==0)
         {
             adapter_device_search adapter=new adapter_device_search(arrayList,getContext());
             admin_search.setAdapter(adapter);
             adapter.notifyDataSetChanged();
         }
         else{
             adapter_device_search adapter=new adapter_device_search(arr,getContext());
             admin_search.setAdapter(adapter);
             adapter.notifyDataSetChanged();
         }
    }
    private void get_device_fromdatabase()
    {
        database.child("devices").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot snap:snapshot.getChildren())
                {

                        device_class device_class=new device_class(snap.child("physical_address").getValue().toString(),snap.child("program").getValue().toString(),
                                snap.child("pay").getValue().toString(),snap.child("notes").getValue().toString());
                        arrayList.add(device_class);


                }
                adapter_device_search adapter=new adapter_device_search(arrayList,getContext());
                admin_search.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
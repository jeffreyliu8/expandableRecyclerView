package com.askjeffreyliu.expandablerecyclerviewtestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<MyGroupChild> childrenA = new ArrayList<>();
        childrenA.add(new MyGroupChild("a", false));
        childrenA.add(new MyGroupChild("b", false));
        childrenA.add(new MyGroupChild("c", false));

        MyGroup group1 = new MyGroup("group 1", childrenA);


        List<MyGroupChild> childrenb = new ArrayList<>();
        childrenb.add(new MyGroupChild("1", false));
        childrenb.add(new MyGroupChild("2", false));
        childrenb.add(new MyGroupChild("3", false));

        MyGroup group2 = new MyGroup("group 2", childrenb);

        List<MyGroupChild> childrenc = new ArrayList<>();
        childrenc.add(new MyGroupChild("1", false));
        childrenc.add(new MyGroupChild("2", false));
        childrenc.add(new MyGroupChild("3", false));

        MyGroup group3 = new MyGroup("group 3", childrenc);


        List<MyGroup> myGroups = new ArrayList<>();
        myGroups.add(group1);
        myGroups.add(group2);
        myGroups.add(group3);
        myGroups.add(group1);
        myGroups.add(group2);
        myGroups.add(group3);
        myGroups.add(group1);
        myGroups.add(group2);
        myGroups.add(group3);
        myGroups.add(group1);
        myGroups.add(group2);
        myGroups.add(group3);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ListAdapter adapter = new ListAdapter(myGroups);
        mRecyclerView.setAdapter(adapter);

    }
}

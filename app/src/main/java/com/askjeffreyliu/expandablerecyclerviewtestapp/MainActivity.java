package com.askjeffreyliu.expandablerecyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<MyGroupChild> childrenA = new ArrayList<>();
        childrenA.add(new MyGroupChild("a", false));
        childrenA.add(new MyGroupChild("b", false));
        childrenA.add(new MyGroupChild("c", false));
        childrenA.add(new MyGroupChild("d", false));
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

        List<MyGroupChild> childrend = new ArrayList<>();
        childrend.add(new MyGroupChild("a", false));
        childrend.add(new MyGroupChild("b", false));
        childrend.add(new MyGroupChild("c", false));

        MyGroup group4 = new MyGroup("group 1", childrend);


        List<MyGroupChild> childrene = new ArrayList<>();
        childrene.add(new MyGroupChild("1", false));
        childrene.add(new MyGroupChild("2", false));
        childrene.add(new MyGroupChild("3", false));

        MyGroup group5 = new MyGroup("group 2", childrene);

        List<MyGroupChild> childrenf = new ArrayList<>();
        childrenf.add(new MyGroupChild("1", false));
        childrenf.add(new MyGroupChild("2", false));
        childrenf.add(new MyGroupChild("3", false));

        MyGroup group6 = new MyGroup("group 3", childrenf);

        List<MyGroupChild> childreng = new ArrayList<>();
        childreng.add(new MyGroupChild("a", false));
        childreng.add(new MyGroupChild("b", false));
        childreng.add(new MyGroupChild("c", false));

        MyGroup group7 = new MyGroup("group 1", childreng);


        List<MyGroupChild> childrenh = new ArrayList<>();
        childrenh.add(new MyGroupChild("1", false));
        childrenh.add(new MyGroupChild("2", false));
        childrenh.add(new MyGroupChild("3", false));

        MyGroup group8 = new MyGroup("group 2", childrenh);

        List<MyGroupChild> childreni = new ArrayList<>();
        childreni.add(new MyGroupChild("1", false));
        childreni.add(new MyGroupChild("2", false));
        childreni.add(new MyGroupChild("3", false));

        MyGroup group9 = new MyGroup("group 3", childreni);


        List<MyGroup> myGroups = new ArrayList<>();
        myGroups.add(group1);
        myGroups.add(group2);
        myGroups.add(group3);
        myGroups.add(group4);
        myGroups.add(group5);
        myGroups.add(group6);
        myGroups.add(group7);
        myGroups.add(group8);
        myGroups.add(group9);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ListAdapter adapter = new ListAdapter(myGroups, new ListAdapter.OnItemSelected() {
            @Override
            public void onChildClicked(int parentId, int childId) {
                Log.d("jeff", "selected " + parentId + " " + childId);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}

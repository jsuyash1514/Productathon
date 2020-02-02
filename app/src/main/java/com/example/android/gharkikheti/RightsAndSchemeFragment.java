package com.example.android.gharkikheti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class RightsAndSchemeFragment extends Fragment {
    RecyclerView recyclerView;
    List<RightsAndSchemeModel> list;
    RightsAndSchemeAdapter adapter;

    public RightsAndSchemeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rights_and_scheme, container, false);
        recyclerView = view.findViewById(R.id.rights_recyclerview);
        list = new ArrayList<>();
        adapter = new RightsAndSchemeAdapter(list);

        list.add(new RightsAndSchemeModel(getResources().getString(R.string.a1), getResources().getString(R.string.a2), getResources().getDrawable(R.drawable.a3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.b1), getResources().getString(R.string.b2), getResources().getDrawable(R.drawable.b3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.c1), getResources().getString(R.string.c2), getResources().getDrawable(R.drawable.c3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.d1), getResources().getString(R.string.d2), getResources().getDrawable(R.drawable.d3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.e1), getResources().getString(R.string.e2), getResources().getDrawable(R.drawable.e3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.f1), getResources().getString(R.string.f2), getResources().getDrawable(R.drawable.f3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.g1), getResources().getString(R.string.g2), getResources().getDrawable(R.drawable.g3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.h1), getResources().getString(R.string.h2), getResources().getDrawable(R.drawable.h3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.i1), getResources().getString(R.string.i2), getResources().getDrawable(R.drawable.i3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.j1), getResources().getString(R.string.j2), getResources().getDrawable(R.drawable.j3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.k1), getResources().getString(R.string.k2), getResources().getDrawable(R.drawable.k3)));
        list.add(new RightsAndSchemeModel(getResources().getString(R.string.l1), getResources().getString(R.string.l2), getResources().getDrawable(R.drawable.l3)));

        adapter.notifyDataSetChanged();
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }



}

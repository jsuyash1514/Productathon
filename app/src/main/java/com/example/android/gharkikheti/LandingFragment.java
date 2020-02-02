package com.example.android.gharkikheti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class LandingFragment extends Fragment {
    private ViewPager viewPager;
    private TabAdapter tabAdapter;

    public LandingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager=view.findViewById(R.id.viewpager);
        List<Fragment> fragment=new ArrayList<>();
        List<String> names=new ArrayList<>();
        fragment.add(new FeedFragment());
        fragment.add(new RightsAndSchemeFragment());
        fragment.add(new AskQuestionFragment());
        fragment.add(new ProfileFragment());
        names.add("FEED");
        names.add("GOVT. SCHEMES");
        names.add("ASK QUESTION");
        names.add("PROFILE");
        tabAdapter=new TabAdapter(getChildFragmentManager(),fragment,names);
        viewPager.setAdapter(tabAdapter);

    }
}

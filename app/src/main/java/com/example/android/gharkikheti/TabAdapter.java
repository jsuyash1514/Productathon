package com.example.android.gharkikheti;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    List<String> title;

    public TabAdapter(FragmentManager fragment, List<Fragment> list, List<String> title) {
        super(fragment);
        this.fragmentList = list;
        this.title= title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}

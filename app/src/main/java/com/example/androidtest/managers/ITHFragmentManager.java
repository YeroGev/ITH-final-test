package com.example.androidtest.managers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ITHFragmentManager {

    public static void addFragment(int containerId, FragmentManager fragmentManager,
                                       Fragment fragment ,String name) {
        fragmentManager.beginTransaction().add(containerId, fragment).addToBackStack(name).commit();
    }
    public static void addFragment(int containerId, FragmentManager fragmentManager,
                                   Fragment fragment ) {
        fragmentManager.beginTransaction().add(containerId, fragment).commit();
    }

    public static void replaceFragment(int containerId, FragmentManager fragmentManager,
                                       Fragment fragment) {
        fragmentManager.beginTransaction().replace(containerId, fragment).commit();
    }
}

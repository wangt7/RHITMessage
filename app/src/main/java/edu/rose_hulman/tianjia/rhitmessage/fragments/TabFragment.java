package edu.rose_hulman.tianjia.rhitmessage.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.rose_hulman.tianjia.rhitmessage.R;

/**
 * Created by wtj46 on 2016/7/22 0022.
 */
public class TabFragment extends Fragment{
   // private Callback mCallback;

    public TabFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }





}

package edu.rose_hulman.tianjia.rhitmessage.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import edu.rose_hulman.tianjia.rhitmessage.R;
import edu.rose_hulman.tianjia.rhitmessage.utils.Friend;
import edu.rose_hulman.tianjia.rhitmessage.utils.FriendAdapter;
import edu.rose_hulman.tianjia.rhitmessage.utils.Group;
import edu.rose_hulman.tianjia.rhitmessage.utils.GroupAdapter;

/**
 * Created by wtj46 on 2016/7/22 0022.
 */
public class GroupListFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private Callback mCallback;


    public GroupListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView view = (RecyclerView)inflater.inflate(R.layout.fragment_groups, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        GroupAdapter adapter  = new GroupAdapter(getContext(), mCallback);
        view.setAdapter(adapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement GroupListFragment.Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }


    public interface Callback {
        void onGroupSelect(Group group);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


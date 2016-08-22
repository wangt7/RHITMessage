package edu.rose_hulman.tianjia.rhitmessage.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.rose_hulman.tianjia.rhitmessage.Constants;
import edu.rose_hulman.tianjia.rhitmessage.R;
import edu.rose_hulman.tianjia.rhitmessage.Constants;
import edu.rose_hulman.tianjia.rhitmessage.utils.Friend;
import edu.rose_hulman.tianjia.rhitmessage.utils.FriendAdapter;

/**
 * Created by wtj46 on 2016/7/22 0022.
 */
public class FriendListFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private Callback mCallback;
    private FriendAdapter mFriendAdapter;
    private DatabaseReference mFriendListdRef;



    public FriendListFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mFriendListdRef = FirebaseDatabase.getInstance().getReference().child("friends");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView view = (RecyclerView)inflater.inflate(R.layout.fragment_friends, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        mFriendAdapter  = new FriendAdapter(mCallback, mFriendListdRef);
        view.setAdapter(mFriendAdapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FriendListFragment.Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        //mFriendListdRef.removeEventListener(mFriendAdapter);
    }

    public interface Callback {
        void onFriendSelect(Friend friend);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

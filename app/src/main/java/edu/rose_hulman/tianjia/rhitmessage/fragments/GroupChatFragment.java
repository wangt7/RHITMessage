package edu.rose_hulman.tianjia.rhitmessage.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.rose_hulman.tianjia.rhitmessage.R;
import edu.rose_hulman.tianjia.rhitmessage.utils.GroupChatAdapter;

/**
 * Created by wtj46 on 2016/8/7 0007.
 */
public class GroupChatFragment extends Fragment  {


    private Callback mCallback;


    public GroupChatFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView view = (RecyclerView)inflater.inflate(R.layout.fragment_groups, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        GroupChatAdapter adapter  = new GroupChatAdapter(getContext(), mCallback);
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

    }


}

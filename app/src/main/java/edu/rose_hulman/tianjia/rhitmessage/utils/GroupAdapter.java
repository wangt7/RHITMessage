package edu.rose_hulman.tianjia.rhitmessage.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import edu.rose_hulman.tianjia.rhitmessage.R;
import edu.rose_hulman.tianjia.rhitmessage.fragments.FriendListFragment;
import edu.rose_hulman.tianjia.rhitmessage.fragments.GroupListFragment;

/**
 * Created by wtj46 on 2016/7/31 0002.
 */
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{
    private ArrayList<Group> mGroups;
    private Callback mCallback;
    private DatabaseReference mGroupRef;

    public GroupAdapter(GroupListFragment.Callback callback, DatabaseReference newgroupRef){
        mCallback = (Callback) callback;
        mGroups = new ArrayList<>();
        mGroupRef = newgroupRef;
        mGroupRef.addChildEventListener(new GroupChildEventListener());
    }

    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder holder, int position) {
        final Group group = mGroups.get(position);
        holder.mGroupNameView.setText(group.getGroupname());
        holder.mGroupIconView.setImageResource(group.getGroupicon());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCallback.onGroupSelect(group);
            }
        });

    }

    class GroupChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Group group = dataSnapshot.getValue(Group.class);
            group.setKey(dataSnapshot.getKey());
            mGroups.add(0, group);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            Group updateGroup = dataSnapshot.getValue(Group.class);
            for (Group tmpgroup : mGroups) {
                if (tmpgroup.getKey().equals(key)) {
                    tmpgroup.setGroupname(updateGroup.getGroupname());
                    notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for (Group tmpgroup : mGroups) {
                if (tmpgroup.getKey().equals(key)) {
                    mGroups.remove(tmpgroup);
                    notifyDataSetChanged();
                    return;
                }
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }

    public void firebaseCreate(Group newgroup) {
        mGroupRef.push().setValue(newgroup);
    }

    public void firebaseRemove(Group group) {
        mGroupRef.child(group.getKey()).removeValue();
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    public interface Callback {
        void onGroupSelect(Group group);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mGroupNameView;
        private ImageView mGroupIconView;

        public ViewHolder(View itemView) {
            super(itemView);
            mGroupNameView = (TextView) itemView.findViewById(R.id.groupname_view);
            mGroupIconView = (ImageView) itemView.findViewById(R.id.groupicon_view);
        }
    }
}

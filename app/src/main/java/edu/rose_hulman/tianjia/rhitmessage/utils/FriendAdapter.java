package edu.rose_hulman.tianjia.rhitmessage.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

/**
 * Created by wtj46 on 2016/7/31 0002.
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder>{

    private ArrayList<Friend> mFriends;
    private Callback mCallback;
    private DatabaseReference mFriendRef;

    public FriendAdapter(FriendListFragment.Callback callback,DatabaseReference newfriendRef){
        mCallback = (Callback) callback;
        mFriends = new ArrayList<>();
        mFriendRef = newfriendRef;
        mFriendRef.addChildEventListener(new FriendChildEventListener());
        //tmptest();
    }

    public void tmptest(){
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
        mFriends.add(new Friend("wtj", R.drawable.rhit_icon));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.rhit_icon));
    }

    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendAdapter.ViewHolder holder, final int position) {
        final Friend friend = mFriends.get(position);
        holder.mFriendNameView.setText(friend.getFriendname());
        holder.mFriendIconView.setImageResource(friend.getFriendicon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onFriendSelect(friend);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mCallback.onFriendDelete(friend);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    class FriendChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Friend friend = dataSnapshot.getValue(Friend.class);
            friend.setKey(dataSnapshot.getKey());
            mFriends.add(0, friend);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            Friend updateFriend = dataSnapshot.getValue(Friend.class);
            for (Friend tmpfriend : mFriends) {
                if (tmpfriend.getKey().equals(key)) {
                    tmpfriend.setFriendname(updateFriend.getFriendname());
                    notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for (Friend tmpfriend : mFriends) {
                if (tmpfriend.getKey().equals(key)) {
                    mFriends.remove(tmpfriend);
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

    public interface Callback {
        void onFriendSelect(Friend friend);
        void onFriendDelete(Friend friend);
    }

    public void firebaseAdd(Friend newfriend) {
        mFriendRef.push().setValue(newfriend);
    }

    public void firebaseRemove(Friend friend) {
        mFriendRef.child(friend.getKey()).removeValue();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mFriendNameView;
        private ImageView mFriendIconView;

        public ViewHolder(View itemView) {
            super(itemView);
            mFriendNameView = (TextView) itemView.findViewById(R.id.friendname_view);
            mFriendIconView = (ImageView) itemView.findViewById(R.id.friendicon_view);
        }
    }
}

package edu.rose_hulman.tianjia.rhitmessage.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.rose_hulman.tianjia.rhitmessage.R;
import edu.rose_hulman.tianjia.rhitmessage.fragments.FriendListFragment;

/**
 * Created by wtj46 on 2016/7/31 0002.
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private ArrayList<Friend> mFriends;
    private Callback mCallback;
    //private firebase...

    public FriendAdapter(Callback callback){
        mCallback = callback;
        mFriends = new ArrayList<>();
        tmptest();
    }

    public FriendAdapter(Context context, FriendListFragment.Callback callback){
        mCallback = (Callback) callback;
        mFriends = new ArrayList<>();
        tmptest();
    }

    public void tmptest(){
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));
        mFriends.add(new Friend("wtj", R.drawable.ic_menu_gallery));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));
        mFriends.add(new Friend("Tianjia Wang", R.drawable.ic_menu_camera));

    }

    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendAdapter.ViewHolder holder, int position) {
        final Friend friend = mFriends.get(position);
        holder.mFriendNameView.setText(friend.getFriendname());
        holder.mFriendIconView.setImageResource(friend.getFriendicon());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //openChatPage
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public interface Callback {
        void onFriendSelect(Friend friend);
    }

    public void add(Friend newfriend){
        mFriends.add(newfriend);
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

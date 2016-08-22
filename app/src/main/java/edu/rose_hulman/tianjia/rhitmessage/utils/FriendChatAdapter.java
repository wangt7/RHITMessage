package edu.rose_hulman.tianjia.rhitmessage.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.rose_hulman.tianjia.rhitmessage.R;
import edu.rose_hulman.tianjia.rhitmessage.fragments.FriendChatFragment;
import edu.rose_hulman.tianjia.rhitmessage.fragments.GroupChatFragment;

/**
 * Created by wtj46 on 2016/8/7 0007.
 */
public class FriendChatAdapter extends RecyclerView.Adapter<FriendChatAdapter.ViewHolder>  {
    private ArrayList<Message> mFriendMessages;
    private Callback mCallback;

    public FriendChatAdapter(Callback callback){
        mCallback = callback;
        mFriendMessages = new ArrayList<>();
        tmptest();
    }

    public FriendChatAdapter(Context context, FriendChatFragment.Callback callback){
        mCallback = (Callback) callback;
        mFriendMessages = new ArrayList<>();
        tmptest();
    }

    public void tmptest(){
        mFriendMessages.add(new Message("Tianjia Wang", "Dr.Boutell", "Hello"));
        mFriendMessages.add(new Message("Dr.Boutell", "Tianjia Wang", "Hi"));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendchat_card_view, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FriendChatAdapter.ViewHolder holder, int position) {
        final Message message = mFriendMessages.get(position);
        holder.mFriendMessageView.setText(message.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mFriendMessages.size();
    }

    public interface Callback {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mFriendMessageView;


        public ViewHolder(View itemView) {
            super(itemView);
            mFriendMessageView = (TextView) itemView.findViewById(R.id.friendmessage_view);
        }
    }
}

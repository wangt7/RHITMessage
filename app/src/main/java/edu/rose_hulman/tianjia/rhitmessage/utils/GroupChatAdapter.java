package edu.rose_hulman.tianjia.rhitmessage.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.rose_hulman.tianjia.rhitmessage.R;
import edu.rose_hulman.tianjia.rhitmessage.fragments.GroupChatFragment;

/**
 * Created by wtj46 on 2016/8/7 0007.
 */
public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.ViewHolder> {
    private ArrayList<Message> mGroupMessages;
    private Callback mCallback;

    public GroupChatAdapter(Callback callback){
        mCallback = callback;
        mGroupMessages = new ArrayList<>();
        tmptest();
    }

    public GroupChatAdapter(Context context, GroupChatFragment.Callback callback){
        mCallback = (Callback) callback;
        mGroupMessages = new ArrayList<>();
        tmptest();
    }

    public void tmptest(){
        mGroupMessages.add(new Message("CSSE483", "wangt5", "Hello Everyone"));
        mGroupMessages.add(new Message("CSSE230", "Tianjia Wang", "Today's homework is to finish the lab0123456789"));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.groupchat_card_view, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(GroupChatAdapter.ViewHolder holder, int position) {
        final Message message = mGroupMessages.get(position);
        holder.mGroupMessageView.setText(message.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mGroupMessages.size();
    }

    public interface Callback {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mGroupMessageView;


        public ViewHolder(View itemView) {
            super(itemView);
            mGroupMessageView = (TextView) itemView.findViewById(R.id.groupmessage_view);
        }
    }
}

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
import edu.rose_hulman.tianjia.rhitmessage.fragments.MessageListFragment;

/**
 * Created by wtj46 on 2016/8/6 0006.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private ArrayList<Message> mMessages;
    private Callback mCallback;

    public MessageAdapter(Callback callback){
        mCallback = callback;
        mMessages = new ArrayList<>();
        tmptest();
    }

    public MessageAdapter(Context context, MessageListFragment.Callback callback){
        mCallback = (Callback) callback;
        mMessages = new ArrayList<>();
        tmptest();
    }

    public void tmptest(){
        mMessages.add(new Message("Tianjia Wang", "Dr.Boutell", "Hello"));
        mMessages.add(new Message("Dr.Boutell", "Tianjia Wang", "Hi"));
        mMessages.add(new Message("Tianjia", "Terry", "HelloHelloHelloHelloHelloHelloHelloHelloHelloHello"));
        mMessages.add(new Message("Tianjia", "Terry", "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello"));
        mMessages.add(new Message("Tianjia", "Terry", "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello"));
        mMessages.add(new Message("Tianjia", "Terry", "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello"));
        mMessages.add(new Message("Tianjia", "Terry", "!@%^%$&^#@%$&^%$#@%$^&%@#^&%*^(*#&$)&(@#*$&(*^@$*(&^#@*&$%)_!#*($+)*(++!#+_)"));

    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {
        final Message message = mMessages.get(position);
        holder.mMessageNameView.setText(message.getReceiver());
        //holder.mMessageIconView.setImageResource();
        String tmp = message.getContent();
        if(tmp.length() > 120) {
            tmp = tmp.substring(0, 120);
        }
        holder.mMessageInfoView.setText(tmp);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public interface Callback {
        void onMessageSelect(Message message);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mMessageNameView;
        private ImageView mMessageIconView;
        private TextView mMessageInfoView;

        public ViewHolder(View itemView) {
            super(itemView);
            mMessageNameView = (TextView) itemView.findViewById(R.id.messagename_view);
            mMessageIconView = (ImageView) itemView.findViewById(R.id.messageicon_view);
            mMessageInfoView = (TextView) itemView.findViewById(R.id.messageinfo_view);
        }
    }
}

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
import edu.rose_hulman.tianjia.rhitmessage.fragments.GroupListFragment;

/**
 * Created by wtj46 on 2016/7/31 0002.
 */
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{
    private ArrayList<Group> mGroups;
    private Callback mCallback;
    //private firebase...

    public GroupAdapter(Callback callback){
        mCallback = callback;
        mGroups = new ArrayList<>();
        tmptest();
    }

    public GroupAdapter(Context context, GroupListFragment.Callback callback){
        mCallback = (Callback) callback;
        mGroups = new ArrayList<>();
        tmptest();
    }

    public void tmptest(){
        mGroups.add(new Group("My Group", R.drawable.ic_menu_camera));
        mGroups.add(new Group("Your Group", R.drawable.ic_menu_gallery));
        mGroups.add(new Group("CSSE220", R.drawable.ic_menu_camera));
        mGroups.add(new Group("CSSE230", R.drawable.ic_menu_camera));
        mGroups.add(new Group("CSSE333", R.drawable.ic_menu_camera));
        mGroups.add(new Group("CSSE481", R.drawable.ic_menu_camera));
        mGroups.add(new Group("CSSE483", R.drawable.ic_menu_camera));
        mGroups.add(new Group("CSSE486", R.drawable.ic_menu_camera));
        mGroups.add(new Group("CSSE999", R.drawable.ic_menu_camera));
        mGroups.add(new Group("CSSE000", R.drawable.ic_menu_camera));


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

            }
        });

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

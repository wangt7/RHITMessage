package edu.rose_hulman.tianjia.rhitmessage.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.rose_hulman.tianjia.rhitmessage.R;
import edu.rose_hulman.tianjia.rhitmessage.fragments.FriendChatFragment;
import edu.rose_hulman.tianjia.rhitmessage.fragments.FriendListFragment;
import edu.rose_hulman.tianjia.rhitmessage.fragments.GroupChatFragment;
import edu.rose_hulman.tianjia.rhitmessage.fragments.GroupListFragment;
import edu.rose_hulman.tianjia.rhitmessage.fragments.LoginFragment;
import edu.rose_hulman.tianjia.rhitmessage.fragments.MessageListFragment;
import edu.rosehulman.rosefire.Rosefire;
import edu.rosehulman.rosefire.RosefireResult;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FriendAdapter.Callback,FriendListFragment.Callback,
        GroupAdapter.Callback, GroupListFragment.Callback,MessageAdapter.Callback, MessageListFragment.Callback, FriendChatAdapter.Callback, FriendChatFragment.Callback,
        GroupChatAdapter.Callback, GroupChatFragment.Callback, LoginFragment.OnLoginListener {

    private FriendAdapter mFriendAdapter;
    private GroupAdapter mGroupAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private static final int RC_ROSEFIRE_LOGIN = 1;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    OnCompleteListener mOnCompleteListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //mToggle.setDrawerIndicatorEnabled(false);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mAuth = FirebaseAuth.getInstance();
        initializeListeners();

        DatabaseReference friendRef = FirebaseDatabase.getInstance().getReference().child("friends");
        mFriendAdapter = new FriendAdapter(this, friendRef);
        DatabaseReference groupRef = FirebaseDatabase.getInstance().getReference().child("groups");
        mGroupAdapter = new GroupAdapter(this, groupRef);

    }

    private void initializeListeners(){
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    switchToMessageListFragment();
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    mToggle.setDrawerIndicatorEnabled(true);
                } else {
                    switchToLoginFragment();
                    mToggle.setDrawerIndicatorEnabled(false);
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }
        };
        mOnCompleteListener = new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (!task.isSuccessful()){
                    showLoginError("Login failed");
                    mToggle.setDrawerIndicatorEnabled(false);
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthStateListener != null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_ROSEFIRE_LOGIN){
            RosefireResult result = Rosefire.getSignInResultFromIntent(data);
            if (result.isSuccessful()) {
                mAuth.signInWithCustomToken(result.getToken())
                        .addOnCompleteListener(this,mOnCompleteListener);
            } else {
                showLoginError("Rosefire sign-in failed");
            }
        }
    }

    @Override
    public void onRosefireLogin() {
        //TODO: Log user in with RoseFire account
        Intent signInIntent = Rosefire.getSignInIntent(this, getString(R.string.rosefire_key));
        startActivityForResult(signInIntent, RC_ROSEFIRE_LOGIN);
    }

    public void onLogout() {
        //TODO: Log the user out.
        mAuth.signOut();
        switchToLoginFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_mymessages) {
            switchToMessageListFragment();
        }
        else if (id == R.id.nav_myfriends) {
            switchToFriendListFragment();
        }
        else if (id == R.id.nav_mygroups) {
            switchToGroupListFragment();
        }

        else if (id == R.id.nav_addfriend) {
            showAddFriendDialog();
        }
        else if (id == R.id.nav_joingroup) {
            showJoinGroupDialog();
        }
        else if (id == R.id.nav_creategroup) {
            showCreateGroupDialog();
        }
        else if(id == R.id.nav_setting){
            switchToFriendChatFragment();
        }
        else if(id == R.id.nav_logout){
            onLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void switchToFriendListFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment friendListFragment = new FriendListFragment();
        ft.replace(R.id.fragment_container,friendListFragment, "Friends");
        ft.addToBackStack("Friend");
        ft.commit();
    }

    public void switchToGroupListFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment groupListFragment = new GroupListFragment();
        ft.replace(R.id.fragment_container,groupListFragment, "Groups");
        ft.addToBackStack("Group");
        ft.commit();
    }

    public void switchToMessageListFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment messageListFragment = new MessageListFragment();
        ft.replace(R.id.fragment_container,messageListFragment, "Messages");
        ft.addToBackStack("Message");
        ft.commit();
    }

    public void switchToFriendChatFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment friendChatFragment = new FriendChatFragment();
        ft.replace(R.id.fragment_container, friendChatFragment, "FriendChat");
        ft.addToBackStack("FriendChat");
        ft.commit();
    }

    public void switchToGroupChatFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment groupChatFragment = new GroupChatFragment();
        ft.replace(R.id.fragment_container,groupChatFragment, "GroupChat");
        ft.addToBackStack("GroupChat");
        ft.commit();
    }

    private void switchToLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new LoginFragment(), "Login");
        ft.commit();
    }

    @Override
    public void onFriendSelect(Friend friend) {
        switchToFriendChatFragment();
    }

    @Override
    public void onFriendDelete(Friend friend) {
        showDeleteFriendDialog(friend);
    }

    @Override
    public void onGroupSelect(Group group) {
        switchToGroupChatFragment();
    }



    public void showAddFriendDialog(){
        DialogFragment df = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.add_friend_title);
                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_friend, null, false);
                builder.setView(view);
                final EditText friendIDEditText = (EditText) view.findViewById(R.id.dialog_friend_id);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Friend newfriend = new Friend(friendIDEditText.getText().toString(), R.drawable.rhit_icon);
                        mFriendAdapter.firebaseAdd(newfriend);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);

                return builder.create();
            }
        };
        df.show(getSupportFragmentManager(), "addfriend");
    }

    public void showJoinGroupDialog(){
        DialogFragment df = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.join_group_title);
                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_join_group, null, false);
                builder.setView(view);
                final EditText groupIDEditText = (EditText) view.findViewById(R.id.dialog_join_group_id);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Group newgroup = new Group(groupIDEditText.getText().toString(), R.drawable.rhit_group);
                        mGroupAdapter.firebaseCreate(newgroup);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);

                return builder.create();
            }
        };
        df.show(getSupportFragmentManager(), "joingroup");
    }

    public void showCreateGroupDialog(){
        DialogFragment df = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.create_group_title);
                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_create_group, null, false);
                builder.setView(view);
                final EditText groupNameEditText = (EditText) view.findViewById(R.id.dialog_create_group_name);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Group newgroup = new Group(groupNameEditText.getText().toString(), R.drawable.rhit_group);
                        mGroupAdapter.firebaseCreate(newgroup);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);

                return builder.create();
            }
        };
        df.show(getSupportFragmentManager(), "creategroup");
    }

    private void showDeleteFriendDialog(final Friend friend) {
        DialogFragment df = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete this friend?");
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mFriendAdapter.firebaseRemove(friend);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);

                return builder.create();
            }
        };
        df.show(getSupportFragmentManager(), "delete");
    }



    private void showLoginError(String message) {
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag("Login");
        loginFragment.onLoginError(message);
    }

    @Override
    public void onMessageSelect(Message message) {

    }
}

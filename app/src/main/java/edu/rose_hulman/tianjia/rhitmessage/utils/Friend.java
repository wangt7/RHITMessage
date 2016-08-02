package edu.rose_hulman.tianjia.rhitmessage.utils;

/**
 * Created by wtj46 on 2016/7/22 0022.
 */
public class Friend {
    private String friendname;
    private int friendicon;

    public Friend(String newname, int newicon){
        friendname = newname;
        friendicon = newicon;
    }

    public String getFriendname() {
        return friendname;
    }

    public void setFriendname(String friendname) {
        this.friendname = friendname;
    }

    public int getFriendicon() {
        return friendicon;
    }

    public void setFriendicon(int friendicon) {
        this.friendicon = friendicon;
    }
}

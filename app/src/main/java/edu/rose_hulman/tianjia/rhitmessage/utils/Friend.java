package edu.rose_hulman.tianjia.rhitmessage.utils;

/**
 * Created by wtj46 on 2016/7/22 0022.
 */
public class Friend {
    private String friendname;
    private int friendicon;
    private String key;

    public Friend(){

    }

    public Friend(String newname){
        friendname = newname;
    }

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

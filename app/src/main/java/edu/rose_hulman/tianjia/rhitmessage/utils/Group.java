package edu.rose_hulman.tianjia.rhitmessage.utils;

import java.util.ArrayList;

/**
 * Created by wtj46 on 2016/7/22 0022.
 */
public class Group {
    private String groupname;
    private int groupicon;
    private ArrayList<String> groupmember;
    private String key;

    public Group(){

    }

    public Group(String newname, int newicon){
        groupname = newname;
        groupicon = newicon;
        groupmember = new ArrayList<>();
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public int getGroupicon() {
        return groupicon;
    }

    public void setGroupicon(int groupicon) {
        this.groupicon = groupicon;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<String> getGroupmember() {
        return groupmember;
    }

    public void setGroupmember(ArrayList<String> groupmember) {
        this.groupmember = groupmember;
    }
}

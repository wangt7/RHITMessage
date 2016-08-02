package edu.rose_hulman.tianjia.rhitmessage.utils;

/**
 * Created by wtj46 on 2016/7/22 0022.
 */
public class Group {
    private String groupname;
    private int groupicon;

    public Group(String newname, int newicon){
        groupname = newname;
        groupicon = newicon;
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
}

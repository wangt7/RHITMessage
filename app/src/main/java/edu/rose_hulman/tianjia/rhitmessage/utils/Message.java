package edu.rose_hulman.tianjia.rhitmessage.utils;

/**
 * Created by wtj46 on 2016/7/22 0022.
 */
public class Message {
    private String content;
    private String sender;
    private String receiver;
    private String time;
    private String key;

    public Message(String newsender, String newreceiver, String newcontent){
        sender = newsender;
        receiver = newreceiver;
        content = newcontent;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

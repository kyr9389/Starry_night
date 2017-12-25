package com.example.tcp.starrynight;

import java.util.Date;

/**
 * Created by tcp on 2017-09-07.
 */

public class list_item {

    private String nickname;
    private String title;
    private String content;
    private String no;

    public list_item()
    {
        super();
    }

    public list_item(String nickname, String title, String content, String no) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.no = no;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) { this.content = content; }

    public String getNo() { return no; }

    public void setNo(String no) { this.no = no; }

}

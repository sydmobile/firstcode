package com.example.firstcode.third_chapter;

/**
 * 说明：$
 * <p>
 * date: 2019-11-09 09:38
 *
 * @author syd
 * @version 1.0
 */
public class Msg {
    public static final  int TYPE_RECEIVE = 0;
    public static final int TYPE_SEND = 1;
    private String content;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    private int type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

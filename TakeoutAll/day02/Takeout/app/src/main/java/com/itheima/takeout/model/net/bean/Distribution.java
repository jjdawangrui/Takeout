package com.itheima.takeout.model.net.bean;

/**
 * 配送信息
 */
public class Distribution {
    // 配送方式
    private String type;
    // 配送说明
    private String des;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}

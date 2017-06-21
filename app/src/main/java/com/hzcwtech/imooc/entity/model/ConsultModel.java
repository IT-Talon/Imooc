package com.hzcwtech.imooc.entity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Talon on 2017/6/20.
 */

public class ConsultModel implements Parcelable {

    /**
     * answer : 有的，150天的有效期，还有两次的免费延期机会，每次各延一个月。也就是说你最多可以有210天的学习时间哦。赶快行动吧！
     * content : 这是有时间限制的吗？
     * create_time : 2017-03-11
     * id : 2144
     * mypraise : 0
     * praise : 35
     */

    private String answer;
    private String content;
    private String create_time;
    private String id;
    private int mypraise;
    private String praise;

    public ConsultModel() {

    }

    protected ConsultModel(Parcel in) {
        answer = in.readString();
        content = in.readString();
        create_time = in.readString();
        id = in.readString();
        mypraise = in.readInt();
        praise = in.readString();
    }

    public static final Creator<ConsultModel> CREATOR = new Creator<ConsultModel>() {
        @Override
        public ConsultModel createFromParcel(Parcel in) {
            return new ConsultModel(in);
        }

        @Override
        public ConsultModel[] newArray(int size) {
            return new ConsultModel[size];
        }
    };

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMypraise() {
        return mypraise;
    }

    public void setMypraise(int mypraise) {
        this.mypraise = mypraise;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(answer);
        dest.writeString(content);
        dest.writeString(create_time);
        dest.writeString(id);
        dest.writeInt(mypraise);
        dest.writeString(praise);
    }
}

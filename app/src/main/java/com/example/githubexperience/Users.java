package com.example.githubexperience;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


//Followers, Followings y URL Avatar
public class Users implements Serializable {

    @SerializedName("followers")
    @Expose
    private int followers;

    @SerializedName("following")
    @Expose
    private int following;

    @SerializedName("avatar_url")
    @Expose
    private String avatar;

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
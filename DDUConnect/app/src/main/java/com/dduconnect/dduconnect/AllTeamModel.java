package com.dduconnect.dduconnect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllTeamModel {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("members_count")
    @Expose
    private String membersCount;
    @SerializedName("members")
    @Expose
    private List<Member> members = null;

    public AllTeamModel(String title, String membersCount, List<Member> members) {
        this.title = title;
        this.membersCount = membersCount;
        this.members = members;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(String membersCount) {
        this.membersCount = membersCount;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}

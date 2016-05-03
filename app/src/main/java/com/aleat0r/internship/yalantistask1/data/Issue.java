package com.aleat0r.internship.yalantistask1.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */

public class Issue implements Serializable {

    private long mId;
    private String mCategory;
    private State mState;
    private Date mCreated;
    private Date mRegistered;
    private Date mDeadline;
    private String mResponsible;
    private String mFullText;
    private int mIconId;
    private int mLikeAmount;

    public Issue(long id, String category, State state, Date created, Date registered, Date deadline, String responsible,
                 int iconId, int likeAmount, String fullText) {
        this.mId = id;
        this.mCategory = category;
        this.mState = state;
        this.mCreated = created;
        this.mRegistered = registered;
        this.mDeadline = deadline;
        this.mResponsible = responsible;
        this.mIconId = iconId;
        this.mLikeAmount = likeAmount;
        this.mFullText = fullText;
    }

    public long getID() {
        return mId;
    }

    public void setID(long id) {
        this.mId = id;
    }

    public String getFullText() {
        return mFullText;
    }

    public void setFullText(String fullText) {
        mFullText = fullText;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        this.mState = state;
    }

    public Date getCreated() {
        return mCreated;
    }

    public void setCreated(Date created) {
        this.mCreated = created;
    }

    public Date getRegistered() {
        return mRegistered;
    }

    public void setRegistered(Date registered) {
        this.mRegistered = registered;
    }

    public Date getDeadline() {
        return mDeadline;
    }

    public void setDeadline(Date deadline) {
        this.mDeadline = deadline;
    }

    public String getResponsible() {
        return mResponsible;
    }

    public void setResponsible(String responsible) {
        this.mResponsible = responsible;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        this.mIconId = iconId;
    }

    public int getLikeAmount() {
        return mLikeAmount;
    }

    public void setLikeAmount(int likeAmount) {
        this.mLikeAmount = likeAmount;
    }

    public int getDaysAmount() {
        Date currentDate = new Date();
        return (int) ((currentDate.getTime() - mCreated.getTime()) / (1000 * 24 * 3600));
    }
}
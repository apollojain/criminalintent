package com.example.apollo.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Apollo on 5/18/2015.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId(){
        return mId;
    }
    @Override
    public String toString(){
        return mTitle;
    }
    public String getTitle(){
       return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public void setDate(Date date){
        mDate = date;
    }
    public Date getDate(){
        return mDate;
    }

    public boolean isSolved(){
        return mSolved;
    }
    public void setSolved(boolean solved){
        mSolved = solved;
    }
}

package edu.up.campus.regier21.dominiongamestate;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DominionCardState {
    protected String mTitle;
    protected int mPhotoId;
    protected String mText;
    protected int mCost;
    protected String mType;
    protected int mAmount;
    transient Method action;

    public DominionCardState(DominionCardState jsonCard) {
        //this = jsonCard;
    }

    public DominionCardState (String name, int photoId, String text, int cost, String type, int amount){
        mTitle = name;
        mPhotoId = photoId;
        mText = text;
        mCost = cost;
        mType = type;
        mAmount = amount;
    }

    public void setmAmount(int mAmount) {
        this.mAmount = mAmount;
    }

    public boolean cardAction() {
        try {
            action.invoke(this); //return state after it recognizes boolean nature
            return true;
        }
        catch (IllegalArgumentException e) {

        }
        catch (IllegalAccessException e) {

        }
        catch (InvocationTargetException e) {

        }
        return false;
    }
}

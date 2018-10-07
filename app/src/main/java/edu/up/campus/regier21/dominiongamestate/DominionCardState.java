package edu.up.campus.regier21.dominiongamestate;


import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.content.ContentValues.TAG;

public class DominionCardState {
    private final String title;
    private final String photoID;
    private final String text;
    private final int cost;
    private final String type;
    private final int amount;
    private final Method action;

    public DominionCardState (String name, String photoStringID, String text, int cost, String type, int amount, String action){
        this.title = name;

        this.photoID = photoStringID;
        this.text = text;
        this.cost = cost;
        this.type = type;
        this.amount = amount;

        this.action = getMethod(action);
    }

    /**
     * TODO: External citation
     * Date: 10/4
     * Source: https://stackoverflow.com/questions/13604111/final-variable-assignment-with-try-catch
     * Problem: wouldn't let action be assigned if final
     * Solution: used method to get Method
     */
    private Method getMethod(String action){
        try {
            return DominionCardState.class.getDeclaredMethod(action);
        }
        catch (NoSuchMethodException e) {
            Log.e(TAG, "Error encountered reflecting action method: " + e);
            return null;
        }
    }

    public boolean cardAction() {
        try {
            action.invoke(this); //return state after it recognizes boolean nature
            return true;
        }
        catch (IllegalArgumentException e) {
            Log.e(TAG, "Illegal argument encountered when running reflected action method: " + e);
        }
        catch (IllegalAccessException e) {
            Log.e(TAG, "Illegal access encountered when running reflected action method: " + e);
        }
        catch (InvocationTargetException e) {
            Log.e(TAG, "Invalid target encountered when running reflected action method: " + e);
        }
        return false;
    }

    @Override
    public String toString() {
        return "\nDominionCardState: {\n" +
                "\ttitle: " + getTitle() + ",\n" +
                "\tmPhotoId: " + getPhotoId() + ",\n" +
                "\ttext: " + getPlainText() + ",\n" +
                "\tmCost: " + getmCost() + ",\n" +
                "\ttype: " + getType() + ",\n" +
                "\tamount: " + getAmount() + ",\n" +
                "\taction: " + getAction() + ",\n" +
                "},";
    }

    /*public void setAmount(int amount) {
        this.amount = amount;
    }*/

    public String getTitle() {
        return title;
    }

    public String getPhotoId() {
        return photoID;
    }

    public String getFormattedText() {
        return text;
    }

    public String getPlainText() {
        return text.replaceAll("[\\s]", " ");
    }

    public int getmCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public Method getAction() {
        return action;
    }

    //Card Action Methods
    private static boolean festivalAction() {
        return true;
    }

    private static boolean harbingerAction() {
        return true;
    }

    private static boolean merchantAction() {
        return true;
    }

    private static boolean remodelAction() {
        return true;
    }

    private static boolean throneAction() {
        return true;
    }

    private static boolean artisanAction() {
        return true;
    }

    private static boolean witchAction() {
        return true;
    }

    private static boolean libraryAction() {
        return true;
    }

    private static boolean laboratoryAction() {
        return true;
    }

    private static boolean militiaAction() {
        return true;
    }

    private static boolean copperAction() {
        return true;
    }

    private static boolean estateAction() {
        return true;
    }

    private static boolean silverAction() {
        return true;
    }

    private static boolean duchyAction() {
        return true;
    }

    private static boolean goldAction() {
        return true;
    }

    private static boolean provinceAction() {
        return true;
    }
}

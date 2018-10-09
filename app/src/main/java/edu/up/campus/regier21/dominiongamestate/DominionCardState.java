package edu.up.campus.regier21.dominiongamestate;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.content.ContentValues.TAG;

/**
 * A data class intended to represent the state of a card object
 * @author Julian Donovan
 */
public class DominionCardState {
    private final String title;
    private final String photoID;
    private final String text;
    private final int cost;
    private final DominionCardType type;
    private final Method action;

    public DominionCardState (String name, String photoStringID, String text, int cost, String type, String action){
        this.title = name;

        this.photoID = photoStringID;
        this.text = text;
        this.cost = cost;
        this.type = DominionCardType.getTypeFromString(type);
        if (this.type == null){
            Log.e(TAG, "Illegal type for card " + this.title);
        }

        //Dynamically assigned by method reflection, allowing for a String method reference to be held in JSON
        this.action = getMethod(action);
    }

    /**
     * TODO: External citation
     * Date: 10/4
     * Source: https://stackoverflow.com/questions/13604111/final-variable-assignment-with-try-catch
     * Problem: wouldn't let action be assigned if final
     * Solution: used method to get Method
     */

    /**
     * Reflection wrapper, allowing for the string function reference as stored in JSON to be
     * translated to the relevant function
     * @param action A String name referencing the relevant DominionCardState function
     * @return A Method reference to a DominionCardState function
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

    /**
     * A method wrapped allowing for any DominionCardState object to invoke the function stored in
     * their action instance variable. Handles common Method errors.
     * @return A boolean regarding the success of the action invocation
     */
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

    /**
     * Overrides the default inherited toString() behavior, properly displaying object data
     * @return A String containing object type, title, photoId, text, cost, type, amount and action info
     */
    @Override
    public String toString() {
        return "\nDominionCardState: {\n" +
                "\ttitle: " + getTitle() + ",\n" +
                "\tphotoId: " + getPhotoId() + ",\n" +
                "\ttext: " + getPlainText() + ",\n" +
                "\tcost: " + getCost() + ",\n" +
                "\ttype: " + getType() + ",\n" +
                "\taction: " + getAction() + ",\n" +
                "},";
    }

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

    public int getCost() {
        return cost;
    }

    public DominionCardType getType() {
        return type;
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

    private static boolean basicAction(){
        return true;
    }
}

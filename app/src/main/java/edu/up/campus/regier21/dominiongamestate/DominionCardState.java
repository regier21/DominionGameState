package edu.up.campus.regier21.dominiongamestate;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.content.ContentValues.TAG;

/**
 * A data class intended to represent the state of a card object.
 * Only one instance should be created per unique card.
 *
 * @author Julian Donovan
 */
public class DominionCardState {
    //Card attributes
    //Final because only one instance is made per card. Changing an attribute would change all copies
    private final String title;
    private final String photoID;
    private final String text;
    private final int cost;
    private final DominionCardType type;
    private final Method action;
    private final int addedTreasure;
    private final int addedActions;
    private final int addedDraw;
    private final int addedBuys;
    private final int victoryPoints;

    /**
     * Constructor
     *
     * @param name The card name
     * @param photoStringID The name of the drawable for the card art
     * @param text The card description
     * @param cost The cost of the card
     * @param type The type of the card. Converted into DominionCardType - must match name exactly
     * @param action The name of the function called when card is played.
     * @param addedTreasure The treasure the card gives when played.
     * @param addedActions The actions the card gives when played.
     * @param addedDraw The cards drawn when played.
     * @param addedBuys The buys the card gives when plays.
     * @param victoryPoints The worth of the card in victory points.
     */
    public DominionCardState (String name, String photoStringID, String text, int cost, String type, String action,
                              int addedTreasure, int addedActions, int addedDraw, int addedBuys, int victoryPoints){
        this.title = name;

        this.photoID = photoStringID;
        this.text = text;
        this.cost = cost;
        this.type = DominionCardType.getTypeFromString(type);
        if (this.type == null){
            Log.e(TAG, "Illegal type for card " + this.title);
            throw new IllegalArgumentException("Card type does not exist.");
        }

        //Dynamically assigned by method reflection, allowing for a String method reference to be held in JSON
        this.action = getMethod(action);

        this.addedTreasure = addedTreasure;
        this.addedActions = addedActions;
        this.addedDraw = addedDraw;
        this.addedBuys = addedBuys;
        this.victoryPoints = victoryPoints;
    }

    /**
     * External Citation
     * Date: 10/4
     * Source: https://stackoverflow.com/questions/13604111/final-variable-assignment-with-try-catch
     * Problem: wouldn't let action be assigned if final
     * Solution: used method to get Method
     */

    /**
     * Gets function of this class corresponding to {@code action}.
     * Used to load functions for card actions from JSON.

     * @param action A String name referencing the relevant DominionCardState function
     * @return A Method reference to a DominionCardState function
     */
    private Method getMethod(String action){
        try {
            return DominionCardState.class.getDeclaredMethod(action);
        }
        catch (NoSuchMethodException e) {
            Log.e(TAG, "Error encountered reflecting action method: " + e + " with card " + this.title);
            throw new IllegalArgumentException("Card function does not exist", e);
        }
    }

    /**
     * A method wrapped allowing for any DominionCardState object to invoke the function stored in
     * their action instance variable. Handles common Method errors.
     * @return A boolean regarding the success of the action invocation
     */
    public boolean cardAction() {
        try {
            Boolean result = (Boolean) action.invoke(this); //return automatically boxed to Boolean
            return result; //Note: automatically unboxed
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
     * Overrides the default inherited toString() behavior, properly displaying object data.
     * Here for debug purposes, currently unused.
     *
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
                "\taddedTreasures: " + getAddedTreasure() + ",\n" +
                "\taddedActions: " + getAddedActions() + ",\n" +
                "\taddedDraw: " + getAddedDraw() + ",\n" +
                "\taddedBuys: " + getAddedBuys() + ",\n" +
                "\tvictoryPoints: " + getVictoryPoints() + ",\n" +
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

    public int getAddedTreasure() { return addedTreasure; }

    public int getAddedActions() { return addedActions; }

    public int getAddedDraw() { return addedDraw; }

    public int getAddedBuys() { return addedBuys; }

    public int getVictoryPoints() { return victoryPoints; }

    //Card Action Methods
    /*
    1. moat
    2. market
    3. laboratory
    4. merchant
    5. gardens
    6. festival
    7. smithy
    8. village
    9. counsel room
    10. money lender(alpha)
        //may trash a copper for 3
    //discard a card per empty supply
     */

    private boolean festivalAction() {
        return true;
    }

    private boolean harbingerAction() {
        return true;
    }

    private boolean merchantAction() {
        return true;
    }

    private boolean remodelAction() {
        return true;
    }

    private boolean throneAction() {
        return true;
    }

    private boolean artisanAction() {
        return true;
    }

    private boolean witchAction() {
        return true;
    }

    private boolean libraryAction() {
        return true;
    }

    private boolean laboratoryAction() {
        return true;
    }

    private boolean militiaAction() {
        return true;
    }

    private boolean copperAction() {
        return true;
    }

    private boolean estateAction() {
        return true;
    }

    private boolean silverAction() {
        return true;
    }

    private boolean duchyAction() {
        return true;
    }

    private boolean goldAction() {
        return true;
    }

    private boolean provinceAction() {
        return true;
    }

    /**
     * Special action.
     * Used by any card whose action consists of only the following:
     * <ul>
     *     <li>Draw</li>
     *     <li>Actions</li>
     *     <li>Buys</li>
     *     <li>Treasure</li>
     * </ul>
     * @return Action success
     */
    private boolean basicAction(){
        return true;
    }
}

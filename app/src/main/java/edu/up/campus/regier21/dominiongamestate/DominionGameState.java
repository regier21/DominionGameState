package edu.up.campus.regier21.dominiongamestate;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class DominionGameState {

    protected static ArrayList<DominionCardState> baseCards;
    protected static ArrayList<DominionCardState> shopCards;
    protected DominionPlayerState dominionPlayers[]; //Sorted by order of turn
    protected int currentTurn; //-1 when game ended
    protected int attackTurn;
    protected boolean isAttackTurn;
    //TODO: hold shop data

    /*public DominionGameState(int numPlayers) {
        this(numPlayers, );
    }*/

    public DominionGameState(int numPlayers, ArrayList<DominionCardState> baseCards, ArrayList<DominionCardState> shopCards) {
        this.baseCards = baseCards;
        this.shopCards = shopCards;

        this.dominionPlayers = new DominionPlayerState[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            this.dominionPlayers[i] = new DominionPlayerState("Player "+i);
        }

        //TODO: Randomize so host doesn't always go first
        this.currentTurn = 0;

        this.isAttackTurn = false;
    }


    //TODO: finish
    @Override
    protected DominionGameState clone() {
        DominionGameState clone = null;

        try{
            clone = (DominionGameState) super.clone();
            clone.dominionPlayers = Arrays.copyOf(dominionPlayers, dominionPlayers.length);
        }
        catch(CloneNotSupportedException cnse) {
            Log.e(TAG, "Error while cloning DominionGameState: ", cnse);
        }

        return clone;
    }

    //ToDo: Implement method
    @Override
    public String toString() {
        return super.toString();
    }

    public ArrayList<DominionCardState> getBaseCards() {
        return baseCards;
    }

    public ArrayList<DominionCardState> getShopCards() {
        return shopCards;
    }
}

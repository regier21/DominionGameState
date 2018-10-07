package edu.up.campus.regier21.dominiongamestate;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class DominionGameState {

    /**
     * The six base cards, in the following order:
     *  0: Copper
     *  1: Estate
     *  2: Silver
     *  3: Duchy
     *  4: Gold
     *  5: Province
     */
    protected static ArrayList<DominionShopPileState> baseCards;
    protected static ArrayList<DominionShopPileState> shopCards;
    protected DominionPlayerState dominionPlayers[]; //Sorted by order of turn
    protected int currentTurn;
    protected int attackTurn;
    protected boolean isAttackTurn;
    protected boolean isGameOver;

    private static DominionGameState instance;

    public static void setupInstance(int numPlayers, ArrayList<DominionCardState> baseCards, ArrayList<DominionCardState> shopCards){
        instance = new DominionGameState(numPlayers, baseCards, shopCards);
    }

    public static DominionGameState getInstance(){
        return instance;
    }

    private DominionGameState(int numPlayers, ArrayList<DominionCardState> baseCardArray,
                              ArrayList<DominionCardState> shopCardArray) {

        //RULE: With 2 players, have 8 of each victory card.
        //      With 3-4 players, have 12 copies of each card.
        int numVictory; //Number of each victory card in the shop
        if (numPlayers == 2){
            numVictory = 8;
        } else {
            numVictory = 12;
        }

        //Generate shop

        baseCards = new ArrayList<>(baseCardArray.size());
        for (DominionCardState card : baseCardArray){
            if (card.getType() == DominionCardType.VICTORY){
                baseCards.add(new DominionShopPileState(card, numVictory));
            } else {
                baseCards.add(new DominionShopPileState(card, card.getAmount()));
            }
        }

        shopCards = new ArrayList<>(shopCardArray.size());
        for (DominionCardState card: baseCardArray){
            shopCards.add(new DominionShopPileState(card, card.getAmount()));
        }

        this.dominionPlayers = new DominionPlayerState[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            this.dominionPlayers[i] = new DominionPlayerState("Player "+i,
                    baseCards.get(0), //The copper pile
                    baseCards.get(1).getCard()); //The estate card
        }

        //TODO: Randomize so host doesn't always go first
        this.currentTurn = 0;
        this.isGameOver = false;

        this.attackTurn = 0;
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
            return null;
        }

        return clone;
    }

    //ToDo: Implement method
    @Override
    public String toString() {
        return super.toString();
    }
}

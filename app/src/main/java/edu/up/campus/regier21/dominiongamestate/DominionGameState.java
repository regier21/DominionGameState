package edu.up.campus.regier21.dominiongamestate;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * A data class intended to represent the state of a game object
 * @author Ryan Regier, Julian Donovan, Ashika Mulgada, Hayden Liao
 */
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

    //use these to create copy constructor
    protected static ArrayList<DominionShopPileState> baseCards;
    protected static ArrayList<DominionShopPileState> shopCards;
    protected DominionPlayerState dominionPlayers[]; //Sorted by order of turn
    protected int currentTurn;
    protected int attackTurn; //player id of responder
    protected boolean isAttackTurn;
    protected boolean isGameOver;

    protected int actions;
    protected int buys;
    protected int treasure;

    private int numVictory; //TODO: make constant
    private int emptyPiles;

    private static DominionGameState instance;

    //singleton, not staying
    public static void setupInstance(int numPlayers, ArrayList<DominionShopPileState> baseCards, ArrayList<DominionShopPileState> shopCards){
        instance = new DominionGameState(numPlayers, baseCards, shopCards);
    }

    public static DominionGameState getInstance(){
        return instance;
    }

    private DominionGameState(int numPlayers, ArrayList<DominionShopPileState> baseCardArray,
                              ArrayList<DominionShopPileState> shopCardArray) {

        //RULE: With 2 players, 8 of each victory card should exist
        //      With 3-4 players, default to 12 copies of each victory card
        //TODO: make constant
        if (numPlayers == 2) numVictory = 8;

        //Generate shop
        for (DominionShopPileState pile : baseCardArray) {
            if (numVictory != 12 && pile.getCard().getType() == DominionCardType.VICTORY) {
                pile.setAmount(8);
                //A nice three step process for when Ryan doesn't like my code - 1:
                //baseCards.add(new DominionShopPileState(pile.getCard(), numVictory));
            }
            //Uncomment this - 2:
            //else baseCards.add(pile);
        }
        //And comment this junk out - 3:
        baseCards = baseCardArray;

        //Handles victory point shop card edge case (ex. Gardens)
        //TODO: skip loop if possible
        for (DominionShopPileState pile : shopCardArray) {
            if (numVictory != 12 && pile.getCard().getType() == DominionCardType.VICTORY) {
                pile.setAmount(8);
                //The same nice three step process for when Ryan doesn't like my code - 1:
                //baseCards.add(new DominionShopPileState(pile.getCard(), numVictory));
            }
            //Uncomment this - 2:
            //else baseCards.add(pile);
        }
        //And comment this junk out - 3:
        shopCards = shopCardArray;

        this.dominionPlayers = new DominionPlayerState[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            this.dominionPlayers[i] = new DominionPlayerState("Player " + i,
                    baseCards.get(0), //The copper pile
                    baseCards.get(1).getCard()); //The estate card //TODO: make constants
        }

        this.currentTurn = new Random().nextInt(numPlayers);
        this.treasure = 0;
        this.buys = 1;
        this.actions = 1;

        this.isGameOver = false;

        this.attackTurn = this.currentTurn; //in the event of an attack
        this.isAttackTurn = false;

        emptyPiles = 0;
    }

    /**
     * Clones a DominionGameState, returning a deep copy
     * @return A deep copy of DominionGameState
     */
    //TODO: finish
    //make this a constructor
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

    /**
     * Yields information to the player as necessary, obscuring game state data not relevant to that particular player
     * @param state Relevant DominionGameState from which data will be gathered
     * @param playerID PlayerID in question, for which data will be found
     */
    protected void hideInformation(DominionGameState state, int playerID){
        //TODO
    }

    @Override
    public String toString() {

        String turnStr, gabStr, baseStr, shopStr, playerStr, emptyPilesStr, gameOverStr;

        String attackString = "";
        if (isAttackTurn){
            attackString = String.format(Locale.US,
                    "An attack has been played. Player #%d is responding to the attack", attackTurn);
        }
        turnStr = String.format(Locale.US, "It is player #%d's turn. %s", currentTurn, attackString);

        gabStr = String.format(Locale.US, "There are %d buys, %d actions, and %d treasure remaining.",
                buys, actions, treasure);

        String[] baseStrs = new String[baseCards.size()];
        for (int i = 0; i < baseCards.size(); i++){
            baseStrs[i] = baseCards.get(i).toString();
        }

        /**
         * TODO
         * Date: 10/7
         * Resource: https://stackoverflow.com/questions/1978933/a-quick-and-easy-way-to-join-array-elements-with-a-separator-the-opposite-of-sp
         */
        baseStr = String.format(Locale.US, "The base cards in the shop:\n%s",
                TextUtils.join("\n", baseStrs));

        String[] shopStrs = new String[shopCards.size()];
        for (int i = 0; i < shopCards.size(); i++){
            shopStrs[i] = shopCards.get(i).toString();
        }
        shopStr = String.format(Locale.US, "The kingdom cards in the shop:\n%s",
                TextUtils.join("\n", shopStrs));

        String[] playerStrs = new String[dominionPlayers.length];
        for (int i = 0; i < dominionPlayers.length; i++){
            playerStrs[i] = dominionPlayers[i].toString();
        }
        playerStr = String.format(Locale.US, "There are %d players in the game:\n%s",
                dominionPlayers.length, TextUtils.join("\n", playerStrs));

        emptyPilesStr = String.format(Locale.US, "There are %d empty piles.", emptyPiles);

        if (isGameOver){
            gameOverStr = "The game is over.";
        } else {
            gameOverStr = "The game is not over.";
        }

        return String.format(Locale.US, "%s\n%s\n%s\n%s\n%s\n%s\n%s", turnStr, gabStr,
                baseStr, shopStr, playerStr, emptyPilesStr, gameOverStr);
    }


    public boolean revealCard(int playerID, DominionGameState card){
        return false;
    }

    public boolean discardCard(int playerID, DominionGameState card){
        return false;
    }

    public boolean buyCard(int playerID, DominionGameState card){
        return false;
    }

    public boolean chooseCard(int playerID, DominionGameState card){
        return false;
    }

    public boolean endTurn(int playerID){
        return false;
    }

    /*public boolean voteCards(int playerID){
        return false;
    }*/

    public boolean gainCard(int playerID, DominionGameState card){
        return false;
    }

    public boolean quit(int playerID){
        return false;
    }

    public boolean playCard(int playerID, DominionGameState card){
        return false;
    }

}

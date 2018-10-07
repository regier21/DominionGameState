package edu.up.campus.regier21.dominiongamestate;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

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

    private int emptyPiles;
    private boolean provinceEmpty;

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

        emptyPiles = 0;
        provinceEmpty = false;
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

    protected void hideInformation(DominionGameState state, int playerID){
        //TODO
    }

    @Override
    public String toString() {

        String turnStr, baseStr, shopStr, playerStr, emptyPilesStr, gameOverStr;

        String attackString = "";
        if (isAttackTurn){
            attackString = String.format(Locale.US,
                    "An attack has been played. Player #%d is responding to the attack", attackTurn);
        }
        turnStr = String.format(Locale.US, "It is player #%d's turn. %s", currentTurn, attackString);

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

        String result = String.format(Locale.US, "%s\n%s\n%s\n%s\n%s\n%s", turnStr, baseStr, shopStr,
                playerStr, emptyPilesStr, gameOverStr);

        return result;
    }
}

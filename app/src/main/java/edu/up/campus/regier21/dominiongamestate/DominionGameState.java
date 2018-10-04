package edu.up.campus.regier21.dominiongamestate;

import android.content.Context;
import android.util.Log;

import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class DominionGameState {
    static protected DominionPileCardsState baseCardPiles;
    static protected DominionPileCardsState shopCardPiles;
    protected DominionPlayerState dominionPlayers[]; //Sorted by order of turn
    protected int currentTurn; //-1 when game ended

    public DominionGameState(int numPlayers) {
        this(numPlayers, "base", 10);
    }

    public DominionGameState(int numPlayers, String cardSet, int numShopCards) {
        baseCardPiles = new DominionPileCardsState(R.raw.base_cards, cardSet);
        shopCardPiles = new DominionPileCardsState(numShopCards, R.raw.shop_cards, cardSet);

        this.dominionPlayers = new DominionPlayerState[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            this.dominionPlayers[i] = new DominionPlayerState("Player"+i);
        }

        this.currentTurn = 0;
    }


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
}

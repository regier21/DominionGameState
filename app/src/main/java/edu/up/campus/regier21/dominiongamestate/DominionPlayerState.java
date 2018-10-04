package edu.up.campus.regier21.dominiongamestate;

import android.content.Context;

public class DominionPlayerState {
    protected String name;
    protected DominionPlayerCardsState deckPile;
    protected DominionPlayerCardsState discardPile;
    protected DominionPlayerCardsState hand;
    protected int actions;
    protected int buys;
    protected int gold;
    protected int victoryPoints;


    protected DominionPlayerState(String name) {
        this.name = name;
        this.hand = new DominionPlayerCardsState();
        this.discardPile = new DominionPlayerCardsState();
        this.deckPile = new DominionPlayerCardsState();
        this.deckPile.populateStartingDeck();

        this.actions = 0;
        this.buys = 0;
        this.gold = 0;
        this.victoryPoints = 3;
    }

}

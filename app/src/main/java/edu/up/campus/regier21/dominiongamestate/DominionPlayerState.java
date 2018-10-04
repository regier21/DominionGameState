package edu.up.campus.regier21.dominiongamestate;

import android.content.Context;

public class DominionPlayerState {
    protected String name;
    protected DominionDeckState deck;
    protected DominionPlayerCardsState hand;
    protected int actions;
    protected int buys;
    protected int gold;
    protected int victoryPoints;


    protected DominionPlayerState(String name) {
        this.name = name;
        this.hand = new DominionPlayerCardsState();
        this.deck = new DominionDeckState();
        populateStartingDeck();

        this.actions = 0;
        this.buys = 0;
        this.gold = 0;
        this.victoryPoints = 3;
    }

    public void populateStartingDeck() {
        //ToDo: Write some logic to search the JSON if copper/estates are not found at the proper positions
        DominionCardState copper = DominionGameState.baseCards.get(0); //0 must be hardcoded as copper in the json
        deck.addManyToDiscard(copper, 7);

        DominionCardState estate = DominionGameState.baseCards.get(1); //1 must be hardcoded as estate in the json
        deck.addManyToDiscard(estate, 3);

        deck.reshuffle();
    }

}

package edu.up.campus.regier21.dominiongamestate;

import android.content.Context;

public class DominionPlayerState {
    protected String name;
    protected DominionDeckState deck;
    protected DominionPlayerCardsState hand;

    protected int victoryPoints;


    protected DominionPlayerState(String name, DominionShopPileState copper, DominionCardState estate) {
        this.name = name;
        this.hand = new DominionPlayerCardsState();
        this.deck = new DominionDeckState(10);

        populateStartingDeck(copper, estate);

        this.victoryPoints = 3;
    }

    public void populateStartingDeck(DominionShopPileState copper, DominionCardState estate) {
        deck.addManyToDiscard(copper.getCard(), 7);
        copper.removeAmount(7);
        deck.addManyToDiscard(estate, 3);
        deck.reshuffle();
    }

    public DominionDeckState getDeck() {
        return deck;
    }

    @Override
    public String toString(){
        return String.format("Player: %s\n%s\n%s", name, deck.toString(), hand.toString());
    }
}

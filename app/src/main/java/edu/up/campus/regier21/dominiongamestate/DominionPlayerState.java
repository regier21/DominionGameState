package edu.up.campus.regier21.dominiongamestate;

import java.util.ArrayList;

/**
 * A data class intended to represent the state of a player object
 * @author Ryan Regier, Julian Donovan, Ashika Mulagada, Hayden Liao
 */
public class DominionPlayerState {

    //Player fields. Note that victoryPoints is not accurate until end of game (gardens)
    protected final String name;
    protected final DominionDeckState deck;
    protected int victoryPoints;

    protected DominionPlayerState(String name, DominionShopPileState copper, DominionCardState estate) {
        this.name = name;

        //Initializes player deck
        this.deck = new DominionDeckState(10);
        populateStartingDeck(copper, estate);

        this.victoryPoints = 3;
    }

    protected DominionPlayerState(DominionPlayerState playerState, boolean isThisPlayer){
        this.name = playerState.name;
        if(isThisPlayer) this.victoryPoints = playerState.victoryPoints;
        this.deck = new DominionDeckState(playerState.deck, isThisPlayer);
    }

    /**
     * Populates deck's discard member variable with 7 copper and 3 estates for start of game.
     * Removes copper from the pile used.
     * Does not remove Estates.
     *
     * @param copper A DominionCardState object containing data pertaining to the copper card
     * @param estate A DominionCardState object containing data pertaining to the estate card
     */
    public void populateStartingDeck(DominionShopPileState copper, DominionCardState estate) {
        deck.addManyToDiscard(copper.getCard(), 7);
        copper.removeAmount(7); //Removes 7 copper from the base card's draw pile
        deck.addManyToDiscard(estate, 3);
        //deck.reshuffle();
    }

    public DominionDeckState getDeck() {
        return deck;
    }

    public String getName() { return name; }

    /**
     * Overrides the default inherited toString() behavior, properly displaying object data
     * @return A String containing object type, name, deck and hand info
     */
    @Override
    public String toString(){
        return String.format("Player: %s\n%s", name, deck.toString());
    }
}

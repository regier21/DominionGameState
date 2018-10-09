package edu.up.campus.regier21.dominiongamestate;

/**
 * A data class intended to represent the state of a player object
 * @author Ryan Regier, Julian Donovan
 */
public class DominionPlayerState {
    protected String name;
    protected DominionDeckState deck;
    protected DominionPlayerCardsState hand;
    protected int victoryPoints;

    protected DominionPlayerState(String name, DominionShopPileState copper, DominionCardState estate) {
        this.name = name;

        //Initializes player hand and deck, populating the deck's discard pile member variable
        this.hand = new DominionPlayerCardsState(); //TODO: Merge PlayerCardsState into DeckState
        this.deck = new DominionDeckState(10);
        populateStartingDeck(copper, estate);

        this.victoryPoints = 3;
    }

    /**
     * Populates deck's discard member variable with 7 copper and 3 estates, effectively prepping
     * players' for the start of the game
     * @param copper A DominionCardState object containing data pertaining to the copper card
     * @param estate A DominionCardState object containing data pertaining to the estate card
     */
    public void populateStartingDeck(DominionShopPileState copper, DominionCardState estate) {
        deck.addManyToDiscard(copper.getCard(), 7);
        copper.removeAmount(7); //Removes 7 copper from the base card's draw pile
        deck.addManyToDiscard(estate, 3);
        deck.reshuffle(); //
    }

    public DominionDeckState getDeck() {
        return deck;
    }

    /**
     * Overrides the default inherited toString() behavior, properly displaying object data
     * @return A String containing object type, name, deck and hand info
     */
    @Override
    public String toString(){
        return String.format("Player: %s\n%s\n%s", name, deck.toString(), hand.toString());
    }
}

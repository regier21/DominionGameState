package edu.up.campus.regier21.dominiongamestate;

import java.util.ArrayList;
import java.util.Random;

public class DominionPlayerCardsState extends DominionCardsAbstract{
    public DominionPlayerCardsState() {
        cards = new ArrayList<>(10);
    }

    private void randomAdd(DominionCardState card) {
        ArrayList<DominionCardState> cards = this.cards;

        Random rand = new Random();
        cards.add(rand.nextInt(cards.size() + 1), card);
    }

    private void randomMultiAdd(int numAdditions, DominionCardState card) {
        for (int i = 0; i < numAdditions; i++) {
            this.randomAdd(card);
        }
    }

    public void populateStartingDeck() {
        //ToDo: Write some logic to search the JSON if copper/estates are not found at the proper positions
        DominionCardState copper = DominionGameState.baseCardPiles.getCards().get(0); //0 must be hardcoded as copper in the json
        this.randomMultiAdd(7, copper);

        DominionCardState estate = DominionGameState.baseCardPiles.getCards().get(1); //1 must be hardcoded as estate in the json
        this.randomMultiAdd(3, estate);
    }
}

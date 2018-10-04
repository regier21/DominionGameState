package edu.up.campus.regier21.dominiongamestate;

import java.util.ArrayList;

public class DominionCardsAbstract {
    public ArrayList<DominionCardState> cards;

    public ArrayList<DominionCardState> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder cardsAbstractString = new StringBuilder("DominionPileCardsState: ");
        cards.forEach(card ->
            cardsAbstractString.append(card.toString()
                    .replaceAll("[\n]", "\n\t")
            )
        );
        return cardsAbstractString.toString() + "\n}\n";
    }
}

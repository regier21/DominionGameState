package edu.up.campus.regier21.dominiongamestate;

public class CardData {

    public DominionCardState[] cards;
    private int numCards = 0;

    public CardData(int uniqueCards) {
        cards = new DominionCardState[uniqueCards];
    }

    private void addBaseCards(){

    }

    public DominionCardState getCardById(int id){
        if (id < 0 || id >= numCards){
            return null;
        } else {
            return cards[id];
        }
    }

    public int getCardId(DominionCardState card){
        for (int i = 0; i < cards.length; i++){
            if (cards[i] == card){
                return i;
            }
        }
        return -1;
    }

    public int addCard(DominionCardState card){
        if (numCards >= cards.length) return -1;
        cards[numCards] = card;
        return numCards++;
    }

}

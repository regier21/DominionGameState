package edu.up.campus.regier21.dominiongamestate;

import java.util.Locale;

/**
 * A data class intended to represent a pile of cards in the shop.
 * Tracks card type and amount.
 *
 * @author Ryan Regier, Julian Donovan
 */
public class DominionShopPileState {
    private final DominionCardState card;
    private int amount;

    public DominionShopPileState(DominionCardState card, int amount){
        this.card = card;
        this.amount = amount;
    }

    public DominionCardState getCard() {
        return card;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void removeCard(){
        amount--;
    }

    public void removeAmount(int amount){
        this.amount -= amount;
    }

    @Override
    public String toString(){
        return String.format(Locale.US, "Card pile. Card: %s, Amount: %d", card.getTitle(), amount);
    }
}

package edu.up.campus.regier21.dominiongamestate;

import java.util.Locale;

/**
 * A data class intended to represent a pile of cards in the shop.
 * Tracks card type and amount.
 *
 * @author Ryan Regier, Julian Donovan, Hayden Liao
 */
public class DominionShopPileState {
    private final DominionCardState card;
    private int amount;

    /**
     * Constructor.
     *
     * If negative number given for {@code amount}, sets {@code amount} to 0.
     *
     * @param card The card the pile represents
     * @param amount The positive number of cards in the pile
     */
    public DominionShopPileState(DominionCardState card, int amount){
        this.card = card;
        this.amount = amount;
        this.amount = Math.max(amount, 0);
    }

    /**
     * Copy constructor
     * @param shopPileState The state to copy
     */
    public DominionShopPileState(DominionShopPileState shopPileState){
        this.card = shopPileState.card;
        this.amount = shopPileState.amount;
    }

    public DominionCardState getCard() {
        return card;
    }

    public int getAmount(){
        return amount;
    }

    /**
     * Sets amount, but raises it to 0 if negative number supplied
     * @param amount Amount of cards left in the pile
     */
    public void setAmount(int amount){
        this.amount = Math.max(amount, 0);
    }

    /**
     * Remove one from the amount of cards in the pile, if the pile is not empty
     */
    public void removeCard(){
        if (amount > 0) amount--;
    }

    /**
     * Removes set amount, but raises it to 0 if it drops below
     * @param amount Amount of cards to remove from the pile
     */
    public void removeAmount(int amount){
        this.amount -= amount;
        this.amount = Math.max(this.amount, 0);
    }

    /**
     * Determine if there are cards left in the pile
     * @return If there is at least one card in the pile
     */
    public boolean isEmpty(){
        return amount <= 0;
    }

    @Override
    public String toString(){
        return String.format(Locale.US, "\nCard pile. Card: %s, Amount: %d", card.getTitle(), amount);
    }
}

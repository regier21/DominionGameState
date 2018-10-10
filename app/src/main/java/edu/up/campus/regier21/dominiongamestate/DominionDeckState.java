package edu.up.campus.regier21.dominiongamestate;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Holds state information for all cards a player posseses: draw, hand, and discard.
 * Provides helper methods to automatically access these cards.
 *
 * @author Ryan Regier, Julian Donovan, Hayden Liao
 */
public class DominionDeckState {

    private final ArrayList<DominionCardState> draw;
    private final ArrayList<DominionCardState> discard;
    private final ArrayList<DominionCardState> hand;

    public DominionDeckState(int startSize){
        draw = new ArrayList<>(startSize);
        discard = new ArrayList<>(startSize);
        hand = new ArrayList<>(10);
    }

    /**
     * Returns the top card from the draw pile, shuffling if necessary.
     * Revealed card remains at top of deck.
     *
     * @return The revealed card, or null if deck is empty
     */
    public DominionCardState reveal(){
        if (draw.isEmpty()){
            if (discard.isEmpty()) {
                //Empty deck, cannot reveal card
                return null;
            }
            reshuffle();
        }

        int index = draw.size() - 1;
        DominionCardState card = draw.get(index);
        return card;
    }

    /**
     * Removes the top card from the draw pile and returns it, shuffling if necessary.
     * Adds drawn card to hand.
     *
     * @return The drawn card, or null if deck is empty
     */
    public DominionCardState draw(){
        DominionCardState card = reveal();
        if (card == null){
            return null;
        }
        draw.remove(draw.size()-1);
        hand.add(card);
        return card;
    }

    /**
     * Gets the number of cards left in the draw pile
     * @return Number of cards in draw pile
     */
    public int getDrawSize(){
        return draw.size();
    }

    /**
     * Puts card in the discard pile.
     * This card will be removed from the hand, if it exists.
     * @param card The card to put in discard
     */
    public void discard(DominionCardState card){
        discard.add(card);
        hand.remove(card);
    }

    /**
     * Adds a card to the discard pile.
     *
     * @param card The new card
     */
    public void discardNew(DominionCardState card){
        discard.add(card);
    }

    /**
     * Add many copies of a card to the discard pile.
     * Used to create starter deck.
     * @param card Card to add
     * @param count Number of cards to add
     */
    public void addManyToDiscard(DominionCardState card, int count){
        for (int i = 0; i < count; i++){
            discard.add(card);
        }
    }

    /**
     * Discards all cards in hand.
     */
    public void discardAll(){
        discard.addAll(hand);

        hand.clear();
    }

    /**
     * Gets the card most recently added to the discard pile.
     * @return The last card, or null if discard is empty
     */
    public DominionCardState getLastDiscard(){
        int size = discard.size();
        if (size == 0){
            return null;
        }

        return discard.get(size - 1);
    }

    /**
     * Gets number of cards in discard pile
     * @return Size of discard pile
     */
    public int getDiscardSize(){
        return discard.size();
    }

    /**
     * Puts all discard cards into the draw pile and randomizes order of cards.
     */
    public void reshuffle(){
        draw.addAll(discard);
        discard.clear();
        Collections.shuffle(draw);
    }

    /**
     * Counts deck for number of victory points
     * @return Number of victory points
     */
    public int countVictory(){
        return Stream.of(discard.parallelStream(), hand.parallelStream(), draw.parallelStream())
                .flatMap(s -> s)
                .mapToInt(DominionCardState::getVictoryPoints)
                .sum();
    }

    @Override
    public String toString(){

        String[] drawStr = new String[draw.size()];
        String[] discardStr = new String[discard.size()];
        String[] handStr = new String[hand.size()];

        createCardArray(drawStr, draw);
        createCardArray(discardStr, discard);
        createCardArray(handStr, hand);

        return String.format(Locale.US, "Deck\n\tDraw: %s\n\tDiscard: %s\n\tHand: %s",
                TextUtils.join(",", drawStr), TextUtils.join(",", discardStr),
                TextUtils.join(",", handStr));
    }

    /**
     * Creates an array of Strings from an ArrayList of cards.
     * Used for toString method to list cards in deck.
     *
     * @param array The array to store card titles in. Must be at least as big as {@code cards}
     * @param cards The array of cards to read from.
     */
    private void createCardArray(String[] array, ArrayList<DominionCardState> cards){
        for (int i = 0; i < array.length; i++){
            array[i] = cards.get(i).getTitle();
        }
    }
}

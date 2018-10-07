package edu.up.campus.regier21.dominiongamestate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Holds state information for a player's deck, including their draw and discard piles
 * @author Ryan Regier
 */
public class DominionDeckState {

    private ArrayList<DominionCardState> draw;
    private ArrayList<DominionCardState> discard;
    private ArrayList<DominionCardState> inPlay;

    /**
     * Returns the top card from the draw pile, shuffling if necessary
     * @return The revealed card, or null if deck is empty
     */
    public DominionCardState reveal(){
        if (draw.isEmpty()){
            reshuffle();
            if (draw.isEmpty()) {
                //Empty deck, cannot reveal card
                return null;
            }
        }

        int index = draw.size() - 1;
        DominionCardState card = draw.get(index);
        return card;
    }

    /**
     * Removes the top card from the draw pile and returns it, shuffling if necessary.
     * @return The drawn card, or null if deck is empty
     */
    public DominionCardState draw(){
        if (draw.isEmpty()){
            reshuffle();
            if (draw.isEmpty()) {
                //Empty deck, cannot draw card
                return null;
            }
        }

        int index = draw.size() - 1;
        DominionCardState card = draw.get(index);
        draw.remove(card);
        inPlay.add(card);
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
     * This card was previously drawn.
     * @param card The card to put in discard
     */
    public void discard(DominionCardState card){
        discard.add(card);
        inPlay.remove(card);
    }

    /**
     * Adds a card to the discard pile.
     * This card was not previously drawn.
     * @param card The new card
     */
    public void discardNew(DominionCardState card){
        discard.add(card);
    }

    /**
     * Add many copies of a card to the discard pile
     * @param card Card to add
     * @param count Number of cards to add
     */
    public void addManyToDiscard(DominionCardState card, int count){
        for (int i = 0; i < count; i++){
            discard.add(card);
        }
    }

    /**
     * Discards all cards that have been drawn and not yet discarded.
     */
    public void discardAll(){
        for (DominionCardState card: inPlay){
            discard.add(card);
        }

        inPlay.clear();
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
        //TODO: implement
        return 0;
    }
}
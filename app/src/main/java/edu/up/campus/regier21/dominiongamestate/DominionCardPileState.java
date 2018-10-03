package edu.up.campus.regier21.dominiongamestate;

import android.util.Log;

//import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DominionCardPileState {
    protected ArrayList<DominionCardPileState> cardStack;
    protected int totalCards;
    protected final static int uniqueCards = 10;

    public DominionCardPileState() {
        this.cardStack = new ArrayList<>(6);
        this.totalCards = 6;
        initializeBaseCards();
    }

    public DominionCardPileState(int totalCards) {
        this.cardStack = new ArrayList<>(totalCards);
        this.totalCards = totalCards;
        generateStack(); //Populates cardStack
    }

    //Credit: https://stackoverflow.com/questions/4307273/how-can-i-create-and-display-an-arraylist-of-random-numbers-in-java
    private void generateStack(){
        //Gson gson = new Gson(); //ToDo: FInish GSON implementation
        //Card card = gson.fromJson(R.raw.shop_cards, Card.class);
        ThreadLocalRandom.current().ints(0, uniqueCards)
                .distinct().limit(totalCards)
                .forEach(randomInt -> initializeRandomCards(randomInt));
    }

    /*private void initializeRandomCard(int randomCard) {

        Card card
    }*/

    private void initializeRandomCards(int randomCard) {

    }

    protected void initializeBaseCards() {

    }
}

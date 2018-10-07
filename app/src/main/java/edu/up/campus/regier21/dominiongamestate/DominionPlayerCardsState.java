package edu.up.campus.regier21.dominiongamestate;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class DominionPlayerCardsState{

    private ArrayList<DominionCardState> cards;

    public DominionPlayerCardsState() {
        cards = new ArrayList<>(10);
    }

    private void randomAdd(DominionCardState card) {
        Random rand = new Random();
        cards.add(rand.nextInt(cards.size() + 1), card);
    }

    private void randomMultiAdd(int numAdditions, DominionCardState card) {
        for (int i = 0; i < numAdditions; i++) {
            this.randomAdd(card);
        }
    }

    @Override
    public String toString(){
        String[] cardStr = new String[cards.size()];
        for (int i = 0; i < cards.size(); i++){
            cardStr[i] = cards.get(i).getTitle();
        }

        return String.format(Locale.US, "Hand: %s", TextUtils.join(",", cardStr));
    }
}

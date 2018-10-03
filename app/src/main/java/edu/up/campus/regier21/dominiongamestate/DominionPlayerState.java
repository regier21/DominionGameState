package edu.up.campus.regier21.dominiongamestate;

import android.content.Context;

public class DominionPlayerState {
    protected String mName;
    protected DominionCardPileState mDeckPile;
    protected DominionCardPileState mDiscardPile;
    protected DominionCardPileState mHand;
    protected int mActions;
    protected int mBuys;
    protected int mGold;
    protected int mVP;
    Context context;

    protected DominionPlayerState(String name, Context context) {
        this(name, 5, context); //Default starting hand size is 5
    }

    protected DominionPlayerState(String name, int numCards, Context context) {
        this.mName = name;
        this.mDeckPile = new DominionCardPileState(numCards, R.raw.shop_cards, context);
        this.mDiscardPile = new DominionCardPileState(context);
        this.mHand = new DominionCardPileState(context);

        this.mActions = 0;
        this.mBuys = 0;
        this.mGold = 0;
        this.mVP = 0;

        this.context = context;
    }

}

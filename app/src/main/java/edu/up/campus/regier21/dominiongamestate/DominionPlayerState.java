package edu.up.campus.regier21.dominiongamestate;

public class DominionPlayerState {
    protected String mName;
    protected DominionCardPileState mDeckPile;
    protected DominionCardPileState mDiscardPile;
    protected DominionCardPileState mHand;
    protected int mActions;
    protected int mBuys;
    protected int mGold;
    protected int mVP;

    protected DominionPlayerState(String name) {
        this(name, 5); //Default starting hand size is 5
    }

    protected DominionPlayerState(String name, int numCards) {
        this.mName = name;
        this.mDeckPile = new DominionCardPileState(numCards);
        this.mDiscardPile = new DominionCardPileState(0);
        this.mHand = new DominionCardPileState(0);

        this.mActions = 0;
        this.mBuys = 0;
        this.mGold = 0;
        this.mVP = 0;
    }

}

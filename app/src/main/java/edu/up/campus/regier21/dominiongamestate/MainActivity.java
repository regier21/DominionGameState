package edu.up.campus.regier21.dominiongamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DominionCardPileState cards = new DominionCardPileState(10, R.raw.shop_cards, this);
        for(DominionCardState card : cards.cardPile) {
            Log.i(TAG, "onCreate: " + card.mTitle);
        }
    }
}

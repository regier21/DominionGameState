package edu.up.campus.regier21.dominiongamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
    TODO: External citation
    Date: 10/3
    Source: https://stackoverflow.com/questions/8355632/how-do-you-usually-tag-log-entries-android
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardReader reader = new CardReader();
        ArrayList<DominionCardState> shopCards = reader.generateCards(getApplicationContext(), 10, R.raw.shop_cards);
        Log.i(TAG, "onCreate: " + shopCards.toString());

        ArrayList<DominionCardState> baseCards = reader.generateCards(getApplicationContext(), R.raw.base_cards);
        Log.i(TAG, "onCreate: " + baseCards.toString());

        DominionGameState.setupInstance(4, baseCards, shopCards);
        DominionGameState game = DominionGameState.getInstance();
        Log.i(TAG, game.toString());
    }
}

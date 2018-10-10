package edu.up.campus.regier21.dominiongamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * External citation
     * Date: 10/3
     * Problem: Wanted tag for logger
     * Resource:
     *  https://stackoverflow.com/questions/8355632/how-do-you-usually-tag-log-entries-android
     * Solution: Using class name as a tag
     **/
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fetch button reference and attach listener
        Button runTestButton = findViewById(R.id.runButton);
        runTestButton.setOnClickListener(buttonOnClickListener);

        //Define a CardReader to deserialize shop_card.json data and base_card.json data to their respective object forms
        CardReader reader = new CardReader("base");
        ArrayList<DominionShopPileState> shopCards = reader.generateCards(getApplicationContext(), 10, R.raw.shop_cards);
        Log.i(TAG, "onCreate: " + shopCards.toString());

        ArrayList<DominionShopPileState> baseCards = reader.generateCards(getApplicationContext(), R.raw.base_cards);
        Log.i(TAG, "onCreate: " + baseCards.toString());

        //Instantiate a DominionGameState object to store all relevant game information
        DominionGameState game = new DominionGameState(4, baseCards, shopCards);
        Log.i(TAG, game.toString());
    }

   private final Button.OnClickListener buttonOnClickListener = (View v) -> {

   };

}

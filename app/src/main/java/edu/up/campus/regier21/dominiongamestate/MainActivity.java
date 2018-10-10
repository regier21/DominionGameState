package edu.up.campus.regier21.dominiongamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        DominionGameState.setupInstance(4, baseCards, shopCards);
        DominionGameState game = DominionGameState.getInstance();
        Log.i(TAG, game.toString());
    }

   private Button.OnClickListener buttonOnClickListener = (View v) -> {

   };

}

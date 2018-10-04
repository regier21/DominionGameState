package edu.up.campus.regier21.dominiongamestate;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.security.auth.login.LoginException;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    protected static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();

        //TEST CODE: Feel free to uncomment for an example as to how DominionCardState's toString() looks
        /*DominionPileCardsState shopCards = new DominionPileCardsState(10, R.raw.shop_cards, "base");
        Log.e(TAG, "onCreate: " + shopCards.toString());

        DominionPileCardsState baseCards = new DominionPileCardsState(R.raw.base_cards, "base");
        Log.e(TAG, "onCreate: " + baseCards.toString());*/

    }
}

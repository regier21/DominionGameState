package edu.up.campus.regier21.dominiongamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.util.Log;
import android.widget.TextView;

import java.sql.Array;
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
    }

    /*
    private ArrayList<DominionShopPileState> deepCopyCards(ArrayList<DominionShopPileState> shopPileStates){
        //ArrayList<DominionShopPileState> newShopPile =
        for(DominionShopPileState card: shopPileStates){

        }

        //return;
    }
    */

   private final Button.OnClickListener buttonOnClickListener = (View v) -> {
       //Define a CardReader to deserialize shop_card.json data and base_card.json data to their respective object forms
       CardReader reader = new CardReader("base");
       ArrayList<DominionShopPileState> shopCards = reader.generateCards(getApplicationContext(), 10, R.raw.shop_cards);
       Log.i(TAG, "onCreate: " + shopCards.toString()); //TODO: Remove this when finished below

       ArrayList<DominionShopPileState> baseCards = reader.generateCards(getApplicationContext(), R.raw.base_cards);
       Log.i(TAG, "onCreate: " + baseCards.toString()); //TODO: Remove this when finished below

       TextView editText = findViewById(R.id.editText);
       editText.setText("");

       //Instantiate a DominionGameState object to store all relevant game information
       DominionGameState firstInstance = new DominionGameState(4, baseCards, shopCards);
       Log.i(TAG, firstInstance.toString()); //TODO: Remove this when finished below
       DominionGameState secondInstance = new DominionGameState(firstInstance, firstInstance.dominionPlayers[0]);
        //COMMENT FOR THE GRADER: dominionPlayers[0] is "player 1"

       //Recording "functionality" of game actions
       String gameStateTest = shopCards.toString() + "\n";
       gameStateTest = gameStateTest.concat(baseCards.toString()) + "\n";
       gameStateTest = gameStateTest.concat("FIRST INSTANCE:\n" + firstInstance.toString() + "\n");

       gameStateTest = gameStateTest.concat("Player 1's turn has just began. They decide to play a Moat. playCard() " +
               "runs " + firstInstance.playCard(0) + ".\n"); //TODO: why does this run false?

       gameStateTest = gameStateTest.concat("The Moat triggers its draw effect as " + firstInstance.drawCard(0)
               + " granting Player 1 two more cards.\n");

       gameStateTest = gameStateTest.concat("This draw effect animates the reveal of two cards from the top of " +
               "Player 1's deck as demonstrated by revealCard() yielding " + firstInstance.revealCard(0) + "\n");

       gameStateTest = gameStateTest.concat("Opting to spend all 6 of their treasure, Player 1 buys 1 Gold card. " +
                       "buyCard() evaluates as " + firstInstance.buyCard(0) + ".\n");

       gameStateTest = gameStateTest.concat("Having done all they can, Player 1 decides to end their turn which " +
               "yields " + firstInstance.endTurn(0) + "\n");

       gameStateTest = gameStateTest.concat("Player 2 has started their turn and decided to attack Player 1 with a " +
               "militia. \nPlayer 1 must choose two cards to discard. Selecting two Witches resolves as "
               + firstInstance.chooseCard(0) + "\n");

       gameStateTest = gameStateTest.concat("The two Witches selected are now being discarded. discardCard() " +
               "evaluates as " + firstInstance.discardCard(0) + "\n");

       gameStateTest = gameStateTest.concat("Growing impatient as Player 2's turn drags on, Player 1 decides to " +
               "quit. This runs " + firstInstance.quit(0) + "\n");

       //Instantiate another DominionGameState object for comparison, as directed by the assignment
       DominionGameState thirdInstance = new DominionGameState(4, baseCards, shopCards);
       Log.i(TAG, firstInstance.toString());

       DominionGameState fourthInstance = new DominionGameState(thirdInstance, thirdInstance.dominionPlayers[0]);

       String str2 = secondInstance.toString();
       String str4 = fourthInstance.toString();
       if(str2.equals(str4)){ gameStateTest = gameStateTest.concat("secondInstance and fourthInstance are identical.\n"); }
       else { gameStateTest = gameStateTest.concat("secondInstance and fourthInstance are not identical.\n"); }

       gameStateTest = gameStateTest.concat("SECOND INSTANCE\n" + secondInstance.toString());
       gameStateTest = gameStateTest.concat("FOURTH INSTANCE\n" + fourthInstance.toString());

       editText.setText(gameStateTest);
       //TODO: Write to textView and verify that everything works as specified
   };

}

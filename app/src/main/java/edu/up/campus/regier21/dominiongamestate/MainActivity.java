package edu.up.campus.regier21.dominiongamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Julian Donovan, Hayden Liao, Ashika Mulagada, Ryan Regier
 */

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
    //TODO: Comment every method
    //TODO: Only read cards we will use in alpha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fetch button reference and attach listener
        Button runTestButton = findViewById(R.id.runButton);
        runTestButton.setOnClickListener(buttonOnClickListener);
    }

   private final Button.OnClickListener buttonOnClickListener = (View v) -> {
       //Define a CardReader to deserialize shop_card.json data and base_card.json data to their respective object forms
       CardReader reader = new CardReader("base");
       ArrayList<DominionShopPileState> shopCards1 = reader.generateCards(getApplicationContext(), 10, R.raw.shop_cards);

       ArrayList<DominionShopPileState> baseCards1 = reader.generateCards(getApplicationContext(), R.raw.base_cards);

       TextView editText = findViewById(R.id.editText);
       editText.setText("");

       //Instantiate a DominionGameState object to store all relevant game information
       DominionGameState firstInstance = new DominionGameState(4, baseCards1, shopCards1);
       DominionGameState secondInstance = new DominionGameState(firstInstance, firstInstance.dominionPlayers[0]);
        //COMMENT FOR THE GRADER: dominionPlayers[0] is "player 1"

       //Recording "functionality" of game actions
       String gameStateTest = shopCards1.toString() + "\n";
       gameStateTest = gameStateTest.concat(baseCards1.toString()) + "\n";
       gameStateTest = gameStateTest.concat("FIRST INSTANCE:\n" + firstInstance.toString() + "\n");

       //gameStateTest = gameStateTest.concat("Player 1's turn has just began. They decide to play a Moat. playCard() " +
       //        "runs " + firstInstance.playCard(0, ) + ".\n");

       gameStateTest = gameStateTest.concat("The Moat triggers its draw effect as " + firstInstance.drawCard(0)
               + " granting Player 1 two more cards.\n");

       //TODO: Delete this
       /*gameStateTest = gameStateTest.concat("This draw effect animates the reveal of two cards from the top of " +
               "Player 1's deck as demonstrated by revealCard() yielding " + firstInstance.revealCard(0) + "\n");*/

       gameStateTest = gameStateTest.concat("Opting to spend all 6 of their treasure, Player 1 buys 1 Gold card. " +
                       "buyCard() evaluates as " + firstInstance.buyCard(0, 4, true) + ".\n");

       gameStateTest = gameStateTest.concat("Having done all they can, Player 1 decides to end their turn which " +
               "yields " + firstInstance.endTurn(0) + "\n");

       //TODO: Delete this
       /*gameStateTest = gameStateTest.concat("Player 2 has started their turn and decided to attack Player 1 with a " +
               "militia. \nPlayer 1 must choose two cards to discard. Selecting two Witches resolves as "
               + firstInstance.chooseCard(0) + "\n");*/

       //TODO: Delete this
       /*
       gameStateTest = gameStateTest.concat("The two Witches selected are now being discarded. discardCard() " +
               "evaluates as " + firstInstance.discardCard(0) + "\n");
       */

       gameStateTest = gameStateTest.concat("Growing impatient as Player 2's turn drags on, Player 1 decides to " +
               "quitGame. This runs " + firstInstance.quitGame() + "\n");

       ArrayList<DominionShopPileState> shopCards2 = reader.generateCards(getApplicationContext(), 10, R.raw.shop_cards);

       ArrayList<DominionShopPileState> baseCards2 = reader.generateCards(getApplicationContext(), R.raw.base_cards);

       //Instantiate another DominionGameState object for comparison, as directed by the assignment
       DominionGameState thirdInstance = new DominionGameState(4, baseCards2, shopCards2);
       Log.i(TAG, firstInstance.toString());

       DominionGameState fourthInstance = new DominionGameState(thirdInstance, thirdInstance.dominionPlayers[0]);

       String str2 = secondInstance.toString();
       String str4 = fourthInstance.toString();
       if(str2.equals(str4)){ gameStateTest = gameStateTest.concat("\nsecondInstance and fourthInstance are identical.\n"); }
       else { gameStateTest = gameStateTest.concat("\nsecondInstance and fourthInstance are not identical.\n"); }

       gameStateTest = gameStateTest.concat("SECOND INSTANCE\n" + secondInstance.toString());
       gameStateTest = gameStateTest.concat("\nFOURTH INSTANCE\n" + fourthInstance.toString());

       editText.setText(gameStateTest);
   };

}

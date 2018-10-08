package edu.up.campus.regier21.dominiongamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    Button runTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runTestButton = (Button)findViewById(R.id.buttonRunTest);
        runTestButton.setOnClickListener(buttonOnClickListener);

    }

   private Button.OnClickListener buttonOnClickListener = new Button.OnClickListener(){
       public void onClick(View v){

       }
   };
}

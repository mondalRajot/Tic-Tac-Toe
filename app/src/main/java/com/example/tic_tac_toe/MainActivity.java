package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 : Tom , 1 : Jerry, 2 :Empty

    int []gameState ={-1, -1, -1, -1, -1, -1, -1, -1, -1};

    int [][]winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    int activePlayer = 0;

    boolean gameActive = true;

    //int count=1;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        //Log.i("tag",counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString()) ;

        if(gameState[tappedCounter] == -1 && gameActive == true) {

            gameState[tappedCounter] = activePlayer;

            //count++;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.tom);

                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.jerry);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != -1) {

                    //someone won !

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 0) {
                        winner = "JERRY";
                    } else {
                        winner = "TOM";
                    }
                    Toast.makeText(this, winner + " Has won !", Toast.LENGTH_SHORT).show();

                    TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);

                    ImageView tomJerry=(ImageView) findViewById(R.id.tomJerry);

                    ImageView hdtom=(ImageView) findViewById(R.id.imageHdtom);

                    ImageView hdJerry=(ImageView) findViewById(R.id.imageHdJerry);

                    winnerTextView.setText(winner + " has won !");

                    if(winner.charAt(0)=='J'){
                        tomJerry.setVisibility(View.INVISIBLE);
                        hdJerry.setVisibility(View.VISIBLE);
                    } else{
                        tomJerry.setVisibility(View.INVISIBLE);
                        hdtom.setVisibility(View.VISIBLE);
                    }
                    winnerTextView.setVisibility(View.VISIBLE);
                }

            }
        }
        playAgainButton.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);

        ImageView tomJerry=(ImageView) findViewById(R.id.tomJerry);

        ImageView hdtom=(ImageView) findViewById(R.id.imageHdtom);

        ImageView hdJerry=(ImageView) findViewById(R.id.imageHdJerry);

        tomJerry.setVisibility(View.VISIBLE);

        hdtom.setVisibility(View.INVISIBLE);

        hdJerry.setVisibility(View.INVISIBLE);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout  gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++){

            ImageView counter = (ImageView)gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for(int i=0;i<gameState.length;i++){
            gameState[i]=-1;
        }
        activePlayer = 0;
        //count =0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
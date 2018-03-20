package com.example.shubham.tictactoe;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    //tags are added to the images so that we can understand which image is presses!!!
    // 0 = happy , 1=angry
    int turn = 0 , count = 0;
    boolean game_live=true;

    //game state to check if the block is already pressed or not
    int tapped;
    int[] game_state = {2,2,2,2,2,2,2,2,2};

    //using 2-d array for building the logic
    int[][] winning_positions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropin(View view){


        ImageView counter = (ImageView) view;

        //get the tag

         //   System.out.println(counter.getTag().toString());
            tapped = Integer.parseInt(counter.getTag().toString());

        if(game_state[tapped]==2 && game_live) {
            game_state[tapped]=turn;
            //set the image to be drop

            if (turn == 0) {
                counter.setImageResource(R.drawable.happ);
                turn = 1;

            } else {
                counter.setImageResource(R.drawable.harder);
                turn = 0;

            }


            //first did the translation above the screen
            counter.setTranslationY(-1000f);

            //get the image to the down
            counter.animate().translationYBy(1000f).rotation(1080).setDuration(500);
            count++;

            for(int[] winning_position : winning_positions){
                LinearLayout linearLayout =  findViewById(R.id.playagainlayout);
                ImageView winner = findViewById(R.id.winner_image);

                if( game_state[winning_position[0]]==game_state[winning_position[1]] &&
                        game_state[winning_position[1]]==game_state[winning_position[2]] && game_state[winning_position[0]]!=2){
                    game_live=false;

                    linearLayout.setVisibility(View.VISIBLE);

                    if(turn==1){
                        winner.setImageResource(R.drawable.happ);
                    }
                    else if(turn==0){
                        winner.setImageResource(R.drawable.harder);
                    }

                }

                else{
                    if(count==9){
                        System.out.println(count);
                        linearLayout.setVisibility(View.VISIBLE); 
                            winner.setImageResource(R.drawable.angry);

                    }
                }


            }




        }

    }



    public void play_again(View view){
        GridLayout gridLayout = findViewById(R.id.maingrid_layout);
        LinearLayout linearLayout = findViewById(R.id.playagainlayout);
        // this function is used to set all the functions default
        linearLayout.setVisibility(View.INVISIBLE);
        game_live=true;
        turn = 0;
        count=0;

        for(int i=0;i<game_state.length;i++){
            game_state[i]=2;
        }

        //setting all images to null
        for(int i=0 ; i<gridLayout.getChildCount();i++){
            ( (ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

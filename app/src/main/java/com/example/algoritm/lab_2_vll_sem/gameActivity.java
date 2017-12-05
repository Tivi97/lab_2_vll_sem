package com.example.algoritm.lab_2_vll_sem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;



public class gameActivity extends AppCompatActivity {
    AlertDialog.Builder ad;
    Context context;

    private int flag_ = 0, flag1_ = 0, mimi = 1;
    private int seconds = 0, minutes, secs, flagFinish=0;
    private boolean running;
    private String score="", winName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void plusFist(View view) {
        flag_++;
        TextView plusFist = (TextView) findViewById(R.id.rating_id1);
        plusFist.setText(Integer.toString(flag_));
    }

    public void plusSecond(View view) {
        flag1_++;
        TextView plusSecond = (TextView) findViewById(R.id.rating_id2);
        plusSecond.setText(Integer.toString(flag1_));

    }

    private void runTimer() {

            final TextView timeView = (TextView) findViewById(R.id.timer);
            final Handler handler = new Handler();


    handler.post(new Runnable() {
        @Override
        public void run() {
            infUpdate();

            minutes = (seconds % 3600) / 60;
            secs = seconds % 60;
            String time = String.format("%02d:%02d", minutes, secs);
            timeView.setText(time);
            if (running) {
                seconds++;
                Log.d("sec", String.valueOf(seconds));
            }
            handler.postDelayed(this, 1000); // postDelayed
        }

    });
}



    public void infUpdate() {
        if (secs == 5 || secs == 10|| secs == 15) {
            running = false;
            mimi++;
            TextView round = (TextView) findViewById(R.id.roundText);

            if (mimi == 2) {
                round.setText("Второй раунд");
                winner();
            }

            else if (mimi == 3) {
                round.setText("Третий раунд");
                winner();
            }
            else if (mimi==4){
                round.setText("");
                running=false;
                seconds=0;
                flag1_=0;
                flag_=0;
                TextView plusSecond = (TextView) findViewById(R.id.rating_id1);
                plusSecond.setText(Integer.toString(flag_));

                TextView plusFirst = (TextView) findViewById(R.id.rating_id2);
                plusFirst.setText(Integer.toString(flag1_));
                finishGame();
            }

        }
       /* else if(seconds>15) {
           ;


        }*/

    }

    public void startGame(View view) {

        running = true;
        runTimer();
    }

    public void winner(){
        TextView rating_id1 = (TextView) findViewById(R.id.rating_id1);
        int num1 = Integer.parseInt(rating_id1.getText().toString());

        TextView rating_id2 = (TextView) findViewById(R.id.rating_id2);
        int num2 = Integer.parseInt(rating_id2.getText().toString());

        if(num1>num2){

            TextView name1 = (TextView) findViewById(R.id.role_name1);
             winName=name1.getText().toString();
            onClic();
        }
        else if(num1<num2){

            TextView name2 = (TextView) findViewById(R.id.role_name2);
            winName=name2.getText().toString();
            onClic();
        }
        else {
            winName=" Ничья";
            onClic();

        }

    }

    public void onClic() {
        AlertDialog.Builder builder = new AlertDialog.Builder(gameActivity.this);
        builder.setTitle("Раунд окончен!")
                .setMessage("Победитель:  "+winName)
                .setCancelable(false)
                .setNegativeButton("Начать следующий раунд",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                running=true;


                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void finishGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(gameActivity.this);
        builder.setTitle("Поединок завершен!")
                .setCancelable(false)
                .setMessage("Победитель:  "+winName)
                .setNegativeButton("Спасибо, завершаем бой",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                running=false;


                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}

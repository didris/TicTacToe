package sample.bzu.uni.tictactoeproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    boolean turn = true; // true = X & false = O
    int turn_count = 0;
    Button[] bArray = null;
    Button a1, a2, a3, b1, b2, b3, c1, c2, c3;
    TextView score;
    int xWonTimes=0;
    int oWonTimes=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score=(TextView) findViewById(R.id.score);
        a1 = (Button) findViewById(R.id.cell1);
        b1 = (Button) findViewById(R.id.cell4);
        c1 = (Button) findViewById(R.id.cell7);
        a2 = (Button) findViewById(R.id.cell2);
        b2 = (Button) findViewById(R.id.cell5);
        c2 = (Button) findViewById(R.id.cell8);
        a3 = (Button) findViewById(R.id.cell3);
        b3 = (Button) findViewById(R.id.cell6);
        c3 = (Button) findViewById(R.id.cell9);
        bArray = new Button[] { a1, a2, a3, b1, b2, b3, c1, c2, c3 };

        for (Button b : bArray)
            b.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_action,  menu);

        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_exit:
                finish();
                System.exit(0);
                break;

            case R.id.action_new:
                turn = true;
                turn = true;
                turn_count = 0;
                enableOrDisable(true);
                score.setText(" ");
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        buttonClicked(v);
    }

    private void buttonClicked(View v) {
        Button b = (Button) v;
        if (turn) {
            // X's turn
            b.setText("X");

        } else {
            // O's turn
            b.setText("O");
        }
        turn_count++;
        b.setClickable(false);
         b.setBackgroundColor(Color.LTGRAY);
        turn = !turn;

        checkForWinner();
    }

    private void checkForWinner() {

        boolean there_is_a_winner = false;

        // horizontal:
        if (a1.getText() == a2.getText() && a2.getText() == a3.getText()
                && !a1.isClickable()){
            there_is_a_winner = true;

            if(turn==false)
                xWonTimes++;
            else oWonTimes++;
        }
        else if (b1.getText() == b2.getText() && b2.getText() == b3.getText()
                && !b1.isClickable()){
            there_is_a_winner = true;
            if(turn==false)
                xWonTimes++;
            else oWonTimes++;
        }
        else if (c1.getText() == c2.getText() && c2.getText() == c3.getText()
                && !c1.isClickable()){
            there_is_a_winner = true;
            if(turn==false)
                xWonTimes++;
            else oWonTimes++;
        }
        // vertical:
        else if (a1.getText() == b1.getText() && b1.getText() == c1.getText()
                && !a1.isClickable()){
            there_is_a_winner = true;
            if(turn==false)
                xWonTimes++;
            else oWonTimes++;
        }
        else if (a2.getText() == b2.getText() && b2.getText() == c2.getText()
                && !b2.isClickable()){
            there_is_a_winner = true;
            if(turn==false)
                xWonTimes++;
            else oWonTimes++;
        }
        else if (a3.getText() == b3.getText() && b3.getText() == c3.getText()
                && !c3.isClickable()){
            there_is_a_winner = true;
            if(turn==false)
                xWonTimes++;
            else oWonTimes++;
        }

        // diagonal:
        else if (a1.getText() == b2.getText() && b2.getText() == c3.getText()
                && !a1.isClickable()){
            there_is_a_winner = true;
            if(turn==false)
                xWonTimes++;
            else oWonTimes++;
        }
        else if (a3.getText() == b2.getText() && b2.getText() == c1.getText()
                && !b2.isClickable()){
            there_is_a_winner = true;
            if(turn==false)
                xWonTimes++;
            else oWonTimes++;
        }

        if (there_is_a_winner) {
            if (!turn)
            {message("X wins");
            }

            else
            { message("O wins");
                ;
            }
            enableOrDisable(false);
        } else if (turn_count == 9)
            message("No winner");

    }

    private void message(String text) {
        score.setText(text+", "+"  x-Score="+xWonTimes+",  "+" "+" o-Score"+oWonTimes);
        new AlertDialog.Builder(this)
                .setTitle("Play again")
                .setMessage("Do you want to play again?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        turn = true;
                        turn = true;
                        turn_count = 0;
                        enableOrDisable(true);
                        score.setText(" ");
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void enableOrDisable(boolean enable) {
        for (Button b : bArray) {
            b.setText("");
            b.setClickable(enable);
            if (enable) {
                b.setBackgroundColor(Color.parseColor("#ffc0cb"));
            } else {
                b.setBackgroundColor(Color.LTGRAY);
            }
        }
    }
}
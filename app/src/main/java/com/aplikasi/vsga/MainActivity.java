package com.aplikasi.vsga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aplikasi.vsga.model.Board;
import com.aplikasi.vsga.model.Place;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mainView;
    private Board board;
    private BoardView boardView;
    private TextView moves;
    private int boardSize = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = (ViewGroup) findViewById(R.id.mainLayout);
        moves = (TextView) findViewById(R.id.moves);
        moves.setTextColor(Color.WHITE);
        moves.setTextSize(20);
        this.newGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void newGame() {
        this.board = new Board(this.boardSize);
        this.board.addBoardChangeListener(boardChangeListener);
        this.board.rearrange();
        this.mainView.removeView(boardView);
        this.boardView = new BoardView(this, board);
        this.mainView.addView(boardView);
        this.moves.setText("Number of movements: 0");
    }
    public void changeSize(int newSize) {
        if (newSize != this.boardSize) {
            this.boardSize = newSize;
            this.newGame();
            boardView.invalidate();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_game:
                new AlertDialog.Builder(this)
                        .setTitle("New Game")
                        .setMessage("apakah anda yakin ingin new game?")
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        board.rearrange();
                                        moves.setText("Number of movements: 0");
                                        boardView.invalidate();
                                    }
                                })
                        .setNegativeButton(android.R.string.no,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
            case R.id.action_help:
                new AlertDialog.Builder(this)
                        .setTitle("Instructions")
                        .setMessage(
                                "Kamu akan menjadi pemenang game puzzle ini apabila berhasil mengurutkan angka dari satu sampai 8.")
                        .setPositiveButton("Mengerti. Let's play!",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Board.BoardChangeListener boardChangeListener = new Board.BoardChangeListener() {
        public void tileSlid(Place from, Place to, int numOfMoves) {
            moves.setText("Number of movements: "
                    + Integer.toString(numOfMoves));
        }

        public void solved(int numOfMoves) {
            moves.setText("Solved in " + Integer.toString(numOfMoves)
                    + " moves!");
            Toast.makeText(getApplicationContext(), "You won!",
                    Toast.LENGTH_LONG).show();
        }
    };
    public class SettingsDialogFragment extends DialogFragment {

        private int size;

        public SettingsDialogFragment(int size) {
            this.size = size;
        }

        void setSize(int size) {
            this.size = size;
        }
        int getSize() {
            return this.size;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Define the size of the puzzle")
                    .setSingleChoiceItems(R.array.size_options, this.size - 2,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    setSize(which + 2);

                                }

                            })
                    .setPositiveButton("Change",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    ((MainActivity) getActivity())
                                            .changeSize(getSize());
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });

            return builder.create();
        }
    }

}

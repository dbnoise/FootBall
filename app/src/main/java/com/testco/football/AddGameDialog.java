package com.testco.football;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sergey on 1/27/17.
 */

public class AddGameDialog extends Dialog {

    MainActivity activity;
    public EditText p1, p2, score1, score2;
    Button add;

    public AddGameDialog(Context context) {
        super(context);
    }

    public AddGameDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AddGameDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public MainActivity getActivity() {
        return activity;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public void init() {
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p1 = (EditText) findViewById(R.id.player1);
        p2 = (EditText) findViewById(R.id.player2);
        score1 = (EditText) findViewById(R.id.score1);
        score2 = (EditText) findViewById(R.id.score2);
        add = (Button) findViewById(R.id.add_game);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.addNewGame(p1.getText().toString(), Integer.parseInt(score1.getText().toString()), p2.getText().toString(), Integer.parseInt(score2.getText().toString()));
                dismiss();
            }
        });
    }
}

package com.example.tugasbesar_02;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameOverFragment extends DialogFragment implements View.OnClickListener {
    protected TextView tvHighScore;
    protected TextView tvPlayerName;
    protected TextView tvPlayerScore;
    protected Button btnPlayAgain;
    protected MainActivity mainActivity;

    public GameOverFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_game_over, container, false);

        this.tvHighScore = view.findViewById(R.id.tv_over_highScore);
        this.tvPlayerName = view.findViewById(R.id.tv_playerName);
        this.tvPlayerScore = view.findViewById(R.id.tv_over_playerScore);
        this.btnPlayAgain = view.findViewById(R.id.btn_playAgain);

        this.btnPlayAgain.setOnClickListener(this);

        this.tvPlayerName.setText(this.mainActivity.tvPlayerName.getText());
        this.tvPlayerScore.setText(this.mainActivity.tvScore.getText());

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == this.btnPlayAgain.getId()){
            getFragmentManager().beginTransaction().remove(GameOverFragment.this).commit();
        }
    }
}

package com.example.tugasbesar_02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameOverFragment extends DialogFragment implements View.OnClickListener,FragmentListener {
    protected TextView tvHighScore;
    protected TextView tvPlayerName;
    protected TextView tvPlayerScore;
    protected Button btnPlayAgain;
    protected MainActivity activity;

    public GameOverFragment() {
    }

    public static GameOverFragment newInstance() {
        GameOverFragment fragment = new GameOverFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_game_over, container, false);

        this.tvHighScore = view.findViewById(R.id.tv_over_highScore);
        this.tvPlayerName = view.findViewById(R.id.tv_over_playerName);
        this.tvPlayerScore = view.findViewById(R.id.tv_over_playerScore);
        this.btnPlayAgain = view.findViewById(R.id.btn_playAgain);

        this.btnPlayAgain.setOnClickListener(this);

        this.tvPlayerName.setText(getTag());
//        this.tvPlayerScore.setText("Score : "+this.activity.tvScore.getText().toString());

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == this.btnPlayAgain.getId()){
            // dibawah ini buat bikin supaya balik lagi ke kelas MainActivity
            Context context = getActivity();
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void closeApp() {

    }

    @Override
    public void updateScore(String score) {
        tvHighScore.setText(score);

        String banding=tvHighScore.getText().toString();

        if(Integer.parseInt(banding)>Integer.parseInt(score))
        {
            tvHighScore.setText(banding);
            banding=banding;
        }
        else
        {
            tvHighScore.setText(score);
            banding=score;
        }
    }
}

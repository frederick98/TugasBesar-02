package com.example.tugasbesar_02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameOverFragment extends Fragment implements View.OnClickListener {
    protected TextView tv_highScore;
    protected TextView tv_playerName;
    protected TextView tv_playerScore;
    protected Button btn_playAgain;

    public GameOverFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_game_over, container, false);

        this.tv_highScore = view.findViewById(R.id.tv_over_highScore);
        this.tv_playerName = view.findViewById(R.id.tv_playerName);
        this.tv_playerScore = view.findViewById(R.id.tv_over_playerScore);
        this.btn_playAgain = view.findViewById(R.id.btn_playAgain);

        this.btn_playAgain.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==this.btn_playAgain.getId()){
            getFragmentManager().beginTransaction().remove(GameOverFragment.this).commit();
            //start game
        }
    }
}

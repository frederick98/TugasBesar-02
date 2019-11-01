package com.example.tugasbesar_02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

public class PauseFragment extends DialogFragment implements View.OnClickListener {
    protected Button btnUnpause;

    public PauseFragment() {
    }

    public static PauseFragment newInstance() {
        PauseFragment fragment = new PauseFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pause, container, false);

        this.btnUnpause = view.findViewById(R.id.btn_unpaused);

        this.btnUnpause.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == this.btnUnpause.getId()){
            getFragmentManager().beginTransaction().remove(PauseFragment.this).commit();
        }
    }
}

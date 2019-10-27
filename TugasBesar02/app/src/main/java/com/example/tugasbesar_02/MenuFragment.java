package com.example.tugasbesar_02;


import androidx.fragment.app.DialogFragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MenuFragment extends DialogFragment implements View.OnClickListener {
    protected EditText etUsername;
    protected Button btnStart;
    protected Button btnExit;
    protected FragmentListener listener;

    public MenuFragment() {
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        this.etUsername = view.findViewById(R.id.et_username);
        this.btnStart = view.findViewById(R.id.frd_btn_start);
        this.btnExit = view.findViewById(R.id.frd_btn_exitGame);

        this.btnStart.setOnClickListener(this);
        this.btnExit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == this.btnStart.getId()){
            getFragmentManager().beginTransaction().remove(MenuFragment.this).commit();
        }
        else if(view.getId() == this.btnExit.getId()){
            this.listener.closeApp();
        }
    }

}

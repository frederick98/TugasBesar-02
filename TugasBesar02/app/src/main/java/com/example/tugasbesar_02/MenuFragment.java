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
    protected EditText et_username;
    protected Button btn_start;
    protected Button btn_exit;
    protected FragmentListener listener;

    public MenuFragment() {
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        this.et_username = view.findViewById(R.id.et_username);
        this.btn_start = view.findViewById(R.id.btn_start);
        this.btn_exit = view.findViewById(R.id.btn_exitGame);

        this.btn_start.setOnClickListener(this);
        this.btn_exit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==this.btn_start.getId()){
            getFragmentManager().beginTransaction().remove(MenuFragment.this).commit();
        }
        else if(view.getId()==this.btn_exit.getId()){
            this.listener.closeApp();
        }
    }

}

package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentoVerde extends Fragment {

    private TextView outputTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.layout_verde, container, false);
        outputTextView = view.findViewById(R.id.text);
        return view;
    }

    public void updateText(String text) {
        if (outputTextView != null) {
            outputTextView.setText(text);
        }
    }
}

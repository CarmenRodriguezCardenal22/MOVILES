package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentoNaranja extends Fragment {

    private OnTextSendListener callback;

    public interface OnTextSendListener {
        void onTextSend(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTextSendListener) {
            callback = (OnTextSendListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTextSendListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.layout_naranja, container, false);

        EditText editText = view.findViewById(R.id.text);
        Button sendButton = view.findViewById(R.id.button);

        sendButton.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (callback != null) {
                callback.onTextSend(text);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}

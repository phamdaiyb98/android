package com.haui.phamdai.fragmentcommunicatorkpt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class AFragment extends Fragment {

    TextView txtTitleA;
    EditText edtA;
    Button btnClickA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        txtTitleA = view.findViewById(R.id.textViewTitle_FragA);
        edtA = view.findViewById(R.id.editTextContent_FragA);
        btnClickA = view.findViewById(R.id.buttonClick_FragA);

        btnClickA.setOnClickListener(v -> {
            TextView textView = requireActivity().findViewById(R.id.textViewTitle_Main);
            textView.setText(edtA.getText().toString());
        });

        return view;
    }

    public void setNoiDung(String noiDung) {
        txtTitleA.setText(noiDung);
    }
}
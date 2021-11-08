package com.haui.phamdai.fragmentcommunicatorkpt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BFragment extends Fragment {

    TextView txtTitleB;
    EditText edtB;
    Button btnClickB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        txtTitleB = view.findViewById(R.id.textViewTitle_FragB);
        edtB = view.findViewById(R.id.editTextContent_FragB);
        btnClickB = view.findViewById(R.id.buttonClick_FragB);

        btnClickB.setOnClickListener(v -> {
            TextView txtA = requireActivity().findViewById(R.id.textViewTitle_FragA);
            txtA.setText(edtB.getText().toString());
        });

        return view;
    }
}
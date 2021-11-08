package com.haui.phamdai.fragmentchangeorientationkpt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentStudentInfo extends Fragment {

    View view;
    TextView txtHoTen, txtNamSinh, txtDiaChi, txtEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.student_info, container, false);
        map();
        return view;
    }

    private void map() {
        txtHoTen = view.findViewById(R.id.textViewHoTen_FragStudentInfo);
        txtNamSinh = view.findViewById(R.id.textViewNamSinh_FragStudentInfo);
        txtDiaChi = view.findViewById(R.id.textViewDiaChi_FragStudentInfo);
        txtEmail = view.findViewById(R.id.textViewEmail_FragStudentInfo);
    }

    public void setInfo(Student info) {
        System.out.println(info);
        txtHoTen.setText(info.getHoTen());
        txtNamSinh.setText("Năm sinh : " + info.getNamSinh());
        txtDiaChi.setText("Địa chỉ : " + info.getDiaChi());
        txtEmail.setText("Email : " + info.getEmail());
    }
}

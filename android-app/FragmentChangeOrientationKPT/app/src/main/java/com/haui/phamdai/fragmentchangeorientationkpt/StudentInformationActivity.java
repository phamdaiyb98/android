package com.haui.phamdai.fragmentchangeorientationkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class StudentInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("thongTinSinhVien");
        FragmentStudentInfo info = (FragmentStudentInfo)
                getSupportFragmentManager().findFragmentById(R.id.fragmentContainerChiTiet);
        assert info != null;
        info.setInfo(student);
    }
}
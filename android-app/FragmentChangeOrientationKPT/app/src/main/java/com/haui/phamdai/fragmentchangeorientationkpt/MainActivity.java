package com.haui.phamdai.fragmentchangeorientationkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TruyenSinhVien {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void dataStudent(Student student) {
        FragmentStudentInfo info = (FragmentStudentInfo)
                getSupportFragmentManager().findFragmentById(R.id.fragmentContainerInfo);

        Configuration configuration = getResources().getConfiguration();
//        if (info != null && info.isInLayout()) { -> cách này giống với dòng dưới
        if (info != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            info.setInfo(student);
        } else {
            Intent intent = new Intent(this, StudentInformationActivity.class);
            intent.putExtra("thongTinSinhVien", student);
            startActivity(intent);
        }
    }
}
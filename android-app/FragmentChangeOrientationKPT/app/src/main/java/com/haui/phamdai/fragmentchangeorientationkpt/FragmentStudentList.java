package com.haui.phamdai.fragmentchangeorientationkpt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentStudentList extends ListFragment {

    List<Student> studentList;
    StudentAdapter studentAdapter;
    TruyenSinhVien truyenSinhVien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        truyenSinhVien = (TruyenSinhVien) getActivity();

        studentList = new ArrayList<>();
        addSV();
        studentAdapter = new StudentAdapter(getActivity(), R.layout.row_student, studentList);
        setListAdapter(studentAdapter);

        return inflater.inflate(R.layout.student_list, container, false);
    }

    private void addSV() {
        studentList.add(new Student("Nguyễn Văn A", 1999, "Hà Nội", "nguyenvana@gmail.com"));
        studentList.add(new Student("Nguyễn Văn B", 2000, "Hà Nam", "abc@gmail.com"));
        studentList.add(new Student("Nguyễn Văn C", 1999, "Hà Nội", "yut@gmail.com"));
        studentList.add(new Student("Nguyễn Văn D", 1998, "Thái Nguyên", "hjk@gmail.com"));
        studentList.add(new Student("Nguyễn Văn E", 1997, "Cần Thơ", "drt@gmail.com"));
        studentList.add(new Student("Nguyễn Văn F", 2001, "Tây Ninh", "efd@gmail.com"));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        truyenSinhVien.dataStudent(studentList.get(position));
    }
}

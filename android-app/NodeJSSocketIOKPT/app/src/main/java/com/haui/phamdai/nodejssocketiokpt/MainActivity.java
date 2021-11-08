package com.haui.phamdai.nodejssocketiokpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.*;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {


    private Socket mSocket;
    ListView lvUser, lvChat;
    EditText edtContent;
    ImageButton btnAddUser, btnSend;
    List<String> userList, chatList;
    ArrayAdapter userAdapter, chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map();

        userList = new ArrayList<>();
        userAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userList);
        lvUser.setAdapter(userAdapter);

        chatList = new ArrayList<>();
        chatAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, chatList);
        lvChat.setAdapter(chatAdapter);

        try {
            // cai endpoint server
            mSocket = IO.socket("http://192.168.1.12:3000/");
        } catch (Exception ignored) {
        }

        //connect den server
        mSocket.connect();

        mSocket.on("sever-send-result", listenRegisterResult);
        mSocket.on("server-send-user", listenListUser);
        mSocket.on("server-send-chat", listenChatContent);

        // gui yeu cau tao moi user
        btnAddUser.setOnClickListener(v -> {
            String userName = edtContent.getText().toString();
            if (userName.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên user!", Toast.LENGTH_SHORT).show();
            } else {
                mSocket.emit("client-register-user", userName);
            }
        });

        btnSend.setOnClickListener(v -> {
            String userName = edtContent.getText().toString();
            if (userName.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập nội dung chat!", Toast.LENGTH_SHORT).show();
            } else {
                mSocket.emit("client-send-chat", userName);
            }
        });
    }

    private void map() {
        lvUser = findViewById(R.id.lvUser);
        lvChat = findViewById(R.id.lvChat);
        edtContent = findViewById(R.id.edtContent);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnSend = findViewById(R.id.btnSend);
    }

    // lang nghe noi dung chat server gui ve
    private Emitter.Listener listenChatContent = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        String content = object.getString("content");
                        chatList.add(content);
                        chatAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    // lang nghe su kien tra ve danh sach nguoi dung
    private Emitter.Listener listenListUser = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        JSONArray array = object.getJSONArray("danhsach");

                        userList.clear();
                        for (int i = 0; i < array.length(); i++) {
                            String userName = array.getString(i);
                            userList.add(userName);
                        }
                        userAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private Emitter.Listener listenRegisterResult = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        boolean exist = object.getBoolean("ketqua");
                        if (exist) {
                            Toast.makeText(MainActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
}
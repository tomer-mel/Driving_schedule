package com.example.drivingschedule;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void nextPage(View view2) {
        Intent intent = new Intent(this, login.class);
        TextView textView = (TextView) view2;
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

}

//new Thread(new Runnable(){
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void run() {
//                String info = "";
//                try{
//                    String ip= "192.168.14.82";
//                    int port = 6789;
//                    Socket s = new Socket(ip, port);
//                    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
//                    dout.writeUTF("GET");
//                    BufferedReader din = new BufferedReader(new InputStreamReader(s.getInputStream()));
//                    int rate = din.read();
//                    info = din.readLine();
//                    din.close();
//                    dout.close();
//                    s.close();
//                    info += Integer.toString(rate);
//                }catch(Exception e){System.out.println(e);}
//            }
//        }).start();
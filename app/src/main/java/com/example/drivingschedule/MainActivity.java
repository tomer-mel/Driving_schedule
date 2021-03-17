package com.example.drivingschedule;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String info = getInfo();
        //loadweek(info);
    }


    public void nextPage(View view2) {
        Intent intent = new Intent(this, login.class);
        TextView textView = (TextView) view2;
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public String getInfo(){
        String info = "";
        try{
            String ip= "127.0.0.1";
            int port = 6789;
            Socket s = new Socket(ip, port);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("GET");
            DataInputStream din = new DataInputStream(s.getInputStream());
            info = din.readUTF();
            din.close();
            s.close();
        }catch(Exception e){System.out.println(e);}
        return info;
    }
    public void loadweek(String info) {
        LinearLayout ll=(LinearLayout)findViewById(R.id.LinearLayout);
        String tag;
        String message;
        for (int i = 1; i != 127; i = i+1) {
            tag = String.valueOf(i);
            TextView txt = (TextView) ll.findViewWithTag(tag);
            if (info.startsWith("O")) {
                info = info.substring(4);
                message = "Ofer";
                txt.setText(message);
            }
            if (info.startsWith("N")) {
                info = info.substring(4);
                message = "Nava";
                txt.setText(message);
            }
            if (info.startsWith("R")) {
                info = info.substring(4);
                message = "Roee";
                txt.setText(message);
            }
            if (info.startsWith("K")) {
                info = info.substring(5);
                message = "Keren";
                txt.setText(message);
            }
            if (info.startsWith("A")) {
                info = info.substring(4);
                message = "Aviv";
                txt.setText(message);
            }
            if (info.startsWith("N")) {
                info = info.substring(2);
                message = "";
                txt.setText(message);
            }
        }
        if (info.startsWith("r")) {
            info = info.substring(4);
            TextView textView = findViewById(R.id.rate);
            String rate ="Need another car:" + info;
            textView.setText(rate);
            }
    }
}
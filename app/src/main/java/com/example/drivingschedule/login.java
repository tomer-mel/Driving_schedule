package com.example.drivingschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.DataOutputStream;
import java.net.*;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String message2 = "Off";
        TextView textView = findViewById(R.id.temp);
        textView.setText(message2);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView2 = findViewById(R.id.UserName);
        textView2.setText(message);
        if (message.equals("Nava"))
            textView2.setTextColor(getResources().getColor(R.color.purple_500));
        if (message.equals("Ofer"))
            textView2.setTextColor(getResources().getColor(R.color.red));
        if (message.equals("Roee"))
            textView2.setTextColor(getResources().getColor(R.color.green));
        if (message.equals("Keren"))
            textView2.setTextColor(getResources().getColor(R.color.pink));
        if (message.equals("Aviv"))
            textView2.setTextColor(getResources().getColor(R.color.teal_700));
    }

    public void selectCube(View view3) {
        TextView textView1 = findViewById(R.id.UserName);
        String message = textView1.getText().toString();
        TextView textView = (TextView) view3;
        if (textView.getText() == "") {
            textView.setText(message);
            if (message.equals("Nava"))
                textView.setTextColor(getResources().getColor(R.color.purple_500));
            if (message.equals("Ofer"))
                textView.setTextColor(getResources().getColor(R.color.red));
            if (message.equals("Roee"))
                textView.setTextColor(getResources().getColor(R.color.green));
            if (message.equals("Keren"))
                textView.setTextColor(getResources().getColor(R.color.pink));
            if (message.equals("Aviv"))
                textView.setTextColor(getResources().getColor(R.color.teal_700));
        } else if (message.equals("Nava")) {
            textView.setText(message);
            textView.setTextColor(getResources().getColor(R.color.purple_500));
        }
        else if (message.equals("Ofer")) {
            textView.setText(message);
            textView.setTextColor(getResources().getColor(R.color.red));
        }
        TextView textView2 = findViewById(R.id.temp);
        if (textView2.getText() == "On"){
            if (textView1.getText() == textView.getText()) {
                message = "";
                textView.setText(message);
            }
            else if (message.equals("Nava")) {
                message = "";
                textView.setText(message);
            }
            else if (message.equals("Ofer")) {
                message = "";
                textView.setText(message);
            }
        }
    }

    public void delete(View view) {
        String message = "On";
        TextView textView = findViewById(R.id.temp);
        if (textView.getText() == "Off")
            textView.setText(message);
        else
            message = "Off";
        textView.setText(message);
    }

    public String save() {
        LinearLayout ll=(LinearLayout)findViewById(R.id.LinearLayout);
        StringBuilder AllInfo = new StringBuilder();
        String tag;
        for (int i = 1; i != 134; i = i+1) {
            tag = String.valueOf(i);
            TextView txt= (TextView) ll.findViewWithTag(tag);
            String message = txt.getText().toString();
            if (message.equals("")) {
                AllInfo.append("No");
            }
            else {
                AllInfo.append(message);
            }
        }
        return AllInfo.toString();
    }
    public void send(){
        String Info = "Post";
        String info = save();
        Info += info;
        try{
            Socket s = new Socket("192.168.14.5",6789);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Hello Server");
            dout.writeChars(Info);
            dout.flush();
            dout.close();
            s.close();
        }catch(Exception e){System.out.println("error");}
    }
}
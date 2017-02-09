package com.zhoushuai.socketdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private TextView getBtn;
    private TextView showText;
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBtn = (TextView) findViewById(R.id.getBtn);
        showText = (TextView) findViewById(R.id.showText);
       // showText.setText("等待连接....");

    }

    public void getMessage(View view) throws Exception {
        //showText.append("hello");
        //socket = new Socket("14.109.124.140", 8888);
        view.post(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("125.87.50.47", 8888);
                    showText.setText("连接成功!");
                     InputStream is=socket.getInputStream();
                     InputStreamReader isr=new InputStreamReader(is);
                    BufferedReader br=new BufferedReader(isr);
                    String data=br.readLine();
                    while(br!=null) {
                        showText.setText("");
                        showText.append(data);
                        data=br.readLine();

                    }
                    br.close();
                    isr.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//   new Thread(new Runnable() {
//       @Override
//       public void run() {
//
//
//           while (data != null)
//
//               showText.append(data);
//       }
//   }).start();

    }
}

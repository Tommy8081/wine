package com.example.servertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText LogInput,RegInput;
    private Button RegButton;
    public static Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textView);
        LogInput = (EditText) findViewById(R.id.LogInput);
        RegInput = (EditText) findViewById(R.id.RegInput);
        Button button= (Button) findViewById(R.id.LogButton);
        RegButton = (Button) findViewById(R.id.RegButton);
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("str1");
        Log.d("------------", "run: "+str1);
        if(str1!=null){Toast.makeText(MainActivity.this,str1,Toast.LENGTH_LONG).show();}
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText ( MainActivity.this, "正在登录", Toast.LENGTH_LONG ).show ();
                final String str = LogInput.getText ().toString ();
                final String str2 = RegInput.getText ().toString ();
                if(str.isEmpty()||str2.isEmpty()||str.contains("#")){
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                new Thread ( new Runnable () {
                    @Override
                    public void run() {
                        HttpURLConnection connection = null;
                        URL url = null;
                        try {
                            url = new URL ( "http://35.221.79.104:8888" );
                            connection = (HttpURLConnection) url.openConnection ();
                            connection.setRequestMethod ( "POST" );
                            connection.setConnectTimeout ( 8000 );
                            connection.setReadTimeout ( 8000 );
                            connection.setRequestProperty ( "Content-Type", "application/json" );
                            OutputStream outputStream = connection.getOutputStream ();
                            BufferedWriter requestBody = new BufferedWriter ( new OutputStreamWriter ( outputStream ) );
                            //String str = URLEncoder.encode("抓哇", "UTF-8");
                            requestBody.write ( "name=javaPost&body=" + str + " " + str2 );
                            requestBody.flush ();
                            requestBody.close ();
                            getResponseJava ( connection );
                        } catch (MalformedURLException e) {
                            e.printStackTrace ();
                        } catch (ProtocolException e) {
                            e.printStackTrace ();
                        } catch (IOException e) {
                            e.printStackTrace ();
                        }
                    }
                } ).start ();
//
            }
        });
        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getResponseJava(HttpURLConnection urlConnection) {
        InputStream in = null;
        try {
            in = urlConnection.getInputStream();//响应
        } catch (IOException e) {
            urlConnection.disconnect();
            //textView.setText(e.getMessage());
            return;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        final StringBuilder result = new StringBuilder();
        String tmp = null;

        try {
            while((tmp = reader.readLine()) != null){
                result.append(tmp);
            }
        } catch (IOException e) {
            //textView.setText(e.getMessage());
            return;
        } finally {
            try {
                reader.close();
                urlConnection.disconnect();
            } catch (IOException e) {
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
            }
        });
        new Thread ( new Runnable () {
            @Override
            public void run() {
                String str1 = result.toString ();
                if(str1.equals( "登录成功！")){
                  Intent intent = new Intent ( MainActivity.this,BottomNavtgaritonActivity.class );
                  startActivity ( intent );
                  MainActivity.this.finish ();
              }
            }
        } ).start ();
    }
}

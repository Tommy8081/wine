package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

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

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText LogInput2,RegInput2;
    private Button RegButton2;
    private String name,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        LogInput2 = (EditText) findViewById(R.id.LogInput2);
        RegInput2 = (EditText) findViewById(R.id.RegInput2);
        RegButton2 = (Button) findViewById(R.id.RegButton2);

        RegButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this,"正在注册",Toast.LENGTH_LONG).show();
                final String str = LogInput2.getText().toString();
                final String str2 = RegInput2.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection connection = null;
                        URL url = null;
                        try {
                            url = new URL("http://35.221.79.104:8888");
                            connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("POST");
                            connection.setConnectTimeout(8000);
                            connection.setReadTimeout(8000);
                            connection.setRequestProperty("Content-Type","application/json");
                            OutputStream outputStream=connection.getOutputStream();
                            BufferedWriter requestBody=new BufferedWriter(new OutputStreamWriter(outputStream));
                            //String str = URLEncoder.encode("抓哇", "UTF-8");
                            requestBody.write("name=javaPost&body2="+str+" "+str2);
                            requestBody.flush();
                            requestBody.close();
                            getResponseJava(connection);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
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
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                Log.d("-------------", "run: "+result.toString());
                intent.putExtra("str1",result.toString());
                startActivity(intent);
            }
        });

    }
}

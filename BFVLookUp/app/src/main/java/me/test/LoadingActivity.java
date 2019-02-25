package me.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class LoadingActivity extends AppCompatActivity {

    private String URL ="http://api.battlefieldtracker.com/api/v1/bfv/profile/origin/Chaos_T3njou";

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            try {
                switch (msg.getData().getString("INFO")){
                    case "OK":
                        Log.v("Test", msg.getData().getString("INFO"));
                        startMainActivity();
                        break;
                    case "FAILED":
                        Log.v("Test", msg.getData().getString("INFO"));
                        Toast.makeText(LoadingActivity.this,"Query Failed,User Not Exists or No Network",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(LoadingActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                /*
                if (msg.getData().getString("INFO").equalsIgnoreCase("OK")){
                    Log.v("Test", msg.getData().getString("INFO"));
                    data=getData();
                    startMainActivity();
                }
                if(msg.getData().getString("ERROR").contentEquals("FAILED")){
                    fragmentManager.beginTransaction().replace(R.id.Container,new FailedFragment()).commit();
                    Intent intent=new Intent(LoadingActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }*/
            }catch(NullPointerException e){
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    }
    MyHandler handler=new MyHandler();

    TextView loading;
    Typeface futura;
    static RootData data;
    FragmentManager fragmentManager;
    boolean success=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);
        if(getIntent().getStringExtra("Url")!=null)
            URL=getIntent().getStringExtra("Url");

        fragmentManager=getSupportFragmentManager();

        futura=Typeface.createFromAsset(getAssets(),"futura medium bt.ttf");
        loading=findViewById(R.id.onetime_loading);
        loading.setTypeface(futura);

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.INTERNET},1);
    }

    @Override
    protected void onStart() {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                saveToFile(getInputStream(URL));
                if(success)
                    data=getData();
                Message message=Message.obtain();
                Bundle bundle=new Bundle();
                if(data!=null&&data.data!=null){
                    message.what=0;
                    bundle.putString("INFO","OK");
                    message.setData(bundle);
                }else{
                    message.what=-1;
                    bundle.putString("INFO","FAILED");
                    message.setData(bundle);
                }
                handler.sendMessage(message);
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
        super.onStart();
    }

    private InputStream getInputStream(String URL){//获取InputStream
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(URL);
            httpURLConnection = (HttpURLConnection) url.openConnection();//获取URL连接对象
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(10000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);//设置开启输入流
            httpURLConnection.addRequestProperty("authority", "api.battlefieldtracker.com");
            httpURLConnection.addRequestProperty("method", "GET");
            httpURLConnection.addRequestProperty("path", "/api/v1/standard/bfv/profile/origin/Chaos_T3njou");
            httpURLConnection.addRequestProperty("scheme", "https");
            httpURLConnection.addRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httpURLConnection.addRequestProperty("accept-encoding", "*");
            httpURLConnection.addRequestProperty("accept-language", "en-US");
            httpURLConnection.addRequestProperty("cache-control", "max-age=0");
            httpURLConnection.addRequestProperty("cookie", "__cfduid=db96606c361acd1098673c467d1b95e0e1545634302; _ga=GA1.2.663561855.1545634337");
            httpURLConnection.addRequestProperty("upgrade-insecure-requests", "1");
            httpURLConnection.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();//连接并获取结果
            if (responseCode == 200) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    private void saveToFile(InputStream inputStream){//将数据保存到本地
        if(inputStream==null){//网络连接失败
            Log.v("INFO","Null");
            Message message=Message.obtain();
            Bundle bundle=new Bundle();
            message.what=-1;
            bundle.putString("INFO","FAILED");
            message.setData(bundle);
            handler.sendMessage(message);
            data=null;
            success=false;
            return;
        }
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("dat_weapon.json",MODE_PRIVATE);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ( fileOutputStream!= null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public RootData getData() {
        RootData data = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("dat_weapon.json"), "utf-8"));
            Gson gson = new Gson();
            data = gson.fromJson(reader, RootData.class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void startMainActivity(){
        Intent intent =new Intent(LoadingActivity.this,MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",data);
        intent.putExtra("data",bundle);
        intent.putExtra("Url",URL);
        startActivity(intent);
        finish();//结束当前活动
    }
}



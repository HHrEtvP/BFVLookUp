package me.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private List<String> platformList;
    private String userUrl;

    Spinner platformSpinner;
    EditText idEditor;
    TextView idTextView;
    TextView platformTextView;
    Button confirmButton;

    Typeface futura;

    String id;
    String platform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        platformList =new ArrayList<String>();
        platformList.add("Origin");
        platformList.add("Xbox");
        platformList.add("PlayStation");


        futura=Typeface.createFromAsset(getAssets(),"futura medium bt.ttf");
        idTextView=findViewById(R.id.onetime_user);
        idTextView.setTypeface(futura);
        platformTextView=findViewById(R.id.onetime_platform);
        platformTextView.setTypeface(futura);
        confirmButton=findViewById(R.id.confirm);
        confirmButton.setTypeface(futura);

        platformSpinner=findViewById(R.id.platformspinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,platformList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        platformSpinner.setAdapter(adapter);

        platformSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                platform="Origin";
            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                platform=platformList.get(i);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=idEditor.getText().toString();
                sendInfo(id,platform);
            }
        });

        idEditor=findViewById(R.id.ideditor);
    }

    public void sendInfo(String id,String platform){
        switch (platform){
            case "Origin":
                platform="origin";
                break;
            case "Xbox":
                platform="xbl";
                break;
            case "PlayStation":
                platform="psn";
                break;
        }
        userUrl="http://api.battlefieldtracker.com/api/v1/bfv/profile/"+platform+"/"+id;
        Intent intent=new Intent(LoginActivity.this,LoadingActivity.class);
        intent.putExtra("Url",userUrl);
        startActivity(intent);
        finish();
    }
}

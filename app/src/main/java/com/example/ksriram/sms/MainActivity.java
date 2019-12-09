package com.example.ksriram.sms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
TextView textView,textView1;
EditText editText;
RadioButton busy,free;
String extra = null;
String senderNumber = null;
    String senderNumber1 = null;
    public SharedPreferences sp;
    Button save;
public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private  String text;
    private  String swich;
@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
        save = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView1 = findViewById(R.id.textView2);
        busy = findViewById(R.id.busy);
        free = findViewById(R.id.free);
    sp= getSharedPreferences("save",0);
    Intent intent = getIntent();
    extra = intent.getStringExtra("message");
    senderNumber = intent.getStringExtra("sender");
    Toast.makeText(this, "Ha...." + extra, Toast.LENGTH_SHORT).show();
    //  textView.setText(" "+extra);
    /*
     */


busy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       String busyText = busy.getText().toString();
       textView1.setText(" "+busyText);



    }
});
    free.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
       String freeText = free.getText().toString();
       textView1.setText(" "+freeText);


        }
    });
 save.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         save();
     }
 });
 load();
 update();
    if (extra != null) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(senderNumber, null, " "+text, null, null);
    }
}
public void save(){
    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(TEXT,textView1.getText().toString());
    editor.apply();
    Toast.makeText(this, "Status Updated", Toast.LENGTH_SHORT).show();
}
public void load(){
    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
    text = sharedPreferences.getString(TEXT,"");

}
public void update(){
    textView1.setText(text);
}
}



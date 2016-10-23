package com.mail.dinesh.realmtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mail.dinesh.realmtest.bo.LocalMail;
import com.mail.dinesh.realmtest.dbTranscations.Manager;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    TextView label_threadId;
    TextView label_threadRealmId;
    TextView label_threadRealm;
    EditText editText_threadId;
    Button button_commitData;
    Realm myRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListener();

    }

    private void init() {

        label_threadId = (TextView)findViewById(R.id.label_threadId);
        label_threadRealmId = (TextView)findViewById(R.id.label_threadRealmId);
        label_threadRealm = (TextView)findViewById(R.id.label_threadRealm);
        editText_threadId = (EditText)findViewById(R.id.editText_threadId);
        button_commitData = (Button)findViewById(R.id.button_commitData);
        myRealm = Realm.getInstance(getApplicationContext());
    }

    private void initListener() {
        button_commitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Save data
                if(editText_threadId.getText()!=null || !editText_threadId.getText().equals("")) {
                    String threadId = editText_threadId.getText().toString();
                    Manager.commitIntoRealm(myRealm,threadId);
                } else {
                    Toast.makeText(getApplicationContext(),"Enter Data",Toast.LENGTH_SHORT).show();
                }

                //Show data
                LocalMail mailObj = Manager.obtainFromRealm(myRealm);
                if(mailObj!=null) {
                    String realmId = String.valueOf(mailObj.getId());
                    if(realmId!=null || !realmId.equals("")) {
                        label_threadRealmId.setText(realmId);
                    } else {
                        Toast.makeText(getApplicationContext(),"no id found",Toast.LENGTH_SHORT).show();
                    }
                    String realmValue = mailObj.getThreadId();
                    if(realmValue!=null || !realmValue.equals("")) {
                        label_threadRealm.setText(realmValue);
                    } else {
                        Toast.makeText(getApplicationContext(),"no value found",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

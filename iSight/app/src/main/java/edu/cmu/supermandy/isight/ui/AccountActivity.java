package edu.cmu.supermandy.isight.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.cmu.supermandy.isight.model.User;
import edu.cmu.supermandy.isight.util.DBDAO;
import edu.cmu.supermandy.isight.ws.remote.UserRequest;

/**
 * Created by Xavier on 2016/5/2.
 */
public class AccountActivity extends Activity {
    int id;

    TextView userNameTextView;
    TextView phoneNumTextView;
    TextView ageTextView;
    TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        userNameTextView=(TextView)findViewById(R.id.account_username2);
        emailTextView=(TextView)findViewById(R.id.account_email2);
        ageTextView=(TextView)findViewById(R.id.account_age2);
        phoneNumTextView=(TextView)findViewById(R.id.account_phoneNum2);
        final DBDAO userdao = new DBDAO(this);
        final UserRequest ur = new UserRequest();
        id = Integer.valueOf(this.getIntent().getStringExtra("Id"));
        String user = ur.getUserInfo(id);
        String[] columnindex = new String[]{"Username", "Email", "Password", "Age", "PhoneNum"};
        String[] userinfo = userdao.getRowData("UserTable", "Id", id + " ", columnindex);
        String username = userinfo[0];
        String email = userinfo[1];
        Integer age = Integer.parseInt(userinfo[3]);
        String phoneNum = userinfo[4];

        userNameTextView.setText(username);
        emailTextView.setText(email);
        ageTextView.setText(String.valueOf(age));
        phoneNumTextView.setText(phoneNum);

    }


}

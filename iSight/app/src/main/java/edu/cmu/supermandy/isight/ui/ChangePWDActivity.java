package edu.cmu.supermandy.isight.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.cmu.supermandy.isight.exception.AuthenticationFailedException;
import edu.cmu.supermandy.isight.model.User;
import edu.cmu.supermandy.isight.util.DBDAO;

/**
 * Created by Xavier on 2016/5/2.
 */
public class ChangePWDActivity extends Activity {
    private Button confirmButton;
    private EditText oldPWDEditText;
    private EditText newPWDEditText;
    private EditText confirmPWDEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPWDEditText = (EditText) findViewById(R.id.old_password);
        newPWDEditText = (EditText) findViewById(R.id.new_password);
        confirmPWDEditText = (EditText) findViewById(R.id.confirm_password);

        confirmButton = (Button) findViewById(R.id.change_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean changeSucceed=changePWD();
                if (changeSucceed) {
                    startActivity(new Intent(ChangePWDActivity.this, LoginActivity.class));
                }
            }
        });

    }

    private boolean changePWD() {
        try {
            final DBDAO userdao = new DBDAO(this);
            int userId = Integer.valueOf(this.getIntent().getStringExtra("Id"));
            String oldPassword = oldPWDEditText.getText().toString();
            String newPassword = newPWDEditText.getText().toString();
            String confirmPassword = confirmPWDEditText.getText().toString();

            String[] columnindex = new String[]{"Username", "Email", "Password", "Age", "PhoneNum"};
            String[] userinfo = userdao.getRowData("UserTable", "Id", userId + " ", columnindex);
            String usernamelong = userinfo[0];
            String username = usernamelong.substring(1, usernamelong.length() - 1);
            String emaillong = userinfo[1];
            String email = emaillong.substring(1, emaillong.length() - 1);
            String truePassword = userinfo[2];
            Integer age = Integer.parseInt(userinfo[3]);
            String phoneNum = userinfo[4];

            /** check old password*/
            if (!oldPassword.equals(truePassword)) {
                throw new AuthenticationFailedException("Password is incorrect.\n");
            }

            /** check password and confirmed password match*/
            if (!newPassword.equals(confirmPassword)) {
                throw new AuthenticationFailedException("New passwords don't match.\n ");
            }

            userdao.deleteUser(userId);
            System.out.println("------------------>deleted!");
            User newuser = new User(username, email, newPassword, age, phoneNum);
            userdao.insertUser(newuser);
            System.out.println("------------------>inserted!");
            return true;
        }
        catch (AuthenticationFailedException e) {
            e.fix(ChangePWDActivity.this);
            return false;
        }
    }
}

package edu.cmu.supermandy.isight.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.supermandy.isight.model.User;
import edu.cmu.supermandy.isight.util.DBDAO;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {
    private AutoCompleteTextView accountAutoView;
    private EditText passwordEditText;
    private Button signInButton;
    private Button signUpButton;

    private String account;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FontsOverride.setDefaultFont(this, "DEFAULT", "Roboto-Thin.ttf");
        final DBDAO userdao=new DBDAO(this);
        accountAutoView= (AutoCompleteTextView) findViewById(R.id.login_email);
        passwordEditText=(EditText)findViewById(R.id.login_password);
        signInButton=(Button)findViewById(R.id.login_sign_in_button);
        signUpButton=(Button)findViewById(R.id.login_sign_up_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User validuser=validatelogin(userdao);
                if(validuser!=null) {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this,MainActivity.class);
                    intent.putExtra("Id", validuser.getId()+"");
                    startActivity(intent);
                }
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }


    private User validatelogin(DBDAO userdao){
        account=accountAutoView.getText().toString();
        password=passwordEditText.getText().toString();
        int userId=-1;
        User user;

        /** check input account info exists*/
        userId=userdao.checkDataExist("UserTable","Username","["+account+"]");
        if(userId==-1) userId=userdao.checkDataExist("UserTable","Email","["+account+"]");
        if(userId==-1) {
            Toast.makeText(LoginActivity.this, "The user doesn't exist.\n " , Toast.LENGTH_LONG).show();
            return null;
        }

        user=checkPassword(userdao,userId,password);
        if(user==null){
            Toast.makeText(LoginActivity.this, "Password isn't incorrect.\n " , Toast.LENGTH_LONG).show();
            return null;
        }
        return user;
    }

    private User checkPassword(DBDAO userdao, int id, String inputPwd){
        String[] columnindex=new String[]{"Username","Email","Password","Age","PhoneNum"};
        String[] userinfo=userdao.getRowData("UserTable","Id",id+" ",columnindex);
        String username=userinfo[0];
        String email=userinfo[1];
        String password=userinfo[2];
        Integer age=Integer.parseInt(userinfo[3]);
        String phoneNum=userinfo[4];
        if(!password.equals(inputPwd)){
            return null;
        }
        User user=new User(username,email,password,age,phoneNum);
        user.setId(id);
        return user;
    }
}


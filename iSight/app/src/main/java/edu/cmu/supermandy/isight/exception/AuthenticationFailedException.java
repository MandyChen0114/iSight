package edu.cmu.supermandy.isight.exception;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by shicheng on 5/3/16.
 */
public class AuthenticationFailedException extends Throwable implements Fixable {
    String message;
    public AuthenticationFailedException(String message) {
        this.message = message;
    }

    @Override
    public void fix(Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

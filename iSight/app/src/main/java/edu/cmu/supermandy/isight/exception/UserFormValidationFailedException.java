package edu.cmu.supermandy.isight.exception;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by shicheng on 5/3/16.
 */
public class UserFormValidationFailedException extends Throwable implements Fixable {
    String message;
    public UserFormValidationFailedException(String message) {
        this.message = message;
    }

    @Override
    public void fix(Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

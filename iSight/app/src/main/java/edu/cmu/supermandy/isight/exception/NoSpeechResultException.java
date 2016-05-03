package edu.cmu.supermandy.isight.exception;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by shicheng on 5/3/16.
 */
public class NoSpeechResultException extends Throwable implements Fixable {
    public void fix(Context context) {
        Toast.makeText(context, "There is no speech detected, please try to say something again.", Toast.LENGTH_SHORT).show();
    }
}

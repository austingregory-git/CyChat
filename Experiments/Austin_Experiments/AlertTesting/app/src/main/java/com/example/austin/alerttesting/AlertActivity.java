package com.example.austin.alerttesting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
    }

    public static void display(Context con, String mes) {
        display(con, mes,"Howdy!");
    }

    public static void display(Context con, String mes, String name) {
        final AlertDialog alertDialog = new AlertDialog.Builder(con).create();

        alertDialog.setTitle(name);
        alertDialog.setMessage(mes);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.hide();
            }
        });

        alertDialog.show();
    }
}

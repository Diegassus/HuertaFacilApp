package com.example.huertafacilapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class Alertas {
  public static void Salir(Context context){
    new AlertDialog.Builder(context)
        .setTitle("Atencion!")
        .setMessage("Usted esta por abandonar la aplicacion. ¿Estas seguro?")
        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            Activity act = (Activity) context;
            act.finish();
          }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(context, "Continuemos...", Toast.LENGTH_LONG).show();
          }
        }).show();
  }
}

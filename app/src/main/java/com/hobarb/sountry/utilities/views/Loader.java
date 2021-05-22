package com.hobarb.sountry.utilities.views;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hobarb.sountry.R;

public class Loader extends AppCompatActivity {

    AlertDialog alertDialog;
    Context context;
    public Loader(Context context)
    {
        this.context = context;
        alertDialog = new AlertDialog.Builder(context).create();
    }

    public void showAlertDialog()
    {

        View customLayout = LayoutInflater.from(context).inflate(R.layout.layout_loader, null);
        alertDialog.setView(customLayout);
        alertDialog.show();

    }

    public void dismissAlertDialog()
    {
        alertDialog.dismiss();
    }



}

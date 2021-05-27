package com.hobarb.sountry.utilities.views;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hobarb.sountry.R;

public class Toaster{

    public static void showToast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


}

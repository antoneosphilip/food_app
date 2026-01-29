package com.example.foodproj.helper;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodproj.R;
import com.example.foodproj.presentation.auth.view.LoginActivity;

abstract public class Helper {
    public static void show(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        Button btnLogin = dialog.findViewById(R.id.btnLogin);
        TextView btnCancel = dialog.findViewById(R.id.btnCancel);

        btnLogin.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}

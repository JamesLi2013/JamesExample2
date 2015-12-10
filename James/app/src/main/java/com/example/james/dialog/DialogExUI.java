package com.example.james.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.james.R;

/**公用对话框的例子*/
public class DialogExUI extends AppCompatActivity {
    CommonDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_ex);
        dialog = new CommonDialog(this, "我要改变内容了");
        dialog.setConfirmOnClickListener(new CommonDialog.ConfirmOnClickListener() {
            @Override
            public void confirmOnClick() {
                Toast.makeText(getApplicationContext(), "已经改变内容了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDialog(View v) {
        dialog.show();

    }
}

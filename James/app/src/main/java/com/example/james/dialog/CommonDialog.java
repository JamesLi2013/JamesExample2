package com.example.james.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.james.R;

/**
 * Created by Administrator on 2015/12/9.
 * 通用的Dialog
 */
public class CommonDialog extends AlertDialog implements View.OnClickListener {
    private TextView mTitle, mMessage, mLeft, mRight;
    private ConfirmOnClickListener mConfirmOnClickListener;
    private String messageText;

    protected CommonDialog(Context context) {
        super(context, R.style.common_dialog);
    }

    public CommonDialog(Context context, String message) {
        super(context, R.style.common_dialog);
        messageText=message;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        initView();

    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.tv_dialog_common_title);
        mMessage = (TextView) findViewById(R.id.tv_dialog_common_content);
        mLeft = (TextView) findViewById(R.id.tv_dialog_common_left);
        mRight = (TextView) findViewById(R.id.tv_dialog_common_right);

        if (messageText != null) {
            setMessage(messageText);
        }

        mLeft.setOnClickListener(this);
        mRight.setOnClickListener(this);

    }

    /**
     * 设置对话框标题
     */
    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    /**
     * 设置对话框内容
     */
    public void setMessage(CharSequence message) {
        mMessage.setText(message);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_common_left:
                mConfirmOnClickListener.confirmOnClick();
                dismiss();
                break;
            case R.id.tv_dialog_common_right:
                dismiss();
                break;
        }
    }

    /**
     * 设置确认的监听接口事件
     */
    public void setConfirmOnClickListener(ConfirmOnClickListener confirmOnClickListener) {
        mConfirmOnClickListener = confirmOnClickListener;
    }

    /**
     * 确认操作的监听接口
     */
    public interface ConfirmOnClickListener {
        void confirmOnClick();
    }
}

package com.example.james.others;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.james.R;

import java.util.ArrayList;

/**
 * 使用原生的分享功能
 */
public class ShareOriginalUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_original);
        Button mBtnShare = (Button) findViewById(R.id.btn_share);
        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText();
//                shareImg();
//                shareMultiple();
            }
        });
    }

    /**
     * 文本分享
     */
    private void shareText() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "这是一个很好的软件,快来和你的小伙伴一起来玩耍吧");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "我的分享"));
    }

    /**
     * 二进制分享,如图片
     */
    private void shareImg() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, getImgUri(Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.jpg"));
        Log.e("lqx", Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.jpg");
        shareIntent.setType("image/jpg");
        startActivity(Intent.createChooser(shareIntent, "分享的图片"));
    }

    private Uri getImgUri(String imgPath) {
        Uri mUri = Uri.parse("content://media/external/images/media");
        Uri mImageUri = null;

        Cursor cursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Images.Media.DEFAULT_SORT_ORDER);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String data = cursor.getString(cursor
                    .getColumnIndex(MediaStore.MediaColumns.DATA));
            if (imgPath.equals(data)) {
                int ringtoneID = cursor.getInt(cursor
                        .getColumnIndex(MediaStore.MediaColumns._ID));
                mImageUri = Uri.withAppendedPath(mUri, ""
                        + ringtoneID);
                break;
            }
            cursor.moveToNext();
        }
        Log.e("lqx", mImageUri == null ? "null" : mImageUri.toString());
        return mImageUri;
    }

    /**
     * 多张图片,多个同类型的,图文混排没有找到
     */
    private void shareMultiple() {
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        Uri imgUri = getImgUri(Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.jpg");
        imageUris.add(imgUri);
        imageUris.add(imgUri);

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "Share images to.."));
    }
}

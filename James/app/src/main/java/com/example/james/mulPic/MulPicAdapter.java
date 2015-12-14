package com.example.james.mulPic;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.james.R;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2015/12/12.
 */
public class MulPicAdapter extends QuickAdapter<MulPicBean> {
   private Context mContext;
    public MulPicAdapter(Context context, int layoutResId, List<MulPicBean> data) {
        super(context, layoutResId, data);
        mContext=context;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, MulPicBean item) {
        helper.setText(R.id.tv_mul_pic_title,item.getTitle());
        if(item.getPics()==null){
            //没有图片
            helper.getView(R.id.iv_item_single).setVisibility(View.GONE);
            helper.getView(R.id.gv_mul_pic).setVisibility(View.GONE);
        }else if(item.getPics().size()==1){
            //单张图片
            helper.getView(R.id.iv_item_single).setVisibility(View.VISIBLE);
            helper.getView(R.id.gv_mul_pic).setVisibility(View.GONE);
            helper.setImageUrl(R.id.iv_item_single,item.getPics().get(0));
        }else{
            //多张图片
            helper.getView(R.id.iv_item_single).setVisibility(View.GONE);
            helper.getView(R.id.gv_mul_pic).setVisibility(View.VISIBLE);
            GridView gridView=helper.getView(R.id.gv_mul_pic);
            gridView.setAdapter(new QuickAdapter<String>(mContext,R.layout.item_gv_img,item.getPics()) {
                @Override
                protected void convert(BaseAdapterHelper helper, String item) {
                    ImageView iv=helper.getView(R.id.iv_item_gv);
                    Picasso.with(mContext).load(item).into(iv);
                }
            });
        }

    }
}

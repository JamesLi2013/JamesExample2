package com.example.james.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.MyData;
import com.example.james.R;
import com.example.james.weixinContact.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ToolBarDemoUI extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_demo);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content);

    }

    private void initData() {
        mAdapter = new MyAdapter(this, MyData.getContactData());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context mContext;
        private ArrayList<Person> mData;
        private static final int TYPE_HEAD = 0;
        private static final int TYPE_BODY = 1;

        public MyAdapter() {
        }

        public MyAdapter(Context context, ArrayList<Person> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           if (viewType == TYPE_HEAD) {
            View view1=View.inflate(mContext,R.layout.item_toolbar,null);
               return new HeadViewHolder(view1);
            }else if(viewType==TYPE_BODY){
               View view2 = View.inflate(mContext, R.layout.item_adapter, null);
               return new MyViewHolder(view2);
           }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof MyViewHolder) {
                MyViewHolder viewHolder = (MyViewHolder) holder;
                viewHolder.mTvTitle.setText(mData.get(position).getName());
//            int side=300+ new Random().nextInt(150);
//            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(500,side);
//            viewHolder.mIvHeader.setLayoutParams(layoutParams);
                Picasso.with(mContext).load(mData.get(position).getImgUrl()).into(viewHolder.mIvHeader);
                viewHolder.mIvHeader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "点击的位置是:" + position , Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            if (mData != null)
                return mData.size() + 1;
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (position==0) {
                return TYPE_HEAD;
            }
            return TYPE_BODY;
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTvTitle;
        public ImageView mIvHeader;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle= (TextView) itemView.findViewById(R.id.tv_item_common_adapter);
            mIvHeader= (ImageView) itemView.findViewById(R.id.iv_item_common_adapter);
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }
}

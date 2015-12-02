package com.example.james.weixinContact;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.james.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/12/1.
 */
public class ContactAdapter extends BaseAdapter {
    private ArrayList<Person> mDataLists=new ArrayList<>();
    private Context mContext;

    public ContactAdapter(){
        super();
    }

    public ContactAdapter(Context context, ArrayList<Person> mDataLists) {
        super();
        mContext=context;
        this.mDataLists = mDataLists;
    }

    @Override
    public int getCount() {
        if(mDataLists!=null&&mDataLists.size()>0)
            return mDataLists.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        Person person=mDataLists.get(position);
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.item_contact,null);
            viewHolder.tv_content= (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.tv_letter= (TextView) convertView.findViewById(R.id.tv_letter);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_letter.setText(person.getLetter());
        viewHolder.tv_content.setText(person.getName());
        return convertView;
    }

    private class ViewHolder{
        TextView tv_content,tv_letter;
    }
}

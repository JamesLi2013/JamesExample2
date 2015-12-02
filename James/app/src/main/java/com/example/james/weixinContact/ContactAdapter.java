package com.example.james.weixinContact;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.james.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/12/1.
 * 配合A-Z快速选取的适配器
 */
public class ContactAdapter extends BaseAdapter {
    private ArrayList<Person> mDataLists = new ArrayList<>();
    private Context mContext;
    private HashMap<String, Integer> mFirstLetterLists = new HashMap<>();//mDataLists中首个字母开头的索引集合

    public ContactAdapter() {
        super();
    }

    public ContactAdapter(Context context, ArrayList<Person> mDataLists) {
        super();
        mContext = context;
        this.mDataLists = mDataLists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDataLists != null && mDataLists.size() > 0)
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
        ViewHolder viewHolder = null;
        Person person = mDataLists.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_contact, null);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.tv_letter = (TextView) convertView.findViewById(R.id.tv_letter);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //如果此对象的索引属于首字母索引集合中的值,就显示字母,否则,不显示
        if (getFirstLetterPostion(person.getLetter())==position) {
            viewHolder.tv_letter.setVisibility(View.VISIBLE);
            viewHolder.tv_letter.setText(person.getLetter());
        } else {
            viewHolder.tv_letter.setVisibility(View.GONE);
        }

        viewHolder.tv_content.setText(person.getName());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_content, tv_letter;
    }

    /**
     * 获取mDataLists中首个字母开头的索引集合
     */
    public void getFirstLetterPositionList() {
        mFirstLetterLists.clear();
        int size = mDataLists.size();
        for (int i = 0; i < size; i++) {
            if (!mFirstLetterLists.containsKey(mDataLists.get(i).getLetter())) {
                mFirstLetterLists.put(mDataLists.get(i).getLetter(), i);
            }
        }
    }

    /**
     * 相同首字母所在的第一个元素的索引
     */
    public int getFirstLetterPostion(String letter) {
        if(mFirstLetterLists.containsKey(letter))
            return mFirstLetterLists.get(letter);
        return -1;
    }

    /**
     * 对集合进行排序
     */
    private void sortDatas() {
        CharacterParser characterParse=CharacterParser.getInstance();
        String firstLetter="";
        for (Person person:mDataLists){
            if(TextUtils.isEmpty(person.getName().trim())){
                person.setLetter("#");//名字为空,首字母默认为"#"
            }else{
                firstLetter=characterParse.getSelling(person.getName().trim()).substring(0,1).toUpperCase();
                if(firstLetter.matches("[A-Z]"))//如果此字符不为A-Z中的一个,那么,则会设置为"#"
                    person.setLetter(firstLetter);
                else
                    person.setLetter("#");
            }
        }
        Collections.sort(mDataLists,new MyComparator());
    }

    /**
     * 重写方法,在更新前,先对集合进行排序
     */
    @Override
    public void notifyDataSetChanged() {
        sortDatas();
        getFirstLetterPositionList();
        super.notifyDataSetChanged();
    }
}

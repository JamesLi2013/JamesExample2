package com.example.james.weixinContact;

import java.util.Comparator;

/**
 * Created by lqx on 2015/12/2.
 * 字母比较器,从A-Z排序
 */
public class MyComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if("#".equals(p1.getLetter())){
            return 1;
        }else if("#".equals(p2.getLetter())){
            return -1;
        }else{
            return p1.getLetter().compareTo(p2.getLetter());
        }
    }
}

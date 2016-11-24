package com.android.wen.cstp.util;

import android.util.Log;

import com.android.wen.cstp.pojo.CSTPReportList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * 集合按照时间排序
 */
public class ComparableUtil implements Comparator {
    SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat sdf1 = new SimpleDateFormat("HHmmss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

    private Integer getWfsj1(CSTPReportList.DataBean m) {
        if (m == null || m.getWFSJ() == null)
            return null;
        try {
            Date date = sdf0.parse(m.getWFSJ());
            String str = sdf2.format(date);
            Integer i = Integer.valueOf(str);
            return i;
        } catch (ParseException e) {
            return null;
        }
    }

    private Integer getWfsj2(CSTPReportList.DataBean m) {
        if (m == null || m.getWFSJ() == null)
            return null;
        try {
            Date date = sdf0.parse(m.getWFSJ());
            String str = sdf1.format(date);
            Integer i = Integer.valueOf(str);
            return i;
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        Log.e("ComparableUtil", "compare: 进行排序");
        //先比较年月日
        Integer d1 = getWfsj1((CSTPReportList.DataBean) o1);
        Integer d2 = getWfsj1((CSTPReportList.DataBean) o2);
        if (d1 == null && d2 == null)
            return 0;
        if (d1 == null)
            return -1;
        if (d2 == null)
            return 1;
        if (d1.equals(d2)) {//年月日相等时 则处理时分秒
            Integer d3 = getWfsj2((CSTPReportList.DataBean) o1);
            Integer d4 = getWfsj2((CSTPReportList.DataBean) o2);
            if (d3 == null && d4 == null)
                return 0;
            if (d3 == null)
                return -1;
            if (d4 == null)
                return 1;
            return d3.compareTo(d4);
        }
        return d1.compareTo(d2);
    }
}


package com.yzx.lib.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2019/7/11.
 */

public class ArithUtil {
    private static final int DEF_DIV_SCALE = 10;

    //String类型数据的+-*/
    //加
    public static double add(String d1, String d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).doubleValue();
    }

    //减
    public static double sub(String d1, String d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2).doubleValue();
    }

    //乘
    public static double mul(String d1, String d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();

    }

    //除
    public static double div(String d1, String d2) {

        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    //比较二个double类型数据的大小
    public static int compare(double a, double b) {
        BigDecimal data1 = new BigDecimal(a);
        BigDecimal data2 = new BigDecimal(b);
        return data1.compareTo(data2);
    }

    //比较二个float类型数据的大小
    public static int compare(float a, float b) {
        BigDecimal data1 = new BigDecimal(a);
        BigDecimal data2 = new BigDecimal(b);
        return data1.compareTo(data2);
    }

    //取消科学计数法
    public static String cancelE(float num) {
        return new DecimalFormat("0.00").format(num);
    }

    /**
     * 四舍五入
     *
     * @param v     要传入的数值
     * @param scale 例：2位小数 "#0.00"
     * @return
     */
    public static String roundByScale(String v, String scale) {
        BigDecimal d = new BigDecimal(String.valueOf(v));
        DecimalFormat decimalFormat = new DecimalFormat(scale);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(d.doubleValue());
    }
}




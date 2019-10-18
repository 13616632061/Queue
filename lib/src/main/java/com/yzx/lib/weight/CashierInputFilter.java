package com.yzx.lib.weight;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/8/2.
 * 描述：EditText输入框中过滤输入指定金额格式</br>
 * 1.小数点前7位数</br>
 * 2.小数点后2位数
 */
public class CashierInputFilter implements InputFilter {

    private Pattern mPattern;
    //输入的最大金额
    private final int MAX_VALUE = Integer.MAX_VALUE;

    //小数点后的2位数
    private final int POINTER_AFTER_LENGTH = 2;

    //小数点前的7位数
    private int POINTER_BEFORE_LENGTH = 5;

    private final String POINTER = ".";

    private static final String ZERO = "0";
    private static final String ZERO_POINT_ZERO = "0.00";

    public CashierInputFilter() {
        mPattern = Pattern.compile("([0-9]|\\.)*");
    }

    public CashierInputFilter(int _maxLength) {
        mPattern = Pattern.compile("([0-9]|\\.)*");
        POINTER_BEFORE_LENGTH = _maxLength - POINTER_AFTER_LENGTH - 1;

    }


    /**
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return 输入内容
     */

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {

        String sourceText = source.toString();
        LogUtils.e("CharSequence: sourceText: " + sourceText);
        String sourceEndText = "";

//        if (sourceText.equals(POINTER)) {
//            sourceText = ZERO;
//        }
//        int mCount = sourceText.length();
//        if (sourceText.startsWith(ZERO_POINT_ZERO)) {
//            sourceText = sourceText.substring(ZERO_POINT_ZERO.length(), mCount);
//        }
        while (sourceText.startsWith(POINTER)||sourceText.startsWith(ZERO)){
            if (sourceText.length()>1){
                sourceText = sourceText.substring(1, sourceText.length());
            }else {
                sourceText="";
            }
        }
        LogUtils.e("CharSequence: sourceText: " + sourceText);
        if (TextUtils.isEmpty(sourceText)) {
            return ZERO_POINT_ZERO;
        }
        int mStart = 0;
        int mBefore = sourceText.length() - 1;
        int mCount = sourceText.length();
        String sourceBeforeText = sourceText.substring(mStart, mBefore);
        String sourceCurText = sourceText.substring(mBefore, mCount);
        LogUtils.e("CharSequence: sourceBeforeText0: " + sourceBeforeText);
        LogUtils.e("CharSequence: sourceCurText0: " + sourceCurText);
//        if (sourceBeforeText.equals(ZERO)) {
//            if (sourceCurText.equals(POINTER)) {
//                sourceCurText = "";
//            }else {
//            }
//        }

        if (sourceBeforeText.contains(POINTER) && sourceCurText.equals(POINTER)) {
            sourceCurText = "";
        } else {
            int pointIndex = sourceText.indexOf(POINTER);
            LogUtils.e("CharSequence: pointIndex: " + pointIndex);
            if (pointIndex == -1) {//小数点前5位
                if (mCount > POINTER_BEFORE_LENGTH) {
                    sourceBeforeText = sourceText.substring(mStart, POINTER_BEFORE_LENGTH);
                    if (!sourceCurText.equals(POINTER)) {
                        sourceCurText = "";
                    }
                }
            }
            if (pointIndex != -1 && mCount > pointIndex + POINTER_AFTER_LENGTH) {//小数点后两位
                sourceBeforeText = sourceText.substring(mStart, pointIndex + POINTER_AFTER_LENGTH + 1);
                sourceCurText = "";
            }
        }

        sourceEndText = sourceBeforeText + sourceCurText;
        LogUtils.e("CharSequence: sourceBeforeText: " + sourceBeforeText);
        LogUtils.e("CharSequence: sourceCurText: " + sourceCurText);
        LogUtils.e("CharSequence: sourceEndText: " + sourceEndText);

        return sourceEndText;
    }


}

package yzx.com.queue.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;

/**
 * Created by Administrator on 2019/10/21.
 */

public class TakeNumberView extends FrameLayout {

    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.tv_paly)
    TextView tvPaly;
    @InjectView(R.id.tv_phone)
    TextView tvPhone;
    @InjectView(R.id.tv_num)
    TextView tvNum;
    @InjectView(R.id.one)
    TextView one;
    @InjectView(R.id.two)
    TextView two;
    @InjectView(R.id.three)
    TextView three;
    @InjectView(R.id.four)
    TextView four;
    @InjectView(R.id.five)
    TextView five;
    @InjectView(R.id.six)
    TextView six;
    @InjectView(R.id.seven)
    TextView seven;
    @InjectView(R.id.eight)
    TextView eight;
    @InjectView(R.id.nine)
    TextView nine;
    @InjectView(R.id.clear)
    LinearLayout clear;
    @InjectView(R.id.zero)
    TextView zero;
    @InjectView(R.id.remove)
    LinearLayout remove;
    @InjectView(R.id.btn_take_number)
    Button btnTakeNumber;
    @InjectView(R.id.tv_more_function)
    TextView tvMoreFunction;
    @InjectView(R.id.layout_phone)
    RelativeLayout layoutPhone;
    @InjectView(R.id.layout_num)
    RelativeLayout layoutNum;


    private Context context;
    private View view;
    private int mPosition = 1;//输入手机号：0，输入就餐人数：1
    private takeNumber takeNumber;



    public TakeNumberView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TakeNumberView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TakeNumberView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TakeNumberView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        view = View.inflate(context, R.layout.main_right_layout, null);
        addView(view);
        ButterKnife.inject(this, view);
        //默认输入就餐人数
        selectPhoneOrNum(1);
    }

    /**
     * 选择手机号码或者就餐人数
     *
     * @param type
     */
    public void selectPhoneOrNum(int type) {

        mPosition = type;

        switch (type) {
            case 0://手机号码
                layoutPhone.setBackgroundResource(R.drawable.bg_while_red);
                layoutNum.setBackgroundResource(R.drawable.bg_while_gray);
                break;
            case 1://就餐人数
                layoutPhone.setBackgroundResource(R.drawable.bg_while_gray);
                layoutNum.setBackgroundResource(R.drawable.bg_while_red);
                break;
        }
    }

    /**
     * 输入手机号码或者就餐人数
     */
    private void inputPhoneOrNum(View v) {
        switch (mPosition) {
            case 0:
                String strPhone = tvPhone.getText().toString();
                tvPhone.setText(strPhone + ((TextView) v).getText().toString());
                break;
            case 1:
                String strNum = tvNum.getText().toString();
                tvNum.setText(strNum + ((TextView) v).getText().toString());
                break;
        }
    }

    /**
     * 清除
     *
     * @param v
     */
    private void clear(View v) {
        switch (mPosition) {
            case 0:
                tvPhone.setText("");
                break;
            case 1:
                tvNum.setText("");
                break;
        }
    }

    /**
     * 删除
     *
     * @param v
     */
    private void remove(View v) {
        switch (mPosition) {
            case 0:
                String strPhone = tvPhone.getText().toString();
                if (strPhone != null && !strPhone.equals("")) {
                    strPhone = strPhone.substring(0, strPhone.length() - 1);
                    tvPhone.setText(strPhone);
                }
                break;
            case 1:
                String strNum = tvNum.getText().toString();
                if (strNum != null && !strNum.equals("")) {
                    strNum = strNum.substring(0, strNum.length() - 1);
                    tvNum.setText(strNum);
                }

                break;
        }

    }

    /**
     * 初始化就餐人数和手机号
     */
    public void initPhoneAndNum(){
        tvPhone.setText("");
        tvNum.setText("");
        selectPhoneOrNum(1);
    }
    @OnClick({R.id.tv_search, R.id.tv_paly, R.id.layout_phone, R.id.layout_num, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
            R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero, R.id.clear, R.id.remove, R.id.btn_take_number})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search://查询
                break;
            case R.id.tv_paly://播放
                break;
            case R.id.layout_phone://手机号
                selectPhoneOrNum(0);
                break;
            case R.id.layout_num://就餐人数
                selectPhoneOrNum(1);
                break;
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.zero:
                inputPhoneOrNum(view);
                break;
            case R.id.clear://清除
                clear(view);
                break;
            case R.id.remove://删除
                remove(view);
                break;
            case R.id.btn_take_number://取号
                takeNumber();
                break;
        }
    }

    /**
     * 取号
     */
    private void takeNumber() {
        String strPhone = tvPhone.getText().toString();
        String strNum = tvNum.getText().toString();

        if (TextUtils.isEmpty(strNum)) {
            Toast.makeText(context,context.getResources().getString(R.string.empty_num),Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isEmpty(strPhone)) {
            if (!RegexUtils.isMobileExact(strPhone)) {
                Toast.makeText(context,context.getResources().getString(R.string.error_phone),Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (takeNumber != null) {
            takeNumber.takeNumber(strNum, strPhone);
        }
    }

    private interface takeNumber {
        void takeNumber(String num, String phone);
    }

    private void setTakeNumber(takeNumber takeNumber) {
        this.takeNumber = takeNumber;
    }



}

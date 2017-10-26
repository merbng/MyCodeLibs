package com.app.netconnection;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.a1merbng.tempdemo.R;
import com.bigkoo.pickerview.OptionsPickerView;
import com.zhouyou.view.seekbar.SignSeekBar;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import static com.app.a1merbng.tempdemo.R.id.tv_show_result;

/**
 * 星座运势查询
 * retrofit网络请求封装库
 * rxJava
 * 特效snackBar
 * 底部弹出选择框
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private OptionsPickerView pvOptions;
    private final ArrayList<String> list = new ArrayList<>();
    private final ArrayList<String> listName = new ArrayList<>();

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.tv_selsct)
    TextView tvSelect;
    @BindView(tv_show_result)
    TextView tvShowResult;
    @BindView(R.id.seek_bar)
    SignSeekBar signSeekBar;
    private  String str = "today";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvShowResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        initData();
    }

    @OnClick(R.id.tv_selsct)
    public void select() {
        pvOptions.show();
    }

    private void initData() {
        initListData();
        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = list.get(options1);

                switch (tx) {
                    case "today":
                        str = "今日";
                        break;
                    case "tomorrow":
                        str = "明日";
                        break;
                    case "week":
                        str = "本周";
                        break;
                    case "nextweek":
                        str = "下周";
                        break;
                    case "month":
                        str = "本月";
                        break;
                    case "year":
                        str = "本年";

                        break;
                }
                tvSelect.setText(str);
                if (!tx.equals("year")) {
                    getAonstellationLuck(etInput.getText().toString(), tx);
                } else {
                    getYearAonstellationLuck(etInput.getText().toString(), tx);
                }
            }
        }).build();
        pvOptions.setPicker(listName);

        signSeekBar.getConfigBuilder()
                .min(0)
                .max(4)
                .progress(2)
                .sectionCount(4)
                .trackColor(ContextCompat.getColor(this, R.color.slide_1))
                .secondTrackColor(ContextCompat.getColor(this, R.color.slide_2))
                .thumbColor(ContextCompat.getColor(this, R.color.slide_3))
                .sectionTextColor(ContextCompat.getColor(this, R.color.slide_4))
                .sectionTextSize(16)
                .thumbTextColor(ContextCompat.getColor(this, R.color.arcColor))
                .thumbTextSize(18)
                .signColor(ContextCompat.getColor(this, R.color.orange700))
                .signTextSize(18)
                .autoAdjustSectionMark()
                .sectionTextPosition(SignSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();
        signSeekBar.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                //fromUser 表示是否是用户触发 是否是用户touch事件产生
                String s = String.format(Locale.CHINA, "onChanged int:%d, float:%.1f", progress, progressFloat);
                tvShowResult.setText(s);
            }

            @Override
            public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {
                String s = String.format(Locale.CHINA, "onActionUp int:%d, float:%.1f", progress, progressFloat);
                tvShowResult.setText(s);
            }

            @Override
            public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                String s = String.format(Locale.CHINA, "onFinally int:%d, float:%.1f", progress, progressFloat);
                tvShowResult.setText(s);
            }
        });
    }

    private void initListData() {
        list.clear();
        list.add("today");
        list.add("tomorrow");
        list.add("week");
        list.add("nextweek");
        list.add("month");
        list.add("year");
        listName.clear();
        listName.add("今日");
        listName.add("明日");
        listName.add("本周");
        listName.add("下周");
        listName.add("本月");
        listName.add("本年");
    }

    /**获取年
     * @param consName
     * @param type
     */
    private void getYearAonstellationLuck(String consName, String type) {
        RxClient.getYearLuck(consName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<LuckYearBean>() {
            @Override
            public void onError(ErrorModel model, Throwable e) {
                tvShowResult.setText("请求错误");
            }

            @Override
            public void onNext(@NonNull LuckYearBean luckBean) {
                Log.e(TAG, luckBean.toString());
                String year = "星座名：" + luckBean.getName() + "\n"
                        + "年份：" + luckBean.getDate() + "\n"
                        + "健康：" + luckBean.getHealth().get(0) + "\n"
                        + "爱情：" + luckBean.getLove().get(0) + "\n"
                        + "幸运石：" + luckBean.getLuckeyStone() + "\n"
                        + "爱情：" + luckBean.getLove().get(0) + "\n"
                        + "信息：" + luckBean.getMima().getInfo() + "\n"
                        + "总结：" + luckBean.getMima().getText().get(0) + "\n";
                if (luckBean.getFinance().size() > 0) {
                    changeStrColor(tvShowResult, year, luckBean.getMima().getText().get(0));
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**获取 日，周，月
     * @param consName
     * @param type
     */
    private void getAonstellationLuck(String consName, String type) {
        RxClient.getAonstellationLuck(consName, type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<LuckBean>() {
            @Override
            public void onError(ErrorModel model, Throwable e) {
                tvShowResult.setText("请求错误");
            }

            @Override
            public void onNext(@NonNull LuckBean luckBean) {
                Log.e(TAG, luckBean.toString());
                String dayAndTommorow = "星座名：" + luckBean.getName() + "\n"
                        + "日期：" + luckBean.getDatetime() + "\n"
                        + "颜色：" + luckBean.getColor() + "\n"
                        + "好友：" + luckBean.getQFriend() + "\n"
                        + "总结：" + luckBean.getSummary() + "\n";

                String weekAndNextWeek = "星座名：" + luckBean.getName() + "\n"
                        + luckBean.getWork() + "\n"
                        + luckBean.getHealth() + "\n"
                        + luckBean.getLove() + "\n"
                        + luckBean.getMoney() + "\n";

                String monthAndNextMonth = "星座名：" + luckBean.getName() + "\n"
                        + "工作：" + luckBean.getWork() + "\n"
                        + "健康：" + luckBean.getHealth() + "\n"
                        + "爱情：" + luckBean.getLove() + "\n"
                        + "财运：" + luckBean.getMoney() + "\n"
                        + "总结：" + luckBean.getAll() + "\n";
                if (str.equals("今日")||str.equals("明日")) {
                    changeStrColor(tvShowResult, dayAndTommorow, luckBean.getSummary());
                }
                if (str.equals("本周")||str.equals("下周")) {
                    changeStrColor(tvShowResult, weekAndNextWeek, luckBean.getMoney());
                }
                if (str.equals("本月")) {
                    changeStrColor(tvShowResult, monthAndNextMonth, luckBean.getAll());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void changeStrColor(TextView tv, String text, String result) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);
        if (!TextUtils.isEmpty(result)) {
            builder.setSpan(blueSpan, text.length() - result.length() - 1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(builder);
        } else {
            tv.setText("数据出错");
        }
    }
}

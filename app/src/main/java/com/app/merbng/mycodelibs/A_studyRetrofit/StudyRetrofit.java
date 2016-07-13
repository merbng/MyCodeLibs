package com.app.merbng.mycodelibs.A_studyRetrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 学习Retrofit
 * Created by zx on 2016/7/13.
 */
public class StudyRetrofit extends BaseActivity {
    private static final String BASE_URL = "http://v.juhe.cn/";
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyretrofit);
        btn = (Button) findViewById(R.id.btn_msg);
        tv = (TextView) findViewById(R.id.tv_msg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
                WeatherInfoService weatherInfoService = retrofit.create(WeatherInfoService.class);
                final Call<ResponseBody> call = weatherInfoService.getString();
//                同步操作
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Response<ResponseBody> response = call.execute();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        tv.setText(response.body().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
//                异步操作
/*                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            tv.setText(response.body().string());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        tv.setText(t.getMessage());
                    }
                })*/
                ;
            }
        });
    }

}

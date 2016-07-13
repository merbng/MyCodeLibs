package com.app.merbng.mycodelibs.A_studyRetrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**http://blog.csdn.net/qq_17250009/article/details/51108414
 * 学习Retrofit
 * Created by zx on 2016/7/13.
 */
public class StudyRetrofitActivity extends BaseActivity {
    private static final String BASE_URL = "http://v.juhe.cn/";
    private static final String FORMAT = "2";
    private static final String CITYNAME = "北京";
    private static final String KEY = "b952ad7acbc7415f3f3c9bf274e39c45";
    Button btn, btn2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyretrofit);
        btn = (Button) findViewById(R.id.btn_msg);
        btn2 = (Button) findViewById(R.id.btn_msg2);
        tv = (TextView) findViewById(R.id.tv_msg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                使用Retrofit获取JSON类型的数据
                final Gson gson = new GsonBuilder().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                WeatherInfoService weatherInfoService = retrofit.create(WeatherInfoService.class);
                Call<WeatherInfo> call = weatherInfoService.getWeatherInfo();
                call.enqueue(new Callback<WeatherInfo>() {
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        WeatherInfo info = response.body();
                        tv.setText(gson.toJson(info));
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable t) {
                        tv.setText("error:" + t.getMessage());
                    }
                });
/*                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
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
*//*                call.enqueue(new Callback<ResponseBody>() {
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
                })*//*
                ;*/
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Gson gson = new GsonBuilder().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                WeatherInfoService weatherInfoService = retrofit.create(WeatherInfoService.class);
                weatherInfoService.getWeatherInfo(FORMAT, CITYNAME, KEY);
            }
        });
    }

}

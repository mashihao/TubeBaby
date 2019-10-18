package com.shsy.tubebaby.network;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shsy.tubebaby.BuildConfig;
import com.shsy.tubebaby.Converter.RemoveShellConverter;
import com.shsy.tubebaby.model.ClientMessage;
import com.shsy.tubebaby.model.LocalLogger;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Retrofit 提供类
 */

public class RetrofitClient {
    private final int TIME_OUT = 15;
    private final String AUTHORZATION = "Authorization";
    private String GET = "GET";
    private String DELETE = "DELETE";
    private Retrofit retrofit = null;
    private OkHttpClient okHttpClient = null;
    private Gson gson = getGson();

    /**
     * 单例 禁止初始化
     */
    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        return RetrofitClientHolder.retrofitClient;
    }

    /**
     * 获取Retrofit实例
     *
     * @return
     */
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            if (okHttpClient == null) {
                initOkHttp();
            }
            retrofit = new Retrofit.Builder().client(okHttpClient)
                    //配置根地址：在 app.gradle 文件中配置
                    .baseUrl(BuildConfig.SELF_BASE_URL)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(RemoveShellConverter.create())
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * 初始化 OKhttp ，并添加过滤器
     */
    private void initOkHttp() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (okHttpClientBuilder.interceptors() != null) {
            okHttpClientBuilder.interceptors().clear();
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                ClientMessage msg = new ClientMessage();
                msg.setName(message);
//                StompClientUtil.send(ObjectToJson.javabeanToJson(msg));

                Log.e("network",message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(interceptor);



        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        okHttpClient = okHttpClientBuilder.build();
    }

    /**
     * 延迟加载，线程安全（java中class加载时互斥的），也减少了内存消耗
     */
    private static class RetrofitClientHolder {
        public static final RetrofitClient retrofitClient = new RetrofitClient();
    }

    /**
     * 获取 Gson 对象
     *
     * @return
     */
    public Gson getGson() {
        return new GsonBuilder().disableHtmlEscaping().create();
    }

    class NullOnEmptyConverterFactory extends Converter.Factory {
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate =
                    retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody value) throws IOException {
                    if (value.contentLength() == 0) return null;
                    return delegate.convert(value);
                }
            };
        }
    }
}


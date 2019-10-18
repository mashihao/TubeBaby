package com.shsy.tubebaby.Converter;

import android.accounts.NetworkErrorException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by mashihao on 2017/11/14.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static String CHARSET = "UTF-8";
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    private final boolean isRemoveShell;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, boolean isRemoveShell) {
        this.gson = gson;
        this.adapter = adapter;
        this.isRemoveShell = isRemoveShell;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {


        //如果不是 移除 外壳
        if (!isRemoveShell) {
            JsonReader jsonReader = gson.newJsonReader(value.charStream());
            try {
                return adapter.read(jsonReader);
            } finally {
                value.close();
            }
        }

        JSONObject jb;

        try {
            String data = value.string();
            jb = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
            //抛出  数据异常 后面的 Rxjava 会捕获到异常
            throw new IOException("the response data is error");
        }

        //可能为 null
        String realData = null;

        try {
            boolean isSuccess = jb.optBoolean("isSuccess");
            String message = jb.optString("message");
            if (!isSuccess) {
                throw new NetworkErrorException(message == null || "".equals(message) ? "the request is fail" : message);
            }
            realData = jb.optString("result");

        } catch (NetworkErrorException e) {
            throw new NetWorkErrorException(e);
        }

        if (realData == null) {
            realData = "";
        }

//        Log.e("MSH", realData);
        InputStream inputStream = new ByteArrayInputStream(realData.getBytes(CHARSET));
        Reader reader = new InputStreamReader(inputStream, CHARSET);

        JsonReader jsonReader = gson.newJsonReader(reader);

        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }


    }
}

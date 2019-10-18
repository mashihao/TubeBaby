package com.shsy.tubebaby.Converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by xiaojinzi on 13/11/2017 9:31 AM
 * blog: http://blog.csdn.net/u011692041
 * github: github.com/xiaojinzi123
 */
public class EHaiGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static String CHARSET = "UTF-8";

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final boolean isRemoveShell;

    EHaiGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, boolean isRemoveShell) {
        this.gson = gson;
        this.adapter = adapter;
        this.isRemoveShell = isRemoveShell;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        if (!isRemoveShell) { // those code is original
            JsonReader jsonReader = gson.newJsonReader(value.charStream());
            try {
                return adapter.read(jsonReader);
            } finally {
                value.close();
            }
        }

        // remove the shell

        JSONObject jb;

        try {

            // all
            String data = value.string();

            // the code is test fail request
            /*JSONObject jj = new JSONObject();
            jj.put("IsSuccess", false)
                    .put("Message", "请求错误啦");
            data = jj.toString();*/

            jb = new JSONObject(data);

        } catch (Exception e) {
            throw new IOException("the response data is error");
        }

        // may be is null
        String realData = null;

        try {
            boolean isSuccess = jb.optBoolean("isSuccess");
            String message = jb.optString("message");
            if (!isSuccess) {
                throw new NetWorkErrorException((message == null || "".equals(message)) ? "the request is fail" : message);
            }
            realData = jb.optString("result");
        } catch (Exception e) {
            throw new NetWorkErrorException(e);
        }

        if (realData == null) {
            realData = "";
        }

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

package com.shsy.tubebaby.Converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by admin on 2017/11/14.
 */

public class RemoveShellConverter extends Converter.Factory {


    public static RemoveShellConverter create() {
        return create(new Gson());
    }


    public static RemoveShellConverter create(Gson gson) {
        return new RemoveShellConverter(gson);
    }

    private final Gson gson;

    public RemoveShellConverter(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        boolean isRemoveShell = false;

        if (annotations != null) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof RemoveShell) {
                    isRemoveShell = true;
                    break;
                }
            }
        }
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyConverter<>(gson, adapter,isRemoveShell);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }

}

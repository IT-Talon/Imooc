package com.hzcwtech.imooc.entity;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.io.Serializable;

/**
 * Created by Talon on 2017/6/9.
 */

public class HttpEntity implements Serializable {
    private static final long serialVersionUID = -2422143920089282776L;

    private static final int HTTP_SUCCESS = 1000;
         /*data
         "errorCode": 1000,
         "errorDesc": "成功",
         "status": 1,
         "timestamp": 1497247862336*/

    private Object data;
    private int errorCode;
    private String errorDesc;
    private int status;
    private long timestamp;

    @Deprecated
    public Object getData() {
        return data;
    }

    @Deprecated
    public void setData(Object data) {
        this.data = data;
    }

    public final boolean isSuccess() {
        return errorCode == HttpEntity.HTTP_SUCCESS;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public final <T> T getDataObject(TypeReference<T> typeReference) {
        T obj = null;
        String dataStr = data == null ? null : JSON.toJSONString(data);

        if (!TextUtils.isEmpty(dataStr)) {
            try {
                obj = JSON.parseObject(dataStr, typeReference, new Feature[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    public final <T> T getDataObject(Class<T> clazz) {
        T obj = null;
        String dataStr = data == null ? null : JSON.toJSONString(data);

        if (!TextUtils.isEmpty(dataStr)) {
            try {
                obj = JSON.parseObject(dataStr, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return obj;
    }
}

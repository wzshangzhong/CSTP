package com.android.wen.cstp.pojo;

/**
 * Created by Administrator on 2016/12/9.
 */
public class Version {
    private String appVsn;
    private String url;
    private String appSize;
    private String picUrl;
    private String code;

    public Version() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppVsn() {
        return appVsn;
    }

    public void setAppVsn(String appVsn) {
        this.appVsn = appVsn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}

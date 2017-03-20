package com.android.wen.cstp.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/20.
 */
public class WFJB implements Serializable {
    private int id;//id
    private String fzjg;//发证机关
    private String hphm; //号牌号码
    private String wfxw; //违法行为
    private String wfsj; //违法时间
    private String wfdd; //违法地点
    private String xsfx; //行驶方向
    private String cllx; //车辆类型
    private String csys; //车身颜色
    private String xxms; //补充说明
    private String tplj;//违法图片,文件路径
    private String jbrxm; //举报人姓名
    private String lxdh;//手机号码
    private String sfzh; //身份证号码
    private String zqdw; //执勤单位

    public WFJB() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getWfxw() {
        return wfxw;
    }

    public void setWfxw(String wfxw) {
        this.wfxw = wfxw;
    }

    public String getWfsj() {
        return wfsj;
    }

    public void setWfsj(String wfsj) {
        this.wfsj = wfsj;
    }

    public String getWfdd() {
        return wfdd;
    }

    public void setWfdd(String wfdd) {
        this.wfdd = wfdd;
    }

    public String getXsfx() {
        return xsfx;
    }

    public void setXsfx(String xsfx) {
        this.xsfx = xsfx;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getCsys() {
        return csys;
    }

    public void setCsys(String csys) {
        this.csys = csys;
    }

    public String getXxms() {
        return xxms;
    }

    public void setXxms(String xxms) {
        this.xxms = xxms;
    }

    public String getTplj() {
        return tplj;
    }

    public void setTplj(String tplj) {
        this.tplj = tplj;
    }

    public String getJbrxm() {
        return jbrxm;
    }

    public void setJbrxm(String jbrxm) {
        this.jbrxm = jbrxm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getZqdw() {
        return zqdw;
    }

    public void setZqdw(String zqdw) {
        this.zqdw = zqdw;
    }

    @Override
    public String toString() {
        return "WFJB{" +
                "id=" + id +
                ", fzjg='" + fzjg + '\'' +
                ", hphm='" + hphm + '\'' +
                ", wfxw='" + wfxw + '\'' +
                ", wfsj='" + wfsj + '\'' +
                ", wfdd='" + wfdd + '\'' +
                ", xsfx='" + xsfx + '\'' +
                ", cllx='" + cllx + '\'' +
                ", csys='" + csys + '\'' +
                ", xxms='" + xxms + '\'' +
                ", tplj='" + tplj + '\'' +
                ", jbrxm='" + jbrxm + '\'' +
                ", lxdh='" + lxdh + '\'' +
                ", sfzh='" + sfzh + '\'' +
                ", zqdw='" + zqdw + '\'' +
                '}';
    }
}

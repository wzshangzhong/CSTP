package com.android.wen.cstp.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/9.
 */
public class User implements Serializable {
    public int id;//		'ID'
    public String lxdh;//		'联系电话（手机号码）'
    public String xm;//	'姓名'
    public String imsi;//		'imsi移动终端标识'
    public String password;//	'密码'
    public String sfzh;//    	'身份证号'
    public String gzdw;//    	'工作单位'
    public String rbsj;//    	'入表时间'
    public String bb;//  		'app版本'
    public String yhm;//    	'用户名或警号'
    public String yhkh;//    	'银行卡号'
    public String hphm;//    	'号牌号码'
    public String xb;//	'性别'
    public String csrq;//     	'出身日期'
    public String xzz;//	'现住址'
    public String yhlx;//  		'用户类型'
    public String yyzt;//    	'状态'
    public String dwdm;//    	'单位代码'
    public String khh;//   		'开户行'
    public String unitcode;//    	'大队代码'
    public String fzjg;//    	'发证机关'
    public String xxly;//    	'信息来源'



    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    public String getRbsj() {
        return rbsj;
    }

    public void setRbsj(String rbsj) {
        this.rbsj = rbsj;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getYhkh() {
        return yhkh;
    }

    public void setYhkh(String yhkh) {
        this.yhkh = yhkh;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getXzz() {
        return xzz;
    }

    public void setXzz(String xzz) {
        this.xzz = xzz;
    }

    public String getYhlx() {
        return yhlx;
    }

    public void setYhlx(String yhlx) {
        this.yhlx = yhlx;
    }

    public String getYyzt() {
        return yyzt;
    }

    public void setYyzt(String yyzt) {
        this.yyzt = yyzt;
    }

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    public String getKhh() {
        return khh;
    }

    public void setKhh(String khh) {
        this.khh = khh;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getXxly() {
        return xxly;
    }

    public void setXxly(String xxly) {
        this.xxly = xxly;
    }

    public User() {
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lxdh='" + lxdh + '\'' +
                ", xm='" + xm + '\'' +
                ", imsi='" + imsi + '\'' +
                ", password='" + password + '\'' +
                ", sfzh='" + sfzh + '\'' +
                ", gzdw='" + gzdw + '\'' +
                ", rbsj='" + rbsj + '\'' +
                ", bb='" + bb + '\'' +
                ", yhm='" + yhm + '\'' +
                ", yhkh='" + yhkh + '\'' +
                ", hphm='" + hphm + '\'' +
                ", xb='" + xb + '\'' +
                ", csrq='" + csrq + '\'' +
                ", xzz='" + xzz + '\'' +
                ", yhlx='" + yhlx + '\'' +
                ", yyzt='" + yyzt + '\'' +
                ", dwdm='" + dwdm + '\'' +
                ", khh='" + khh + '\'' +
                ", unitcode='" + unitcode + '\'' +
                ", fzjg='" + fzjg + '\'' +
                ", xxly='" + xxly + '\'' +
                '}';
    }

}
package com.android.wen.cstp.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/13.
 */
public class CstpWfjb implements Serializable {

    private Long id;
    /**
     * Not-null value.
     */
    private String mark;
    /**
     * Not-null value.
     */
    private String wfsj;
    /**
     * Not-null value.
     */
    private String wfld;
    /**
     * Not-null value.
     */
    private String wfch;
    /**
     * Not-null value.
     */
    private String qksm;
    /**
     * Not-null value.
     */
    private String jbr;
    /**
     * Not-null value.
     */
    private String sfhm;
    /**
     * Not-null value.
     */
    private String lxdh;
    /**
     * Not-null value.
     */
    private String zqdw;
    /**
     * Not-null value.
     */
    private String image1_path;
    private String image2_path;
    private String image3_path;

    public CstpWfjb() {
    }

    public CstpWfjb(Long id) {
        this.id = id;
    }

    public CstpWfjb(Long id, String mark, String wfsj, String wfld, String wfch, String qksm, String jbr, String sfhm, String lxdh, String zqdw, String image1_path, String image2_path, String image3_path) {
        this.id = id;
        this.mark = mark;
        this.wfsj = wfsj;
        this.wfld = wfld;
        this.wfch = wfch;
        this.qksm = qksm;
        this.jbr = jbr;
        this.sfhm = sfhm;
        this.lxdh = lxdh;
        this.zqdw = zqdw;
        this.image1_path = image1_path;
        this.image2_path = image2_path;
        this.image3_path = image3_path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Not-null value.
     */
    public String getMark() {
        return mark;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * Not-null value.
     */
    public String getWfsj() {
        return wfsj;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setWfsj(String wfsj) {
        this.wfsj = wfsj;
    }

    /**
     * Not-null value.
     */
    public String getWfld() {
        return wfld;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setWfld(String wfld) {
        this.wfld = wfld;
    }

    /**
     * Not-null value.
     */
    public String getWfch() {
        return wfch;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setWfch(String wfch) {
        this.wfch = wfch;
    }

    /**
     * Not-null value.
     */
    public String getQksm() {
        return qksm;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setQksm(String qksm) {
        this.qksm = qksm;
    }

    /**
     * Not-null value.
     */
    public String getJbr() {
        return jbr;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    /**
     * Not-null value.
     */
    public String getSfhm() {
        return sfhm;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setSfhm(String sfhm) {
        this.sfhm = sfhm;
    }

    /**
     * Not-null value.
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * Not-null value.
     */
    public String getZqdw() {
        return zqdw;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setZqdw(String zqdw) {
        this.zqdw = zqdw;
    }

    /**
     * Not-null value.
     */
    public String getImage1_path() {
        return image1_path;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setImage1_path(String image1_path) {
        this.image1_path = image1_path;
    }

    public String getImage2_path() {
        return image2_path;
    }

    public void setImage2_path(String image2_path) {
        this.image2_path = image2_path;
    }

    public String getImage3_path() {
        return image3_path;
    }

    public void setImage3_path(String image3_path) {
        this.image3_path = image3_path;
    }


}

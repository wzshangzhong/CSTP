package com.android.wen.cstp.pojo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class CSTPReportList {

    /**
     * code : 1
     * data : [{"WFBH":"500000600","WFSJ":"2016/9/18 10:14:51","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"123456","HPZL":"02","FZJGDM":"A1","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/18 10:15:56","GXSJ":"2016/9/20 8:30:58","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"JTWFJB","SHSJ":"2016/9/20 8:30:58","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000601","WFSJ":"2016/9/18 10:14:51","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"123456","HPZL":"02","FZJGDM":"A1","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/18 10:16:03","GXSJ":"2016/9/20 8:31:05","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"JTWFJB","SHSJ":"2016/9/20 8:31:05","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000643","WFSJ":"2016/9/22 9:53:06","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B32145","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/22 9:55:11","GXSJ":"2016/9/22 9:42:43","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000644","WFSJ":"2016/9/22 9:53:06","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B32145","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/22 9:56:53","GXSJ":"2016/9/22 9:43:56","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000646","WFSJ":"2016/9/22 16:53:29","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B456987","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/22 16:54:58","GXSJ":"2016/9/22 16:47:31","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000647","WFSJ":"2016/9/22 17:02:11","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B121212","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/22 17:02:56","GXSJ":"2016/9/22 16:53:27","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000648","WFSJ":"2016/9/22 17:02:11","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B00000","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/22 17:03:57","GXSJ":"2016/9/22 16:53:01","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000650","WFSJ":"2016/9/22 17:17:38","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"C12222","HPZL":"02","FZJGDM":"湘C","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/22 17:18:56","GXSJ":"2016/9/22 17:08:16","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000645","WFSJ":"2016/9/22 9:53:06","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B32145","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/22 9:59:38","GXSJ":"2016/9/22 9:52:37","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000628","WFSJ":"2016/9/21 9:06:37","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"C12345","HPZL":"02","FZJGDM":"湘C","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:29:31","GXSJ":"2016/9/21 9:23:43","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000629","WFSJ":"2016/9/21 9:34:57","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"345212","HPZL":"02","FZJGDM":"C3","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:37:27","GXSJ":"2016/9/21 9:28:03","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000630","WFSJ":"2016/9/21 9:34:57","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"C345212","HPZL":"02","FZJGDM":"湘C","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:38:42","GXSJ":"2016/9/21 9:36:41","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000631","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:53:51","GXSJ":"2016/9/21 9:42:59","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000638","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 10:00:15","GXSJ":"2016/9/21 9:44:08","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000639","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 10:00:58","GXSJ":"2016/9/21 9:57:20","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000642","WFSJ":"2016/9/21 10:05:16","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B32123","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 10:06:12","GXSJ":"2016/9/21 10:01:14","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000632","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:57:51","GXSJ":"2016/9/21 9:45:45","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000633","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:57:54","GXSJ":"2016/9/21 9:48:24","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000634","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:58:03","GXSJ":"2016/9/21 9:47:50","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000635","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:58:12","GXSJ":"2016/9/21 9:45:00","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000636","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:58:14","GXSJ":"2016/9/21 9:53:04","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000637","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 9:58:34","GXSJ":"2016/9/21 9:55:18","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000640","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 10:02:49","GXSJ":"2016/9/21 9:57:28","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000641","WFSJ":"2016/9/21 9:52:30","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123443","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/21 10:04:08","GXSJ":"2016/9/21 9:55:09","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000649","WFSJ":"2016/9/22 17:02:11","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B111111","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/9/22 17:15:26","GXSJ":"2016/9/22 17:11:42","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"1","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""},{"WFBH":"500000860","WFSJ":"2016/10/9 10:10:28","WFDDDM":"820282361","CFDM":"13450","BBHM":"10","HPHM":"B123321","HPZL":"02","FZJGDM":"湘B","FKJE":"0","ZJS":"1","PZHM":"","RKBJ":"0","FCSJ":"2016/10/9 10:11:29","GXSJ":"2016/10/9 9:54:55","CWYY":"","LRDWDM":"JTWFJBDW","LRMJDM":"app","ZQDWDM":"JTWFJBDW","ZQMJDM":"app","CLLX":"无","SJZL":"1","CFJDS":"app","SHBJ":"0","ZT":"0","WFSJLX":"","SHR":"","SHSJ":"","THCS":"0","SYXZ":"","PZBS":"","SCZ":"0","BZZ":"0","XSFX":"","ZPSB":"","CD":"","ZJLX":"图片_jpg","TZDH":"","DDGLS":"","DDMS":"","SJLYLX":"1","BZ":""}]
     */

    private String code;
    /**
     * WFBH : 500000600
     * WFSJ : 2016/9/18 10:14:51
     * WFDDDM : 820282361
     * CFDM : 13450
     * BBHM : 10
     * HPHM : 123456
     * HPZL : 02
     * FZJGDM : A1
     * FKJE : 0
     * ZJS : 1
     * PZHM :
     * RKBJ : 0
     * FCSJ : 2016/9/18 10:15:56
     * GXSJ : 2016/9/20 8:30:58
     * CWYY :
     * LRDWDM : JTWFJBDW
     * LRMJDM : app
     * ZQDWDM : JTWFJBDW
     * ZQMJDM : app
     * CLLX : 无
     * SJZL : 1
     * CFJDS : app
     * SHBJ : 1
     * ZT : 0
     * WFSJLX :
     * SHR : JTWFJB
     * SHSJ : 2016/9/20 8:30:58
     * THCS : 0
     * SYXZ :
     * PZBS :
     * SCZ : 0
     * BZZ : 0
     * XSFX :
     * ZPSB :
     * CD :
     * ZJLX : 图片_jpg
     * TZDH :
     * DDGLS :
     * DDMS :
     * SJLYLX : 1
     * BZ :
     */

    private List<DataBean> data;

    public static CSTPReportList objectFromData(String str) {

        return new Gson().fromJson(str, CSTPReportList.class);
    }

    public static CSTPReportList objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), CSTPReportList.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<CSTPReportList> arrayJavaBeanListFromData(String str) {

        Type listType = new TypeToken<ArrayList<CSTPReportList>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String WFBH;
        private String WFSJ;
        private String WFDDDM;
        private String CFDM;
        private String BBHM;
        private String HPHM;
        private String HPZL;
        private String FZJGDM;
        private String FKJE;
        private String ZJS;
        private String PZHM;
        private String RKBJ;
        private String FCSJ;
        private String GXSJ;
        private String CWYY;
        private String LRDWDM;
        private String LRMJDM;
        private String ZQDWDM;
        private String ZQMJDM;
        private String CLLX;
        private String SJZL;
        private String CFJDS;
        private String SHBJ;
        private String ZT;
        private String WFSJLX;
        private String SHR;
        private String SHSJ;
        private String THCS;
        private String SYXZ;
        private String PZBS;
        private String SCZ;
        private String BZZ;
        private String XSFX;
        private String ZPSB;
        private String CD;
        private String ZJLX;
        private String TZDH;
        private String DDGLS;
        private String DDMS;
        private String SJLYLX;
        private String BZ;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static DataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getWFBH() {
            return WFBH;
        }

        public void setWFBH(String WFBH) {
            this.WFBH = WFBH;
        }

        public String getWFSJ() {
            return WFSJ;
        }

        public void setWFSJ(String WFSJ) {
            this.WFSJ = WFSJ;
        }

        public String getWFDDDM() {
            return WFDDDM;
        }

        public void setWFDDDM(String WFDDDM) {
            this.WFDDDM = WFDDDM;
        }

        public String getCFDM() {
            return CFDM;
        }

        public void setCFDM(String CFDM) {
            this.CFDM = CFDM;
        }

        public String getBBHM() {
            return BBHM;
        }

        public void setBBHM(String BBHM) {
            this.BBHM = BBHM;
        }

        public String getHPHM() {
            return HPHM;
        }

        public void setHPHM(String HPHM) {
            this.HPHM = HPHM;
        }

        public String getHPZL() {
            return HPZL;
        }

        public void setHPZL(String HPZL) {
            this.HPZL = HPZL;
        }

        public String getFZJGDM() {
            return FZJGDM;
        }

        public void setFZJGDM(String FZJGDM) {
            this.FZJGDM = FZJGDM;
        }

        public String getFKJE() {
            return FKJE;
        }

        public void setFKJE(String FKJE) {
            this.FKJE = FKJE;
        }

        public String getZJS() {
            return ZJS;
        }

        public void setZJS(String ZJS) {
            this.ZJS = ZJS;
        }

        public String getPZHM() {
            return PZHM;
        }

        public void setPZHM(String PZHM) {
            this.PZHM = PZHM;
        }

        public String getRKBJ() {
            return RKBJ;
        }

        public void setRKBJ(String RKBJ) {
            this.RKBJ = RKBJ;
        }

        public String getFCSJ() {
            return FCSJ;
        }

        public void setFCSJ(String FCSJ) {
            this.FCSJ = FCSJ;
        }

        public String getGXSJ() {
            return GXSJ;
        }

        public void setGXSJ(String GXSJ) {
            this.GXSJ = GXSJ;
        }

        public String getCWYY() {
            return CWYY;
        }

        public void setCWYY(String CWYY) {
            this.CWYY = CWYY;
        }

        public String getLRDWDM() {
            return LRDWDM;
        }

        public void setLRDWDM(String LRDWDM) {
            this.LRDWDM = LRDWDM;
        }

        public String getLRMJDM() {
            return LRMJDM;
        }

        public void setLRMJDM(String LRMJDM) {
            this.LRMJDM = LRMJDM;
        }

        public String getZQDWDM() {
            return ZQDWDM;
        }

        public void setZQDWDM(String ZQDWDM) {
            this.ZQDWDM = ZQDWDM;
        }

        public String getZQMJDM() {
            return ZQMJDM;
        }

        public void setZQMJDM(String ZQMJDM) {
            this.ZQMJDM = ZQMJDM;
        }

        public String getCLLX() {
            return CLLX;
        }

        public void setCLLX(String CLLX) {
            this.CLLX = CLLX;
        }

        public String getSJZL() {
            return SJZL;
        }

        public void setSJZL(String SJZL) {
            this.SJZL = SJZL;
        }

        public String getCFJDS() {
            return CFJDS;
        }

        public void setCFJDS(String CFJDS) {
            this.CFJDS = CFJDS;
        }

        public String getSHBJ() {
            return SHBJ;
        }

        public void setSHBJ(String SHBJ) {
            this.SHBJ = SHBJ;
        }

        public String getZT() {
            return ZT;
        }

        public void setZT(String ZT) {
            this.ZT = ZT;
        }

        public String getWFSJLX() {
            return WFSJLX;
        }

        public void setWFSJLX(String WFSJLX) {
            this.WFSJLX = WFSJLX;
        }

        public String getSHR() {
            return SHR;
        }

        public void setSHR(String SHR) {
            this.SHR = SHR;
        }

        public String getSHSJ() {
            return SHSJ;
        }

        public void setSHSJ(String SHSJ) {
            this.SHSJ = SHSJ;
        }

        public String getTHCS() {
            return THCS;
        }

        public void setTHCS(String THCS) {
            this.THCS = THCS;
        }

        public String getSYXZ() {
            return SYXZ;
        }

        public void setSYXZ(String SYXZ) {
            this.SYXZ = SYXZ;
        }

        public String getPZBS() {
            return PZBS;
        }

        public void setPZBS(String PZBS) {
            this.PZBS = PZBS;
        }

        public String getSCZ() {
            return SCZ;
        }

        public void setSCZ(String SCZ) {
            this.SCZ = SCZ;
        }

        public String getBZZ() {
            return BZZ;
        }

        public void setBZZ(String BZZ) {
            this.BZZ = BZZ;
        }

        public String getXSFX() {
            return XSFX;
        }

        public void setXSFX(String XSFX) {
            this.XSFX = XSFX;
        }

        public String getZPSB() {
            return ZPSB;
        }

        public void setZPSB(String ZPSB) {
            this.ZPSB = ZPSB;
        }

        public String getCD() {
            return CD;
        }

        public void setCD(String CD) {
            this.CD = CD;
        }

        public String getZJLX() {
            return ZJLX;
        }

        public void setZJLX(String ZJLX) {
            this.ZJLX = ZJLX;
        }

        public String getTZDH() {
            return TZDH;
        }

        public void setTZDH(String TZDH) {
            this.TZDH = TZDH;
        }

        public String getDDGLS() {
            return DDGLS;
        }

        public void setDDGLS(String DDGLS) {
            this.DDGLS = DDGLS;
        }

        public String getDDMS() {
            return DDMS;
        }

        public void setDDMS(String DDMS) {
            this.DDMS = DDMS;
        }

        public String getSJLYLX() {
            return SJLYLX;
        }

        public void setSJLYLX(String SJLYLX) {
            this.SJLYLX = SJLYLX;
        }

        public String getBZ() {
            return BZ;
        }

        public void setBZ(String BZ) {
            this.BZ = BZ;
        }
    }
}

package com.android.wen.cstp.pojo;

import java.io.Serializable;

// 用户信息
public class User implements Serializable {
    private Integer id;
    private String username;//车主姓名
    private String password;//密码
    private String device;//终端号
    private String carNum;//车牌号码
    private String BcankCar;//银行卡号
    private String phone;//电话号码
    private String idCard;//身份证
    private String sex;//性别
    private String birthday;//出生日期
    private String locationNow;//现住址
    private String unit;//工作单位
    private String time;//创建时间
    private String status;//状态

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", device='" + device + '\'' +
                ", carNum='" + carNum + '\'' +
                ", BcankCar='" + BcankCar + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", locationNow='" + locationNow + '\'' +
                ", unit='" + unit + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
/*zdh 	终端号*
czxm 	车主姓名*
hphm	号牌号码*
yhkh	银行卡号*
lxdh	联系电话
sfzh	身份证号
xb	性别
csrq	出生日期
xzz	现住址
gzdw	工作单位
rbsj	入表时间
yzzt	状态
fzjg	发证机关
xxly	信息来源
ssdw	所属单位*/

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getBcankCar() {
        return BcankCar;
    }

    public void setBcankCar(String bcankCar) {
        BcankCar = bcankCar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCadr() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocationNow() {
        return locationNow;
    }

    public void setLocationNow(String locationNow) {
        this.locationNow = locationNow;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
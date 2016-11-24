package com.android.wen.cstp.pojo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public class DataWeather {
    /**
     * reason : successed!
     * result : {"data":{"pubdate":"2016-11-14","pubtime":"08:00:00","realtime":{"city_code":"101250101","city_name":"长沙","date":"2016-11-14","time":"08:00:00","week":1,"moon":"十月十五","dataUptime":1479083765,"weather":{"temperature":"15","humidity":"95","info":"多云","img":"1"},"wind":{"direct":"北风","power":"0级","offset":null,"windspeed":null}},"life":{"date":"2016-11-14","info":{"chuanyi":["较舒适","建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"],"ganmao":["较易发","天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}},"weather":[{"date":"2016-11-14","info":{"day":["3","阵雨","19","西北风","微风","06:49"],"night":["7","小雨","16","西北风","微风","17:35"]},"week":"一","nongli":"十月十五"},{"date":"2016-11-15","info":{"dawn":["7","小雨","16","西北风","微风","17:35"],"day":["7","小雨","18","北风","微风","06:50"],"night":["7","小雨","16","北风","微风","17:35"]},"week":"二","nongli":"十月十六"},{"date":"2016-11-16","info":{"dawn":["7","小雨","16","北风","微风","17:35"],"day":["2","阴","19","北风","微风","06:51"],"night":["2","阴","16","北风","微风","17:34"]},"week":"三","nongli":"十月十七"},{"date":"2016-11-17","info":{"dawn":["2","阴","16","北风","微风","17:34"],"day":["1","多云","18","北风","微风","06:51"],"night":["1","多云","16","北风","微风","17:34"]},"week":"四","nongli":"十月十八"},{"date":"2016-11-18","info":{"dawn":["1","多云","16","北风","微风","17:34"],"day":["1","多云","24","南风","微风","06:52"],"night":["2","阴","17","南风","微风","17:34"]},"week":"五","nongli":"十月十九"}],"f3h":{"temperature":[{"jg":"20161114080000","jb":"15"},{"jg":"20161114110000","jb":"18"},{"jg":"20161114140000","jb":"17"},{"jg":"20161114170000","jb":"17"},{"jg":"20161114200000","jb":"18"},{"jg":"20161114230000","jb":"18"},{"jg":"20161115020000","jb":"17"},{"jg":"20161115050000","jb":"17"},{"jg":"20161115080000","jb":"16"}],"precipitation":[{"jg":"20161114080000","jf":"0"},{"jg":"20161114110000","jf":"2.9"},{"jg":"20161114140000","jf":"1.3"},{"jg":"20161114170000","jf":"1"},{"jg":"20161114200000","jf":"1.1"},{"jg":"20161114230000","jf":"0.6"},{"jg":"20161115020000","jf":"0.1"},{"jg":"20161115050000","jf":"0.4"},{"jg":"20161115080000","jf":"0.1"}]},"pm25":{"key":"Changsha","show_desc":0,"pm25":{"curPm":"121","pm25":"91","pm10":"117","level":3,"quality":"轻度污染","des":"轻微污染 易感人群症状有轻度加剧，健康人群出现刺激症状 心脏病和呼吸系统疾病患者应减少体力消耗和户外活动。"},"dateTime":"2016年11月14日08时","cityName":"长沙"},"jingqu":"","jingqutq":"","date":"","isForeign":"0"}}
     * error_code : 0
     */

    private String reason;
    /**
     * data : {"pubdate":"2016-11-14","pubtime":"08:00:00","realtime":{"city_code":"101250101","city_name":"长沙","date":"2016-11-14","time":"08:00:00","week":1,"moon":"十月十五","dataUptime":1479083765,"weather":{"temperature":"15","humidity":"95","info":"多云","img":"1"},"wind":{"direct":"北风","power":"0级","offset":null,"windspeed":null}},"life":{"date":"2016-11-14","info":{"chuanyi":["较舒适","建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"],"ganmao":["较易发","天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}},"weather":[{"date":"2016-11-14","info":{"day":["3","阵雨","19","西北风","微风","06:49"],"night":["7","小雨","16","西北风","微风","17:35"]},"week":"一","nongli":"十月十五"},{"date":"2016-11-15","info":{"dawn":["7","小雨","16","西北风","微风","17:35"],"day":["7","小雨","18","北风","微风","06:50"],"night":["7","小雨","16","北风","微风","17:35"]},"week":"二","nongli":"十月十六"},{"date":"2016-11-16","info":{"dawn":["7","小雨","16","北风","微风","17:35"],"day":["2","阴","19","北风","微风","06:51"],"night":["2","阴","16","北风","微风","17:34"]},"week":"三","nongli":"十月十七"},{"date":"2016-11-17","info":{"dawn":["2","阴","16","北风","微风","17:34"],"day":["1","多云","18","北风","微风","06:51"],"night":["1","多云","16","北风","微风","17:34"]},"week":"四","nongli":"十月十八"},{"date":"2016-11-18","info":{"dawn":["1","多云","16","北风","微风","17:34"],"day":["1","多云","24","南风","微风","06:52"],"night":["2","阴","17","南风","微风","17:34"]},"week":"五","nongli":"十月十九"}],"f3h":{"temperature":[{"jg":"20161114080000","jb":"15"},{"jg":"20161114110000","jb":"18"},{"jg":"20161114140000","jb":"17"},{"jg":"20161114170000","jb":"17"},{"jg":"20161114200000","jb":"18"},{"jg":"20161114230000","jb":"18"},{"jg":"20161115020000","jb":"17"},{"jg":"20161115050000","jb":"17"},{"jg":"20161115080000","jb":"16"}],"precipitation":[{"jg":"20161114080000","jf":"0"},{"jg":"20161114110000","jf":"2.9"},{"jg":"20161114140000","jf":"1.3"},{"jg":"20161114170000","jf":"1"},{"jg":"20161114200000","jf":"1.1"},{"jg":"20161114230000","jf":"0.6"},{"jg":"20161115020000","jf":"0.1"},{"jg":"20161115050000","jf":"0.4"},{"jg":"20161115080000","jf":"0.1"}]},"pm25":{"key":"Changsha","show_desc":0,"pm25":{"curPm":"121","pm25":"91","pm10":"117","level":3,"quality":"轻度污染","des":"轻微污染 易感人群症状有轻度加剧，健康人群出现刺激症状 心脏病和呼吸系统疾病患者应减少体力消耗和户外活动。"},"dateTime":"2016年11月14日08时","cityName":"长沙"},"jingqu":"","jingqutq":"","date":"","isForeign":"0"}
     */

    private ResultBean result;
    private int error_code;

    public static DataWeather objectFromData(String str) {

        return new Gson().fromJson(str, DataWeather.class);
    }

    public static DataWeather objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), DataWeather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<DataWeather> arrayDataWeatherFromData(String str) {

        Type listType = new TypeToken<ArrayList<DataWeather>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * pubdate : 2016-11-14
         * pubtime : 08:00:00
         * realtime : {"city_code":"101250101","city_name":"长沙","date":"2016-11-14","time":"08:00:00","week":1,"moon":"十月十五","dataUptime":1479083765,"weather":{"temperature":"15","humidity":"95","info":"多云","img":"1"},"wind":{"direct":"北风","power":"0级","offset":null,"windspeed":null}}
         * life : {"date":"2016-11-14","info":{"chuanyi":["较舒适","建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"],"ganmao":["较易发","天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}}
         * weather : [{"date":"2016-11-14","info":{"day":["3","阵雨","19","西北风","微风","06:49"],"night":["7","小雨","16","西北风","微风","17:35"]},"week":"一","nongli":"十月十五"},{"date":"2016-11-15","info":{"dawn":["7","小雨","16","西北风","微风","17:35"],"day":["7","小雨","18","北风","微风","06:50"],"night":["7","小雨","16","北风","微风","17:35"]},"week":"二","nongli":"十月十六"},{"date":"2016-11-16","info":{"dawn":["7","小雨","16","北风","微风","17:35"],"day":["2","阴","19","北风","微风","06:51"],"night":["2","阴","16","北风","微风","17:34"]},"week":"三","nongli":"十月十七"},{"date":"2016-11-17","info":{"dawn":["2","阴","16","北风","微风","17:34"],"day":["1","多云","18","北风","微风","06:51"],"night":["1","多云","16","北风","微风","17:34"]},"week":"四","nongli":"十月十八"},{"date":"2016-11-18","info":{"dawn":["1","多云","16","北风","微风","17:34"],"day":["1","多云","24","南风","微风","06:52"],"night":["2","阴","17","南风","微风","17:34"]},"week":"五","nongli":"十月十九"}]
         * f3h : {"temperature":[{"jg":"20161114080000","jb":"15"},{"jg":"20161114110000","jb":"18"},{"jg":"20161114140000","jb":"17"},{"jg":"20161114170000","jb":"17"},{"jg":"20161114200000","jb":"18"},{"jg":"20161114230000","jb":"18"},{"jg":"20161115020000","jb":"17"},{"jg":"20161115050000","jb":"17"},{"jg":"20161115080000","jb":"16"}],"precipitation":[{"jg":"20161114080000","jf":"0"},{"jg":"20161114110000","jf":"2.9"},{"jg":"20161114140000","jf":"1.3"},{"jg":"20161114170000","jf":"1"},{"jg":"20161114200000","jf":"1.1"},{"jg":"20161114230000","jf":"0.6"},{"jg":"20161115020000","jf":"0.1"},{"jg":"20161115050000","jf":"0.4"},{"jg":"20161115080000","jf":"0.1"}]}
         * pm25 : {"key":"Changsha","show_desc":0,"pm25":{"curPm":"121","pm25":"91","pm10":"117","level":3,"quality":"轻度污染","des":"轻微污染 易感人群症状有轻度加剧，健康人群出现刺激症状 心脏病和呼吸系统疾病患者应减少体力消耗和户外活动。"},"dateTime":"2016年11月14日08时","cityName":"长沙"}
         * jingqu :
         * jingqutq :
         * date :
         * isForeign : 0
         */

        private DataBean data;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public static ResultBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ResultBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ResultBean> arrayResultBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ResultBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            private String pubdate;
            private String pubtime;
            /**
             * city_code : 101250101
             * city_name : 长沙
             * date : 2016-11-14
             * time : 08:00:00
             * week : 1
             * moon : 十月十五
             * dataUptime : 1479083765
             * weather : {"temperature":"15","humidity":"95","info":"多云","img":"1"}
             * wind : {"direct":"北风","power":"0级","offset":null,"windspeed":null}
             */

            private RealtimeBean realtime;
            /**
             * date : 2016-11-14
             * info : {"chuanyi":["较舒适","建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"],"ganmao":["较易发","天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}
             */

            private LifeBean life;
            private F3hBean f3h;
            /**
             * key : Changsha
             * show_desc : 0
             * pm25 : {"curPm":"121","pm25":"91","pm10":"117","level":3,"quality":"轻度污染","des":"轻微污染 易感人群症状有轻度加剧，健康人群出现刺激症状 心脏病和呼吸系统疾病患者应减少体力消耗和户外活动。"}
             * dateTime : 2016年11月14日08时
             * cityName : 长沙
             */

            private Pm25Data pm25Data;
            private String jingqu;
            private String jingqutq;
            private String date;
            private String isForeign;
            /**
             * date : 2016-11-14
             * info : {"day":["3","阵雨","19","西北风","微风","06:49"],"night":["7","小雨","16","西北风","微风","17:35"]}
             * week : 一
             * nongli : 十月十五
             */

            private List<WeatherBean> weather;

            public static DataBean objectFromData(String str) {

                return new Gson().fromJson(str, DataBean.class);
            }

            public static DataBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str),
                            DataBean.class);
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

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getPubtime() {
                return pubtime;
            }

            public void setPubtime(String pubtime) {
                this.pubtime = pubtime;
            }

            public RealtimeBean getRealtime() {
                return realtime;
            }

            public void setRealtime(RealtimeBean realtime) {
                this.realtime = realtime;
            }

            public LifeBean getLife() {
                return life;
            }

            public void setLife(LifeBean life) {
                this.life = life;
            }

            public F3hBean getF3h() {
                return f3h;
            }

            public void setF3h(F3hBean f3h) {
                this.f3h = f3h;
            }

            public Pm25Data getPm25Data() {
                return pm25Data;
            }

            public void setPm25Data(Pm25Data pm25Data) {
                this.pm25Data = pm25Data;
            }

            public String getJingqu() {
                return jingqu;
            }

            public void setJingqu(String jingqu) {
                this.jingqu = jingqu;
            }

            public String getJingqutq() {
                return jingqutq;
            }

            public void setJingqutq(String jingqutq) {
                this.jingqutq = jingqutq;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getIsForeign() {
                return isForeign;
            }

            public void setIsForeign(String isForeign) {
                this.isForeign = isForeign;
            }

            public List<WeatherBean> getWeather() {
                return weather;
            }

            public void setWeather(List<WeatherBean> weather) {
                this.weather = weather;
            }

            public static class RealtimeBean {
                private String city_code;
                private String city_name;
                private String date;
                private String time;
                private int week;
                private String moon;
                private int dataUptime;
                /**
                 * temperature : 15
                 * humidity : 95
                 * info : 多云
                 * img : 1
                 */

                private WeatherBean weather;
                /**
                 * direct : 北风
                 * power : 0级
                 * offset : null
                 * windspeed : null
                 */

                private WindBean wind;

                public static RealtimeBean objectFromData(String str) {

                    return new Gson().fromJson(str, RealtimeBean.class);
                }

                public static RealtimeBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), RealtimeBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<RealtimeBean> arrayRealtimeBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<RealtimeBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public int getWeek() {
                    return week;
                }

                public void setWeek(int week) {
                    this.week = week;
                }

                public String getMoon() {
                    return moon;
                }

                public void setMoon(String moon) {
                    this.moon = moon;
                }

                public int getDataUptime() {
                    return dataUptime;
                }

                public void setDataUptime(int dataUptime) {
                    this.dataUptime = dataUptime;
                }

                public WeatherBean getWeather() {
                    return weather;
                }

                public void setWeather(WeatherBean weather) {
                    this.weather = weather;
                }

                public WindBean getWind() {
                    return wind;
                }

                public void setWind(WindBean wind) {
                    this.wind = wind;
                }

                public static class WeatherBean {
                    private String temperature;
                    private String humidity;
                    private String info;
                    private String img;

                    public static WeatherBean objectFromData(String str) {

                        return new Gson().fromJson(str, WeatherBean.class);
                    }

                    public static WeatherBean objectFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);

                            return new Gson().fromJson(jsonObject.getString(str), WeatherBean.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    public static List<WeatherBean> arrayWeatherBeanFromData(String str) {

                        Type listType = new TypeToken<ArrayList<WeatherBean>>() {
                        }.getType();

                        return new Gson().fromJson(str, listType);
                    }

                    public String getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(String temperature) {
                        this.temperature = temperature;
                    }

                    public String getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(String humidity) {
                        this.humidity = humidity;
                    }

                    public String getInfo() {
                        return info;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }
                }

                public static class WindBean {
                    private String direct;
                    private String power;
                    private Object offset;
                    private Object windspeed;

                    public static WindBean objectFromData(String str) {

                        return new Gson().fromJson(str, WindBean.class);
                    }

                    public static WindBean objectFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);

                            return new Gson().fromJson(jsonObject.getString(str), WindBean.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    public static List<WindBean> arrayWindBeanFromData(String str) {

                        Type listType = new TypeToken<ArrayList<WindBean>>() {
                        }.getType();

                        return new Gson().fromJson(str, listType);
                    }

                    public String getDirect() {
                        return direct;
                    }

                    public void setDirect(String direct) {
                        this.direct = direct;
                    }

                    public String getPower() {
                        return power;
                    }

                    public void setPower(String power) {
                        this.power = power;
                    }

                    public Object getOffset() {
                        return offset;
                    }

                    public void setOffset(Object offset) {
                        this.offset = offset;
                    }

                    public Object getWindspeed() {
                        return windspeed;
                    }

                    public void setWindspeed(Object windspeed) {
                        this.windspeed = windspeed;
                    }
                }
            }

            public static class LifeBean {
                private String date;
                private InfoBean info;

                public static LifeBean objectFromData(String str) {

                    return new Gson().fromJson(str, LifeBean.class);
                }

                public static LifeBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), LifeBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<LifeBean> arrayLifeBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<LifeBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public static class InfoBean {
                    private List<String> chuanyi;
                    private List<String> ganmao;
                    private List<String> kongtiao;
                    private List<String> xiche;
                    private List<String> yundong;
                    private List<String> ziwaixian;

                    public static InfoBean objectFromData(String str) {

                        return new Gson().fromJson(str, InfoBean.class);
                    }

                    public static InfoBean objectFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);

                            return new Gson().fromJson(jsonObject.getString(str), InfoBean.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    public static List<InfoBean> arrayInfoBeanFromData(String str) {

                        Type listType = new TypeToken<ArrayList<InfoBean>>() {
                        }.getType();

                        return new Gson().fromJson(str, listType);
                    }

                    public List<String> getChuanyi() {
                        return chuanyi;
                    }

                    public void setChuanyi(List<String> chuanyi) {
                        this.chuanyi = chuanyi;
                    }

                    public List<String> getGanmao() {
                        return ganmao;
                    }

                    public void setGanmao(List<String> ganmao) {
                        this.ganmao = ganmao;
                    }

                    public List<String> getKongtiao() {
                        return kongtiao;
                    }

                    public void setKongtiao(List<String> kongtiao) {
                        this.kongtiao = kongtiao;
                    }

                    public List<String> getXiche() {
                        return xiche;
                    }

                    public void setXiche(List<String> xiche) {
                        this.xiche = xiche;
                    }

                    public List<String> getYundong() {
                        return yundong;
                    }

                    public void setYundong(List<String> yundong) {
                        this.yundong = yundong;
                    }

                    public List<String> getZiwaixian() {
                        return ziwaixian;
                    }

                    public void setZiwaixian(List<String> ziwaixian) {
                        this.ziwaixian = ziwaixian;
                    }
                }
            }

            public static class F3hBean {
                /**
                 * jg : 20161114080000
                 * jb : 15
                 */

                private List<TemperatureBean> temperature;
                /**
                 * jg : 20161114080000
                 * jf : 0
                 */

                private List<PrecipitationBean> precipitation;

                public static F3hBean objectFromData(String str) {

                    return new Gson().fromJson(str, F3hBean.class);
                }

                public static F3hBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), F3hBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<F3hBean> arrayF3hBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<F3hBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public List<TemperatureBean> getTemperature() {
                    return temperature;
                }

                public void setTemperature(List<TemperatureBean> temperature) {
                    this.temperature = temperature;
                }

                public List<PrecipitationBean> getPrecipitation() {
                    return precipitation;
                }

                public void setPrecipitation(List<PrecipitationBean> precipitation) {
                    this.precipitation = precipitation;
                }

                public static class TemperatureBean {
                    private String jg;
                    private String jb;

                    public static TemperatureBean objectFromData(String str) {

                        return new Gson().fromJson(str, TemperatureBean.class);
                    }

                    public static TemperatureBean objectFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);

                            return new Gson().fromJson(jsonObject.getString(str), TemperatureBean.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    public static List<TemperatureBean> arrayTemperatureBeanFromData(String str) {

                        Type listType = new TypeToken<ArrayList<TemperatureBean>>() {
                        }.getType();

                        return new Gson().fromJson(str, listType);
                    }

                    public String getJg() {
                        return jg;
                    }

                    public void setJg(String jg) {
                        this.jg = jg;
                    }

                    public String getJb() {
                        return jb;
                    }

                    public void setJb(String jb) {
                        this.jb = jb;
                    }
                }

                public static class PrecipitationBean {
                    private String jg;
                    private String jf;

                    public static PrecipitationBean objectFromData(String str) {

                        return new Gson().fromJson(str, PrecipitationBean.class);
                    }

                    public static PrecipitationBean objectFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);

                            return new Gson().fromJson(jsonObject.getString(str), PrecipitationBean.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    public static List<PrecipitationBean> arrayPrecipitationBeanFromData(String str) {

                        Type listType = new TypeToken<ArrayList<PrecipitationBean>>() {
                        }.getType();

                        return new Gson().fromJson(str, listType);
                    }

                    public String getJg() {
                        return jg;
                    }

                    public void setJg(String jg) {
                        this.jg = jg;
                    }

                    public String getJf() {
                        return jf;
                    }

                    public void setJf(String jf) {
                        this.jf = jf;
                    }
                }
            }

            public static class Pm25Data {
                private String key;
                private int show_desc;
                /**
                 * curPm : 121
                 * pm25 : 91
                 * pm10 : 117
                 * level : 3
                 * quality : 轻度污染
                 * des : 轻微污染 易感人群症状有轻度加剧，健康人群出现刺激症状 心脏病和呼吸系统疾病患者应减少体力消耗和户外活动。
                 */

                private Pm25Bean pm25;
                private String dateTime;
                private String cityName;

                public static Pm25Bean objectFromData(String str) {

                    return new Gson().fromJson(str, Pm25Bean.class);
                }

                public static Pm25Bean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), Pm25Bean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<Pm25Bean> arrayPm25BeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<Pm25Bean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public int getShow_desc() {
                    return show_desc;
                }

                public void setShow_desc(int show_desc) {
                    this.show_desc = show_desc;
                }

                public Pm25Bean getPm25() {
                    return pm25;
                }

                public void setPm25(Pm25Bean pm25) {
                    this.pm25 = pm25;
                }

                public String getDateTime() {
                    return dateTime;
                }

                public void setDateTime(String dateTime) {
                    this.dateTime = dateTime;
                }

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }

                public static class Pm25Bean {
                    private String curPm;
                    private String pm25;
                    private String pm10;
                    private int level;
                    private String quality;
                    private String des;

                    public static Pm25Bean objectFromData(String str) {

                        return new Gson().fromJson(str, Pm25Bean.class);
                    }

                    public static Pm25Bean objectFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);

                            return new Gson().fromJson(jsonObject.getString(str), Pm25Bean.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    public static List<Pm25Bean> arrayPm25BeanFromData(String str) {

                        Type listType = new TypeToken<ArrayList<Pm25Bean>>() {
                        }.getType();

                        return new Gson().fromJson(str, listType);
                    }

                    public String getCurPm() {
                        return curPm;
                    }

                    public void setCurPm(String curPm) {
                        this.curPm = curPm;
                    }

                    public String getPm25() {
                        return pm25;
                    }

                    public void setPm25(String pm25) {
                        this.pm25 = pm25;
                    }

                    public String getPm10() {
                        return pm10;
                    }

                    public void setPm10(String pm10) {
                        this.pm10 = pm10;
                    }

                    public int getLevel() {
                        return level;
                    }

                    public void setLevel(int level) {
                        this.level = level;
                    }

                    public String getQuality() {
                        return quality;
                    }

                    public void setQuality(String quality) {
                        this.quality = quality;
                    }

                    public String getDes() {
                        return des;
                    }

                    public void setDes(String des) {
                        this.des = des;
                    }
                }
            }

            public static class WeatherBean {
                private String date;
                private InfoBean info;
                private String week;
                private String nongli;

                public static WeatherBean objectFromData(String str) {

                    return new Gson().fromJson(str, WeatherBean.class);
                }

                public static WeatherBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), WeatherBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<WeatherBean> arrayWeatherBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<WeatherBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getNongli() {
                    return nongli;
                }

                public void setNongli(String nongli) {
                    this.nongli = nongli;
                }

                public static class InfoBean {
                    private List<String> day;
                    private List<String> night;

                    public static InfoBean objectFromData(String str) {

                        return new Gson().fromJson(str, InfoBean.class);
                    }

                    public static InfoBean objectFromData(String str, String key) {

                        try {
                            JSONObject jsonObject = new JSONObject(str);

                            return new Gson().fromJson(jsonObject.getString(str), InfoBean.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    public static List<InfoBean> arrayInfoBeanFromData(String str) {

                        Type listType = new TypeToken<ArrayList<InfoBean>>() {
                        }.getType();

                        return new Gson().fromJson(str, listType);
                    }

                    public List<String> getDay() {
                        return day;
                    }

                    public void setDay(List<String> day) {
                        this.day = day;
                    }

                    public List<String> getNight() {
                        return night;
                    }

                    public void setNight(List<String> night) {
                        this.night = night;
                    }
                }
            }
        }
    }
}

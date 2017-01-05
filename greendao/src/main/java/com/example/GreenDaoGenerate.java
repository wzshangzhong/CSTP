package com.example;
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerate {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.sivan.greendaopractice");
        addPerson(schema);
        new DaoGenerator().generateAll(schema, "../CSTP/app/src/main/java-gen");
    }
    private static void addPerson(Schema schema) {

        Entity wfjb = schema.addEntity("cstp_wfjb");
        wfjb.addIdProperty().primaryKey();
        wfjb.addStringProperty("mark").notNull();//标记
        wfjb.addStringProperty("wfsj").notNull();//时间
        wfjb.addStringProperty("wfld").notNull();//路段
        wfjb.addStringProperty("wfch").notNull();//车牌
        wfjb.addStringProperty("qksm").notNull();//情况说明
        wfjb.addStringProperty("jbr").notNull();//举报人
        wfjb.addStringProperty("sfhm").notNull();//身份号码
        wfjb.addStringProperty("lxdh").notNull();//联系电话
        wfjb.addStringProperty("zqdw").notNull();//执勤单位
        wfjb.addStringProperty("image1_path").notNull();
        wfjb.addStringProperty("image2_path");
        wfjb.addStringProperty("image3_path");
    }
}

package com.android.wen.cstp.util;



/**
 * Created by Administrator on 2016/11/3.
 */
public class DaoGenerator {
   /* private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoSession mDaoSession;
    private DaoMaster mDaoMaster;
    private CSTPReportDao mCSTPReportDao;

    private Context mContext;

    public DaoGenerator(Context context) {
        this.mContext = context;
        // 第一个参数为 context，第二个参数为数据库表名，第三个参数通常为 null
        mHelper = new DaoMaster.DevOpenHelper(mContext, "test-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        // 得到 Dao 对象，数据库的 CRUD 操作都是通过此对象来进行
        mCSTPReportDao = mDaoSession.getCSTPReportDao();


    }


    public void add(CSTPReport cstpReport) {
        mCSTPReportDao.insert(cstpReport);// 通过 insert 方法向数据库中添加数据，因为设置了 id 为主键，所以这里 id 填 null

    }

    public List<CSTPReport> search() {
        // 通过构建 QueryBuilder 来实现查询功能
        QueryBuilder<CSTPReport> queryBuilder = mCSTPReportDao.queryBuilder()
                .where(CSTPReportDao.Properties.Mark.eq("mark"));//注意 这里需要填写条件
        // .list() 方法会返回实体类集合

        List<CSTPReport> CSTPReports = queryBuilder.list();
        return CSTPReports;

    }

    // 通过主键来删除数据
    public void delete(long id) {
        mCSTPReportDao.deleteByKey(Long.valueOf(id));
    }

    // 删除所有
    public void deleteAll() {
        mCSTPReportDao.deleteAll();
    }*/

}

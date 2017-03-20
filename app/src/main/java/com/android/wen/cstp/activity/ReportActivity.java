package com.android.wen.cstp.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;
import com.android.wen.cstp.pojo.WFJB;
import com.android.wen.cstp.util.MD5;
import com.android.wen.cstp.util.UserUtils;
import com.android.wen.cstp.view.DateTimePickDialogUtil;
import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.util.ImageTools;
import com.android.wen.cstp.view.LoadDialog;
import com.bumptech.glide.Glide;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.model.HttpParams;
import com.lzy.okhttputils.request.BaseRequest;
import com.sivan.greendaopractice.DaoMaster;
import com.sivan.greendaopractice.DaoSession;
import com.sivan.greendaopractice.cstp_wfjb;
import com.sivan.greendaopractice.cstp_wfjbDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/*违法举报*/
public class ReportActivity extends BaseActivity {

    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.et_hphm_report)
    EditText etHphmReport;   //号牌号码 如：12345
    @Bind(R.id.tv_fzjg_report)
    TextView tvFzjgReport;   //发证机关 如：湘
    @Bind(R.id.tv_hpzl_report)
    TextView tvHpzlReport;   //号牌类型 如 A
    @Bind(R.id.tv_wfxw_report)
    TextView tvWfxwReport;  //违法行为 如：故意遮挡机动车牌
    @Bind(R.id.tv_time_report)
    TextView tvTimeReport;  //违法时间 如：2016-11-2 11:28:53
    @Bind(R.id.et_place_report)
    EditText etPlaceReport; //违法地点 如：湖南省长沙市岳麓区平川路
    @Bind(R.id.tv_xsfx_report)
    TextView tvXsfxReport;  //行驶方向 如：由东向西
    @Bind(R.id.tv_cllx_report)
    TextView tvCllxReport;  //车辆类型 如：小型汽车
    @Bind(R.id.tv_csys_report)
    TextView tvCsysReport;  //车身颜色 如：白色
    @Bind(R.id.et_bcsm_report)
    EditText etBcsmReport;  //补充说明
    @Bind(R.id.img_1_report)
    ImageView img1Report;    //照片1
    @Bind(R.id.img_2_report)
    ImageView img2Report;   //照片2
    @Bind(R.id.img_3_report)
    ImageView img3Report;   //照片3
    @Bind(R.id.et_name_report)
    EditText etNameReport;  //举报人名字
    @Bind(R.id.et_phone_report)
    EditText etPhoneReport;  //举报人手机号码
    @Bind(R.id.et_card_report)
    EditText etCardReport;  //举报人身份号码
    @Bind(R.id.tv_units_report)
    TextView tvUnitsReport; //值勤单位


    //定位
    private AMapLocationClient mLocationClient = null;

    //图片集合
    private Bitmap[] bitmaps = new Bitmap[3];
    private String uploadFiles[] = new String[3];
    private List<File> files;
    private File file;
    private int position = 0;
    //招聘来源模式
    private static final int TAKE_PHOTO = 0;
    private static final int CHOOSE_PHONE = 1;
    private static final int SCALE = 2;

    //放数据库的
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoSession mDaoSession;
    private DaoMaster mDaoMaster;
    private cstp_wfjbDao mWfjbDao;
    private Cursor cursor;
    private WFJB wfjb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topTitle.setText("违法举报");//设置头标
        //设置时间
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);//年
        int month = c.get(Calendar.MONTH);//月
        int day = c.get(Calendar.DAY_OF_MONTH);//日
        int hour = c.get(Calendar.HOUR_OF_DAY);//时 Calendar.HOUR_OF_DAY 24小时制 Calendar.HOUR 12小时制
        int minute = c.get(Calendar.MINUTE);//分钟
        int second = c.get(Calendar.SECOND);//秒
        String cal = String.format("%d/%d/%d %d:%d:%d", year, month, day, hour, minute, second);
        tvTimeReport.setText(cal);

        //设置地点
        postion();

        // 临时 图片文件
        file = new File(Environment.getExternalStorageDirectory(), "/CSTP/image.jpg");

        //显示默认用户信息
        etNameReport.setText(UserUtils.getUser(this).getXm());  //举报人名字
        etPhoneReport.setText(UserUtils.getUser(this).getLxdh());  //举报人手机号码
        etCardReport.setText(UserUtils.getUser(this).getSfzh());  //举报人身份号码
        tvUnitsReport.setText(UserUtils.getUser(this).getGzdw()); //值勤单位

        //放数据库的
        // mDaoGenerator = new DaoGenerator(this);
    }

    //设置地点
    private void postion() {
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);//设置是否启动单次定位 默认不启动
        mLocationOption.setInterval(5000);
        mLocationOption.setWifiActiveScan(true);//强制刷新wifi
        AMapLocationListener mLocationListener = new AMapLocationListener() {

            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                String location = "";
                if (aMapLocation.getErrorCode() == 0) {
                    //省、市、区、街道
                    location = String.format("%s%s%s%s",
                            aMapLocation.getProvince(), aMapLocation.getCity(),
                            aMapLocation.getDistrict(), aMapLocation.getStreet());
                    etPlaceReport.setText(location);
                    Log.i("ReportActivity", "详细地址:" + location);
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        };
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation(); //启动定位
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();
    }

    @OnClick({R.id.top_back, R.id.tv_fzjg_report, R.id.tv_hpzl_report,
            R.id.tv_wfxw_report, R.id.tv_time_report, R.id.iv_local_report,
            R.id.tv_xsfx_report, R.id.tv_cllx_report, R.id.tv_csys_report,
            R.id.img_1_report, R.id.img_2_report, R.id.img_3_report, R.id.tv_units_report,
            R.id.btn_submit_report})
    public void onClick(View view) {
        AlertDialog.Builder builder;
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.tv_fzjg_report:
                builder = new AlertDialog.Builder(this);

                builder.setItems(GlobalApp.fzjgs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvFzjgReport.setText(GlobalApp.fzjgs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_hpzl_report:
                builder = new AlertDialog.Builder(this);

                builder.setItems(GlobalApp.hpzls, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvHpzlReport.setText(GlobalApp.hpzls[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_wfxw_report:
                builder = new AlertDialog.Builder(this);


                builder.setItems(GlobalApp.wfxws, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvWfxwReport.setText(GlobalApp.wfxws[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_time_report://时间
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this);
                dateTimePicKDialog.dateTimePicKDialog(tvTimeReport);
                break;
            case R.id.iv_local_report://定位刷新
                etPlaceReport.setText("");
                mLocationClient.onDestroy();
                postion();
                break;
            case R.id.tv_xsfx_report://方向
                builder = new AlertDialog.Builder(this);


                builder.setItems(GlobalApp.xsfxs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvXsfxReport.setText(GlobalApp.xsfxs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_cllx_report://车辆类型
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.cllxs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCllxReport.setText(GlobalApp.cllxs[which]);
                    }
                });
                builder.create().show();

                break;
            case R.id.tv_csys_report://车身颜色
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.csyss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCsysReport.setText(GlobalApp.csyss[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.img_1_report:
                startImage(0);
                break;
            case R.id.img_2_report:
                startImage(1);
                break;
            case R.id.img_3_report:
                startImage(2);
                break;
            case R.id.tv_units_report://值勤单位
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.units, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvUnitsReport.setText(GlobalApp.units[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.btn_submit_report:
                submitDate();
                break;
        }
    }

    private void submitDate() {
        wfjb = new WFJB();
        //发证机关
        String fzjg = tvFzjgReport.getText().toString() +
                tvHpzlReport.getText().toString();
        wfjb.setFzjg(fzjg);
        wfjb.setHphm(tvHpzlReport.getText().toString() +
                etHphmReport.getText().toString());
        wfjb.setWfxw(tvWfxwReport.getText().toString());//违法行为
        wfjb.setWfsj(tvTimeReport.getText().toString());//违法时间
        wfjb.setWfdd(etPlaceReport.getText().toString());//违法地点
        wfjb.setXsfx(tvXsfxReport.getText().toString());//行驶方向
        wfjb.setCllx(tvCllxReport.getText().toString());//车辆类型
        wfjb.setCsys(tvCsysReport.getText().toString());//车声颜色
        wfjb.setXxms(etBcsmReport.getText().toString());//补充说明
        //  wfjb.setTplj(String.format("s%,s%,s%"));违法图片
        wfjb.setJbrxm(etNameReport.getText().toString());//举报人姓名
        wfjb.setLxdh(etPhoneReport.getText().toString());//手机号码
        wfjb.setSfzh(etCardReport.getText().toString());//身份证号码
        wfjb.setZqdw(tvUnitsReport.getText().toString());//执勤单位

       /* String wfch = tvFzjgReport.getText().toString()
                + tvHpzlReport.getText().toString()
                + etHphmReport.getText().toString();//违法车牌号
        //String qksm = tvWfxwReport.getText().toString();//情况记录
        String name = etNameReport.getText().toString();//举报人
        String idCard = etCardReport.getText().toString();//身份证
        String phone = etPhoneReport.getText().toString();//联系电话
        String snits = tvUnitsReport.getText().toString();//执勤单位*/
        files = new ArrayList<>();
        files.add(new File(uploadFiles[0]));
        if (uploadFiles[1] != null && uploadFiles[1].length() > 0) {
            files.add(new File(uploadFiles[1]));
        }
        if (uploadFiles[2] != null && uploadFiles[2].length() > 0) {
            files.add(new File(uploadFiles[2]));
        }
        //判断是否有值
        if (TextUtils.isEmpty(etPlaceReport.getText().toString())) {
            Toast.makeText(this, "请记录违法地点!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etHphmReport.getText().toString())) {
            Toast.makeText(this, "请记录违法车牌号!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etNameReport.getText().toString())) {
            Toast.makeText(this, "请记录举报人名字!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etCardReport.getText().toString())) {
            Toast.makeText(this, "请记录举报人身份号码!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etPhoneReport.getText().toString())) {
            Toast.makeText(this, "请记录举报人电话号码!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (files.size() < 2) {
            Toast.makeText(this, "请选择两张照片!", Toast.LENGTH_SHORT).show();
            return;
        }
       /* //Person person = new Person(null, name, sex);
        cstp_wfjb wfjbData = new cstp_wfjb(null, "mark", time, place, wfch, qksm, name,
                idCard, phone, snits, uploadFiles[0], uploadFiles[1], uploadFiles[2]);
        //数据库操作
        initSQLite(wfjbData);*/

        HttpParams params = new HttpParams();
        params.put("fzjg", wfjb.getFzjg()); //发证机关
        params.put("hphm", wfjb.getHphm()); //号牌号码
        params.put("wfxw", wfjb.getWfxw());  //违法行为
        params.put("wfsj", wfjb.getWfsj());  //违法时间
        params.put("wfdd", wfjb.getWfdd()); //违法地点
        params.put("xsfx", wfjb.getXsfx()); //行驶方向
        params.put("cllx", wfjb.getCllx());  //车辆类型
        params.put("csys", wfjb.getCsys());  //车身颜色
        params.put("xxms", wfjb.getXxms());  //补充说明
        params.put("tplj", wfjb.getTplj()); //违法图片,文件路径
        params.put("jbrxm", wfjb.getJbrxm());  //举报人姓名
        params.put("lxdh", wfjb.getLxdh());//手机号码
        params.put("sfzh", wfjb.getSfzh());  //身份证号码
        params.put("zqdw", wfjb.getZqdw()); //执勤单位
        //params.putFileParams("image", files);//上传多个文件

        OkHttpUtils.post(GlobalApp.WFJB_URL)
                .addFileParams("image", files)//
                .params(params)
                .execute(new StringCallback() {
                    LoadDialog dialog;

                    @Override
                    public void onBefore(BaseRequest request) {

                        dialog = new LoadDialog(ReportActivity.this, "提交数据", "正在上传...");
                        dialog.show();
                    }

                    @Override
                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                        Log.v("ReportActivity ", "onResponse:" + s);
                        // super.onResponse(isFromCache,s,request,response);
                    }


                    @Override
                    public void onAfter(boolean isFromCache, @Nullable String s, Call call, @Nullable Response response, @Nullable Exception e) {
                        dialog.dismiss();
                        Log.v("ReportActivity", "onAfter:" + s);
                    }

                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Log.v("ReportActivity", "onError:" + response.toString());
                        Log.v("ReportActivity", "onError:" + e.getMessage());
                    }
                });

    }

 /*   private void initSQLite(cstp_wfjb wfjbData) {
        mHelper = new DaoMaster.DevOpenHelper(this, "test-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        // 得到 Dao 对象，数据库的 CRUD 操作都是通过此对象来进行
        mWfjbDao = mDaoSession.getCstp_wfjbDao();

        //添加数据

        // 通过 insert 方法向数据库中添加数据，因为设置了 id 为主键，所以这里 id 填 null
        long l = mWfjbDao.insert(wfjbData);
        Log.v("ReportActivity", "SQLite添加成功" + l + "");
    }*/

    private void startImage(int i) {
        position = i;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (i == 1 && uploadFiles[0] == null && uploadFiles[1].length() == 0) {
            builder.setTitle("提示：");
            builder.setMessage("请拍摄第一张");
            builder.create().show();
        } else if (i == 2) {
            if (uploadFiles[0] == null && uploadFiles[0].length() == 0) {
                builder.setTitle("提示：");
                builder.setMessage("请选择第一张");
                builder.create().show();
            } else if (uploadFiles[1] == null && uploadFiles[1].length() == 0) {
                builder.setTitle("提示：");
                builder.setMessage("请拍摄第二张");
                builder.create().show();
            } else {
                selectImage();
            }
        } else {
            selectImage();
        }

    }

    //选择图片来源
    private void selectImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("图片来源:");
        builder.setNegativeButton("取消", null);
        builder.setItems(
                new CharSequence[]{"相机", "相册"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case TAKE_PHOTO:
                                // 打开 相机 GlobalApp
                                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                Uri imageUri = Uri.fromFile(file);
                                // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换

                                // Glide.
                                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                startActivityForResult(openCameraIntent, TAKE_PHOTO);
                                break;
                            case CHOOSE_PHONE:
                                // 从 相册 中选择图片
                                Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                openAlbumIntent.setType("image/*");
                                startActivityForResult(openAlbumIntent, CHOOSE_PHONE);
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO:
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    //水印
                    Bitmap bmp = ImageTools.zoomBitmap(bitmap, 320, 480);
                    bitmap.recycle();
                    bitmap = ImageTools.createWatermark(bmp, MD5.md5(tvTimeReport.getText().toString()));

                    handleImage(bitmap);//显示并保存图片
                    break;
                case CHOOSE_PHONE:

                    ContentResolver resolver = this.getContentResolver();
                    Uri originalUri = data.getData();// 照片的原始资源地址
                    try {
                        // 通过内容访问者和图片url获得bitmap的图片(相册中的原图)
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        if (photo != null) {
                            // 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
                           /* Bitmap smallBitmap = ImageTools.zoomBitmap(
                                    photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);*/
                            handleImage(photo);//显示并保存图片
                        }
                    } catch (FileNotFoundException e) {
                        Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(this, "读取文件,出错啦", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    private void handleImage(Bitmap bitmap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");  // 同时复制至存储相片的目录
        String filename = sdf.format(new Date());
        filename = "IMG_" + filename; // 文件名
        String dir = Environment.getExternalStorageDirectory() + "/CSTP";// 图片目录
        // 上传文件名
        uploadFiles[position] = dir + "/" + filename + ".jpg";
        //显示图片
        Glide.with(this)
                .load(new File(uploadFiles[position]))
                .into(showImage());
       /* //判断图片大小
        if (bitmap.getByteCount() > 2 * 1024 * 1024) {
            bitmap = ImageTools.zoomBitmap(
                    bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
        }
        for (; ; ) {
            if (bitmap.getByteCount() > 2 * 1024 * 1024) {
                bitmap = ImageTools.zoomBitmap(
                        bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
            } else {
                break;
            }

        }*/
        Log.v("ReportActivity", bitmap.getByteCount() + "");//63700992 6.15M
        //清除图片
        file.delete();
        ImageTools.savePhotoToSDCard(bitmap, dir, filename);
    }


    private ImageView showImage() {
        switch (position) {
            case 0:
                return img1Report;
            case 1:
                return img2Report;
            case 2:
                return img3Report;
        }
        return null;
    }
}


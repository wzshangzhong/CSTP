package com.android.wen.cstp.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.android.wen.cstp.base.BaseActivity;
import com.android.wen.cstp.util.DaoGenerator;
import com.android.wen.cstp.view.DateTimePickDialogUtil;
import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.util.ImageTools;
import com.android.wen.cstp.view.LoadDialog;
import com.bumptech.glide.Glide;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.model.HttpParams;
import com.lzy.okhttputils.request.BaseRequest;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private DaoGenerator mDaoGenerator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
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
                final String[] fzjgs = new String[]{
                        "湘", "粤", "桂", "琼", "川", "贵", "云",
                        "渝", "藏", "陕", "甘", "青", "宁", "新",
                        "京", "津", "冀", "晋", "蒙", "辽", "吉",
                        "黑", "沪", "苏", "浙", "皖", "闽", "赣",
                        "鲁", "豫", "鄂"};

                builder.setItems(fzjgs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvFzjgReport.setText(fzjgs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_hpzl_report:
                builder = new AlertDialog.Builder(this);
                final String[] hpzls = new String[]{
                        "A", "B", "C", "D", "E", "F", "G",
                        "H", "I", "J", "K", "L", "M", "N",
                        "O", "P", "Q", "R", "S", "T", "U",
                        "V", "W", "X", "Y", "Z",};

                builder.setItems(hpzls, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvHpzlReport.setText(hpzls[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_wfxw_report:
                builder = new AlertDialog.Builder(this);
                final String[] wfxws = new String[]{
                        "故意遮挡机动车号牌", "故意污损机动车号牌", "使用其他机动车号牌",
                        "使用伪造、变造机动车号牌", "无证或驾驶证被扣留期间驾驶",
                        "未按规定安装机动车号牌", "工程车装载超出栏版", "工程车闯红灯",
                        "工程车逆向行驶"};

                builder.setItems(wfxws, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvWfxwReport.setText(wfxws[which]);
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
                final String[] xsfxs = new String[]{
                        "东到西", "东到南", "东到北",
                        "西到东", "西到南", "西到北",
                        "南到东", "南到西", "南到北",
                        "北到东", "北到西", "北到南",};

                builder.setItems(xsfxs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvXsfxReport.setText(xsfxs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_cllx_report://车辆类型
                builder = new AlertDialog.Builder(this);
                final String[] cllxs = new String[]{
                        "小型汽车", "大型汽车", "普通摩托车",
                        "挂车", "低速车", "轻便摩托车",
                        "教练汽车", "教练摩托车"};

                builder.setItems(cllxs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCllxReport.setText(cllxs[which]);
                    }
                });
                builder.create().show();

                break;
            case R.id.tv_csys_report://车身颜色
                builder = new AlertDialog.Builder(this);
                final String[] csyss = new String[]{
                        "灰色", "黄色", "粉色",
                        "紫色", "绿色", "红色",
                        "蓝色", "棕色", "黑色",
                        "白色", "橙色", "不确定",};

                builder.setItems(csyss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCsysReport.setText(csyss[which]);
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
                final String[] zqdws = new String[]{
                        "岳麓大队", "天心大队", "开福大队", "芙蓉大队", "雨花交通警察大队",
                        "高新区大队", "其他"};

                builder.setItems(zqdws, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvUnitsReport.setText(zqdws[which]);
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
        String time = tvTimeReport.getText().toString();//违法时间
        String place = etPlaceReport.getText().toString();//违法地点
        String wfch = tvFzjgReport.getText().toString()
                + tvHpzlReport.getText().toString()
                + etHphmReport.getText().toString();//违法车牌号
        String qksm = tvWfxwReport.getText().toString();//情况记录
        String name = etNameReport.getText().toString();//举报人
        String idCard = etCardReport.getText().toString();//身份证
        String phone = etPhoneReport.getText().toString();//联系电话
        String snits = tvUnitsReport.getText().toString();//执勤单位
        files = new ArrayList<>();
        files.add(new File(uploadFiles[0]));
        if (uploadFiles[1] != null && uploadFiles[1].length() > 0) {
            files.add(new File(uploadFiles[1]));
        }
        if (uploadFiles[2] != null && uploadFiles[2].length() > 0) {
            files.add(new File(uploadFiles[2]));
        }
        //判断是否有值
        if (TextUtils.isEmpty(place)) {
            Toast.makeText(this, "请记录违法地点!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(wfch)) {
            Toast.makeText(this, "请记录违法车牌号!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请记录举报人名字!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(idCard)) {
            Toast.makeText(this, "请记录举报人身份号码!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请记录举报人电话号码!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (files.size() < 2) {
            Toast.makeText(this, "请选择两张照片!", Toast.LENGTH_SHORT).show();
            return;
        }

     /*   CSTPReport report = new CSTPReport(null,"mark", time, place, wfch,
                qksm, name, idCard, phone, snits, uploadFiles[0], uploadFiles[1], uploadFiles[2]);
        mDaoGenerator.add(report);// 通过 insert 方法向数据库中添加数据，因为设置了 id 为主键，所以这里 id 填 null
        List<CSTPReport> reportList=  mDaoGenerator.search();

        Log.v("ReportActivity",reportList.get(0).getWfsj());*/

        HttpParams params = new HttpParams();
        params.put("wfsj", time);//违法时间
        params.put("wfld", place);//违法路段
        params.put("wfch", wfch);//违法车牌号码
        params.put("qksm", qksm);//情况记录
        params.put("jbr", name);//举报人
        params.put("sfhm", idCard);//身份号码
        params.put("lxdh", phone);//联系电话
        params.put("zqdw", snits);//执勤单位
        params.putFileParams("image", files);//上传多个文件

        OkHttpUtils.post(GlobalApp.BASE_URL)
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
                        Log.v("ReportActivity ", "onResponse:"+s );
                      // super.onResponse(isFromCache,s,request,response);
                    }


                    @Override
                    public void onAfter(boolean isFromCache, @Nullable String s, Call call, @Nullable Response response, @Nullable Exception e) {
                        dialog.dismiss();
                        Log.v("ReportActivity", "onAfter:"+s);
                    }

                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        Log.v("ReportActivity", "onError:"+e.toString());
                        super.onError(isFromCache, call, response, e);
                    }
                });

    }

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
                            Bitmap smallBitmap = ImageTools.zoomBitmap(
                                    photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);

                            handleImage(smallBitmap);//显示并保存图片
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
        //判断图片大小
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

        }
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


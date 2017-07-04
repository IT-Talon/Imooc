package com.hzcwtech.imooc.ui.course;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.ViewPagerFragmentAdapter;
import com.hzcwtech.imooc.base.BaseActivity;
import com.hzcwtech.imooc.entity.HttpEntity;
import com.hzcwtech.imooc.entity.model.ConsultModel;
import com.hzcwtech.imooc.entity.model.CourseDetailModel;
import com.hzcwtech.imooc.utils.AppUtils;
import com.hzcwtech.imooc.utils.CommonUtil;
import com.hzcwtech.imooc.utils.ResourceUtil;
import com.hzcwtech.imooc.view.JCVideoPlayerStandardShowShareButtonAfterFullscreen;
import com.pingplusplus.android.Pingpp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import c.b.BP;
import c.b.PListener;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class CourseDetailActivity extends BaseActivity {

    /**
     * 微信支付渠道
     */
    private static final String CHANNEL_WECHAT = "wx";
    /**
     * 微信支付渠道
     */
    private static final String CHANNEL_QPAY = "qpay";
    /**
     * 支付支付渠道
     */
    private static final String CHANNEL_ALIPAY = "alipay";
    private static String YOUR_URL = "http://218.244.151.190/demo/charge";
    public static final String CHARGE_URL = YOUR_URL;

    private static final String COURSE = "course";
    private static final String IMG_URL = "http://climg.imooc.com/59030cc50001144806000338.jpg";
    private static final String VIDEO_URL = "http://clvideo.mukewang.com/58c20168e520e59d7f8b459b/H.mp4";
//    private static final String VIDEO_URL = "rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov";

    @BindView(R.id.videoPlayer)
    JCVideoPlayerStandardShowShareButtonAfterFullscreen mVideoPlayer;
    @BindView(R.id.tabs)
    SlidingTabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.tv_course_price)
    TextView tvPrice;
    @BindView(R.id.tv_buy)
    TextView tvBuy;

    private CourseDetailModel mCourse;
    private List<Fragment> fragments;
    private List<String> titles;
    private ArrayList<ConsultModel> consultlist;

    public static void start(Activity activity, CourseDetailModel course) {
        Intent start = new Intent(activity, CourseDetailActivity.class);
        start.putExtra(COURSE, course);
        activity.startActivity(start);
        activity.overridePendingTransition(R.anim.in_fade_bottomtop, R.anim.no_translate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_course_detail);
        ButterKnife.bind(this);
        CommonUtil.resetTopViewHeight(this, mainContent);
        mCourse = (CourseDetailModel) getIntent().getSerializableExtra(COURSE);
        initData();
        initView();

    }

    private void initData() {
        consultlist = new ArrayList<>();
        String jsonData = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"answer\": \"有的，150天的有效期，还有两次的免费延期机会，每次各延一个月。也就是说你最多可以有210天的学习时间哦。赶快行动吧！\",\n" +
                "      \"content\": \"这是有时间限制的吗？\",\n" +
                "      \"create_time\": \"2017-03-11\",\n" +
                "      \"id\": \"2144\",\n" +
                "      \"mypraise\": 1,\n" +
                "      \"praise\": \"35\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"html5是html的升级。对于初学者，一般建议先学html，然后再学html5。因为html是最基础的知识，只有学会了基础才能更好的进阶。《前端小白入门系列课程》路径中是从最基础的讲起，讲的是html，没有html5的新增内容、而《HTML5与CSS3实现动态网页系列课程》讲的是html5相对于html新增的内容，没有html的基础知识。\",\n" +
                "      \"content\": \"和那个599的前端区别在哪里的啊\",\n" +
                "      \"create_time\": \"2017-03-11\",\n" +
                "      \"id\": \"2143\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"17\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"您好，想成为一名合格的PHP工程师，前端是基本功，建议先修炼前端入门知识后，再继续学习PHP相关内容！\",\n" +
                "      \"content\": \"打算以PHP工程师为目标，学习前端有用吗？\",\n" +
                "      \"create_time\": \"2017-03-23\",\n" +
                "      \"id\": \"2479\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"12\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"路径课程以视频方式配合小案例，详细、系统的讲解，配有丰富的练习题，做到学与练结合，同时还有上传作业题，有专业的老师进行点评并给予修改建议。另外，不会的内容还可以在问答区进行提问，助教老师会帮你解答。\",\n" +
                "      \"content\": \"这个课程和免费的html/css课程有什么区别吗\",\n" +
                "      \"create_time\": \"2017-03-11\",\n" +
                "      \"id\": \"2152\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"11\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"可以的哦，咱们每个步骤都有一个案例来练习和讲解，最后的实战是实现的大项目。\",\n" +
                "      \"content\": \"学完的话，能做项目吗？\",\n" +
                "      \"create_time\": \"2017-03-12\",\n" +
                "      \"id\": \"2168\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"10\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"您好~首先感谢您对慕课网免费课程的关注。这套职业路径课程和慕课网的免费课程是不一样的。这套课程是我们精心从0打造的专为小白用户入门前端开发而设计的。课程是依托练习，编程题而建立的。并且我们也有专业的助教团队进行辅导和1v1的作业批改，这些都是免费课没有的哦！慕课网祝您学习愉快\",\n" +
                "      \"content\": \"这些课程跟那些免费的课程有没有重复的？因为觉得课程有点贵，想谨慎一点\",\n" +
                "      \"create_time\": \"2017-03-24\",\n" +
                "      \"id\": \"2517\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"8\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"《前端小白入门》路径和《HTML5与CSS3实现动态网页》都是基础的路径，但是面向的用户群不一样，如果你没有任何基础，请购买《前端小白入门》，如果你有html和css基础，那么请购买《HTML5与CSS3实现动态网页》。针对两个基础路径后期我们都会退出承接这两个路径的不同的进阶路径， 亲要根据自己的实际情况，看清内容再购买哦，详情可以查看我们的宣传页。http://class.imooc.com/sc/2和http://class.imooc.com/sc/20\",\n" +
                "      \"content\": \"这个课程和那个599的课程内容是否有重叠的地方\",\n" +
                "      \"create_time\": \"2017-04-28\",\n" +
                "      \"id\": \"3703\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"6\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"主要有html、css、js、jQuery和实战课程，详情可见http://class.imooc.com/sc/20。此路径偏向基础，想学好前端这个都是必备的基础，实习生的程度应该是没有问题的。另外后期还有进阶和高级专题推出，欢迎来学习。\",\n" +
                "      \"content\": \"学完这套课程能到什么程度？\",\n" +
                "      \"create_time\": \"2017-03-23\",\n" +
                "      \"id\": \"2483\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"总共33门课，每门课中有若干个章节，每个章节里面有若干个视频节，每个小视频节的平均时长在十分钟左右，总时长为52个小时。详细的内容可以参考宣传页。http://class.imooc.com/sc/20\",\n" +
                "      \"content\": \"每节课多长时间啊\",\n" +
                "      \"create_time\": \"2017-06-11\",\n" +
                "      \"id\": \"5665\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"专题视频是全新制作，并不是慕课网以往免费视频的组合。\",\n" +
                "      \"content\": \"这个是放往期视频？\",\n" +
                "      \"create_time\": \"2017-04-12\",\n" +
                "      \"id\": \"3070\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errorCode\": 1000,\n" +
                "  \"errorDesc\": \"成功\",\n" +
                "  \"status\": 1,\n" +
                "  \"timestamp\": 1497853485419\n" +
                "}";
        HttpEntity httpEntity = JSON.parseObject(jsonData, HttpEntity.class);
        if (httpEntity.isSuccess()) {
            List<ConsultModel> consultData = httpEntity.getDataObject(new TypeReference<List<ConsultModel>>() {
            });
            if (consultData != null && !consultData.isEmpty()) {
                consultlist.addAll(consultData);
            }
        }
    }

    private void initView() {
        initVideoPlayer();
        initTabs();
        tvPrice.setText(CommonUtil.fillString(this, R.string.format_course_price, mCourse.getPrice() / 100));
    }

    private void initTabs() {
        fragments = new ArrayList<>();
        fragments.add(ClassInfoFragment.newInstance());
        fragments.add(EvaluateFragment.newInstance());
        fragments.add(ConsultFragment.newInstance(consultlist));
        titles = new ArrayList<>();
        titles.add("介绍");
        titles.add("评价");
        titles.add("咨询");
        viewpager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, titles));
        tabs.setViewPager(viewpager);
    }

    private void initVideoPlayer() {
        JCVideoPlayerStandard.ACTION_BAR_EXIST = false;
        JCVideoPlayerStandard.TOOL_BAR_EXIST = false;
        mVideoPlayer.setUp(VIDEO_URL, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        Glide.with(this).load(IMG_URL).into(mVideoPlayer.thumbImageView);
        mVideoPlayer.setClickListener(new JCVideoPlayerStandardShowShareButtonAfterFullscreen.ButtonClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.clear:
                        onBackPressed();
                        break;
                    case R.id.share:
                        AppUtils.shareApp(CourseDetailActivity.this, mCourse, null);
                        break;
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }

    @Override
    protected Integer getStatusBarColor() {
//        return null;
        return ResourceUtil.getColor(this, R.color.transparent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_translate, R.anim.out_fade_topbottom);
    }

    @OnClick({R.id.tv_course_price, R.id.tv_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_course_price:
                break;
            case R.id.tv_buy:
                showPayDialog();
                break;
        }
    }

    private void showPayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请选择支付方式");
        builder.setNegativeButton("微信支付", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                payBy(false);

            }
        });
        builder.setPositiveButton("支付宝支付", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                payBy(true);
            }
        });
        builder.create().show();

    }

    ProgressDialog dialog;

    void showDialog(String message) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(this);
                dialog.setCancelable(true);
            }
            dialog.setMessage(message);
            dialog.show();
        } catch (Exception e) {
            // 在其他线程调用dialog会报错
        }
    }

    void hideDialog() {
        if (dialog != null && dialog.isShowing())
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
    }

    /**
     * 调用支付
     *
     * @param alipayOrWechatPay 支付类型，true为支付宝支付,false为微信支付
     */
    private void payBy(boolean alipayOrWechatPay) {
        if (alipayOrWechatPay) {
            if (!checkPackageInstalled("com.eg.android.AlipayGphone",
                    "https://www.alipay.com")) { // 支付宝支付要求用户已经安装支付宝客户端
                Toast.makeText(CourseDetailActivity.this, "请安装支付宝客户端", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        } else {
            if (checkPackageInstalled("com.tencent.mm", "http://weixin.qq.com")) {// 需要用微信支付时，要安装微信客户端，然后需要插件
                // 有微信客户端，看看有无微信支付插件，170602更新了插件，这里可检查可不检查
                if (!BP.isAppUpToDate(this, "cn.bmob.knowledge", 8)) {
                    Toast.makeText(
                            CourseDetailActivity.this,
                            "监测到本机的支付插件不是最新版,最好进行更新,请先更新插件(无流量消耗)",
                            Toast.LENGTH_SHORT).show();
                    installApk("bp.db");
                    return;
                }
            } else {// 没有安装微信
                Toast.makeText(CourseDetailActivity.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        showDialog("正在获取订单...\nSDK版本号:" + BP.getPaySdkVersion());
        final String name = getName();

        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.bmob.app.sport",
                    "com.bmob.app.sport.wxapi.BmobActivity");
            intent.setComponent(cn);
            this.startActivity(intent);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        BP.pay(name, getBody(), getPrice(), alipayOrWechatPay, new PListener() {

            // 因为网络等原因,支付结果未知(小概率事件),出于保险起见稍后手动查询
            @Override
            public void unknow() {
                Toast.makeText(CourseDetailActivity.this, "支付结果未知,请稍后手动查询", Toast.LENGTH_SHORT)
                        .show();
//                tv.append(name + "'s pay status is unknow\n\n");
                hideDialog();
            }

            // 支付成功,如果金额较大请手动查询确认
            @Override
            public void succeed() {
                Toast.makeText(CourseDetailActivity.this, "支付成功!", Toast.LENGTH_SHORT).show();
//                tv.append(name + "'s pay status is success\n\n");
                hideDialog();
            }

            // 无论成功与否,返回订单号
            @Override
            public void orderId(String orderId) {
                // 此处应该保存订单号,比如保存进数据库等,以便以后查询
                Toast.makeText(CourseDetailActivity.this, orderId, Toast.LENGTH_SHORT).show();
//                order.setText(orderId);
//                tv.append(name + "'s orderid is " + orderId + "\n\n");
                showDialog("获取订单成功!请等待跳转到支付页面~");
            }

            // 支付失败,原因可能是用户中断支付操作,也可能是网络原因
            @Override
            public void fail(int code, String reason) {

                // 当code为-2,意味着用户中断了操作
                // code为-3意味着没有安装BmobPlugin插件
                if (code == -3) {
                    Toast.makeText(
                            CourseDetailActivity.this,
                            "监测到你尚未安装支付插件,无法进行支付,请先安装插件(已打包在本地,无流量消耗),安装结束后重新支付",
                            Toast.LENGTH_SHORT).show();
//                    installBmobPayPlugin("bp.db");
                    installApk("bp.db");
                } else {
                    Toast.makeText(CourseDetailActivity.this, "支付中断!", Toast.LENGTH_SHORT)
                            .show();
                }
                Toast.makeText(CourseDetailActivity.this, name + "'s pay status is fail, error code is \n"
                        + code + " ,reason is " + reason + "\n\n", Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });

    }

    // 默认为0.02
    double getPrice() {
        double price = 0.02;
        return price;
    }

    // 商品详情(可不填)
    String getName() {
        return "Name";
    }

    // 商品详情(可不填)
    String getBody() {
        return "Body";
    }

    /**
     * 安装assets里的apk文件
     *
     * @param fileName
     */
    void installBmobPayPlugin(String fileName) {
        try {
            InputStream is = getAssets().open(fileName);
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + fileName + ".apk");
            if (file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + file),
                    "application/vnd.android.package-archive");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final int REQUESTPERMISSION = 101;

    private void installApk(String s) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSION);
        } else {
            installBmobPayPlugin(s);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTPERMISSION) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    installBmobPayPlugin("bp.db");
                } else {
                    //提示没有权限，安装不了
                    Toast.makeText(CourseDetailActivity.this, "您拒绝了权限，这样无法安装支付插件", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * 检查某包名应用是否已经安装
     *
     * @param packageName 包名
     * @param browserUrl  如果没有应用市场，去官网下载
     * @return
     */
    private boolean checkPackageInstalled(String packageName, String browserUrl) {
        try {
            // 检查是否有支付宝客户端
            getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // 没有安装支付宝，跳转到应用市场
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + packageName));
                startActivity(intent);
            } catch (Exception ee) {// 连应用市场都没有，用浏览器去支付宝官网下载
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(browserUrl));
                    startActivity(intent);
                } catch (Exception eee) {
                    Toast.makeText(CourseDetailActivity.this,
                            "您的手机上没有没有应用市场也没有浏览器，我也是醉了，你去想办法安装支付宝/微信吧",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        return false;
    }

    class PaymentTask extends AsyncTask<PaymentRequest, Void, String> {

        @Override
        protected void onPreExecute() {
            //按键点击之后的禁用，防止重复点击
//            tvBuy.setOnClickListener(null);
        }

        @Override
        protected String doInBackground(PaymentRequest... pr) {

            PaymentRequest paymentRequest = pr[0];
            String data = null;
            try {
                JSONObject object = new JSONObject();
                object.put("channel", paymentRequest.channel);
                object.put("amount", paymentRequest.amount);
                String json = object.toString();
                //向Your Ping++ Server SDK请求数据
                data = postJson(CHARGE_URL, json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }

        /**
         * 获得服务端的charge，调用ping++ sdk。
         */
        @Override
        protected void onPostExecute(String data) {
            if (null == data) {
                showMsg("请求出错", "请检查URL", "URL无法获取charge");
                return;
            }
            Log.d("charge", data);

            //除QQ钱包外，其他渠道调起支付方式：
            //参数一：Activity  当前调起支付的Activity
            //参数二：data  获取到的charge或order的JSON字符串
            Pingpp.createPayment(CourseDetailActivity.this, data);

            //QQ钱包调用方式
            //参数一：Activity  当前调起支付的Activity
            //参数二：data  获取到的charge或order的JSON字符串
            //参数三：“qwalletXXXXXXX”需与AndroidManifest.xml中的scheme值一致
            //Pingpp.createPayment(ClientSDKActivity.this, data, "qwalletXXXXXXX");
        }

    }

    /**
     * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
     * 最终支付成功根据异步通知为准
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                showMsg(result, errorMsg, extraMsg);
            }
        }
    }

    public void showMsg(String title, String msg1, String msg2) {
        String str = title;
        if (null != msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null != msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(CourseDetailActivity.this);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }

    /**
     * 获取charge
     *
     * @param urlStr charge_url
     * @param json   获取charge的传参
     * @return charge
     * @throws IOException
     */
    private static String postJson(String urlStr, String json) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.getOutputStream().write(json.getBytes());

        if (conn.getResponseCode() == 200) {
            BufferedReader
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
        return null;
    }

    class PaymentRequest {
        String channel;
        int amount;

        public PaymentRequest(String channel, int amount) {
            this.channel = channel;
            this.amount = amount;
        }
    }
}

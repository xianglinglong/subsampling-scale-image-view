package com.davemorrissey.labs.subscaleview.test.basicfeatures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.test.AbstractPagesActivity;
import com.davemorrissey.labs.subscaleview.test.Page;
import com.davemorrissey.labs.subscaleview.test.R;
import com.davemorrissey.labs.subscaleview.test.R.id;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static com.davemorrissey.labs.subscaleview.test.R.string.*;
import static com.davemorrissey.labs.subscaleview.test.R.layout.*;

public class BasicFeaturesActivity extends AbstractPagesActivity {

    String path = "http://reachpass.oss-cn-hangzhou.aliyuncs.com/1239e969-9594-4c34-b501-3861543fbe4d.jpg";
    String localePath = "/storage/emulated/0/DCIM/VIMI8/92474e89-d26d-4d6b-aadf-a1e4688c01b5.jpg";

    SubsamplingScaleImageView view;

    public BasicFeaturesActivity() {
        super(basic_title, pages_activity, Arrays.asList(
                new Page(basic_p1_subtitle, basic_p1_text),
                new Page(basic_p2_subtitle, basic_p2_text),
                new Page(basic_p3_subtitle, basic_p3_text),
                new Page(basic_p4_subtitle, basic_p4_text),
                new Page(basic_p5_subtitle, basic_p5_text)
        ));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         view = findViewById(id.imageView);

          // new MyTask().execute();

        //view.setImage(ImageSource.asset("sss.png"));
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.iv_default)
//                .error(R.mipmap.iv_default)
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(ChatBigImageActivity.this)
//                .load(imgurl)
//                .thumbnail(0.1f)
//                .apply(options)
//                .downloadOnly(new SimpleTarget<File>() {
//                    @Override
//                    public void onResourceReady(File resource, Transition<? super File> transition) {
//                        final float scale = getInitImageScale(resource.getAbsolutePath());
//                        LogUtil.i(Tag,"downloadOnly===scale==="+scale+"====getAbsolutePath==="+resource.getAbsolutePath());
//                        imageView.setImage(ImageSource.uri(resource.getAbsolutePath()));
//                    }
//                });
    }



    private class MyTask extends AsyncTask{

        @Override
        protected Bitmap doInBackground(Object[] objects) {

            return returnBitMap1(path);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(o!=null){
                Bitmap bitmap = (Bitmap)o;
                view.setImage(ImageSource.bitmap(bitmap));

                //view.setImage(ImageSource.);
            }
        }
    }



    public Bitmap returnBitMap1(final String url){
        Bitmap bitmap = null;
        URL imageurl = null;

        try {
            imageurl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }



    public static InputStream returnBitMap(String path) {
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();    //得到网络返回的输入流

        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

}

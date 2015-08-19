package com.example.complain1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.complain1.utils.Constant;
import com.example.complain1.utils.ImageTools;
import com.example.complain1.utils.ImageUtils;
import com.example.complain1.utils.TimeUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wang on 2015/4/6.
 */
public class Tab3_photoActivity extends Activity{
    private TextView takePhoto,photoAlbum,cancel;
    private Intent intent;
    private String path = ImageUtils.path;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab3_photo);
        Intent intent1 = getIntent();
        from = intent1.getStringExtra("from");
        init();
        initListener();

    }

    private void init(){
        takePhoto = (TextView)this.findViewById(R.id.tab3_take_pictures);
        photoAlbum = (TextView)this.findViewById(R.id.tab3_photo_album);
        cancel = (TextView)this.findViewById(R.id.tab3_photo_cancel);

    }

    private void initListener(){
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);//采用ForResult打开
            }
        });
        photoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, 1);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(data != null && data.getData() != null) {
                    String imgkey = null;
                    ContentResolver resolver = getContentResolver();
                    try {
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, data.getData());
                        if (photo != null) {
                            Bitmap smallBitmap;
                            smallBitmap = ImageTools.zoomBitmapAutoByWidth(photo);
                            photo.recycle();
                            //得到bitmap后的操作
                            imgkey = TimeUtil.getFileKeyByNowDate();
                            try{
                                savePicToSD(smallBitmap, imgkey);//保存在SD卡中
                            } catch (Exception e){
                                // 保存不成功时捕获异常
                                e.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(Tab3_photoActivity.this,DisplayImageActivity.class);
                    intent.putExtra("imgkey", imgkey);
                    intent.putExtra("from",from);
                    //ZXLApplication.getInstance().showTextToast("传递过去的参数："+workDetailList.getBigimage());
                    startActivityForResult(intent, 3);
                /*    Intent intent=new Intent(ctx,DisplayImageActivity.class);
                    intent.putExtra("bigurl",workDetailList.getBigimage());
                    intent.putExtra("imgkey", TimeUtil.getFileKeyByReportTime(workDetailList.getReporttime()));
                    //ZXLApplication.getInstance().showTextToast("传递过去的参数："+workDetailList.getBigimage());
                    ctx.startActivity(intent);*/
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    System.out.println("resultCode == RESULT_OK ");
                    if(data !=null){ //可能尚未指定intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        //返回有缩略图
                        if(data.hasExtra("data")){
                            String imgkey=null;
                            Bitmap photo = data.getParcelableExtra("data");
                            System.out.println("width:"+photo.getWidth()+";height:"+photo.getHeight());
                            // ZXLApplication.getInstance().showTextToast("imageSize "+"width:"+photo.getWidth()+";height:"+photo.getHeight());
                            if (photo != null) {
                                //   Bitmap smallBitmap;
                                //  smallBitmap = ImageTools.zoomBitmap(photo, photo.getWidth(), photo.getHeight() / 2);
                                // photo.recycle();
                                Bitmap smallBitmap;
                                smallBitmap = ImageTools.zoomBitmapAutoByWidth(photo);
                                System.out.println("smallBitmap w:"+smallBitmap.getWidth()+";smallBitmap h:"+smallBitmap.getHeight());
                                //得到bitmap后的操作
                                imgkey = TimeUtil.getFileKeyByNowDate();
                                try{
                                    savePicToSD(smallBitmap, imgkey);//保存在SD卡中
                                } catch (Exception e){
                                    // 保存不成功时捕获异常
                                    e.printStackTrace();
                                }
                                photo.recycle();
                            }
                            Intent intent=new Intent(Tab3_photoActivity.this,DisplayImageActivity.class);
                            intent.putExtra("imgkey", imgkey);
                            intent.putExtra("from",from);
                            //ZXLApplication.getInstance().showTextToast("传递过去的参数："+workDetailList.getBigimage());
                            startActivityForResult(intent, 3);
                        }
                    }else{
                        //由于指定了目标uri，存储在目标uri，intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        // 通过目标uri，找到图片
                        // 对图片的缩放处理
                        // 操作
                        Toast.makeText(this, "用过指定目标uri来实现", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case 3:

                String sendorback = data.getStringExtra("sendorback");
//                ZXLApplication.getInstance().showTextToast("addHead case 3"+sendorback);
                if("send".equals(sendorback)){
                    String imgkey = data.getStringExtra("imgkey");
                    Intent intent = new Intent();
                    intent.putExtra("imgkey", imgkey);
                    setResult(Constant.ResultCode,intent);
                    finish();
                }

                if("back".equals(sendorback)){
                    Toast.makeText(this, "继续选择！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //保存存图片到SD卡中
    private void savePicToSD(Bitmap mBitmap,String fileKey){
        //检测SD卡是否可用
        String sdStatus = Environment.getExternalStorageState();
        if(!sdStatus.equals(Environment.MEDIA_MOUNTED)){
            return;
        }
        FileOutputStream fileOutputStream = null;
        File file = new File(path);
        file.mkdir();
        String fileName = path + fileKey + ".jpg";
        try {
            fileOutputStream = new FileOutputStream(file);
            //写入文件
            mBitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

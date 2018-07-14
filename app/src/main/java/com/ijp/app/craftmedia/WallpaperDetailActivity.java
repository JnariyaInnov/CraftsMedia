package com.ijp.app.craftmedia;

import android.Manifest;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ijp.app.craftmedia.Adapter.VideoDetailAdapter;
import com.ijp.app.craftmedia.Adapter.WallpaperDetailAdapter;
import com.ijp.app.craftmedia.Helper.SaveImageHelper;
import com.ijp.app.craftmedia.Model.VideoDetailItem;
import com.ijp.app.craftmedia.Model.WallpeperDetailItem;
import com.ijp.app.craftmedia.Retrofit.ICraftsMediaApi;
import com.ijp.app.craftmedia.Utils.Common;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;
import java.util.UUID;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WallpaperDetailActivity extends AppCompatActivity {

    ICraftsMediaApi mService;

    RelativeLayout rootLayout;

    Button wallpaperDownload,wallpaperSet;
    RecyclerView wallpaperDetailRV;

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 1000:
            {
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    AlertDialog dialog=new SpotsDialog(WallpaperDetailActivity.this);
                    dialog.show();
                    dialog.setMessage("Please Wait...");

                    String fileName= UUID.randomUUID().toString()+".png";
                    Picasso.with(getBaseContext())
                            .load(Common.currentPicsItem.getLink())
                            .into(new SaveImageHelper(getBaseContext(),dialog,getApplicationContext().getContentResolver(),fileName,"Picsta LiveWalpaper Image"));
                }
                else
                    Toast.makeText(this, "You Need To Accept Permission To Download Image", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    private Target target= new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
            try{
                wallpaperManager.setBitmap(bitmap);
                Snackbar.make(rootLayout,"Wallpaper was set",Snackbar.LENGTH_SHORT).show();
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_detail);

        mService= Common.getAPI();

        rootLayout=findViewById(R.id.root_layout);

        wallpaperDetailRV=findViewById(R.id.wallpaper_detail_rv);
        wallpaperDetailRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        wallpaperDetailRV.setHasFixedSize(true);

        wallpaperDownload=findViewById(R.id.wallpaper_download);
        wallpaperSet=findViewById(R.id.wallpaper_set);

        //Download and image
        wallpaperDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(WallpaperDetailActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
                }
                else
                {
                    AlertDialog dialog=new SpotsDialog(WallpaperDetailActivity.this);
                    dialog.show();
                    dialog.setMessage("Please Wait...");

                    String fileName= UUID.randomUUID().toString()+".png";
                    Picasso.with(getBaseContext())
                            .load(Common.currentPicsItem.getLink())
                            .into(new SaveImageHelper(getBaseContext(),dialog,getApplicationContext().getContentResolver(),fileName,"Picsta LiveWalpaper Image"));
                }
            }
        });

        // Setting a wallpaper
        wallpaperSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(getBaseContext())
                        .load(Common.currentPicsItem.getLink())
                        .into(target);
            }
        });

        loadPics(Common.currentPicsItem.ID);
    }

    private void loadPics(String menuid) {

        compositeDisposable.add(mService.getWallpaperLink(menuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<WallpeperDetailItem>>() {
                    @Override
                    public void accept(List<WallpeperDetailItem> wallpeperDetailItems) throws Exception {
                        displayVideo(wallpeperDetailItems);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));

    }

    private void displayVideo(List<WallpeperDetailItem> wallpeperDetailItems) {
        WallpaperDetailAdapter adapter=new WallpaperDetailAdapter(this,wallpeperDetailItems);
        wallpaperDetailRV.setAdapter(adapter);
    }
}
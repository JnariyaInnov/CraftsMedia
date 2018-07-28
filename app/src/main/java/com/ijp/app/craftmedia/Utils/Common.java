package com.ijp.app.craftmedia.Utils;


import com.ijp.app.craftmedia.Database.DataSource.FavoriteRepository;
import com.ijp.app.craftmedia.Database.Local.CraftsMediaRoomDatabase;
import com.ijp.app.craftmedia.Database.ModelDB.Favorites;
import com.ijp.app.craftmedia.Model.InfiniteListItem;
import com.ijp.app.craftmedia.Model.NewPicsItem;
import com.ijp.app.craftmedia.Model.NewVideosItem;
import com.ijp.app.craftmedia.Model.PicstaModel.CategoryFragmentItem;
import com.ijp.app.craftmedia.Model.PicstaModel.CategoryListItem;
import com.ijp.app.craftmedia.Model.PicstaModel.RandomListItem;
import com.ijp.app.craftmedia.Model.TopPicsItem;
import com.ijp.app.craftmedia.Model.TopVideosItem;
import com.ijp.app.craftmedia.Model.VideoDetailItem;
import com.ijp.app.craftmedia.Model.VideoModel.VideoBannerItem;
import com.ijp.app.craftmedia.Model.VideoModel.VideoCategoriesItem;
import com.ijp.app.craftmedia.Model.VideoModel.VideoRandomModel;
import com.ijp.app.craftmedia.Retrofit.ICraftsMediaApi;
import com.ijp.app.craftmedia.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static List<InfiniteListItem> infiniteListItems=new ArrayList<>();

    //10.0.2.2 - Emulator localhost
    //http://www.thingspeakapi.tk/DrinkShop/
    public static final String BASE_URL = "http://www.thingspeakapi.tk/CraftsMedia/";

    public static TopPicsItem currentPicsItem = null;
    public static TopVideosItem currentVideosItem = null;
    public static NewPicsItem currentNewPicsItem = null;
    public static NewVideosItem currentNewVideosItem = null;

    public static CategoryFragmentItem currentCategoryFragmentsItem = null;
    public static CategoryListItem currentCategoryListItem = null;
    public static RandomListItem currentRandomListItem = null;

    public static VideoDetailItem currentVideoDetailItem=null;
    public static VideoBannerItem currentVideoBannerItem=null;
    public static VideoCategoriesItem currentVideoCategoriesItem=null;
    public static VideoRandomModel currentVideoRandomItem=null;

    public static CraftsMediaRoomDatabase craftsMediaRoomDatabase;
    public static FavoriteRepository favoriteRepository;
    public static Favorites currentFavoritesItem=null;




    public static ICraftsMediaApi getAPI() {
        return RetrofitClient.getClient(BASE_URL).create(ICraftsMediaApi.class);
    }
}

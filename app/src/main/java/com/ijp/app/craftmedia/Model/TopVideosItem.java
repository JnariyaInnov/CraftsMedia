package com.ijp.app.craftmedia.Model;

import com.google.gson.annotations.SerializedName;

public class TopVideosItem {
    public String ID ;
    public String Name ;
    public String Link ;
    public String Category ;
    public String video_link;

    public TopVideosItem() {
    }



    public TopVideosItem(String ID, String name, String link, String category,String video_link) {
        this.ID = ID;
        Name = name;
        Link = link;
        Category = category;
        this.video_link = video_link;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}

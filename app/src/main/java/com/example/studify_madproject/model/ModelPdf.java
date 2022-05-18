package com.example.studify_madproject.model;

import android.service.quicksettings.Tile;

public class ModelPdf {
    String vid,id,title,description,categoryid,url;
    long timestamp,viewsCount,downloadsCount;
    public ModelPdf(){}
    public ModelPdf(String vid,String id,String description,String categoryid,String url,long timestamp,long viewsCount,long downloadsCount)
    {
        this.vid=vid;
        this.id=id;
        this.title=title;
        this.description=description;
        this.categoryid=categoryid;
        this.url=url;
        this.timestamp=timestamp;
        this.viewsCount=viewsCount;
        this.downloadsCount=downloadsCount;
    }
    public String getVid(){return vid;}
    
    public void setVid(String vid){this.vid=vid;}
    
    public String getId(){return id;}
    
    public void setId(String id){this.id=id;}
    
    public String getTitle(){return title;}
    
    public void setTitle(String title){this.title=title;}
    
    public String getDescription(){return description;}
    
    public void setDescription(String description){this.description=description;}
    
    public String getCategoryid(){return categoryid;}
    
    public void setCategoryid(String categoryid){this.categoryid=categoryid;}
    
    public String getUrl(){return url;}
    
    public void setUrl(String url){this.url=url;}
    
    public long getTimestamp(){return timestamp;}
    
    public void setTimestamp(long timestamp){this.timestamp=timestamp; }

    public long getViewsCount(){return viewsCount;}
    
    public void setViewsCount(long viewsCount){this.viewsCount=viewsCount;}

    public long getDownloadsCount(){return downloadsCount;}

    public void setDownloadsCount(long downloadsCount){this.downloadsCount=downloadsCount;}
    }

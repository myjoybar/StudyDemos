package com.example.joybar.myaskunagjia.demo.Retrofit.Retrofit2.Model;

import com.example.joybar.myaskunagjia.utils.PrintLogUtils;

import java.util.List;

/**
 * Created by joybar on 4/20/16.
 */
public class ImageShowMode {

    private int count;
    private int fcount;
    private int galleryclass;
    private int id;

    private String img;

    private int rcount;
    private int size;

    private boolean status;

    private long time;

    private String title;
    private String url;

    private List<ListImage> list;


   public class ListImage{
       private int gallery;
       private int id;
       private String src;

       public int getGallery() {
           return gallery;
       }

       public void setGallery(int gallery) {
           this.gallery = gallery;
       }

       public int getId() {
           return id;
       }

       public void setId(int id) {
           this.id = id;
       }

       public String getSrc() {
           return src;
       }

       public void setSrc(String src) {
           this.src = src;
       }

       @Override
       public String toString() {
           return "ListImage{" +
                   "gallery=" + gallery +
                   ", id=" + id +
                   ", src='" + src + '\'' +
                   '}';
       }
   }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ListImage> getList() {
        return list;
    }

    public void setList(List<ListImage> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ImageShowMode{" +
                "count=" + count +
                ", fcount=" + fcount +
                ", galleryclass=" + galleryclass +
                ", id=" + id +
                ", img='" + img + '\'' +
                ", rcount=" + rcount +
                ", size=" + size +
                ", status=" + status +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", list=" + new PrintLogUtils().ListToString(list).toString() +
                '}';
    }



}

package com.project.project2test.bean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/3/4.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class CartBean {

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public class DataBean {
        private boolean group_flag;
        private String sellerName;
        private String sellerid;
        private List<GoodsBean> list;

        public boolean getGroup_flag() {
            return group_flag;
        }

        public boolean isGroup_flag() {
            return group_flag;
        }

        public void setGroup_flag(boolean group_flag) {
            this.group_flag = group_flag;
        }

        public DataBean(boolean group_flag) {
            this.group_flag = group_flag;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public List<GoodsBean> getList() {
            return list;
        }

        public void setList(List<GoodsBean> list) {
            this.list = list;
        }
    }

    public static class GoodsBean {
        private boolean child_flag;
        private double bargainPrice;
        private String createtime;
        private String detailUrl;
        private String images;
        private int num;
        private int pid;


        public boolean isChild_flag() {
            return child_flag;
        }

        public void setChild_flag(boolean child_flag) {
            this.child_flag = child_flag;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        private double price;
        private int pscid;
        private int selected;
        private int sellerid;
        private String subhead;
        private String title;

        public double getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(double bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPscid() {
            return pscid;
        }

        public void setPscid(int pscid) {
            this.pscid = pscid;
        }

        public int getSelected() {
            return selected;
        }

        public void setSelected(int selected) {
            this.selected = selected;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean getChild_flag() {
            return child_flag;
        }
    }

}

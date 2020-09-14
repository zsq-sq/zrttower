package com.example.arttower.bean;

public class OthersNewsBean {

    /**
     * code : 200000
     * msg :
     * data : {"userId":"3a2ba8bd245393bd8653b95af8840e8a","nickName":"18911001307","headUrl":"http://vod.tingwu365.com/7906c981f06d41c9952fde1b1ca6722b/snapshots/3445b5f78b0f45afb603a90a1c568727-00001.jpg","prestige":null,"focusCount":null,"fansCount":0,"videoCount":null,"danceMoney":null,"isFocus":null,"content":"","collectNum":0,"courseNum":0,"vide":null,"praiseNum":null}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /*
         * userId : 3a2ba8bd245393bd8653b95af8840e8a
         * nickName : 18911001307
         * headUrl : http://vod.tingwu365.com/7906c981f06d41c9952fde1b1ca6722b/snapshots/3445b5f78b0f45afb603a90a1c568727-00001.jpg
         * prestige : null
         * focusCount : null
         * fansCount : 0
         * videoCount : null
         * danceMoney : null
         * isFocus : null
         * content :
         * collectNum : 0
         * courseNum : 0
         * vide : null
         * praiseNum : null
         */

        private String userId;
        private String nickName;
        private String headUrl;
        private Object prestige;
        private Object focusCount;
        private int fansCount;
        private Object videoCount;
        private Object danceMoney;
        private Object isFocus;
        private String content;
        private int collectNum;
        private int courseNum;
        private Object vide;
        private Object praiseNum;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public Object getPrestige() {
            return prestige;
        }

        public void setPrestige(Object prestige) {
            this.prestige = prestige;
        }

        public Object getFocusCount() {
            return focusCount;
        }

        public void setFocusCount(Object focusCount) {
            this.focusCount = focusCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public Object getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(Object videoCount) {
            this.videoCount = videoCount;
        }

        public Object getDanceMoney() {
            return danceMoney;
        }

        public void setDanceMoney(Object danceMoney) {
            this.danceMoney = danceMoney;
        }

        public Object getIsFocus() {
            return isFocus;
        }

        public void setIsFocus(Object isFocus) {
            this.isFocus = isFocus;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getCourseNum() {
            return courseNum;
        }

        public void setCourseNum(int courseNum) {
            this.courseNum = courseNum;
        }

        public Object getVide() {
            return vide;
        }

        public void setVide(Object vide) {
            this.vide = vide;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }
    }
}

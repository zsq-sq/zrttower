package com.example.arttower.bean;

import java.util.List;

public class HomeBean {


    /**
     * code : 200000
     * msg : null
     * data : [{"commentNum":"6","imgUrl":"http://vod.tingwu365.com/2326fd4579584c58b53e1984fcd36b96/snapshots/0ff1403d89644efebca0e6d82a90194f-00001.jpg","forwardNum":"0","videoUrl":[{"videoUrl":"http://vod.tingwu365.com/15d4c68653bd49b49bbf1726b6f60a4d/2798a704e6324333b0be0f9c98528878-4228c6046d7b1339968d0e41137ff1bc-ld.mp4","id":"cae3f0026c697197b7e335fb4a967c36"}],"nickName":"逍遥豆子","headUrl":"http://vod.tingwu365.com/7906c981f06d41c9952fde1b1ca6722b/snapshots/3445b5f78b0f45afb603a90a1c568727-00001.jpg","praiseNum":"5","isPraise":"1","label":[{"labelId":"1","labelName":"芭蕾舞"}],"id":"2326fd4579584c58b53e1984fcd36b96","videoContent":"安多里尼先生可以说是我这辈子有过的最好老师。你可以跟他一起开玩笑，却不致于失去对他的尊敬。"}]
     */

    private int code;
    private Object msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commentNum : 6
         * imgUrl : http://vod.tingwu365.com/2326fd4579584c58b53e1984fcd36b96/snapshots/0ff1403d89644efebca0e6d82a90194f-00001.jpg
         * forwardNum : 0
         * videoUrl : [{"videoUrl":"http://vod.tingwu365.com/15d4c68653bd49b49bbf1726b6f60a4d/2798a704e6324333b0be0f9c98528878-4228c6046d7b1339968d0e41137ff1bc-ld.mp4","id":"cae3f0026c697197b7e335fb4a967c36"}]
         * nickName : 逍遥豆子
         * headUrl : http://vod.tingwu365.com/7906c981f06d41c9952fde1b1ca6722b/snapshots/3445b5f78b0f45afb603a90a1c568727-00001.jpg
         * praiseNum : 5
         * isPraise : 1
         * label : [{"labelId":"1","labelName":"芭蕾舞"}]
         * id : 2326fd4579584c58b53e1984fcd36b96
         * videoContent : 安多里尼先生可以说是我这辈子有过的最好老师。你可以跟他一起开玩笑，却不致于失去对他的尊敬。
         */

        private String commentNum;
        private String imgUrl;
        private String forwardNum;
        private String nickName;
        private String headUrl;
        private String praiseNum;
        private String isPraise;
        private String id;
        private String videoContent;
        private List<VideoUrlBean> videoUrl;
        private List<LabelBean> label;

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getForwardNum() {
            return forwardNum;
        }

        public void setForwardNum(String forwardNum) {
            this.forwardNum = forwardNum;
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

        public String getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(String praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(String isPraise) {
            this.isPraise = isPraise;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideoContent() {
            return videoContent;
        }

        public void setVideoContent(String videoContent) {
            this.videoContent = videoContent;
        }

        public List<VideoUrlBean> getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(List<VideoUrlBean> videoUrl) {
            this.videoUrl = videoUrl;
        }

        public List<LabelBean> getLabel() {
            return label;
        }

        public void setLabel(List<LabelBean> label) {
            this.label = label;
        }

        public static class VideoUrlBean {
            /**
             * videoUrl : http://vod.tingwu365.com/15d4c68653bd49b49bbf1726b6f60a4d/2798a704e6324333b0be0f9c98528878-4228c6046d7b1339968d0e41137ff1bc-ld.mp4
             * id : cae3f0026c697197b7e335fb4a967c36
             */

            private String videoUrl;
            private String id;

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class LabelBean {
            /**
             * labelId : 1
             * labelName : 芭蕾舞
             */

            private String labelId;
            private String labelName;

            public String getLabelId() {
                return labelId;
            }

            public void setLabelId(String labelId) {
                this.labelId = labelId;
            }

            public String getLabelName() {
                return labelName;
            }

            public void setLabelName(String labelName) {
                this.labelName = labelName;
            }
        }
    }
}

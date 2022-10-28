package com.spoon.app.jsbridge_se.bean;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdk on 2020/5/26 14:09
 *
 * @author thinkpad
 */
public class NavigationBarDataBean implements Serializable{

    /**
     * isShowNavigationBar : 0是隐藏，1是显示
     * isShowShare : 0是隐藏，1是显示
     * navigationBar : {"changeLeftImage":"0，1，2，3各代表一种图片显示样式","changeRightImage":[{"id":"显示功能的ID","image":"0，1，2，3各代表一种图片显示样式","method":"前端页面需要做的操作","methodDec":"前端方法的描述","sort":1},{"id":"显示功能的ID","image":"0，1，2，3各代表一种图片显示样式","method":"前端页面需要做的操作","methodDec":"前端方法的描述","sort":1}],"isShowClose":"是否显示关闭按钮，0是隐藏，1是显示","isShowTitle":"是否显示标题","navigationBarBackGroundColor":"标题栏的背景颜色","title":"标题栏的标题","titleColor":"标题显示的颜色","titleSize":"标题显示的字体的大小字号"}
     * shareModel : {"shareContent":"分享的内容","shareImageUrl":"分享图标的url","shareTitle":"分享标题","shareWebUrl":"分享的跳转链接"}
     */

    private String isShowNavigationBar;
    private String isShowShare;
    private NavigationBarBean navigationBar;
    private ShareModelBean shareModel;

    public String getIsShowNavigationBar() {
        return isShowNavigationBar == null ? "" : isShowNavigationBar;
    }

    public void setIsShowNavigationBar(String isShowNavigationBar) {
        this.isShowNavigationBar = isShowNavigationBar;
    }

    public String getIsShowShare() {
        return isShowShare == null ? "" : isShowShare;
    }

    public void setIsShowShare(String isShowShare) {
        this.isShowShare = isShowShare;
    }

    public NavigationBarBean getNavigationBar() {
        return navigationBar;
    }

    public void setNavigationBar(NavigationBarBean navigationBar) {
        this.navigationBar = navigationBar;
    }

    public ShareModelBean getShareModel() {
        return shareModel;
    }

    public void setShareModel(ShareModelBean shareModel) {
        this.shareModel = shareModel;
    }

    public static class NavigationBarBean implements Serializable{
        /**
         * changeLeftImage : 0，1，2，3各代表一种图片显示样式
         * changeRightImage : [{"id":"显示功能的ID","image":"0，1，2，3各代表一种图片显示样式","method":"前端页面需要做的操作","methodDec":"前端方法的描述","sort":1},{"id":"显示功能的ID","image":"0，1，2，3各代表一种图片显示样式","method":"前端页面需要做的操作","methodDec":"前端方法的描述","sort":1}]
         * isShowClose : 是否显示关闭按钮，0是隐藏，1是显示
         * isShowTitle : 是否显示标题
         * navigationBarBackGroundColor : 标题栏的背景颜色
         * title : 标题栏的标题
         * titleColor : 标题显示的颜色
         * titleSize : 标题显示的字体的大小字号
         */

        private String changeLeftImage;
        private String isShowClose;
        private String isShowTitle;
        private String navigationBarBackGroundColor;
        private String title;
        private String titleColor;
        private String titleSize;
        private List<ChangeRightImageBean> changeRightImage;

        public String getChangeLeftImage() {
            return changeLeftImage == null ? "" : changeLeftImage;
        }

        public void setChangeLeftImage(String changeLeftImage) {
            this.changeLeftImage = changeLeftImage;
        }

        public String getIsShowClose() {
            return isShowClose == null ? "" : isShowClose;
        }

        public void setIsShowClose(String isShowClose) {
            this.isShowClose = isShowClose;
        }

        public String getIsShowTitle() {
            return isShowTitle == null ? "" : isShowTitle;
        }

        public void setIsShowTitle(String isShowTitle) {
            this.isShowTitle = isShowTitle;
        }

        public String getNavigationBarBackGroundColor() {
            return navigationBarBackGroundColor == null ? "" : navigationBarBackGroundColor;
        }

        public void setNavigationBarBackGroundColor(String navigationBarBackGroundColor) {
            this.navigationBarBackGroundColor = navigationBarBackGroundColor;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleColor() {
            return titleColor == null ? "" : titleColor;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public String getTitleSize() {
            return titleSize == null ? "" : titleSize;
        }

        public void setTitleSize(String titleSize) {
            this.titleSize = titleSize;
        }

        public List<ChangeRightImageBean> getChangeRightImage() {
            if (changeRightImage == null) {
                return new ArrayList<>();
            }
            return changeRightImage;
        }

        public void setChangeRightImage(List<ChangeRightImageBean> changeRightImage) {
            this.changeRightImage = changeRightImage;
        }

        public static class ChangeRightImageBean implements Comparable<ChangeRightImageBean>, Serializable {
            private String id;
            private String image;
            private String method;
            private String methodDec;
            private int sort;

            public String getId() {
                return id == null ? "" : id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image == null ? "" : image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getMethod() {
                return method == null ? "" : method;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public String getMethodDec() {
                return methodDec == null ? "" : methodDec;
            }

            public void setMethodDec(String methodDec) {
                this.methodDec = methodDec;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            @Override
            public int compareTo(@NonNull ChangeRightImageBean changeRightImageBean) {
                return this.sort - changeRightImageBean.sort; //升序
                //return o.id-this.id;  降序
            }
        }
    }

    public static class ShareModelBean implements Serializable{

        private String imageUrl;
        private String shareDescription;
        private String shareTitle;
        private String shareUrl;

        public String getImageUrl() {
            return imageUrl == null ? "" : imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getShareDescription() {
            return shareDescription == null ? "" : shareDescription;
        }

        public void setShareDescription(String shareDescription) {
            this.shareDescription = shareDescription;
        }

        public String getShareTitle() {
            return shareTitle == null ? "" : shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getShareUrl() {
            return shareUrl == null ? "" : shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }
    }
}

package com.spoon.app.jsbridge_n22.core.extension.bean;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-04-02 17:50
 * description :
 */
public class Options implements Serializable {
    public boolean location = true;
    public boolean hidden = false;
    public boolean clearcache = false;
    public boolean clearsessioncache = false;
    public boolean zoom = true;
    public boolean hardwareback = true;
    public boolean isShowClose = true;
    public boolean isShowBack = false;
    public boolean isShowForward = false;
    public boolean isShowShare = false;

    public Toolbar toolbar;
    public Title title;
    public BrowserButton backButton;
    public BrowserButton forwardButton;
    public BrowserButton closeButton;
    public BrowserMenu menu;
    public BrowserButton[] customButtons;
    public boolean backButtonCanClose;
    public boolean disableAnimation;
    public boolean fullscreen;
    public BrowserProgress browserProgress;

    private String imageUrl; //分享的图片
    private String shareDescription; //分享的描述
    private String shareTitle; //分享的标题
    private String shareUrl; //分享的跳转链接

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

    public boolean isLocation() {
        return location;
    }

    public void setLocation(boolean location) {
        this.location = location;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isClearcache() {
        return clearcache;
    }

    public void setClearcache(boolean clearcache) {
        this.clearcache = clearcache;
    }

    public boolean isClearsessioncache() {
        return clearsessioncache;
    }

    public void setClearsessioncache(boolean clearsessioncache) {
        this.clearsessioncache = clearsessioncache;
    }

    public boolean isZoom() {
        return zoom;
    }

    public void setZoom(boolean zoom) {
        this.zoom = zoom;
    }

    public boolean isHardwareback() {
        return hardwareback;
    }

    public void setHardwareback(boolean hardwareback) {
        this.hardwareback = hardwareback;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public BrowserButton getBackButton() {
        return backButton;
    }

    public void setBackButton(BrowserButton backButton) {
        this.backButton = backButton;
    }

    public BrowserButton getForwardButton() {
        return forwardButton;
    }

    public void setForwardButton(BrowserButton forwardButton) {
        this.forwardButton = forwardButton;
    }

    public BrowserButton getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(BrowserButton closeButton) {
        this.closeButton = closeButton;
    }

    public BrowserMenu getMenu() {
        return menu;
    }

    public void setMenu(BrowserMenu menu) {
        this.menu = menu;
    }

    public BrowserButton[] getCustomButtons() {
        return customButtons;
    }

    public void setCustomButtons(BrowserButton[] customButtons) {
        this.customButtons = customButtons;
    }

    public boolean isBackButtonCanClose() {
        return backButtonCanClose;
    }

    public void setBackButtonCanClose(boolean backButtonCanClose) {
        this.backButtonCanClose = backButtonCanClose;
    }

    public boolean isDisableAnimation() {
        return disableAnimation;
    }

    public void setDisableAnimation(boolean disableAnimation) {
        this.disableAnimation = disableAnimation;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public BrowserProgress getBrowserProgress() {
        return browserProgress;
    }

    public void setBrowserProgress(BrowserProgress browserProgress) {
        this.browserProgress = browserProgress;
    }

    public boolean isShowClose() {
        return isShowClose;
    }

    public void setShowClose(boolean showClose) {
        isShowClose = showClose;
    }

    public boolean isShowBack() {
        return isShowBack;
    }

    public void setShowBack(boolean showBack) {
        isShowBack = showBack;
    }

    public boolean isShowForward() {
        return isShowForward;
    }

    public void setShowForward(boolean showForward) {
        isShowForward = showForward;
    }

    public boolean isShowShare() {
        return isShowShare;
    }

    public void setShowShare(boolean showShare) {
        isShowShare = showShare;
    }
}

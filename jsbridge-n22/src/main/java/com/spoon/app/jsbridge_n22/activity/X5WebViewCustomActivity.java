package com.spoon.app.jsbridge_n22.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.spoon.app.jsbridge_n22.R;
import com.spoon.app.jsbridge_n22.base.BaseActivity;
import com.spoon.app.jsbridge_n22.core.WebViewLoadListener;
import com.spoon.app.jsbridge_n22.core.X5WebView;
import com.spoon.app.jsbridge_n22.core.extension.HideSelectedAdapter;
import com.spoon.app.jsbridge_n22.core.extension.MenuSpinner;
import com.spoon.app.jsbridge_n22.core.extension.bean.BrowserButton;
import com.spoon.app.jsbridge_n22.core.extension.bean.EventLabel;
import com.spoon.app.jsbridge_n22.core.extension.bean.Options;
import com.spoon.app.jsbridge_n22.core.extension.bean.Title;
import com.spoon.app.jsbridge_n22.core.extension.bean.Toolbar;
import com.spoon.app.jsbridge_n22.utils.ShareUtils;
import com.spoon.app.jsbridge_n22.utils.Utils;
import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;

import java.io.IOException;

import static com.spoon.app.jsbridge_n22.core.extension.Constants.ALIGN_RIGHT;
import static com.spoon.app.jsbridge_n22.core.extension.Constants.DISABLED_ALPHA;
import static com.spoon.app.jsbridge_n22.core.extension.Constants.TOOLBAR_DEF_HEIGHT;
import static com.spoon.app.jsbridge_n22.core.extension.bean.UploadMessage.FILE_CHOOSER_RESULT_CODE;

public class X5WebViewCustomActivity extends BaseActivity {

    private final static String ROOT_URL = "ROOT_URL";
    private final static String OPTIONS = "OPTIONS";

    private X5WebView inAppWebView;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, X5WebViewCustomActivity.class);
        intent.putExtra(ROOT_URL, url);
        context.startActivity(intent);
    }

    public static void start(Context context, String url, Options options) {
        Intent intent = new Intent(context, X5WebViewCustomActivity.class);
        intent.putExtra(ROOT_URL, url);
        intent.putExtra(OPTIONS, options);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Options options = (Options) getIntent().getSerializableExtra(OPTIONS);

        //初始化Toolbar
        Toolbar toolbar = new Toolbar();
        toolbar.height = options.toolbar.height;
        toolbar.color = options.toolbar.color;
        options.toolbar = toolbar;

        //初始化Title
        Title title = new Title();
        title.color = options.title.color;
        title.staticText = options.title.staticText;
        title.showPageTitle = true;
        options.title = title;

        //初始化关闭按钮
        BrowserButton closeButton = new BrowserButton();
        closeButton.image = "icon_close";
        closeButton.imagePressed = "icon_close_pressed";
        closeButton.align = "left";
        closeButton.event = "closePressed";
        if (options.isShowClose) {
            options.closeButton = closeButton;
        }

        //初始化返回按钮
        BrowserButton backButton = new BrowserButton();
        backButton.image = "icon_back";
        backButton.imagePressed = "icon_back_pressed";
        backButton.align = "left";
        backButton.event = "backPressed";
        if (options.isShowBack) {
            options.backButton = backButton;
            options.backButtonCanClose = true;
        }

        //初始化前进按钮
        BrowserButton forwardButton = new BrowserButton();
        forwardButton.image = "icon_forward";
        forwardButton.imagePressed = "forward_pressed";
        forwardButton.align = "left";
        forwardButton.event = "forwardPressed";
        if (options.isShowForward) {
            options.forwardButton = forwardButton;
        }

        //初始化分享按钮
        BrowserButton shareButtons = new BrowserButton();
        shareButtons.image = "icon_share";
        shareButtons.imagePressed = "icon_share_pressed";
        shareButtons.align = "right";
        shareButtons.event = "sharePressed";
        if (options.isShowShare) {
            options.customButtons = new BrowserButton[]{shareButtons};
        }

        //默认非全屏
        options.fullscreen = false;
        setContentView(getLayout(options));
    }

    /**
     * 显示带有指定URL的新浏览器。
     *
     * @param features
     * @return
     */
    public ViewGroup getLayout(final Options features) {

        // Main container layout
        ViewGroup main = null;

        //全屏模式
        if (features.fullscreen) {
            main = new FrameLayout(this);
        } else {
            main = new LinearLayout(this);
            ((LinearLayout) main).setOrientation(LinearLayout.VERTICAL);
        }

        // Toolbar layout
        Toolbar toolbarDef = features.toolbar;
        FrameLayout toolbar = new FrameLayout(this);
        toolbar.setBackgroundColor(Utils.hexStringToColor(
                toolbarDef != null && toolbarDef.color != null
                        ? toolbarDef.color : "#ffffffff"));
        toolbar.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                Utils.dpToPixels(this, toolbarDef != null
                        ? toolbarDef.height : TOOLBAR_DEF_HEIGHT)));

        if (toolbarDef != null
                && (toolbarDef.image != null || toolbarDef.wwwImage != null)) {
            try {
                Drawable background = Utils.getImage(this, toolbarDef.image
                        , toolbarDef.wwwImage, toolbarDef.wwwImageDensity);
                setBackground(toolbar, background);
            } catch (Resources.NotFoundException e) {

            } catch (IOException ioe) {

            }
        }

        // Left Button Container layout
        LinearLayout leftButtonContainer = new LinearLayout(this);
        FrameLayout.LayoutParams leftButtonContainerParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftButtonContainerParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        leftButtonContainer.setLayoutParams(leftButtonContainerParams);
        leftButtonContainer.setVerticalGravity(Gravity.CENTER_VERTICAL);

        // Right Button Container layout
        LinearLayout rightButtonContainer = new LinearLayout(this);
        FrameLayout.LayoutParams rightButtonContainerParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightButtonContainerParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        rightButtonContainer.setLayoutParams(rightButtonContainerParams);
        rightButtonContainer.setVerticalGravity(Gravity.CENTER_VERTICAL);


        // 返回按钮
        final Button back = createButton(
                features.backButton,
                "back button",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (features.backButtonCanClose && !canGoBack()) {
                            closePage();
                        } else {
                            goBack();
                        }
                    }
                }
        );

        if (back != null) {
            back.setEnabled(features.backButtonCanClose);
        }

        // 前进按钮
        final Button forward = createButton(
                features.getForwardButton(),
                "forward button",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goForward();
                    }
                }
        );

        if (back != null) {
            back.setEnabled(false);
        }


        // 关闭按钮
        Button close = createButton(
                features.closeButton,
                "close button",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        closePage();
                    }
                }
        );

        // Menu button
        Spinner menu = features.menu != null
                ? new MenuSpinner(this) : null;
        if (menu != null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 0, 10, 0);
            menu.setLayoutParams(layoutParams);
            menu.setContentDescription("menu button");
            setButtonImages(menu, features.menu, DISABLED_ALPHA);

            // We are not allowed to use onClickListener for Spinner, so we will use
            // onTouchListener as a fallback.
            menu.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {

                    }
                    return false;
                }
            });

            if (features.menu.items != null) {
                HideSelectedAdapter<EventLabel> adapter
                        = new HideSelectedAdapter<EventLabel>(
                        this,
                        android.R.layout.simple_spinner_item,
                        features.menu.items);
                adapter.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item);
                menu.setAdapter(adapter);
                menu.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(
                                    AdapterView<?> adapterView,
                                    View view, int i, long l) {
                                if (inAppWebView != null
                                        && i < features.menu.items.length) {

                                }
                            }

                            @Override
                            public void onNothingSelected(
                                    AdapterView<?> adapterView) {
                            }
                        }
                );
            }
        }

        // Title
        final TextView title = features.title != null
                ? new TextView(this) : null;
        if (title != null) {
            FrameLayout.LayoutParams titleParams
                    = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            titleParams.gravity = Gravity.CENTER;
            title.setLayoutParams(titleParams);
            title.setSingleLine();
            title.setEllipsize(TextUtils.TruncateAt.END);
            title.setGravity(Gravity.CENTER);
            title.setTextColor(Utils.hexStringToColor(
                    features.title.color != null
                            ? features.title.color : "#000000ff"));
            if (features.title.staticText != null) {
                title.setText(features.title.staticText);
            }
        }
        final ProgressBar progressbar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        FrameLayout.LayoutParams progressbarLayout = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 6);
        progressbarLayout.gravity = Gravity.CENTER;
        //progressbarLayout.
        progressbar.setLayoutParams(progressbarLayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            progressbar.setForegroundGravity(Gravity.CENTER);
        }
        if (features.browserProgress != null) {
            Integer progressColor = Color.BLUE;
            if (features.browserProgress.progressColor != null
                    && features.browserProgress.progressColor.length() > 0) {
                progressColor = Color.parseColor(features.browserProgress.progressColor);
            }
            ClipDrawable progressDrawable = new ClipDrawable(new ColorDrawable(progressColor), Gravity.LEFT, ClipDrawable.HORIZONTAL);
            progressbar.setProgressDrawable(progressDrawable);
            Integer progressBgColor = Color.GRAY;
            if (features.browserProgress.progressBgColor != null
                    && features.browserProgress.progressBgColor.length() > 0) {
                progressBgColor = Color.parseColor(features.browserProgress.progressBgColor);
            }
            progressbar.setBackgroundColor(progressBgColor);
        }
        // WebView
        inAppWebView = new X5WebView(this, new WebViewLoadListener() {
            @Override
            public void onPageFinished(String url, boolean canGoBack, boolean canGoForward) {
                if (inAppWebView != null
                        && title != null && features.title != null
                        && features.title.staticText == null
                        && features.title.showPageTitle) {
                    title.setText(inAppWebView.getTitle());
                }

                if (back != null) {
                    back.setEnabled(canGoBack || features.backButtonCanClose);
                }

                if (forward != null) {
                    forward.setEnabled(canGoForward);
                }
            }
        });
        final ViewGroup.LayoutParams inAppWebViewParams = features.fullscreen
                ? new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                : new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        if (!features.fullscreen) {
            ((LinearLayout.LayoutParams) inAppWebViewParams).weight = 1;
        }
        inAppWebView.setLayoutParams(inAppWebViewParams);

        if (features.clearcache) {
            CookieManager.getInstance().removeAllCookie();
        } else if (features.clearsessioncache) {
            CookieManager.getInstance().removeSessionCookie();
        }

        //加载页面
        inAppWebView.loadUrl(getIntent().getStringExtra(ROOT_URL));

        //将按钮添加到leftButtonsContainer或
        // rightButtonsContainer根据用户的对齐方式
        //配置。
        int leftContainerWidth = 0;
        int rightContainerWidth = 0;

        if (features.customButtons != null) {
            for (int i = 0; i < features.customButtons.length; i++) {
                final BrowserButton buttonProps = features.customButtons[i];
                final int index = i;
                final Button button = createButton(buttonProps, String.format("custom button at %d", i), null);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupShareMenu(X5WebViewCustomActivity.this, button);
                    }
                });

                if (ALIGN_RIGHT.equals(buttonProps.align)) {
                    rightButtonContainer.addView(button);
                    rightContainerWidth
                            += button.getLayoutParams().width;
                } else {
                    leftButtonContainer.addView(button, 0);
                    leftContainerWidth
                            += button.getLayoutParams().width;
                }
            }
        }

        //后退和前进按钮必须添加特殊的排序逻辑
        //如果两个按钮都有的话，后退按钮总是在前进按钮的左边
        //在同一边。
        if (forward != null && features.forwardButton != null
                && !ALIGN_RIGHT.equals(features.forwardButton.align)) {
            leftButtonContainer.addView(forward, 0);
            leftContainerWidth
                    += forward.getLayoutParams().width;
        }

        if (back != null && features.backButton != null
                && ALIGN_RIGHT.equals(features.backButton.align)) {
            rightButtonContainer.addView(back);
            rightContainerWidth
                    += back.getLayoutParams().width;
        }

        if (back != null && features.backButton != null
                && !ALIGN_RIGHT.equals(features.backButton.align)) {
            leftButtonContainer.addView(back, 0);
            leftContainerWidth
                    += back.getLayoutParams().width;
        }

        if (forward != null && features.forwardButton != null
                && ALIGN_RIGHT.equals(features.forwardButton.align)) {
            rightButtonContainer.addView(forward);
            rightContainerWidth
                    += forward.getLayoutParams().width;
        }

        if (menu != null) {
            if (features.menu != null
                    && ALIGN_RIGHT.equals(features.menu.align)) {
                rightButtonContainer.addView(menu);
                rightContainerWidth
                        += menu.getLayoutParams().width;
            } else {
                leftButtonContainer.addView(menu, 0);
                leftContainerWidth
                        += menu.getLayoutParams().width;
            }
        }

        if (close != null) {
            if (features.closeButton != null
                    && ALIGN_RIGHT.equals(features.closeButton.align)) {
                rightButtonContainer.addView(close);
                rightContainerWidth
                        += close.getLayoutParams().width;
            } else {
                leftButtonContainer.addView(close, 0);
                leftContainerWidth
                        += close.getLayoutParams().width;
            }
        }

        // 将视图添加到工具栏
        toolbar.addView(leftButtonContainer);
        // Don't show address bar.
        // toolbar.addView(edittext);
        toolbar.addView(rightButtonContainer);

        if (title != null) {
            int titleMargin = Math.max(
                    leftContainerWidth, rightContainerWidth);

            FrameLayout.LayoutParams titleParams
                    = (FrameLayout.LayoutParams) title.getLayoutParams();
            titleParams.setMargins(titleMargin, 0, titleMargin, 0);
            toolbar.addView(title);
        }

        if (features.fullscreen) {
            // 如果是全屏模式，我们必须在添加工具栏之前添加inAppWebView。
            main.addView(inAppWebView);
        }

        // 如果工具栏被禁用，不要添加它
        if (features.location) {
            //将工具栏添加到主视图/布局中
            main.addView(toolbar);
            if (features.browserProgress != null && features.browserProgress.showProgress) {
                main.addView(progressbar);
            }
        }

        if (!features.fullscreen) {
            // 如果不是全屏，我们在添加工具栏之后添加inAppWebView。
            main.addView(inAppWebView);
        }

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        return main;
    }

    private void popupShareMenu(final Activity activity, Button button) {
        TopRightMenu mTopRightMenu = new TopRightMenu(activity);
        mTopRightMenu
                .setHeight(Utils.dpToPixels(activity, 95))     //默认高度480
                //.setWidth(Utils.dpToPixels(activity,500))      //默认宽度wrap_content
                .showIcon(true)     //显示菜单图标，默认为true
                .dimBackground(true)           //背景变暗，默认为true
                .needAnimationStyle(true)   //显示动画，默认为true
                .setAnimationStyle(R.style.TRM_ANIM_STYLE)  //默认为R.style.TRM_ANIM_STYLE
                .addMenuItem(new MenuItem(R.drawable.icon_friends, "分享给好友"))
                .addMenuItem(new MenuItem(R.drawable.icon_circle, "分享到朋友圈"))
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        if (TextUtils.isEmpty(inAppWebView.getUrl())) {
                            return;
                        }
                        String title = inAppWebView.getTitle();
                        if (!title.isEmpty()) {
                            String mainTitle = "";
                            String subTitle = "";
                            if (title.contains("%|%")) {
                                String[] titles = title.split("%|%");
                                mainTitle = titles[0];
                                subTitle = titles[1];
                            } else {
                                mainTitle = title;
                            }
                            if (position == 0) {
                                ShareUtils.shareWeb(activity, mainTitle, subTitle, inAppWebView.getFavicon(), inAppWebView.getUrl(), "1");
                            } else if (position == 1) {
                                ShareUtils.shareWeb(activity, mainTitle, subTitle, inAppWebView.getFavicon(), inAppWebView.getUrl(), "2");
                            }
                        }
                    }
                })
                .showAsDropDown(button, -325, 10);
    }


    private void setButtonImages(View view, BrowserButton buttonProps, int disabledAlpha) {
        Drawable normalDrawable = null;
        Drawable disabledDrawable = null;
        Drawable pressedDrawable = null;

        CharSequence description = view.getContentDescription();

        if (buttonProps.image != null || buttonProps.wwwImage != null) {
            try {
                normalDrawable = Utils.getImage(this, buttonProps.image, buttonProps.wwwImage,
                        buttonProps.wwwImageDensity);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.width = normalDrawable.getIntrinsicWidth();
                params.height = normalDrawable.getIntrinsicHeight();
            } catch (Resources.NotFoundException e) {

            } catch (IOException ioe) {

            }
        } else {

        }

        if (buttonProps.imagePressed != null || buttonProps.wwwImagePressed != null) {
            try {
                pressedDrawable = Utils.getImage(this, buttonProps.imagePressed, buttonProps.wwwImagePressed,
                        buttonProps.wwwImageDensity);
            } catch (Resources.NotFoundException e) {

            } catch (IOException e) {

            }
        } else {

        }

        if (normalDrawable != null) {
            // Create the disabled state drawable by fading the normal state
            // drawable. Drawable.setAlpha() stopped working above Android 4.4
            // so we gotta bring out some bitmap magic. Credit goes to:
            // http://stackoverflow.com/a/7477572
            Bitmap enabledBitmap = ((BitmapDrawable) normalDrawable).getBitmap();
            Bitmap disabledBitmap = Bitmap.createBitmap(
                    normalDrawable.getIntrinsicWidth(),
                    normalDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(disabledBitmap);

            Paint paint = new Paint();
            paint.setAlpha(disabledAlpha);
            canvas.drawBitmap(enabledBitmap, 0, 0, paint);

            Resources activityRes = this.getResources();
            disabledDrawable = new BitmapDrawable(activityRes, disabledBitmap);
        }

        StateListDrawable states = new StateListDrawable();
        if (pressedDrawable != null) {
            states.addState(
                    new int[]{
                            android.R.attr.state_pressed
                    },
                    pressedDrawable
            );
        }
        if (normalDrawable != null) {
            states.addState(
                    new int[]{
                            android.R.attr.state_enabled
                    },
                    normalDrawable
            );
        }
        if (disabledDrawable != null) {
            states.addState(
                    new int[]{},
                    disabledDrawable
            );
        }

        setBackground(view, states);
    }

    private void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    /**
     * 创建按钮
     *
     * @param buttonProps
     * @param description
     * @param listener
     * @return
     */
    private Button createButton(BrowserButton buttonProps, String description,
                                View.OnClickListener listener) {
        Button result = null;
        if (buttonProps != null) {
            result = new Button(this);
            result.setContentDescription(description);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 0, 10, 0);
            result.setLayoutParams(layoutParams);
            setButtonImages(result, buttonProps, DISABLED_ALPHA);
            if (listener != null) {
                result.setOnClickListener(listener);
            }
        }
        return result;
    }

    /**
     * 检查是否可以返回历史记录中的一页，然后执行此操作。
     */
    public void goBack() {
        if (this.inAppWebView != null && this.inAppWebView.canGoBack()) {
            this.inAppWebView.goBack();
        }
    }

    /**
     * web浏览器可以返回吗?
     *
     * @return boolean
     */
    public boolean canGoBack() {
        return this.inAppWebView != null && this.inAppWebView.canGoBack();
    }

    /**
     * 检查是否可以将历史记录向前移动一页，然后执行此操作。
     */
    private void goForward() {
        if (this.inAppWebView != null && this.inAppWebView.canGoForward()) {
            this.inAppWebView.goForward();
        }
    }

    /**
     * 关闭页面
     */
    private void closePage() {
        if (this != null) {
            this.finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (inAppWebView.getWebChromeClient() != null) {
                inAppWebView.getWebChromeClient().getUploadMessage().onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
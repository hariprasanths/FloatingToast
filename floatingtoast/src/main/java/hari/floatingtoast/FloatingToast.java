package hari.floatingtoast;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import java.lang.ref.WeakReference;

public class FloatingToast implements FloatingToastStyle{

    private WeakReference<Activity> activity;
    private WeakReference<View> view;
    private int duration;
    private int standByDuration = 200;
    private int fadeOutDuration = FADE_DURATION_MEDIUM;
    private Dialog dialog;
    private WindowManager.LayoutParams layoutParams;
    private TextView messageTextView;
    private TextView blurViewRight;
    private TextView blurViewLeft;
    private boolean isTouchInsideView = true;
    private float translationDistance = DISTANCE_MEDIUM;

    /**
     * Duration of the toast being shown. This time could be user-definable.
     */
    public static final int LENGTH_TOO_LONG = 1500;
    public static final int LENGTH_LONG = 1250;
    public static final int LENGTH_MEDIUM = 1000;
    public static final int LENGTH_SHORT = 750;
    public static final int LENGTH_QUICK = 500;

    /**
     * Fade out duration of the toast. This time could be user-definable.
     * Default is {@link #FADE_DURATION_MEDIUM}
     * @see #setFadeOutDuration(int)
     */
    public static final int FADE_DURATION_TOO_LONG = 1250;
    public static final int FADE_DURATION_LONG = 1000;
    public static final int FADE_DURATION_MEDIUM = 750;
    public static final int FADE_DURATION_SHORT = 500;
    public static final int FADE_DURATION_QUICK = 250;

    /**
     * Float distance of the toast. This distance
     * could be user-definable.  Default is {@link #DISTANCE_MEDIUM}
     * @see #setFloatDistance(float)
     */
    public static final int DISTANCE_LONG = 50;
    public static final int DISTANCE_MEDIUM = 40;
    public static final int DISTANCE_SHORT = 30;

    /**
     * Location at which the toast should appear on the screen. This could
     * be user-definable. Default is {@link #GRAVITY_CENTER}
     * @see #setGravity(int)
     */
    public static final int GRAVITY_TOP = Gravity.TOP;
    public static final int GRAVITY_MID_TOP = 3000;
    public static final int GRAVITY_CENTER = Gravity.CENTER;
    public static final int GRAVITY_MID_BOTTOM = 3001;
    public static final int GRAVITY_BOTTOM = Gravity.BOTTOM;

    /**
     * Style of the text in the toast.  This style
     * could be user-definable.  Default is {@link #STYLE_NORMAL}
     * @see #setTextStyle(int)
     */
    public static final int STYLE_BOLD = Typeface.BOLD;
    public static final int STYLE_ITALIC = Typeface.ITALIC;
    public static final int STYLE_NORMAL = Typeface.NORMAL;
    public static final int STYLE_BOLD_ITALIC = Typeface.BOLD_ITALIC;

    /**
     * Make a standard toast that just contains a text view.
     * (Recommended method over {@link #makeToast(Activity, String, int)}
     *
     * @param view     The view which was used to call the toast.
     * @param text     The text to show.  Can be formatted text.
     * @param duration Duration of the toast to be shown in milliseconds(int).
     *                 Either user-defined int or predefined constant duration
     *                 Available options - {@link #LENGTH_LONG},
     *                 {@link #LENGTH_MEDIUM}, {@link #LENGTH_SHORT},
     *                 {@link #LENGTH_TOO_LONG}, {@link #LENGTH_QUICK}
     *
     */
    public static FloatingToast makeToast(View view, String text, int duration) {
        return new FloatingToast(view, text, duration);
    }

    /**
     * Make a standard toast that just contains a text view with the text from a resource.
     * (Recommended method over {@link #makeToast(Activity, int, int)}
     *
     * @param view     The view which was used to call the toast.
     * @param resId    The resource id of the string resource to use.  Can be formatted text.
     * @param duration Duration of the toast to be shown in milliseconds(int).
     *                 Either user-defined int or predefined constant duration
     *                 Available options - {@link #LENGTH_LONG},
     *                 {@link #LENGTH_MEDIUM}, {@link #LENGTH_SHORT},
     *                 {@link #LENGTH_TOO_LONG}, {@link #LENGTH_QUICK}
     *
     * @throws Resources.NotFoundException if the resource can't be found.
     */
    public static FloatingToast makeToast(View view, @StringRes int resId, int duration)
            throws Resources.NotFoundException {
        return new FloatingToast(view, view.getContext().getResources().getText(resId).toString(), duration);
    }

    /**
     * Make a standard toast that just contains a text view.
     * (Only use this method if not calling from a view)
     *
     * @param activity The activity to use.  Usually your {@link android.app.Activity} object.
     * @param text     The text to show.  Can be formatted text.
     * @param duration Duration of the toast to be shown in milliseconds(int).
     *                 Either user-defined int or predefined constant duration
     *                 Available options - {@link #LENGTH_LONG},
     *                 {@link #LENGTH_MEDIUM}, {@link #LENGTH_SHORT},
     *                 {@link #LENGTH_TOO_LONG}, {@link #LENGTH_QUICK}
     *
     */
    public static FloatingToast makeToast(Activity activity, String text, int duration) {
        return new FloatingToast(activity, text, duration);
    }

    /**
     * Make a standard toast that just contains a text view with the text from a resource.
     * (Only use this method if not calling from a view)
     *
     * @param activity The activity to use.  Usually your {@link android.app.Activity} object.
     * @param resId    The resource id of the string resource to use.  Can be formatted text.
     * @param duration Duration of the toast to be shown in milliseconds(int).
     *                 Either user-defined int or predefined constant duration
     *                 Available options - {@link #LENGTH_LONG},
     *                 {@link #LENGTH_MEDIUM}, {@link #LENGTH_SHORT},
     *                 {@link #LENGTH_TOO_LONG}, {@link #LENGTH_QUICK}
     *
     * @throws Resources.NotFoundException if the resource can't be found.
     */
    public static FloatingToast makeToast(Activity activity, @StringRes int resId, int duration)
            throws Resources.NotFoundException {
        return new FloatingToast(activity, activity.getResources().getText(resId).toString(), duration);
    }

    /**
     * Set the location at which the notification should appear on the screen.
     *
     * @param gravity   Position of toast in the screen
     *                  Default is {@link #GRAVITY_CENTER}
     * Available options - {@link #GRAVITY_MID_TOP}, {@link #GRAVITY_CENTER},
     *                     {@link #GRAVITY_MID_BOTTOM}, {@link #GRAVITY_BOTTOM},
     *                     {@link #GRAVITY_TOP}
     * @see android.view.Gravity
     */
    @Override
    public FloatingToastStyle setGravity(int gravity) {
        int screenHeight = getDisplayHeight();
        Double midHeight = screenHeight * 0.25;

        switch (gravity) {
            case GRAVITY_MID_TOP:
                layoutParams.gravity = Gravity.TOP | Gravity.CENTER;
                layoutParams.y = midHeight.intValue();
                dialog.getWindow().setAttributes(layoutParams);
                break;

            case GRAVITY_MID_BOTTOM:
                layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER;
                layoutParams.y = midHeight.intValue();
                dialog.getWindow().setAttributes(layoutParams);
                break;

            default:
                this.dialog.getWindow().setGravity(gravity);
                break;
        }
        return this;
    }

    /**
     * Set the location at which the notification should appear on the screen.
     * @param gravity   Position of toast in the screen
     *                  Available options - {@link #GRAVITY_TOP},
     *                  {@link #GRAVITY_CENTER}, {@link #GRAVITY_BOTTOM}
     * @param xOffset   X offset in pixels to apply to the gravity's location
     * @param yOffset   Y offset in pixels to apply to the gravity's location
     * @see android.view.Gravity
     */
    @Override
    public FloatingToastStyle setGravity(int gravity, int xOffset, int yOffset) {
        layoutParams.gravity = gravity;
        layoutParams.x = xOffset;
        layoutParams.y = yOffset;
        dialog.getWindow().setAttributes(layoutParams);
        return this;
    }

    /**
     * Set the fade out duration of the toast.
     *
     * @param fadeOutDuration Fade out duration in milliseconds(int). Total animation time
     *                        of the toast - {@link #duration} + {@link #fadeOutDuration}
     *                        Default is {@link #FADE_DURATION_MEDIUM}
     *                        This ca either user-defined int or predefined constant duration
     *                        Available options - {@link #FADE_DURATION_LONG},
     *                        {@link #FADE_DURATION_MEDIUM}, {@link #FADE_DURATION_SHORT},
     *                        {@link #FADE_DURATION_TOO_LONG}, {@link #FADE_DURATION_QUICK}
     *
     */
    @Override
    public FloatingToastStyle setFadeOutDuration(int fadeOutDuration) {
        this.fadeOutDuration = fadeOutDuration;
        return this;
    }

    /**
     * Sets the text color for all the states (normal, selected,
     * focused) to be this color.
     *
     * @param color A color value in the form 0xAARRGGBB.
     * Do not pass a resource ID. To get a color value from a resource ID, call
     * @link android.support.v4.content.ContextCompat#getColor(Context, int) getColor
     * @attr ref android.R.styleable#TextView_textColor
     */
    @Override
    public FloatingToastStyle setTextColor(int color) {
        updateTextColor(color);
        return this;
    }

    /**
     * Sets the typeface and style in which the text should be displayed.
     * Note that not all Typeface families actually have bold and italic
     * variants, so you may need to use
     * {@link #setTextStyle(int)} to get the appearance
     * that you actually want.
     *
     * @attr ref android.R.styleable#TextView_fontFamily
     * @attr ref android.R.styleable#TextView_typeface
     * @attr ref android.R.styleable#TextView_textStyle
     */
    @Override
    public FloatingToastStyle setTextTypeface(Typeface typeface) {
        updateTextTypeface(typeface);
        return this;
    }

    /**
     * Sets the style in which the text should be displayed,
     * and turns on the fake bold and italic bits in the Paint if the
     * Typeface that you provided does not have all the bits in the
     * style that you specified.
     *
     * @param style - Style of the text shown in the toast
     *                Default is {@link #STYLE_NORMAL}
     * Available options - {@link #STYLE_NORMAL}, {@link #STYLE_BOLD},
     *                     {@link #STYLE_ITALIC}, {@link #STYLE_BOLD_ITALIC}
     *
     * @attr ref android.R.styleable#TextView_typeface
     * @attr ref android.R.styleable#TextView_textStyle
     */
    @Override
    public FloatingToastStyle setTextStyle(int style) {
        if(style == STYLE_NORMAL || style == STYLE_BOLD
                || style == STYLE_BOLD_ITALIC || style == STYLE_ITALIC) {
            updateTextStyle(style);
        }
        return this;
    }

    /**
     * Set the default text size to the given value, interpreted as "scaled
     * pixel" units.  This size is adjusted based on the current density and
     * user font size preference.
     *
     * @param sizeInSp The scaled pixel size.
     *
     * @attr ref android.R.styleable#TextView_textSize
     */
    @Override
    public FloatingToastStyle setTextSizeInSp(float sizeInSp) {
        updateTextSize(TypedValue.COMPLEX_UNIT_SP, sizeInSp);
        return this;
    }

    /**
     * Set the default text size to the given value, interpreted as "density
     * independent pixel" units.  This size is adjusted based on the current density.
     *
     * @param sizeInDp The density independent pixel size.
     *                 Default is 16dp.
     *
     * @attr ref android.R.styleable#TextView_textSize
     */
    @Override
    public FloatingToastStyle setTextSizeInDp(float sizeInDp) {
        updateTextSize(TypedValue.COMPLEX_UNIT_DIP, sizeInDp);
        return this;
    }

    /**
     * Set the default text size to a given unit and value. See {@link
     * TypedValue} for the possible dimension units.
     *
     * @param unit The desired dimension unit.
     * @param size The desired size in the given units.
     *
     * @attr ref android.R.styleable#TextView_textSize
     */
    @Override
    public FloatingToastStyle setTextSizeCustomUnit(int unit, float size) {
        updateTextSize(unit, size);
        return this;
    }

    /**
     * Sets the distance of the toast till which it floats.
     * @param floatDistance Distance in pixels
     *                      Default is {@link #DISTANCE_MEDIUM}
     */
    @Override
    public FloatingToastStyle setFloatDistance(float floatDistance) {
        if(floatDistance > 100) floatDistance = 100;
        this.translationDistance = floatDistance;
        return this;
    }

    /**
     * Gives the text a shadow of the specified blur radius and color, the specified
     * distance from its drawn position.
     * <p>
     * The text shadow produced does not interact with the properties on view
     * that are responsible for real time shadows,
     * {@link View#getElevation() elevation} and
     * {@link View#getTranslationZ() translationZ}.
     *
     * @attr ref android.R.styleable#TextView_shadowColor
     * @attr ref android.R.styleable#TextView_shadowDx
     * @attr ref android.R.styleable#TextView_shadowDy
     * @attr ref android.R.styleable#TextView_shadowRadius
     */
    @Override
    public FloatingToastStyle setShadowLayer(float shadowRadius, float shadowDx, float shadowDy, int shadowColor) {
        if(shadowRadius > 25) shadowRadius = 25;
        this.messageTextView.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        return this;
    }

    /**
     * Sets the blur background for the text in the toast.
     * @param bool  boolean value - false disables the blur.
     *              Default is true.
     */
    @Override
    public FloatingToastStyle setBackgroundBlur(boolean bool) {
        if(!bool) {
            blurViewRight.setVisibility(View.GONE);
            blurViewLeft.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * Show the view for the specified duration at the touch position.
     * @param view  View over which the toast is to be shown
     */
    @Override
    public void showAtTouchPosition(View view) {
        WeakReference<View> view1 = new WeakReference<>(view);
        if(view1.get() != null) {
            view1.get().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    int action = event.getAction();

                    if (action == MotionEvent.ACTION_DOWN) {
                        isTouchInsideView = true;

                    } else if (action == MotionEvent.ACTION_UP) {
                        if (isTouchInsideView) {

                            int actionBarSize = (int) getActionBarSize();

                            int x = (int) event.getRawX();
                            int y = (int) event.getRawY() - actionBarSize;

                            layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
                            layoutParams.x = x;
                            layoutParams.y = y;

                            dialog.getWindow().setAttributes(layoutParams);

                            showDialog();

                            return false;
                        }
                    }else if (action == MotionEvent.ACTION_MOVE) {
                        if (isTouchInsideView) {
                            float currentX = event.getX();
                            float currentY = event.getY();
                            float currentPosX = currentX + v.getLeft();
                            float currentPosY = currentY + v.getTop();
                            float viewLeft = v.getLeft();
                            float viewTop = v.getTop();
                            float viewRight = v.getRight();
                            float viewBottom = v.getBottom();
                            if (!(currentPosX > viewLeft && currentPosX < viewRight
                                    && currentPosY > viewTop && currentPosY < viewBottom)) {
                                isTouchInsideView = false;
                            }

                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    /**
     * Show the view for the specified duration.
     */
    @Override
    public void show() {
        showDialog();
    }

    private void showDialog() {
        this.dialog.show();
        onStartDialog();
    }

    private void dismissDialog() {
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    private FloatingToast(View view, String messageText, int duration) {
        this.view = new WeakReference<>(view);
        if(this.view.get() != null) {
            this.activity = new WeakReference<>(getActivity(this.view.get()));
        }

        if(this.activity.get() != null)
            dialog = new Dialog(this.activity.get()){
                @Override
                public boolean onTouchEvent(@NonNull MotionEvent event) {
                    int action = event.getAction();

                    if(action == MotionEvent.ACTION_OUTSIDE) {
                        Rect rect = new Rect();
                        FloatingToast.this.view.get().getGlobalVisibleRect(rect);

                        float rectLeft = rect.left;
                        float rectRight = rect.right;
                        float rawX = event.getRawX();

                        float rectTop = rect.top;
                        float rectBottom = rect.bottom;
                        float rawY = event.getRawY();
                        if((rawX > rectLeft && rawX < rectRight
                                && rawY > rectTop && rawY < rectBottom)) {
                            if(isShowing())
                                dismiss();
                            return false;
                        }
                    }

                    return false;
                }

                @Override
                public void onBackPressed() {
                    FloatingToast.this.activity.get().onBackPressed();
                }
            };

        initToast(messageText, duration);
    }

    private FloatingToast(Activity activity, String messageText, final int duration) {
        this.activity = new WeakReference<>(activity);

        if(this.activity.get() != null)
            dialog = new Dialog(this.activity.get()){
                @Override
                public boolean onTouchEvent(@NonNull MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_OUTSIDE)
                        if(isShowing())
                            dismiss();
                    return false;
                }

                @Override
                public void onBackPressed() {
                    FloatingToast.this.activity.get().onBackPressed();
                }
            };

        initToast(messageText, duration);

    }

    private static Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    private void initToast(String messageText, final int duration) {
        this.duration = duration;

        layoutParams = dialog.getWindow().getAttributes();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_toast);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setDimAmount(0.0F);
        dialog.getWindow().setGravity(GRAVITY_CENTER);
        dialog.getWindow().setWindowAnimations(R.style.ToastAnimation);

        messageTextView = dialog.findViewById(R.id.messageTextView);
        blurViewRight = dialog.findViewById(R.id.blurViewRight);
        blurViewLeft = dialog.findViewById(R.id.blurViewLeft);

        updateTextContent(messageText);

    }

    private void onStartDialog() {
        final View decorView = this.dialog.getWindow()
                .getDecorView();

        decorView.animate()
                .translationY(-(this.translationDistance))
                .setStartDelay(0)
                .setDuration(this.duration)
                .start();

        decorView.animate()
                .alpha(0.0f)
                .setStartDelay(this.duration + this.standByDuration)
                .setDuration(this.fadeOutDuration)
                .start();

        final Application.ActivityLifecycleCallbacks lifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {
                dismissDialog();
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activity.getApplication().unregisterActivityLifecycleCallbacks(this);
            }
        };

        this.activity.get().getApplication().registerActivityLifecycleCallbacks(lifecycleCallbacks);

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(!activity.get().isFinishing()) {
                            dismissDialog();

                            if (activity.get() != null)
                                activity.get().getApplication().unregisterActivityLifecycleCallbacks(lifecycleCallbacks);
                        }
                    }
                },
                this.duration + this.standByDuration + this.fadeOutDuration);
    }

    private void updateTextContent(String text) {
        messageTextView.setText(text);
        blurViewRight.setText(text);
        blurViewLeft.setText(text);
    }

    private void updateTextColor(int color) {
        this.messageTextView.setTextColor(color);
        this.blurViewRight.setTextColor(color);
        this.blurViewLeft.setTextColor(color);
    }

    private void updateTextTypeface(Typeface typeface) {
        this.messageTextView.setTypeface(typeface);
        this.blurViewRight.setTypeface(typeface);
        this.blurViewLeft.setTypeface(typeface);
    }

    private void updateTextStyle(int style) {
        this.messageTextView.setTypeface(messageTextView.getTypeface(), style);
        this.blurViewRight.setTypeface(messageTextView.getTypeface(), style);
        this.blurViewLeft.setTypeface(messageTextView.getTypeface(), style);
    }

    private void updateTextSize(int unit, float size) {
        this.messageTextView.setTextSize(unit, size);
        this.blurViewRight.setTextSize(unit, size);
        this.blurViewLeft.setTextSize(unit, size);
    }

    private int getDisplayHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.activity.get().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private float getActionBarSize() {
        final TypedArray styledAttributes = this.activity.get().getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize});
        float actionBarSize = styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return actionBarSize;
    }
}

package toong.vn.androidflagattributes;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomView extends LinearLayout {
    private ImageView imgImage;
    private TextView tvText;

    // Constants for the flags
    private static final int BORDER_NONE_DEFAULT = 0;
    private static final int BORDER_TOP = 1;  //Thường đặt là 1<<0
    private static final int BORDER_RIGHT = 2; // 1<<1
    private static final int BORDER_BOTTOM = 4; //1<<2
    private static final int BORDER_LEFT = 8;  //1<<3
    // Variable for the current value
    private int mDrawBorder = BORDER_NONE_DEFAULT;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.custom_layout, this, true);

        imgImage = (ImageView) findViewById(R.id.image);
        tvText = (TextView) findViewById(R.id.text);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyView);
        String text = ta.getString(R.styleable.MyView_title);

        mDrawBorder = ta.getInt(R.styleable.MyView_drawBorder, 0);

        Log.i("mDrawBorder", "draw border = " + mDrawBorder);

        // Method call
        boolean drawBorderTop = containsFlag(mDrawBorder, BORDER_TOP);
        boolean drawBorderRight = containsFlag(mDrawBorder, BORDER_RIGHT);
        boolean drawBorderBottom = containsFlag(mDrawBorder, BORDER_BOTTOM);
        boolean drawBorderLeft = containsFlag(mDrawBorder, BORDER_LEFT);

        findViewById(R.id.border_top).setVisibility(drawBorderTop ? View.VISIBLE : View.GONE);
        findViewById(R.id.border_right).setVisibility(drawBorderRight ? View.VISIBLE : View.GONE);
        findViewById(R.id.border_bottom).setVisibility(drawBorderBottom ? View.VISIBLE : View.GONE);
        findViewById(R.id.border_left).setVisibility(drawBorderLeft ? View.VISIBLE : View.GONE);

        ta.recycle();
        tvText.setText(text);
    }

    private boolean containsFlag(int flagSet, int flag){
        return (flagSet|flag) == flagSet;
    }

}
package cn.edu.order.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ShopListVIew extends ListView {

    public ShopListVIew(Context context) {
        super(context);
    }

    public ShopListVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShopListVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpace = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpace);
    }
}


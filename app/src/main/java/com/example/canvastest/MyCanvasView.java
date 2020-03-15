package com.example.canvastest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

public class MyCanvasView extends View {
    private Paint mPaint;
    private Path mPath;
    private int mDrawColor;
    private int mBackgroundColor;
    private Canvas mExtraCanvas;
    private Bitmap mExtraBitmap;
    private Double length;

    private MyCanvasListener local;
    public void setMyCanvasListener(MyCanvasListener myCanvasListener){
        this.local = myCanvasListener;
    }
    public MyCanvasListener getMyCanvasListener(){
        return this.local;
    }


    MyCanvasView(Context context) {
        this(context, null);
    }

    public MyCanvasView(Context context, AttributeSet attributeSet) {
        super(context);

        mBackgroundColor = ResourcesCompat.getColor(getResources(),
                R.color.opaque_orange, null);
        mDrawColor = ResourcesCompat.getColor(getResources(),
                R.color.opaque_yellow, null);

        // Holds the path we are currently drawing.
        mPath = new Path();
        // Set up the paint with which to draw.
        mPaint = new Paint();
        mPaint.setColor(mDrawColor);
        // Smoothes out edges of what is drawn without affecting shape.
        mPaint.setAntiAlias(true);
        // Dithering affects how colors with higher-precision device
        // than the are down-sampled.
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE); // default: FILL
        mPaint.setStrokeJoin(Paint.Join.ROUND); // default: MITER
        mPaint.setStrokeCap(Paint.Cap.ROUND); // default: BUTT
        mPaint.setStrokeWidth(12); // default: Hairline-width (really thin)
    }


    @Override
    protected void onSizeChanged(int width, int height,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
// Create bitmap, create canvas with bitmap, fill canvas with color.
//        mExtraBitmap = Bitmap.createBitmap(width, height,
//                Bitmap.Config.ARGB_8888);

        mExtraBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(),
                R.drawable.insta_egg),
                width,
                height, false);
        mExtraCanvas = new Canvas(mExtraBitmap);
// Fill the Bitmap with the background color.
//        mExtraCanvas.drawColor(mBackgroundColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
// Draw the bitmap that stores the path the user has drawn.
// Initially the user has not drawn anything
// so we see only the colored bitmap.
        canvas.drawBitmap(mExtraBitmap, 0, 0, null);
    }

    private float mX=0, mY=0, mX0=0, mY0=0;
    private static final float TOUCH_TOLERANCE = 4;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                mX0=x;
                mY0=y;
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                //   Mx1=(int) event.getX();
                //  My1= (int) event.getY();
                invalidate();
                break;
        }

        if(getMyCanvasListener()!=null){
            getMyCanvasListener().canvasLength(
                    2.0);
        }
        return true;
    }
    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            // mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mExtraCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }

    public double getLineSize() {
        //return Math.sqrt((mY - mY0) * (mY - mY0) + (mX - mX0) * (mX - mX0));
        return 5;
    }

    public String getPouet() {
        return "popopo";
    }

    public interface MyCanvasListener {
        void canvasLength(Double val);
    }
}

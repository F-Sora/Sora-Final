package jp.ac.gifu_u.sora.vsai_drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {

    private List<Stroke> strokes = new ArrayList<>();
    private Stroke currentStroke;

    private int currentColor = Color.BLACK;
    private float currentStrokeWidth = 8f;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // ペンの色を設定
    public void setColor(String colorHex) {
        currentColor = Color.parseColor(colorHex);
    }

    // ペンの太さを設定
    public void setStrokeWidth(float width) {
        currentStrokeWidth = width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Stroke stroke : strokes) {
            canvas.drawPath(stroke.path, stroke.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentStroke = new Stroke(currentColor, currentStrokeWidth);
                currentStroke.path.moveTo(x, y);
                currentStroke.path.lineTo(x, y); // 最初の点も描画されるようにする
                strokes.add(currentStroke);
                break;

            case MotionEvent.ACTION_MOVE:
                if (currentStroke != null) {
                    currentStroke.path.lineTo(x, y);
                }
                break;

            case MotionEvent.ACTION_UP:
                if (currentStroke != null) {
                    currentStroke.path.lineTo(x, y); // 最後の点
                }
                currentStroke = null;
                break;
        }

        invalidate(); // 再描画
        return true;
    }

    // ストローク（1本の線）を表す内部クラス
    private static class Stroke {
        Path path;
        Paint paint;

        Stroke(int color, float strokeWidth) {
            path = new Path();
            paint = new Paint();
            paint.setColor(color);
            paint.setStrokeWidth(strokeWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND); // 丸みをつける
            paint.setStrokeJoin(Paint.Join.ROUND); // なめらかな結合
            paint.setAntiAlias(true);
        }
    }
}

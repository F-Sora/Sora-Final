package jp.ac.gifu_u.sora.vsai_drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    DrawingView drawingView;
    Button btnEraser, btnPen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        drawingView = findViewById(R.id.drawingView);
        btnEraser = findViewById(R.id.btn_eraser);
        btnPen = findViewById(R.id.btn_pen);

        // 消しゴムボタン：背景色と同じ白で描く
        btnEraser.setOnClickListener(v -> {
            drawingView.setStrokeWidth(40); // 太くすると消しやすく見える
            drawingView.setColor("#FFFFFF"); // 背景と同じ白
        });

        // ペンボタン：通常の描画色（黒など）に戻す
        btnPen.setOnClickListener(v -> {
            drawingView.setStrokeWidth(8); // 通常のペン幅に戻す
            drawingView.setColor("#000000"); // 黒色
        });
    }
    }
}
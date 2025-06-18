package jp.ac.gifu_u.sora.vsai_drawing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private Button btnEraser, btnPen;

    // 10色ボタン
    private int[] colorButtonIds = {
            R.id.btn_color_black, R.id.btn_color_red, R.id.btn_color_blue,
            R.id.btn_color_green, R.id.btn_color_yellow, R.id.btn_color_orange,
            R.id.btn_color_purple, R.id.btn_color_brown, R.id.btn_color_gray,
            R.id.btn_color_pink
    };

    private String[] colorHexCodes = {
            "#000000", "#FF0000", "#0000FF",
            "#00FF00", "#FFFF00", "#FFA500",
            "#800080", "#A52A2A", "#808080",
            "#FFC0CB"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);
        btnEraser = findViewById(R.id.btn_eraser);
        btnPen = findViewById(R.id.btn_pen);

        // 消しゴムモード
        btnEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setColor("#FFFFFF");
                drawingView.setStrokeWidth(40f);
            }
        });

        // ペンモード（黒）
        btnPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setColor("#000000");
                drawingView.setStrokeWidth(8f);
            }
        });

        // 色ボタンの設定
        for (int i = 0; i < colorButtonIds.length; i++) {
            final String color = colorHexCodes[i];
            Button colorButton = findViewById(colorButtonIds[i]);
            colorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawingView.setColor(color);
                    drawingView.setStrokeWidth(8f); // 通常の太さに戻す
                }
            });
        }
    }
}

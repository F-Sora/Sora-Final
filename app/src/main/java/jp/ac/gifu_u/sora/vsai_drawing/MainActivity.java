package jp.ac.gifu_u.sora.vsai_drawing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private TextView labelResult;
    private Button btnAnalyze, btnPen, btnEraser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);
        labelResult = findViewById(R.id.label_result);
        btnAnalyze = findViewById(R.id.btn_analyze);
        btnPen = findViewById(R.id.btn_pen);
        btnEraser = findViewById(R.id.btn_eraser);

        // ペンモード
        btnPen.setOnClickListener(v -> {
            drawingView.setColor("#000000");
            drawingView.setStrokeWidth(8f);
        });

        // 消しゴムモード
        btnEraser.setOnClickListener(v -> {
            drawingView.setColor("#FFFFFF");
            drawingView.setStrokeWidth(40f);
        });

        // AIで画像ラベル解析
        btnAnalyze.setOnClickListener(v -> analyzeDrawing());
    }

    private void analyzeDrawing() {
        // ViewをBitmapに変換
        Bitmap bitmap = Bitmap.createBitmap(
                drawingView.getWidth(),
                drawingView.getHeight(),
                Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(bitmap);
        drawingView.draw(canvas);

        InputImage image = InputImage.fromBitmap(bitmap, 0);
        // ここを修正：ImageLabelerOptions.DEFAULT → new ImageLabelerOptions.Builder().build()
        ImageLabeler labeler = ImageLabeling.getClient(new ImageLabelerOptions.Builder().build());

        labeler.process(image)
                .addOnSuccessListener(labels -> {
                    StringBuilder result = new StringBuilder();
                    for (ImageLabel label : labels) {
                        result.append(label.getText())
                                .append("（")
                                .append(String.format("%.1f", label.getConfidence() * 100))
                                .append("%）\n");
                    }

                    if (result.length() == 0) {
                        result.append("ラベルが見つかりませんでした");
                    }

                    labelResult.setText(result.toString());
                })
                .addOnFailureListener(e -> {
                    labelResult.setText("解析失敗: " + e.getMessage());
                    Log.e("MLKit", "Image labeling failed", e);
                });
    }
}

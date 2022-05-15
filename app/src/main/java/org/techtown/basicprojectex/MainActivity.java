package org.techtown.basicprojectex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    private static final int FROM_ALBUM = 1;    // onActivityResult 식별자
    private static final int FROM_CAMERA = 2;   // 카메라는 사용 안함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 인텐트의 결과는 onActivityResult 함수에서 수신.
        // 여러 개의 인텐트를 동시에 사용하기 때문에 숫자를 통해 결과 식별(FROM_ALBUM 등등)
        findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");                      // 이미지만
                intent.setAction(Intent.ACTION_GET_CONTENT);    // 카메라(ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, FROM_ALBUM);
            }
        });
    }

    private Interpreter getTfliteInterpreter(String modelPath) {
        try {
            return new Interpreter(loadModelFile(MainActivity.this, modelPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MappedByteBuffer loadModelFile(Activity activity, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    // 사진첩에서 사진 파일 불러와 빈자리 유무 분류하는 코드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 카메라를 다루지 않기 때문에 앨범 상수에 대해서 성공한 경우에 대해서만 처리
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != FROM_ALBUM || resultCode != RESULT_OK)
            return;

        //각 모델에 따른 input , output shape 각자 맞게 변환
        // mobilenetcheck.h5 일시 224 * 224 * 3
        float[][][][] input = new float[1][224][224][3];
        float[][] output = new float[1][2]; //tflite에 버섯 종류 2개라서 (내기준)

        try {
            int batchNum = 0;
            InputStream buf = getContentResolver().openInputStream(data.getData());
            Bitmap bitmap = BitmapFactory.decodeStream(buf);
            buf.close();

            //이미지 뷰에 선택한 사진 띄우기
            ImageView iv = findViewById(R.id.image);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageBitmap(bitmap);

            // x,y 최댓값 사진 크기에 따라 달라짐 (조절 해줘야함)
            for (int x = 0; x < 224; x++) {
                for (int y = 0; y < 224; y++) {
                    int pixel = bitmap.getPixel(x, y);
                    input[batchNum][x][y][0] = Color.red(pixel) / 1.0f;
                    input[batchNum][x][y][1] = Color.green(pixel) / 1.0f;
                    input[batchNum][x][y][2] = Color.blue(pixel) / 1.0f;
                }
            }

            // 자신의 tflite 이름 써주기
            Interpreter lite = getTfliteInterpreter("converted_model.tflite");
            lite.run(input, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView tv_output = findViewById(R.id.tv_output);
        int i;

        // 텍스트뷰에 무슨 버섯인지 띄우기 but error남
        for (i = 0; i < 2; i++) {
            if (output[0][i] * 100 > 90) {
                if (i == 0) {
                    tv_output.setText(String.format("빈자리 없음  %d %.5f", i, output[0][0] * 100));
                }
                else {
                    tv_output.setText(String.format("빈자리 있음, %d, %.5f", i, output[0][1] * 100));
                }
            } else
                continue;
        }
    }
}
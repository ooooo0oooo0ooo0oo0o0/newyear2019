package com.example.tsuzuki.newyear2019;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        // 問題数と正解数を取得
        int totalNum = getIntent().getIntExtra("totalNum", 0);
        int correctNum = getIntent().getIntExtra("correctNum", 0);


        TextView correctNumText = findViewById(R.id.correctNum);
        String resultString = ("結果 : " + String.valueOf(totalNum) + "問中 "
                + String.valueOf(correctNum) + "問正解");
        correctNumText.setText(resultString);

        // 正解率を％に変換
        double doubleTotalNum = totalNum;
        double doubleCorrectNum = correctNum;
        double doublePercent = (doubleCorrectNum / doubleTotalNum * 100);

        // 正解率表示
        TextView correctPerText = findViewById(R.id.correctPercentage);
        String percentString = ("(正解率" + String.valueOf(doublePercent) + "％)");
        correctPerText.setText(percentString);

        // その他諸々を表示
        showMisc(doublePercent);
    }

    // 戻るボタン押下
    @Override
    public void onBackPressed() {
        // 何もしない(無効化)
    }

    // 終了ボタン押下
    public void onPushFinish(View v) {
        // おしまい
        finish();
    }

    // 成果に応じた絵と文言の表示処理
    private void showMisc(double doublePercent) {

        ImageView imageView = findViewById(R.id.resultImage);
        Drawable drawable;

        TextView textView = findViewById(R.id.messageText);

        if (doublePercent >= 100.0) {
            drawable = getResources().getDrawable(R.drawable.result_100);
            textView.setText("すげーよ！全問正解！\n いちまんえーん！");
        } else if (doublePercent >= 80.0) {
            drawable = getResources().getDrawable(R.drawable.result_80);
            textView.setText("よくやった。\n￥５０００よ♡");
        } else if (doublePercent >= 60.0) {
            drawable = getResources().getDrawable(R.drawable.result_60);
            textView.setText("おしいね。\n￥４０００だな。");
        } else if (doublePercent >= 40.0) {
            drawable = getResources().getDrawable(R.drawable.result_40);
            textView.setText("もう少し頑張れよー。\n￥３０００だな。");
        } else if (doublePercent >= 20.0) {
            drawable = getResources().getDrawable(R.drawable.result_20);
            textView.setText("もうちょい新聞読め。\n￥２０００だ。");
        } else if (doublePercent >= 1.0) {
            drawable = getResources().getDrawable(R.drawable.result_20);
            textView.setText("￥１０００くれてやる。\n情けだ。");
        } else {
            drawable = getResources().getDrawable(R.drawable.result_0);
            textView.setText("今年はお年玉なしじゃぁー！");
        }
        imageView.setImageDrawable(drawable);
    }
}

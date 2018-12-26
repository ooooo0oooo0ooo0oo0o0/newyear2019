package com.example.tsuzuki.newyear2019;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    // 総問題数
    final int maxQuestionNum = 10;
    // 現在の問題番号
    int currentQuestionNo = 1;
    // 現在の問題
    Questions currentQuestion;
    // 問題オブジェクト
    public ArrayList<Questions> questions = new ArrayList<>();
    // 正解数
    int correctNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // 問題文生成
        createQuestions();

        // 一問目の準備
        currentQuestion = this.questions.get(currentQuestionNo - 1);
        showQuestion();
    }

    // 戻るボタン押下
    @Override
    public void onBackPressed() {
        // 何もしない(無効化)
    }

    // 回答押下処理
    public void checkResult(View v) {
        Button ansBtn = findViewById(v.getId());
        String ansNum = ansBtn.getText().toString();

        // 正誤Popup用の画像
        ImageView iv = new ImageView(this);

        if (currentQuestion.isCorrect(Integer.parseInt(ansNum) - 1)) {
            // 正解
            iv.setImageResource(R.drawable.correct);
            correctNum++;
        } else {
            // はずれ
            iv.setImageResource(R.drawable.incorrect);
        }

        iv.setAdjustViewBounds(true);

        // 正誤Popupの生成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(iv);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (currentQuestionNo >= maxQuestionNum) {
                    // 全問題回答完了後は終了シーケンスに突入
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("correctNum", correctNum);
                    intent.putExtra("totalNum", maxQuestionNum);
                    startActivity(intent);
                } else {
                    currentQuestionNo++;
                    // TODO : 次の問題の準備
                    currentQuestion = questions.get(currentQuestionNo - 1);
                    showQuestion();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }


    // 問題画面表示処理
    public void showQuestion() {
        // 問題番号
        TextView questionNum = this.findViewById(R.id.questionNum);
        questionNum.setText("[第" + currentQuestionNo + "問]");

        // 問題文
        TextView questionText = this.findViewById(R.id.questionText);
        questionText.setText(currentQuestion.getQuestion());

        // 回答一覧
        String[] ansList = currentQuestion.getAnsList();
        // 1
        TextView ans1Text = this.findViewById(R.id.ans_1_text);
        ans1Text.setText(ansList[0]);
        // 2
        TextView ans2Text = this.findViewById(R.id.ans_2_text);
        ans2Text.setText(ansList[1]);
        // 3
        TextView ans3Text = this.findViewById(R.id.ans_3_text);
        ans3Text.setText(ansList[2]);
        // 4
        TextView ans4Text = this.findViewById(R.id.ans_4_text);
        ans4Text.setText(ansList[3]);
    }

    public void createQuestions() {
        // 問題文をガンガン突っ込む
        // TODO : xmlを読み込む等の方式にしたい。暇な時にやる。
        // #01
        {
            String question = "2018年12月現在の日本の外務大臣の名前は？";
            String[] ansList = {"麻生太郎", "野田聖子", "山本太郎", "河野太郎"};
            pushQuestion(question, ansList, 3);
        }
        // #02
        {
            String question = "北朝鮮の首都はどこ？";
            String[] ansList = {"ヘルシンキ", "ピョンヤン", "メルボルン", "ペキン"};
            pushQuestion(question, ansList, 1);
        }
        // #03
        {
            String question = "2018年にヴィッセル神戸に移籍したアンドレス・イニエスタ。そんなイニエスタの嫁さんの名前は？";
            String[] ansList = {"イヴァンカ", "アンナ", "エカチェリーナ", "ヴィクトリア"};
            pushQuestion(question, ansList, 1);
        }
        // #04
        {
            String question = "「I have a dream」の演説で有名な、アメリカの公民権運動の指導者はだれ？";
            String[] ansList = {"マーティン・ルーサー・キング", "キャロル・キング", "キング・クリムゾン", "ダイアナ・キング"};
            pushQuestion(question, ansList, 0);
        }
        // #05
        {
            String question = "道迷い遭難の子供を救出して脚光を浴びたボランティア活動家の小畠春夫さん。小畠さんが40歳のときに初めて山登りをした際に行った山脈の名前は何？";
            String[] ansList = {"朝日連峰", "日高山脈", "北アルプス", "九重連山"};
            pushQuestion(question, ansList, 3);
        }
        // #06
        {
            String question = "2018年、カナダでは大麻の所持及び使用が合法化された。さて、大麻に多く含まれる向精神成分は以下のうちどれか？";
            String[] ansList = {"TPP", "DHCP", "THC", "ACDC"};
            pushQuestion(question, ansList, 2);
        }
        // #07
        {
            String question = "今年ギネス記録を更新したハンター・イーウェンさん。彼が1時間に膨らませた風船の数はいくつ？";
            String[] ansList = {"512個", "763個", "910個", "1013個"};
            pushQuestion(question, ansList, 2);
        }
        // #08
        {
            String question = "シリアでイスラム武装勢力の拉致から開放された安田純平さん。イスラム教徒に改宗させられた際に選択した名前は何？";
            String[] ansList = {"ウマル", "アクバル", "ホジャ", "ムハンマド"};
            pushQuestion(question, ansList, 0);
        }
        // #09
        {
            String question = "表舞台から身を引いた安室奈美恵さん。彼女がかつてポンキッキーズで「シスターラビッツ」を演じていた際のパートナーは誰？";
            String[] ansList = {"篠原ともえ", "鈴木蘭々", "知念里奈", "和田アキ子"};
            pushQuestion(question, ansList, 1);
        }
        // #10
        {
            String question = "グラミー賞を受賞したケンドリック・ラマー。かつて彼をホワイトハウスに招待したことのあるアメリカ大統領は？";
            String[] ansList = {"ビル・クリントン", "ジョージ・W・ブッシュ", "バラク・オバマ", "ドナルド・トランプ"};
            pushQuestion(question, ansList, 2);
        }
        // #11
        {
            String question = "新たに世界文化遺産に登録された「長崎と天草地方の潜伏キリシタン関連遺産」。隠れキリシタンをテーマにした諸星大二郎の漫画作品はどれ？";
            String[] ansList = {"孔子暗黒伝", "マッドメン", "闇の客人", "生命の樹"};
            pushQuestion(question, ansList, 3);
        }
        // #12
        {
            String question = "富田林の警察署を脱走した窃盗犯が、逃走に際して偽っていた身分は？";
            String[] ansList = {"グラップラー", "チャリダー", "ディアハンター", "バックパッカー"};
            pushQuestion(question, ansList, 1);
        }
        // #13
        {
            String question = "今年は平成最後の年。「平成」という元号を発表した当時の官房長官は誰？";
            String[] ansList = {"ケイゾー小渕", "チャーリー小林", "マック赤坂", "又吉イエス"};
            pushQuestion(question, ansList, 0);
        }
        // #14
        {
            String question = "今年、多くのファンに惜しまれながら閉店したレンタルCDショップ、Janis(ジャニス)。故ジャニス・ジョプリンの死因となったとされる薬物は何？";
            String[] ansList = {"トリプタミン", "ヘロイン", "アンフェタミン", "LSD-25"};
            pushQuestion(question, ansList, 1);
        }
        // #15
        {
            String question = "FIFAワールドカップがロシアで開催された2018年。グループステージ グループBのモロッコの最終的な得失点は？";
            String[] ansList = {"1", "0", "-1", "-2"};
            pushQuestion(question, ansList, 3);
        }
        // #16
        {
            String question = "キラウェア火山の噴火によって大きな被害を受けたハワイ島。そんなハワイ島と姉妹都市である日本の都市はどれ？";
            String[] ansList = {"北海道・秩父別町", "鳥取県・湯梨浜町", "群馬県・鼻毛石町", "鹿児島県・志布志市"};
            pushQuestion(question, ansList, 1);
        }
        // TODO : 暇な時に追加する

        // かき混ぜる
        Collections.shuffle(questions);
    }

    private void pushQuestion(String question, String[] ansList, int answer) {
        Questions obj = new Questions(question, ansList, answer);
        questions.add(obj);
    }
}

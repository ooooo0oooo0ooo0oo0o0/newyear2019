package com.example.tsuzuki.newyear2019;

// 問題クラス
public class Questions {
    // コンストラクタ
    Questions(String question, String[] ansList, int answer) {
        this.question = question;
        this.ansList = ansList;
        this.answer = answer;
    }

    // 問題文getter
    public String getQuestion() {
        return question;
    }

    // 回答候補取得
    public String[] getAnsList()
    {
        return ansList;
    }

    // 答え合わせ
    public boolean isCorrect(int num) {
        return (num == answer);
    }

    // 問題文
    private String question;
    // 回答候補
    private String[] ansList;
    // 回答番号
    private int answer;
}

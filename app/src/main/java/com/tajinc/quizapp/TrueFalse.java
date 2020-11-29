package com.tajinc.quizapp;

public class TrueFalse {
    //Определенный вопрос в массиве
    private int question;
    //Содержит ответ true или false
    private boolean isTrueQuestion;

    //создаем или сгнерируем конструктор
  public TrueFalse(int question, boolean isTrueQuestion) {
      this.question=question;
      this.isTrueQuestion=isTrueQuestion;
  }
    //Getter и Setter установить и выводить
    public int getQuestion() {
        return question;
    }
    public void setQuestion(int question) {
        this.question = question;
    }
    public boolean isTrueQuestion() {
        return isTrueQuestion;
    }
    public void setTrueQuestion(boolean trueQuestion) {
        isTrueQuestion = trueQuestion;
    }
}
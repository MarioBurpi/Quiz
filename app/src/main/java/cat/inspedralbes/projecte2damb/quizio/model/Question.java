package cat.inspedralbes.projecte2damb.quizio.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    String category;
    String type;
    String difficulty;
    String statement;
    String correctAnswer;
    List<String> incorrectAnswers;
    List<String> allAnswers;

    public Question(){
        incorrectAnswers = new ArrayList<String>();
        allAnswers = new ArrayList<String>();
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public String getStatement() {
        return statement;
    }
    public void setStatement(String statement) {
        this.statement = statement;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }
    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }
    public List<String> getAllAnswers() {
        return allAnswers;
    }
    public void setAllAnswers(List<String> allAnswers) {
        this.allAnswers = allAnswers;
    }

    public void addIncorrectAnswer(String ia){
        incorrectAnswers.add(ia);
    }

    public void shuffleAnswers(){
        allAnswers.addAll(incorrectAnswers);
        allAnswers.add(correctAnswer);
        Collections.shuffle(allAnswers);
    }
    @Override
    public String toString() {
        return "Question{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", statement='" + statement + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers.toString() +
                '}';
    }
}

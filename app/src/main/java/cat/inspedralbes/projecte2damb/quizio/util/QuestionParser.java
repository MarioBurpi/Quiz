package cat.inspedralbes.projecte2damb.quizio.util;

import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import cat.inspedralbes.projecte2damb.quizio.model.Question;

public class QuestionParser {

    private String json;

    public List<Question> getQuestionList(String json) throws JSONException, UnsupportedEncodingException {
        List<Question> questionList = new ArrayList<Question>();
        Question question = null;
        JSONObject doc = new JSONObject(json);
        JSONArray results = doc.getJSONArray("results");
        JSONObject questionData = null;
        JSONArray incorrectAnswers = null;

        String category, type, difficulty, statement, correctAnswer, incorrectAnswer;
        for (int i = 0; i < results.length(); i++){
            questionData = results.getJSONObject(i);
            category = questionData.getString("category");
            type = questionData.getString("type");
            difficulty = questionData.getString("difficulty");
            statement = questionData.getString("question");
            correctAnswer = questionData.getString("correct_answer");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                category = decodeURL3986toUTF8(category);
                type = decodeURL3986toUTF8(type);
                difficulty = decodeURL3986toUTF8(difficulty);
                statement = decodeURL3986toUTF8(statement);
                correctAnswer = decodeURL3986toUTF8(correctAnswer);
            }

            question = new Question();
            question.setCategory(category);
            question.setType(type);
            question.setDifficulty(difficulty);
            question.setStatement(statement);
            question.setCorrectAnswer(correctAnswer);

            incorrectAnswers = questionData.getJSONArray("incorrect_answers");
            for (int j = 0; j < incorrectAnswers.length(); j++){
                incorrectAnswer = incorrectAnswers.getString(j);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    incorrectAnswer = decodeURL3986toUTF8(incorrectAnswer);
                }
                question.addIncorrectAnswer(incorrectAnswer);
            }
            question.shuffleAnswers();
            questionList.add(question);

        }
        return questionList;
    }

    private String decodeURL3986toUTF8(String string) throws UnsupportedEncodingException {
        String decodedString = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            decodedString = URLDecoder.decode(string, StandardCharsets.UTF_8.name());
        }
        return decodedString;
    }
}



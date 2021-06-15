package cat.inspedralbes.projecte2damb.quizio.util;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cat.inspedralbes.projecte2damb.quizio.model.Question;

public class QuestionDownloader {
    /**
     * https://opentdb.com/api_config.php
     */
    public static final String URL_TRIVIA_REQUEST = "https://opentdb.com/api.php";
    public static final String URL_TRIVIA_REQUEST_AMOUNT = "?amount=";
    public static final String URL_TRIVIA_REQUEST_CATEGORY = "&category=";
    public static final String URL_TRIVIA_REQUEST_DIFFICULTY = "&difficulty=";
    public static final String URL_TRIVIA_REQUEST_ENCODING = "&encode=url3986";
    public static final String DEFAULT_AMOUNT = "50";
    public static List<Question> questionList;

    public static void requestTrivia(String amount, String category, String difficulty){
        if (amount.length() > 0) amount = URL_TRIVIA_REQUEST_AMOUNT + amount;
            else amount =  URL_TRIVIA_REQUEST_AMOUNT + DEFAULT_AMOUNT;
        if (category.length() > 0) category = URL_TRIVIA_REQUEST_CATEGORY + category;
            else category = "";
        if (difficulty.length() > 0) difficulty = URL_TRIVIA_REQUEST_DIFFICULTY + difficulty;
            else difficulty = "";
        OkHttpClient okHttpClient = new OkHttpClient();
        QuestionParser parser = new QuestionParser();
        Request request = new Request.Builder()
                .url(URL_TRIVIA_REQUEST + amount + category + difficulty + URL_TRIVIA_REQUEST_ENCODING)
                .addHeader("Content-Type", "text/json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("JSON", "onFailure: F");
            }
            @Override
            public void onResponse(Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    String json = responseBody.string();
                    Log.d("JSON", "" + json);
                    questionList = parser.getQuestionList(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

package cat.inspedralbes.projecte2damb.quizio.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cat.inspedralbes.projecte2damb.quizio.MainActivity;
import cat.inspedralbes.projecte2damb.quizio.R;
import cat.inspedralbes.projecte2damb.quizio.util.QuestionDownloader;
import cat.inspedralbes.projecte2damb.quizio.util.SoundManager;
import cat.inspedralbes.projecte2damb.quizio.model.Question;

public class GameFragment extends Fragment {

    private TextView tvQuestion, tvRound, tvScore;
    private Button btnA, btnB, btnC, btnD;
    private int nRound, score;
    private SoundManager soundManager;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        nRound = 0;
        score = 0;
        soundManager = new SoundManager(getContext());
        View rootView = inflater.inflate(R.layout.fragment_game_screen, container, false);
        tvQuestion = rootView.findViewById(R.id.game_screen_textview_question);
        tvRound = rootView.findViewById(R.id.game_screen_textview_round);
        tvScore = rootView.findViewById(R.id.game_screen_textview_score);
        btnA = rootView.findViewById(R.id.game_screen_button_a);
        btnB = rootView.findViewById(R.id.game_screen_button_b);
        btnC = rootView.findViewById(R.id.game_screen_button_c);
        btnD = rootView.findViewById(R.id.game_screen_button_d);
        btnA.setOnClickListener(this::onClick);
        btnB.setOnClickListener(this::onClick);
        btnC.setOnClickListener(this::onClick);
        btnD.setOnClickListener(this::onClick);
        showQuestion();
        return rootView;
    }

    private void onClick(View view) {
        Button b = (Button) view;
        String userAnswered = b.getText().toString();
        if (userAnswered.equals(QuestionDownloader.questionList.get(nRound -1).getCorrectAnswer())){
            b.setBackgroundColor(Color.GREEN);
            score += 100;
            soundManager.playSoundCorrect();
            showQuestion();
        }else {
            b.setBackgroundColor(Color.rgb(230, 20, 20));
            b.setActivated(false);
            soundManager.playSoundWrong();
            score -= 25;
        }
        tvScore.setText(String.valueOf(score));
    }

    public void showQuestion(){
        Question question = QuestionDownloader.questionList.get(nRound);
        resetButtons();
        tvQuestion.setText(question.getStatement());
        tvRound.setText(String.valueOf(nRound+1));

        if (question.getType().equals("multiple")) {
            btnA.setText(question.getAllAnswers().get(0));
            btnB.setText(question.getAllAnswers().get(1));
            btnC.setText(question.getAllAnswers().get(2));
            btnD.setText(question.getAllAnswers().get(3));
        }else {
            btnA.setText(question.getAllAnswers().get(0));
            btnB.setText(question.getAllAnswers().get(1));
            btnC.setVisibility(View.GONE);
            btnD.setVisibility(View.GONE);
        }
        nRound++;
    }

    public void resetButtons(){
        btnA.setVisibility(View.VISIBLE);
        btnA.setActivated(true);
        btnA.setBackgroundColor(Color.parseColor("#2196F3"));
        btnB.setVisibility(View.VISIBLE);
        btnB.setActivated(true);
        btnB.setBackgroundColor(Color.parseColor("#2196F3"));
        btnC.setVisibility(View.VISIBLE);
        btnC.setActivated(true);
        btnC.setBackgroundColor(Color.parseColor("#2196F3"));
        btnD.setVisibility(View.VISIBLE);
        btnD.setActivated(true);
        btnD.setBackgroundColor(Color.parseColor("#2196F3"));
    }
}
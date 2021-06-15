package cat.inspedralbes.projecte2damb.quizio.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import cat.inspedralbes.projecte2damb.quizio.MainActivity;
import cat.inspedralbes.projecte2damb.quizio.R;
import cat.inspedralbes.projecte2damb.quizio.fragments.GameFragment;
import cat.inspedralbes.projecte2damb.quizio.util.QuestionDownloader;

public class OptionsFragment extends Fragment {

    EditText etNumberOfQuestions;
    Spinner spCategory;
    RadioButton rdbEasy;
    RadioButton rdbMedium;
    RadioButton rdbHard;
    ImageButton imgButtonOK;

    public OptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_options, container, false);
        etNumberOfQuestions = rootView.findViewById(R.id.edittext_options_fragment_num_questions);
        spCategory = rootView.findViewById(R.id.spinner_options_fragment_category);
        rdbEasy = rootView.findViewById(R.id.rdbtn_options_fragment_easy);
        rdbMedium = rootView.findViewById(R.id.rdbtn_options_fragment_medium);
        rdbHard = rootView.findViewById(R.id.rdbtn_options_fragment_hard);
        imgButtonOK = rootView.findViewById(R.id.imgbutton_options_fragment_ok);
        imgButtonOK.setOnClickListener(this::onClick);
        return rootView;
    }

    private void onClick(View view){
        String numQuestions = "15";
        String category = "";
        String difficulty = "";
        if (etNumberOfQuestions.getText().length() > 0)
            numQuestions = etNumberOfQuestions.getText().toString();
        if (spCategory.getSelectedItem().toString().length() > 0){
            category = spCategory.getSelectedItem().toString();
            category = category.substring(0, 2);
            category = String.valueOf(Integer.parseInt(category) + 8);
        }
        if (!rdbEasy.isChecked() && !rdbMedium.isChecked() && !rdbHard.isChecked())
            difficulty = "";
        if (rdbEasy.isChecked()){
            difficulty = "easy";
        }
        if (rdbMedium.isChecked()){
            difficulty = "medium";
        }
        if (rdbHard.isChecked()){
            difficulty = "hard";
        }

        QuestionDownloader.questionList.removeAll(QuestionDownloader.questionList);
        QuestionDownloader.requestTrivia(numQuestions, category, difficulty);
        Toast.makeText(getContext(), "Setting game parameters", Toast.LENGTH_LONG).show();
        try {
            Thread.sleep(2000);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_main, new GameFragment())
                    .commit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
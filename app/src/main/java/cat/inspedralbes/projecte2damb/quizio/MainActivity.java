package cat.inspedralbes.projecte2damb.quizio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import cat.inspedralbes.projecte2damb.quizio.fragments.MenuFragment;
import cat.inspedralbes.projecte2damb.quizio.model.Question;
import cat.inspedralbes.projecte2damb.quizio.util.QuestionParser;
import cat.inspedralbes.projecte2damb.quizio.util.SoundManager;

import static cat.inspedralbes.projecte2damb.quizio.util.QuestionDownloader.requestTrivia;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main, new MenuFragment())
                .commit();
    }
}
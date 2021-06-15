package cat.inspedralbes.projecte2damb.quizio.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import cat.inspedralbes.projecte2damb.quizio.R;

public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        ImageButton imgButtonPlay = rootView.findViewById(R.id.imgbutton_menu_play);
        ImageButton imgButtonOptions = rootView.findViewById(R.id.imgbutton_menu_options);
        ImageButton imgButtonLeaderBoard = rootView.findViewById(R.id.imgbutton_menu_leaderboard);
        imgButtonPlay.setOnClickListener(this::onClick);
        imgButtonOptions.setOnClickListener(this::onClick);

        return rootView;
    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.imgbutton_menu_play:
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_main, new GameFragment())
                        .commit();
                break;
            case R.id.imgbutton_menu_options:
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_main, new OptionsFragment())
                        .commit();
                break;
        }

    }
}
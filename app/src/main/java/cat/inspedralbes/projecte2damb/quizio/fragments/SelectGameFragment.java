package cat.inspedralbes.projecte2damb.quizio.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.inspedralbes.projecte2damb.quizio.R;

public class SelectGameFragment extends Fragment {

    public SelectGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_game, container, false);
        return rootView;
    }
}
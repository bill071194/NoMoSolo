package com.example.nomosoloapp.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nomosoloapp.DBManager;
import com.example.nomosoloapp.R;
import com.example.nomosoloapp.User;
import com.example.nomosoloapp.databinding.FragmentEditProfileBinding;
import com.example.nomosoloapp.databinding.FragmentMatchProfileBinding;

public class EditProfileFragment extends Fragment {

    private DBManager dbManager;
    private FragmentEditProfileBinding binding;
    private User user;

    public EditProfileFragment(User user) {
        // Required empty public constructor
        this.user = user;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);

        loadCurrentValues();

        dbManager = new DBManager(getContext());
        dbManager.open();

        final Button saveProfileBtn = binding.saveBtn;
        saveProfileBtn.setOnClickListener(view -> {
            updateProfile();
            getUpdatedUser(user);
            ProfileFragment.loadPersonalProfile(user);
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

        return binding.getRoot();
    }

    private void loadCurrentValues(){
        TextView profileUserBioTV = binding.userBio;
        Spinner profileUserInstrumentSpinner = binding.userInstrumentSpinner;
        Spinner profileUserSkillSpinner = binding.userSkillSpinner;
        Spinner profileUserGenre1Spinner = binding.userGenre1Spinner;
        Spinner profileUserGenre2Spinner = binding.userGenre2Spinner;
        Spinner profileSeekingInstrumentSpinner = binding.seekingInstrumentSpinner;
        Spinner profileSeekingSkillSpinner = binding.seekingSkillSpinner;
        Spinner profileSeekingGenreSpinner = binding.seekingGenreSpinner;

        profileUserBioTV.setText(user.getBio());
        profileUserInstrumentSpinner.setSelection(((ArrayAdapter)profileUserInstrumentSpinner.getAdapter()).getPosition(user.getInstrument()));
        profileUserSkillSpinner.setSelection(((ArrayAdapter)profileUserSkillSpinner.getAdapter()).getPosition(user.getSkillLevel()));
        profileUserGenre1Spinner.setSelection(((ArrayAdapter)profileUserGenre1Spinner.getAdapter()).getPosition(user.getGenre1()));
        profileUserGenre2Spinner.setSelection(((ArrayAdapter)profileUserGenre2Spinner.getAdapter()).getPosition(user.getGenre2()));
        profileSeekingInstrumentSpinner.setSelection(((ArrayAdapter)profileSeekingInstrumentSpinner.getAdapter()).getPosition(user.getSeekingInstrument()));
        profileSeekingSkillSpinner.setSelection(((ArrayAdapter)profileSeekingSkillSpinner.getAdapter()).getPosition(user.getSeekingSkill()));
        profileSeekingGenreSpinner.setSelection(((ArrayAdapter)profileSeekingGenreSpinner.getAdapter()).getPosition(user.getSeekingGenre()));
    }

    private void updateProfile(){

        TextView profileUserBioTV = binding.userBio;
        Spinner profileUserInstrumentSpinner = binding.userInstrumentSpinner;
        Spinner profileUserSkillSpinner = binding.userSkillSpinner;
        Spinner profileUserGenre1Spinner = binding.userGenre1Spinner;
        Spinner profileUserGenre2Spinner = binding.userGenre2Spinner;
        Spinner profileSeekingInstrumentSpinner = binding.seekingInstrumentSpinner;
        Spinner profileSeekingSkillSpinner = binding.seekingSkillSpinner;
        Spinner profileSeekingGenreSpinner = binding.seekingGenreSpinner;

        String userBio = profileUserBioTV.getText().toString();
        String userInstrument = profileUserInstrumentSpinner.getSelectedItem().toString();
        String userSkill = profileUserSkillSpinner.getSelectedItem().toString();
        String userGenre1 = profileUserGenre1Spinner.getSelectedItem().toString();
        String userGenre2 = profileUserGenre2Spinner.getSelectedItem().toString();
        String seekingInstrument = profileSeekingInstrumentSpinner.getSelectedItem().toString();
        String seekingSkill = profileSeekingSkillSpinner.getSelectedItem().toString();
        String seekingGenre = profileSeekingGenreSpinner.getSelectedItem().toString();

        dbManager.updateProfile(user.getId(), userBio, userInstrument, userSkill, userGenre1, userGenre2, seekingInstrument, seekingSkill, seekingGenre);
    }

    private User getUpdatedUser(User oldUser){
        user = dbManager.getUserProfile(oldUser.getId());
        if (user == null){
            Toast.makeText(getActivity(), "Error loading user profile.", Toast.LENGTH_SHORT).show();
        }
        return user;
    }
}
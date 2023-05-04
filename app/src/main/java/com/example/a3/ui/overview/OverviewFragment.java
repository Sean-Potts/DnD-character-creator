/*
File: OverviewFragment.java
Project: MAD-A3
Programmer: Jacob, Isaiah Bartlett
Date: 3/14/2023
Description: an overview fragment for the character
 */


package com.example.a3.ui.overview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.content.CursorLoader;

import com.example.a3.CharacterInfo;
import com.example.a3.databinding.FragmentOverviewBinding;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OverviewFragment extends Fragment {

    private OverviewViewModel overviewViewModel;
    FragmentOverviewBinding overviewBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        overviewViewModel = ViewModelProviders.of(this).get(OverviewViewModel.class);
        overviewBinding = FragmentOverviewBinding.inflate(inflater);
        View root = overviewBinding.getRoot();


        List<String> classNames = Stream.of(CharacterInfo.Classes.values()).map(Enum::name).collect(Collectors.toList());
        List<String> raceNames = Stream.of(CharacterInfo.Races.values()).map(Enum::name).collect(Collectors.toList());
        List<String> backgroundNames = Stream.of(CharacterInfo.Backgrounds.values()).map(Enum::name).collect(Collectors.toList());

        final TextView nameVal = overviewBinding.characterNameVal;
        nameVal.setText(CharacterInfo.CurrentCharacter.getCharacterName());

        final TextView classVal = overviewBinding.characterClassVal;
        classVal.setText(classNames.get(CharacterInfo.CurrentCharacter.getCharacterClass()));

        final TextView raceVal = overviewBinding.characterRaceVal;
        raceVal.setText(raceNames.get(CharacterInfo.CurrentCharacter.getRace()));

        final TextView backgroundVal = overviewBinding.characterBackgroundVal;
        backgroundVal.setText(backgroundNames.get(CharacterInfo.CurrentCharacter.getBackground()));


        return root;
    }
}
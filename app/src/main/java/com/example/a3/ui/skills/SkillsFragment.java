/*
File: BaseFragment.java
Project: MAD-A2
Programmer: Isaiah Bartlett
Date: 3/14/2023
Description: the fragment for selecting the skills of your character
 */


package com.example.a3.ui.skills;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.a3.CharacterInfo;
import com.example.a3.databinding.FragmentSkillsBinding;
import com.example.a3.ui.appWidget.AppWidget;

public class SkillsFragment extends Fragment {
    private SkillsViewModel skillsViewModel;
    FragmentSkillsBinding skillsBinding;

    //Strength
    CheckBox athletics = null;

    //Dexterity
    CheckBox Acrobatics = null;
    CheckBox sleight_of_hand = null;
    CheckBox stealth = null;

    //Intelligence
    CheckBox arcana = null;
    CheckBox history = null;
    CheckBox investigation = null;
    CheckBox nature = null;
    CheckBox religion = null;

    //Wisdom
    CheckBox animal_handling = null;
    CheckBox insight = null;
    CheckBox medicine = null;
    CheckBox perception = null;
    CheckBox survival = null;

    //Charisma
    CheckBox deception = null;
    CheckBox intimidation = null;
    CheckBox performance = null;
    CheckBox persuasion = null;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        skillsViewModel = ViewModelProviders.of(this).get(SkillsViewModel.class);
        skillsBinding = FragmentSkillsBinding.inflate(inflater);
        View root = skillsBinding.getRoot();


        // connecting the checkboxes to the character
        skillsBinding.athleticsCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Athletics.ordinal(), b);
            }
        });
        skillsBinding.acrobaticsCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Acrobatics.ordinal(), b);
            }
        });
        skillsBinding.sleightOfHandCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Sleight_of_Hand.ordinal(), b);
            }
        });
        skillsBinding.stealthCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Stealth.ordinal(), b);
            }
        });
        skillsBinding.arcanaCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Arcana.ordinal(), b);
            }
        });
        skillsBinding.historyCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.History.ordinal(), b);
            }
        });
        skillsBinding.investigationCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Investigation.ordinal(), b);
            }
        });
        skillsBinding.natureCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Nature.ordinal(), b);
            }
        });
        skillsBinding.religionCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Religion.ordinal(), b);
            }
        });
        skillsBinding.animalHandlingCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Animal_Handling.ordinal(), b);
            }
        });
        skillsBinding.insightCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Insight.ordinal(), b);
            }
        });
        skillsBinding.medicineCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Medicine.ordinal(), b);
            }
        });
        skillsBinding.perceptionCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Perception.ordinal(), b);
            }
        });
        skillsBinding.survivalCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Survival.ordinal(), b);
            }
        });
        skillsBinding.deceptionCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Deception.ordinal(), b);
            }
        });
        skillsBinding.intimidationCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Intimidation.ordinal(), b);
            }
        });
        skillsBinding.performanceCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Performance.ordinal(), b);
            }
        });
        skillsBinding.persuasionCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
                CharacterInfo.CurrentCharacter.setSkillProficiency(CharacterInfo.SkillsList.Persuasion.ordinal(), b);
            }
        });


        return root;
    }
}
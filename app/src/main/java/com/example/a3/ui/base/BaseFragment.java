/*
File: BaseFragment.java
Project: MAD-A2
Programmer: Isaiah Bartlett
Date: 3/14/2023
Description: the fragment for selecting the base elements of your character
 */


package com.example.a3.ui.base;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Spinner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.a3.CharacterInfo;
import com.example.a3.R;
import com.example.a3.databinding.FragmentBaseBinding;
import com.example.a3.databinding.FragmentStatsBinding;
import com.example.a3.ui.appWidget.AppWidget;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseFragment extends Fragment {

    private BaseViewModel baseViewModel;

    EditText name = null;

    Spinner classSpinner = null;
    Spinner raceSpinner = null;
    Spinner backgroundSpinner = null;

    ArrayList<String> classList = new ArrayList<String>();
    ArrayAdapter<String> classAdapter = null;

    ArrayList<String> raceList = new ArrayList<String>();
    ArrayAdapter<String> raceAdapter = null;

    ArrayList<String> backgroundList = new ArrayList<String>();
    ArrayAdapter<String> backgroundAdapter = null;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        FragmentBaseBinding baseBinding = FragmentBaseBinding.inflate(inflater);
        View root = baseBinding.getRoot();


        // name
        name = baseBinding.nameVal;
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CharacterInfo.CurrentCharacter.setCharacterName(charSequence.toString());
                CharacterInfo.CurrentCharacter.isSaved = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }
        });

        // class
        classAdapter = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item,
                Stream.of(CharacterInfo.Classes.values()).map(Enum::name).collect(Collectors.toList()));
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner = baseBinding.baseClassVal;
        classSpinner.setAdapter(classAdapter);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo.CurrentCharacter.setCharacterClass(i);
                CharacterInfo.CurrentCharacter.isSaved = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //race
        raceAdapter = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item,
                Stream.of(CharacterInfo.Races.values()).map(Enum::name).collect(Collectors.toList()));
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner = baseBinding.baseRaceVal;
        raceSpinner.setAdapter(raceAdapter);
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo.CurrentCharacter.setRace(i);
                CharacterInfo.CurrentCharacter.isSaved = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //background
        backgroundAdapter = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item,
                Stream.of(CharacterInfo.Backgrounds.values()).map(Enum::name).collect(Collectors.toList()));
        backgroundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner = baseBinding.baseBackgroundVal;
        backgroundSpinner.setAdapter(backgroundAdapter);
        backgroundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo.CurrentCharacter.setBackground(i);
                CharacterInfo.CurrentCharacter.isSaved = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // load character icon if it exists
        if (CharacterInfo.CurrentCharacter.getImageUri() != null) {
            try {
                InputStream imageStream = getContext().getContentResolver().openInputStream(CharacterInfo.CurrentCharacter.getImageUri());
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                baseBinding.characterIcon.setImageBitmap(selectedImage);
            }
            catch (Exception e) {
                Log.d("Error", "unable to load image");
            }
        }

        // call for setting a new image
        ActivityResultLauncher<Intent> pickerResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            try {
                                Intent data = result.getData();
                                Uri imageUri = data.getData();
                                InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                                baseBinding.characterIcon.setImageBitmap(selectedImage);

                                CharacterInfo.CurrentCharacter.setImageUri(imageUri);
                            }
                            catch (Exception e) {
                                Log.d("Error", "unable to load image");
                            }
                        }
                    }
                });
        baseBinding.iconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                );
                pickerResultLauncher.launch(intent);
            }


        });

        return root;
    }

}
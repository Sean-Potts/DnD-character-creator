package com.example.a3.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.a3.CharacterInfo;
import com.example.a3.databinding.FragmentStatsBinding;
import com.example.a3.ui.appWidget.AppWidget;

import java.util.ArrayList;
import java.util.Random;

public class StatsFragment extends Fragment {
    private StatsViewModel statsViewModel;

    FragmentStatsBinding statsBinding;

    Spinner strBnsSpinner = null;
    Spinner dexBnsSpinner = null;
    Spinner conBnsSpinner = null;
    Spinner intBnsSpinner = null;
    Spinner wisBnsSpinner = null;
    Spinner chaBnsSpinner = null;

    Spinner strBaseSpinner = null;
    Spinner dexBaseSpinner = null;
    Spinner conBaseSpinner = null;
    Spinner intBaseSpinner = null;
    Spinner wisBaseSpinner = null;
    Spinner chaBaseSpinner = null;

    Button btnRoll = null;
    TextView statsTextView = null;

    ArrayList<String> BonusList = new ArrayList<String>();
    ArrayAdapter<String> bnsAdapter = null;

    ArrayList<String> baseList = new ArrayList<String>();
    ArrayAdapter<String> baseAdapter = null;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        statsViewModel = ViewModelProviders.of(this).get(StatsViewModel.class);
        statsBinding = FragmentStatsBinding.inflate(inflater);
        View root = statsBinding.getRoot();

        // the options for each stat bonus
        BonusList.add("---");
        BonusList.add("+1");
        BonusList.add("+2");

        // every bonus stat spinner will have the same options so we can reuse the same adapter
        bnsAdapter = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item, BonusList);
        bnsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        strBnsSpinner = statsBinding.statStrBns;
        strBnsSpinner.setAdapter(bnsAdapter);
        strBnsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo.CurrentCharacter.setBonusStrength(i);
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dexBnsSpinner = statsBinding.statDexBns;
        dexBnsSpinner.setAdapter(bnsAdapter);
        dexBnsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo.CurrentCharacter.setBonusDexterity(i);
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        conBnsSpinner = statsBinding.statConBns;
        conBnsSpinner.setAdapter(bnsAdapter);
        conBnsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo.CurrentCharacter.setBonusConstitution(i);
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        intBnsSpinner = statsBinding.statIntBns;
        intBnsSpinner.setAdapter(bnsAdapter);
        intBnsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo.CurrentCharacter.setBonusIntelligence(i);
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        wisBnsSpinner = statsBinding.statWisBns;
        wisBnsSpinner.setAdapter(bnsAdapter);
        wisBnsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo.CurrentCharacter.setBonusWisdom(i);
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        chaBnsSpinner = statsBinding.statChaBns;
        chaBnsSpinner.setAdapter(bnsAdapter);
        chaBnsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo.CurrentCharacter.setBonusCharisma(i);
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        baseList.add("---");
        for (int i :CharacterInfo.CurrentCharacter.getStatsList()) {
            baseList.add(String.valueOf(i));
        }

        baseAdapter = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item, baseList);
        baseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        strBaseSpinner = statsBinding.statStrVal;
        strBaseSpinner.setAdapter(baseAdapter);
        strBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo character = CharacterInfo.CurrentCharacter; // text was getting too long
                if (i == 0) {
                    character.setBaseStrength(0);
                }
                else {
                    character.setBaseStrength(character.getStatsList().get(i-1));
                }
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dexBaseSpinner = statsBinding.statDexVal;
        dexBaseSpinner.setAdapter(baseAdapter);
        dexBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo character = CharacterInfo.CurrentCharacter;
                if (i == 0) {
                    character.setBaseDexterity(0);
                }
                else {
                    character.setBaseDexterity(character.getStatsList().get(i-1));
                }
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        conBaseSpinner = statsBinding.statConVal;
        conBaseSpinner.setAdapter(baseAdapter);
        conBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo character = CharacterInfo.CurrentCharacter;
                if (i == 0) {
                    character.setBaseConstitution(0);
                }
                else {
                    character.setBaseConstitution(character.getStatsList().get(i-1));
                }
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        intBaseSpinner = statsBinding.statIntVal;
        intBaseSpinner.setAdapter(baseAdapter);
        intBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo character = CharacterInfo.CurrentCharacter;
                if (i == 0) {
                    character.setBaseIntelligence(0);
                }
                else {
                    character.setBaseIntelligence(character.getStatsList().get(i-1));
                }
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        wisBaseSpinner = statsBinding.statWisVal;
        wisBaseSpinner.setAdapter(baseAdapter);
        wisBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo character = CharacterInfo.CurrentCharacter;
                if (i == 0) {
                    character.setBaseWisdom(0);
                }
                else {
                    character.setBaseWisdom(character.getStatsList().get(i-1));
                }
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        chaBaseSpinner = statsBinding.statChaVal;
        chaBaseSpinner.setAdapter(baseAdapter);
        chaBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterInfo character = CharacterInfo.CurrentCharacter;
                if (i == 0) {
                    character.setBaseCharisma(0);
                }
                else {
                    character.setBaseCharisma(character.getStatsList().get(i-1));
                }
                CharacterInfo.CurrentCharacter.isSaved = false;
                AppWidget.updateAll(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        // in dnd to generate stats you roll 4 6-sided dice (d6) and drop the lowest roll
        // repeat 6 times as there are 6 different stats
        statsTextView = statsBinding.statsTextView;
        btnRoll = statsBinding.btnRoll;
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                ArrayList<Integer> newStats = new ArrayList<Integer>();
                Random rand = new Random();

                for (int i = 0; i < 6; i++) {
                    int total = 0;
                    int lowest = 0;

                    for (int j = 0; j < 4; j++) {
                        int roll = rand.nextInt(6)+1;

                        if (j == 0) {
                            lowest = roll;
                        }
                        else if (lowest > roll) {
                            total += lowest;
                            lowest = roll;
                        }
                        else {
                            total += roll;
                        }
                    }

                    newStats.add(total);
                }

                CharacterInfo.CurrentCharacter.setStatsList(newStats);
                UpdateStatsTextList();

                // reset the selectors
                strBaseSpinner.setSelection(0);
                dexBaseSpinner.setSelection(0);
                conBaseSpinner.setSelection(0);
                intBaseSpinner.setSelection(0);
                wisBaseSpinner.setSelection(0);
                chaBaseSpinner.setSelection(0);
            }
        });
        return root;
    }


    void UpdateStatsTextList() {
        ArrayList<Integer> stats = CharacterInfo.CurrentCharacter.getStatsList();
        CharacterInfo.CurrentCharacter.isSaved = false;
        AppWidget.updateAll(getContext());

        String statsString = "";
        for (int i = 0; i < stats.size(); i++) {
            statsString += String.valueOf(stats.get(i));
            if (i != stats.size()-1) {
                statsString += ", ";
            }
        }
        statsBinding.statsTextView.setText(statsString);

        baseList.clear();
        baseList.add("---");
        for (int i :CharacterInfo.CurrentCharacter.getStatsList()) {
            baseList.add(String.valueOf(i));
        }
        baseAdapter.notifyDataSetChanged();
    }
}

/*
class RollThread extends AsyncTask<Void, Void, ArrayList> {
    @Override
    protected  void onPreExecute() {

    }

    protected ArrayList doInBackground(Void... args) { // it wont build if there is no argument, why....
        ArrayList<Integer> stats = new ArrayList<Integer>();
        Random rand = new Random();

        // in dnd to generate stats you roll 4 6-sided dice (d6) and drop the lowest roll
        // repeat 6 times as there are 6 different stats
        for (int i = 0; i < 6; i++) {
            int total = 0;
            int lowest = 0;

            for (int j = 0; j < 4; j++) {
                int roll = rand.nextInt(6)+1;

                if (j == 0) {
                    lowest = roll;
                }
                else if (lowest > roll) {
                    total += lowest;
                    lowest = roll;
                }
                else {
                    total += roll;
                }
            }

            stats.add(total);
        }

        return stats;
    }

    @Override
    protected void onPostExecute(ArrayList results) {
        CharacterInfo.CurrentCharacter.SetStatsList(results);

    }
}

 */
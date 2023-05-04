/*
File: BaseFragment.java
Project: MAD-A2
Programmer: Isaiah Bartlett
Date: 3/14/2023
Description: the fragment for saving, deleting, exporting, and loading your characters
 */

package com.example.a3.ui.save_load;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.content.ContentResolver;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3.CharacterInfo;
import com.example.a3.R;
import com.example.a3.database.CharacterDB;

import com.example.a3.database.DataAccessor;
import com.example.a3.database.DataContentProvider;
import com.example.a3.databinding.FragmentSaveLoadBinding;
import com.example.a3.tasks.CharacterFileParserTask;
import com.example.a3.ui.appWidget.AppWidget;


import java.util.List;


/*
Name: SaveLoadFragment
Purpose: This class populates the fragment_save_load. This class uses a recycle view and populates it with
         names from the database. This allows for selection of these names from the recycle view in order to interact with it.
         The names can be deleted or leaded when it is clicked on. If save is used the current character is saved to the database.
 */
public class SaveLoadFragment extends Fragment {

    private SaveLoadViewModel saveLoadViewModel;
    private RecyclerView DBRecyclerView = null;

    private Button saveBtn = null;
    private Button exportBtn = null;
    private Button loadBtn = null;
    private Button deleteBtn = null;

    private TextView selectedText = null;

    private List<String> CharacterNameList = null;
    private String selectedName = "";


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        saveLoadViewModel = ViewModelProviders.of(this).get(SaveLoadViewModel.class);
        FragmentSaveLoadBinding saveLoadBinding = FragmentSaveLoadBinding.inflate(inflater);
        View root = saveLoadBinding.getRoot();

        DBRecyclerView = saveLoadBinding.DBRecyclerView;
        DBRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CharacterDB db = new CharacterDB(getContext());
        super.onCreate(savedInstanceState);

        // using content provider to get cursor and then populate CharacterNameList
        Uri charactersUri = Uri.parse("content://" + DataContentProvider.AUTHORITY + "/characters");
        Cursor cursor = getContext().getContentResolver().query(charactersUri,null,null,null,null);

        // Checks if the cursor is null In case the query failed!
        if(cursor != null)
        {
            CharacterNameList = db.getCharacterNames(cursor);
        }
        else
        {
            // Non content provider method for getting names
            CharacterNameList = db.getCharacterNames();
        }

        DBRecyclerView.setAdapter(new ListAdapter());

        selectedText = saveLoadBinding.selectedText;
        selectedText.setText("Current Character: " + CharacterInfo.CurrentCharacter.getCharacterName() + CharacterInfo.CurrentCharacter.savedStatus());


        saveBtn = saveLoadBinding.saveBtn;
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save button get current character and saves them
                CharacterInfo.CurrentCharacter.isSaved = true;
                selectedText.setText("Current Character: " + CharacterInfo.CurrentCharacter.getCharacterName() + CharacterInfo.CurrentCharacter.savedStatus());
                AppWidget.updateAll(getContext());

                Uri charactersUri = Uri.parse("content://" + DataContentProvider.AUTHORITY + "/characters");

                // Creates a ContentValues object for the content provider
                ContentValues valuesc = new ContentValues();

                // Messy but adds the current character info into the ContentValues to be put into db with content provider
                valuesc.put("id", "0");
                valuesc.put("name", CharacterInfo.CurrentCharacter.getCharacterName());
                valuesc.put("class", CharacterInfo.CurrentCharacter.getCharacterClass());

                valuesc.put("race", String.valueOf(CharacterInfo.CurrentCharacter.getRace()));
                valuesc.put("background", String.valueOf(CharacterInfo.CurrentCharacter.getBackground()));

                valuesc.put("base_strength",String.valueOf(CharacterInfo.CurrentCharacter.getBaseStrength()));
                valuesc.put("base_dexterity", String.valueOf(CharacterInfo.CurrentCharacter.getBaseDexterity()));
                valuesc.put("base_constitution", String.valueOf(CharacterInfo.CurrentCharacter.getBaseConstitution()));
                valuesc.put("base_intelligence", String.valueOf(CharacterInfo.CurrentCharacter.getBaseIntelligence()));
                valuesc.put("base_wisdom", String.valueOf(CharacterInfo.CurrentCharacter.getBaseWisdom()));
                valuesc.put("base_charisma", String.valueOf(CharacterInfo.CurrentCharacter.getBaseCharisma()));
                valuesc.put("bonus_strength", String.valueOf(CharacterInfo.CurrentCharacter.getBonusStrength()));
                valuesc.put("bonus_dexterity",String.valueOf(CharacterInfo.CurrentCharacter.getBonusDexterity()));
                valuesc.put("bonus_constitution",String.valueOf(CharacterInfo.CurrentCharacter.getBonusConstitution()));
                valuesc.put("bonus_intelligence",String.valueOf(CharacterInfo.CurrentCharacter.getBonusIntelligence()));
                valuesc.put("bonus_wisdom",String.valueOf(CharacterInfo.CurrentCharacter.getBonusWisdom()));
                valuesc.put("bonus_charisma",String.valueOf(CharacterInfo.CurrentCharacter.getBonusCharisma()));


                Uri num  =   getContext().getContentResolver().insert(charactersUri, valuesc);

                // adds character name to recycle view re-use for other buttons
                if(CharacterNameList.contains(CharacterInfo.CurrentCharacter.getCharacterName()) == false)
                {
                    CharacterNameList.add(CharacterNameList.size(), CharacterInfo.CurrentCharacter.getCharacterName());
                    DBRecyclerView.getAdapter().notifyItemInserted(CharacterNameList.size());
                }
            }
        });

        exportBtn = saveLoadBinding.exportBtn;
        exportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppWidget.updateAll(getContext());
                selectedText.setText("Current Character: " + CharacterInfo.CurrentCharacter.getCharacterName() + CharacterInfo.CurrentCharacter.savedStatus());
                CharacterFileParserTask task = new CharacterFileParserTask();
                task.context = getContext();
                task.execute(CharacterInfo.CurrentCharacter);
            }
        });

        selectedText = saveLoadBinding.selectedText;

        return root;
    }



    private class ListItemHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView itemText;
        private TextView itemDialogText;

        private TextView myTextView = null;
        private  CharacterDB db = new CharacterDB(getContext());


        public ListItemHolder(View view) {
            super(view);
            this.itemView = view;
            itemText = itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedName = itemText.getText().toString();
                    selectedText.setText("Current Character: " + selectedName + CharacterInfo.CurrentCharacter.savedStatus());
                    showCustomDialog();
                }
            });
        }


        void showCustomDialog() {

            final Dialog dialog = new Dialog(getActivity());

            // no title for dialog box
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            // Allows for you to click out of the dialog box
            dialog.setCancelable(true);

            dialog.setContentView(R.layout.dialog_save_load);

            // set buttons and text
            Button deleteButton = dialog.findViewById(R.id.dialog_delete);
            Button loadButton = dialog.findViewById(R.id.dialog_load);
            Button cancelButton = dialog.findViewById(R.id.dialog_cancel);
            itemDialogText = dialog.findViewById(R.id.characterdialog);
            itemDialogText.setText("Selected Character: " + selectedName);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // reset selected character
                    selectedText.setText("Current Character: ");

                    // deletes a character
                    CharacterInfo.CurrentCharacter.isSaved = false;
                    Uri charactersUri = Uri.parse("content://" + DataContentProvider.AUTHORITY + "/characters");

                    int num  =   getContext().getContentResolver().delete(charactersUri, selectedName, null);


                    // adds character name to recycle view re-use for other buttons
                    int indexOfRemovedCharacter = CharacterNameList.indexOf(selectedName);
                    CharacterNameList.remove(indexOfRemovedCharacter);
                    DBRecyclerView.getAdapter().notifyItemRemoved(indexOfRemovedCharacter);
                    dialog.dismiss();

                }
            });
            loadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataAccessor dataAccessor = new DataAccessor(db);
                    CharacterInfo.CurrentCharacter = new CharacterInfo(dataAccessor.getCharacter(selectedName));
                    dialog.dismiss();
                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });

            dialog.show();
        }
        public TextView getItemText() {
            return itemText;
        }
        public void setItemText(TextView itemText) {
            this.itemText = itemText;
        }
    }


    private class ListAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.db_list_item, parent, false);
            return new ListItemHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            String name = CharacterNameList.get(position);
            ((ListItemHolder)holder).getItemText().setText(name);
        }

        @Override
        public int getItemCount() { return CharacterNameList.size(); }
    }
}


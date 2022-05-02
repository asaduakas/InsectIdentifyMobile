package com.example.insectidentify.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.insectidentify.InsectPagerActivity;
import com.example.insectidentify.R;
import com.example.insectidentify.databinding.FragmentInsectPagerBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_INSECT = "saved_insect";
    private static final String ARG_NUM_OF_INSECTS = "number_of_insects";

    private PageViewModel pageViewModel;
    private FragmentInsectPagerBinding binding;
    Button subBtn;
    CheckBox readyBox;
    ImageButton addBtn;
    ImageButton minBtn;
    TextView counter;
    Button addTabBtn;
    TextView batchName;
    String insect;
    Integer numOfInsects;
    TextInputEditText taxonName;
    TextInputEditText noteField;
    AutoCompleteTextView textView;

    public static PlaceholderFragment newInstance(int index, int num, String insect) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putString(ARG_INSECT, insect);
        bundle.putInt(ARG_NUM_OF_INSECTS, num);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            insect = getArguments().getString(ARG_INSECT);
            numOfInsects = getArguments().getInt(ARG_NUM_OF_INSECTS);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentInsectPagerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {

        taxonName = getView().findViewById(R.id.taxName);
        noteField = getView().findViewById(R.id.notes);

        //Batch Name
        batchName = getView().findViewById(R.id.bName);
        InsectPagerActivity act = (InsectPagerActivity) getActivity();
        act.setBatchName(batchName);

        //READY CHECKBOX
        readyBox = getView().findViewById(R.id.checkBox);
        readyBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    subBtn.setEnabled(true);

                } else {
                    subBtn.setEnabled(false);
                }
            }
        });

        //SUBMIT BUTTON
        subBtn = getView().findViewById(R.id.submit);
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsectPagerActivity act = (InsectPagerActivity) getActivity();
                act.onClickSub();
            }
        });

        //PLUS AND MINUS BUTTONS
        addBtn = getView().findViewById(R.id.add);
        minBtn = getView().findViewById(R.id.subtract);
        counter = getView().findViewById(R.id.counter);
        counter.setText(numOfInsects.toString());
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentCount = counter.getText().toString();
                int temp = Integer.parseInt(currentCount);
                temp++;
                String addedCount = Integer.toString(temp);
                counter.setText(addedCount);
            }
        });
        minBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentCount = counter.getText().toString();
                int temp = Integer.parseInt(currentCount);
                if(temp>0){
                    temp--;
                }
                String subtractedCount = Integer.toString(temp);
                counter.setText(subtractedCount);
            }
        });

        //DROPDOWN
        String[] type = getResources().getStringArray(R.array.orders);
        List<String> nameList = new ArrayList<>(Arrays.asList(type));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(), R.layout.dropdown_menu_item, type);
        textView = getView().findViewById(R.id.orderDropdown);
        textView.setAdapter(adapter);
        if(insect!=null){
            textView.setText(insect);
        }

        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        public void onFocusChange(View view, boolean hasFocus) {
            if(!hasFocus){
                if(!nameList.contains(textView.getText().toString())){
                        textView.setText("");
                        Toast.makeText(getView().getContext(), "Please choose an appropriate insect order!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        });

        //ADD TAB BUTTON
        addTabBtn = getView().findViewById(R.id.addTab);
        addTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsectPagerActivity act = (InsectPagerActivity) getActivity();
                act.onClickAddTab();
            }
        });
    }

    public String getCounter(){
        return this.counter.getText().toString();
    }
    public String getTaxName(){
        return this.taxonName.getText().toString();
    }

    public String getInsectOrder(){
        return this.textView.getText().toString();
    }

    public String getNote(){
        return  this.noteField.getText().toString();
    }

}
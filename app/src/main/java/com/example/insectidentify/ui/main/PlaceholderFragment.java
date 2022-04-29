package com.example.insectidentify.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.insectidentify.InsectPagerActivity;
import com.example.insectidentify.R;
import com.example.insectidentify.databinding.FragmentInsectPagerBinding;

/**
 * A placeholder fragment containing a simple view.
 */

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentInsectPagerBinding binding;
    Button subBtn;
    CheckBox readyBox;
    ImageButton addBtn;
    ImageButton minBtn;
    TextView counter;
    Button addTabBtn;
    TextView batchName;

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
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getView().getContext(), R.layout.dropdown_menu_item, type);

        AutoCompleteTextView editTextFilledExposedDropdown =
                getView().findViewById(R.id.orderDropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);

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
}
package com.example.insectidentify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class pagerFragment extends Fragment {

    private static final String ARG_COUNT = "count";
    private String mCount;
    Button addTabBtn;
    Button subBtn;
    CheckBox readyBox;
    ImageButton addBtn;
    ImageButton minBtn;
    TextView counter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCount = getArguments().getString(ARG_COUNT);
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param Parameter 1.
     * @return A new instance of fragment pagerFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static pagerFragment newInstance(String param) {
        pagerFragment fragment = new pagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COUNT, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        addTabBtn = getView().findViewById(R.id.addTab);
        subBtn = getView().findViewById(R.id.submit);
        readyBox = getView().findViewById(R.id.checkBox);
        readyBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    subBtn.setEnabled(true);

                }else{
                    subBtn.setEnabled(false);
                }

            }
        });

        addTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityFragmentViewPager act = (ActivityFragmentViewPager) getActivity();
                act.onClickAddTab();
            }
        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityFragmentViewPager act = (ActivityFragmentViewPager) getActivity();
                act.onClickSub();
            }
        });

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

        String[] type = getResources().getStringArray(R.array.orders);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getView().getContext(), R.layout.dropdown_menu_item, type);

        AutoCompleteTextView editTextFilledExposedDropdown =
                getView().findViewById(R.id.orderDropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
    }

}
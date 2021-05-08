package com.example.cardtoolkit.CardDisplay.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cardtoolkit.R;

public class MyDialogFragment extends DialogFragment {

    private Button mAddBrandButton;
    private EditText mEditBrandName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_add_brand,container,false);
        mAddBrandButton = v.findViewById(R.id.save_button);
        mEditBrandName = v.findViewById(R.id.edit_brand_name);
        mAddBrandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brandName = mEditBrandName.getText().toString();
                if (brandName.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
                else{
//                    FIrebase
                }
            }
        });
        return v;
    }

}

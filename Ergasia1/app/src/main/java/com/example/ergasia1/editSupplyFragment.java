package com.example.ergasia1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class editSupplyFragment extends Fragment {
    EditText editText1, editText2, editText3, editText4;
    Button button;
    public editSupplyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_supply, container, false);
        editText1 = view.findViewById(R.id.editTextEditSupCID);
        editText2 = view.findViewById(R.id.editTextEditSupPID);
        editText3 = view.findViewById(R.id.editTextEditSupDate);
        editText4 = view.findViewById(R.id.editTextEditSupAmmount);
        button = view.findViewById(R.id.btnEditSup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_compId = 0;
                try {
                    Var_compId = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }int Var_prodId = 0;
                try {
                    Var_prodId = Integer.parseInt(editText2.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                int Var_ammount = 0;
                try {
                    Var_ammount = Integer.parseInt(editText4.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_date = editText3.getText().toString();
                try {
                    Supply supply = new Supply();
                    supply.setCid(Var_compId);
                    supply.setPid(Var_prodId);
                    supply.setSupply_day(Var_date);
                    supply.setSupply_ammount(Var_ammount);
                    MainActivity.myAppDatabase.myDao().editSupply(supply);
                    Toast.makeText(getActivity(),"Supply updated.",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
            }
        });
        return view;
    }
}
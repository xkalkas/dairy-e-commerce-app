package com.example.ergasia1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class addCompanyFragment extends Fragment {
    EditText editText1, editText2;
    Button button;
    public addCompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_company, container, false);
        editText1 = view.findViewById(R.id.editTextAddComp);
        editText2 = view.findViewById(R.id.editTextAddCompName);
        button =view.findViewById(R.id.btnAddComp);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int Var_compId = 0;
                try{
                    Var_compId = Integer.parseInt(editText1.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("Could not parse " + ex);
                }
                String Var_compName = editText2.getText().toString();
                try{
                    Company comp = new Company();
                    comp.setId(Var_compId);
                    comp.setName(Var_compName);
                    MainActivity.myAppDatabase.myDao().addCompany(comp);
                    Toast.makeText(getActivity(), "Company Added", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }
                editText1.setText("");
                editText2.setText("");
            }
        });
        return view;
    }
}
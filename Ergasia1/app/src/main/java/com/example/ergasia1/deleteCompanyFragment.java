package com.example.ergasia1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link deleteCompanyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class deleteCompanyFragment extends Fragment {
    EditText editText;
    Button button;
    public deleteCompanyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_company, container, false);
        editText = view.findViewById(R.id.editTextDelComp);
        button = view.findViewById(R.id.btnDelComp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_id = 0;
                try {
                    Var_id = Integer.parseInt(editText.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                Company comp = new Company();
                comp.setId(Var_id);
                MainActivity.myAppDatabase.myDao().deleteCompany(comp);
                Toast.makeText(getActivity(),"Company deleted ",Toast.LENGTH_LONG).show();
                editText.setText("");
            }
        });
        return view;
    }
}
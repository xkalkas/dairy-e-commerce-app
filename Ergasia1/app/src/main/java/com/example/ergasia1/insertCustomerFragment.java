package com.example.ergasia1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

public class insertCustomerFragment extends Fragment {
    EditText editText1, editText2, editText3;
    Button bn;


    public insertCustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_customer, container, false);
        editText1 = view.findViewById(R.id.editTextC1);
        editText2 = view.findViewById(R.id.editTextC2);
        editText3 = view.findViewById(R.id.editTextC3);
        bn = view.findViewById(R.id.AddCustomerBtn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_customerid = 0;
                try {
                    Var_customerid = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_customername = editText2.getText().toString();
                int Var_customerzip = 0;
                try {
                    Var_customerzip = Integer.parseInt(editText3.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                try {
                    FsCustomer customerX = new FsCustomer();
                    customerX.setCustomerId(Var_customerid);
                    customerX.setCustomerName(Var_customername);
                    customerX.setZip(Var_customerzip);


                    MainActivity.db.
                            collection("CustomerData").
                            document("" + Var_customerid).
                            set(customerX).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity(), "Customer added.", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "add operation failed.", Toast.LENGTH_LONG).show();
                                }
                            });
                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
            }
        });
        return view;
    }
}
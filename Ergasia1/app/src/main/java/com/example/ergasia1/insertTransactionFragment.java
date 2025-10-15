package com.example.ergasia1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;


public class insertTransactionFragment extends Fragment {
    EditText editText1,editText2,editText3,editText4,editText5;
    Button bn;
    boolean keyVolume = false;
    boolean keyId = false;
    public insertTransactionFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_transaction, container, false);
        editText1 = view.findViewById(R.id.editText12);
        editText2 = view.findViewById(R.id.editText22);
        editText3 = view.findViewById(R.id.editText32);
        editText4 = view.findViewById(R.id.editText42);
        editText5 = view.findViewById(R.id.editText52);
        bn = view.findViewById(R.id.AddTransactionContentsBtn);
        bn.setOnClickListener(new View.OnClickListener()    {
            @Override
            public void onClick(View v) {
                String Var_cid = editText1.getText().toString();
                DocumentReference Var_customerid = MainActivity.db.document("/CustomerData/" + Var_cid);
                int Var_transactionid = 0;
                try {
                    Var_transactionid = Integer.parseInt(editText2.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_date = editText3.getText().toString();
                int Var_volume = 0;
                try {
                    Var_volume = Integer.parseInt(editText5.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                int Var_productid = 0;
                try {
                    Var_productid = Integer.parseInt(editText4.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }


                List<Integer> ConnectionCheck = MainActivity.myAppDatabase.myDao().getSupply();
                for (Integer i: ConnectionCheck) {
                    if(i.equals(Var_productid)){
                        Integer TotalAmmountCheck = MainActivity.myAppDatabase.myDao().getSupplyTotalAmmount(Var_productid);
                        if(TotalAmmountCheck>= Var_volume){
                            try {
                                FsTransaction TI = new FsTransaction();
                                TI.setCustomerId(Var_customerid);
                                TI.setTransactionId(Var_transactionid);
                                TI.setDate(Var_date);
                                TI.setVolume(Var_volume);
                                TI.setPid(Var_productid);

                                MainActivity.db.
                                        collection("TransactionInfo").
                                        add(TI).
                                        addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(getActivity(),"Transaction Info added.", Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getActivity(),"add operation failed.",Toast.LENGTH_LONG).show();
                                            }
                                        }); } catch (Exception e) {
                                String message = e.getMessage();
                            }
                        }else{
                            keyVolume = true;
                        }
                    }else {
                        keyId = true;
                    }
                }
                if(keyVolume == true){
                    Toast.makeText(getActivity(),"Volume too great",Toast.LENGTH_LONG).show();
                } else if (keyId) {
                    Toast.makeText(getActivity(),"Product doesn't exist",Toast.LENGTH_LONG).show();
                }




                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText5.setText("");
            }
        });
        return view;
    }
}
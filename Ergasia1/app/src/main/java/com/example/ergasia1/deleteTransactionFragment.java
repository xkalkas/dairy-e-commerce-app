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
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class deleteTransactionFragment extends Fragment {
    EditText editText1;
    Button btn1;
    public deleteTransactionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_transaction, container, false);
        editText1 = view.findViewById(R.id.editTextDelTrans);


        btn1 = view.findViewById(R.id.btnDelTrans);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int Var_transactionid = 0;
                try {
                    Var_transactionid = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                MainActivity.db.collection("TransactionInfo")
                        .whereEqualTo("transactionId", Var_transactionid)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    document.getReference().delete();
                                }
                                Toast.makeText(getActivity(), "Transaction deleted.", Toast.LENGTH_LONG).show();
                            }
                        });
                editText1.setText("");
            }
        });
        return view;
    }
}
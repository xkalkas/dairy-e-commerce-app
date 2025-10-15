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
 * Use the {@link deleteProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class deleteProductFragment extends Fragment {
    EditText editText;
    Button button;
    public deleteProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_product, container, false);
        editText = view.findViewById(R.id.editTextDelProd);
        button = view.findViewById(R.id.btnDelProd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_id = 0;
                try {
                    Var_id = Integer.parseInt(editText.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                Product product = new Product();
                product.setId(Var_id);
                MainActivity.myAppDatabase.myDao().deleteItem(product);
                Toast.makeText(getActivity(),"Product deleted ",Toast.LENGTH_LONG).show();
                editText.setText("");
            }
        });
        return view;
    }
}
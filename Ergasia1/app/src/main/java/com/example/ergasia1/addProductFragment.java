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
 * Use the {@link addProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addProductFragment extends Fragment {
    EditText editText1, editText2 , editText3 , editText4;
    Button button;
    public addProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        editText1 = view.findViewById(R.id.editTextAddProd);
        editText2 = view.findViewById(R.id.editTextAddProdName);
        editText3 = view.findViewById(R.id.editTextAddProdType);
        editText4 = view.findViewById(R.id.editTextAddProdVolume);
        button =view.findViewById(R.id.btnAddProd);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int Var_prodId = 0;
                try{
                    Var_prodId = Integer.parseInt(editText1.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("Could not parse " + ex);
                }
                String Var_prodType = editText3.getText().toString();
                int Var_prodVolume = 0;
                try{
                    Var_prodVolume = Integer.parseInt(editText4.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("Could not parse " + ex);
                }
                String Var_prodName = editText2.getText().toString();
                try{
                    Product product = new Product();
                    product.setId(Var_prodId);
                    product.setName(Var_prodName);
                    product.setPvolume(Var_prodVolume);
                    product.setType(Var_prodType);
                    MainActivity.myAppDatabase.myDao().addItem(product);
                    Toast.makeText(getActivity(), "Product Added", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
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
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
 * Use the {@link editProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class editProductFragment extends Fragment {
    EditText editText1, editText2, editText3, editText4;
    Button button;
    public editProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_product, container, false);
        editText1 = view.findViewById(R.id.editTextEditProd);
        editText2 = view.findViewById(R.id.editTextEditProdName);
        editText3 = view.findViewById(R.id.editTextEditType);
        editText4 = view.findViewById(R.id.editTextEditVolume);
        button =view.findViewById(R.id.btnEditProd);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int Var_prodId = 0;
                try{
                    Var_prodId = Integer.parseInt(editText1.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("Could not parse " + ex);
                }
                int Var_prodVolume = 0;
                try{
                    Var_prodVolume = Integer.parseInt(editText4.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("Could not parse " + ex);
                }
                String Var_prodName = editText2.getText().toString();
                String Var_prodType = editText3.getText().toString();
                try{
                    Product product = new Product();
                    product.setId(Var_prodId);
                    product.setName(Var_prodName);
                    product.setType(Var_prodType);
                    product.setPvolume(Var_prodVolume);
                    MainActivity.myAppDatabase.myDao().editItem(product);
                    Toast.makeText(getActivity(), "Product Updated", Toast.LENGTH_LONG).show();
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
package com.example.ergasia1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.List;

public class QueryFragment extends Fragment {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    TextView queryTextView, queryTextResult;
    Button btnQueryRun;
    DocumentReference documentReference;
    int test;
    public QueryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        final String[] queryArray = getResources().getStringArray(R.array.queries_description_array);
        queryTextView = view.findViewById(R.id.discQuery);
        spinner = view.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.queries_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                queryTextView.setText(queryArray[position]);
                test = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        queryTextResult = view.findViewById(R.id.resultQuery);
        btnQueryRun = view.findViewById(R.id.btnQuery);
        btnQueryRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result ="";
                switch (test){
                    case 1:
                        List<ResultStringInt2> resultStringInts2 = MainActivity.myAppDatabase.myDao().getQuery11();
                        for (ResultStringInt2 i: resultStringInts2) {
                            String CompanyName = i.getField1();
                            int CompanyId = i.getField2();
                            result = result + "\n Company's Name: " + CompanyName + "\n Id: " + CompanyId + "\n";
                        }
                        queryTextResult.setText(result);
                        break;
                    case 2:
                        List<ResultStringInt> supply = MainActivity.myAppDatabase.myDao().getQuery4();
                        for(ResultStringInt i: supply){


                            int cid = i.getField1();
                            int Max_ammount = i.getField2();
                            result = result + "\n Company's Id: " + cid + "\n Maximum Ammount of Product: " + Max_ammount + "\n";
                        }
                        queryTextResult.setText(result);
                        break;
                    case 3:
                        List<String> companyMilk = MainActivity.myAppDatabase.myDao().getQuery5();
                        for(String i: companyMilk){
                            result = result + "\n Company's Name: " + i + "\n";
                        }
                        queryTextResult.setText(result);
                        break;

                    case 4:

                        documentReference = MainActivity.db.
                                collection("CustomerData").
                                document("110");
                        documentReference.
                                get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists()){
                                            FsCustomer customer = documentSnapshot.toObject(FsCustomer.class);
                                            int cid = customer.getCustomerId();
                                            String cname = customer.getCustomerName();
                                            int czip = customer.getZip();

                                            queryTextResult.setText(" cid: " + cid + "\n name: " + cname + "\n zip:"+ czip);
                                        } else {
                                            Toast.makeText(getActivity(),"document does not exist.",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(),"query operation failed.",Toast.LENGTH_LONG).show();
                                    }
                                });

                        break;

                    case 5:
                        CollectionReference colRef = MainActivity.db.collection("TransactionInfo");


                        colRef.whereEqualTo("pid" , 10)
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                       @Override
                                                       public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                           String result = "";
                                                           for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){

                                                               FsTransaction FST = documentSnapshot.toObject(FsTransaction.class);

                                                               Integer tid = FST.getTransactionId();
                                                               Integer pid = FST.getPid();
                                                               String date = FST.getDate();
                                                               result+= "|Transaction ID: " + tid + "\n" +"Products Purchased ID: " + pid + "\n"+
                                                                       "Date of transaction:"+date+"\n"+"\n" ;
                                                           }
                                                           queryTextResult.setText(result);
                                                       }
                                                   }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(),"query operation failed.", Toast.LENGTH_LONG).show();
                                    }
                                });
                        break;
                    case 6:
                    Query queryOR = MainActivity.db.collection("TransactionInfo").where(Filter.or(
                            Filter.equalTo("date", "25.12.22"),
                            Filter.equalTo("date", "15.8.22")
                    ));

                            queryOR.get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    String result = "";
                                    for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){

                                        FsTransaction FST = documentSnapshot.toObject(FsTransaction.class);

                                        Integer tid = FST.getTransactionId();
                                        Integer pid = FST.getPid();
                                        String date = FST.getDate();
                                        Integer volume = FST.getVolume();
                                        result+= "|Transaction ID: " + tid + "\n" +"Products Purchased ID: " + pid + "\n"+ "date of transaction:"+date+"\n"+ "Volume:"+volume+"\n"+"\n";
                                    }
                                    queryTextResult.setText(result);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(),"query operation failed.", Toast.LENGTH_LONG).show();
                                }
                            });
                    break;
                    case 7 :

                        Query queryOrder = MainActivity.db.collection("TransactionInfo")
                                .whereEqualTo("date" , "25.12.22").orderBy("volume" );

                        queryOrder.get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        String result = "";
                                        for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){

                                            FsTransaction FST = documentSnapshot.toObject(FsTransaction.class);

                                            Integer tid = FST.getTransactionId();
                                            Integer pid = FST.getPid();
                                            String date = FST.getDate();
                                            Integer volume = FST.getVolume();
                                            result+= "|Transaction ID: " + tid + "\n" +"Products Purchased ID: " + pid + "\n"+ "date of transaction:"+date+"\n"+ "Volume:"+volume+"\n"+"\n";
                                        }
                                        queryTextResult.setText(result);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("TAG", "Error: " + e.getMessage());
                                        Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_LONG).show();
                                        queryTextResult.setText(e.getMessage());
                                    }
                                });
                        break;


                }
            }
        });

        return view;
    }
}
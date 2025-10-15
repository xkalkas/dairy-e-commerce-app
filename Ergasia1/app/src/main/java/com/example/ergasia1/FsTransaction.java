package com.example.ergasia1;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

public class FsTransaction {
    private int transactionId;
    private DocumentReference customerId;

    private String date;
    private int volume;
    private int pid;

    public FsTransaction() {}

    public FsTransaction(int transactionId, DocumentReference customerId, String date,int volume,int pid) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.date = date;
        this.volume = volume;
        this.pid = pid;
    }





    public DocumentReference getCustomerId() {
        return customerId;
    }

    public void setCustomerId(DocumentReference customerId) {
        this.customerId = customerId;
    }


    public int getTransactionId() {
        return transactionId;
    }


    public String getDate() {
        return date;
    }



    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}

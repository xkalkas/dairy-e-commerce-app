package com.example.ergasia1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void addCompany(Company company);

    @Insert
    public void addItem(Product product);

    @Insert
    public void addSupply(Supply supply);

    @Delete
    public void deleteCompany(Company company);

    @Delete
    public void deleteItem(Product product);

    @Delete
    public void deleteSupply(Supply supply);

    @Update
    public void editCompany(Company company);

    @Update
    public void editItem(Product product);

    @Update
    public void editSupply(Supply supply);

    @Query("select * from company")
    public List<Company> getCompanies();

    @Query("select * from product")
    public List<Product> getProducts();

    @Query("select * from supply")
    public List<Supply> getSupplies();
    @Query("SELECT DISTINCT C.cname as field1, C.cid as field2 " +
            "FROM Company C INNER JOIN supply S ON C.cid = S.scid INNER JOIN product P ON S.spid = P.pid " +
            "WHERE P.ptype='Chocolate Milk' " +
            "EXCEPT " +
            "SELECT DISTINCT C.cname as field1, C.cid as field2  " +
            "FROM Company C INNER JOIN supply S ON C.cid = S.scid INNER JOIN product P ON S.spid = P.pid " +
            "WHERE P.ptype = 'Milk'")
    public List<ResultStringInt2> getQuery11();

    @Query("SELECT scid as field1, Max(sammount) as field2 " +
            "FROM supply " +
            " WHERE scid = 101 " +
            "GROUP BY sammount")
    public List<ResultStringInt> getQuery4();
    @Query("SELECT DISTINCT C.cname " +
            "FROM Company C INNER JOIN Supply S ON C.cid = S.scid INNER JOIN Product P ON S.spid = P.pid " +
            "WHERE P.ptype=\"Cheese\"")
    public List<String> getQuery5();
    @Query("select DISTINCT spid from Supply")
    public List<Integer> getSupply();

    @Query("SELECT SUM (sammount)"+
            " FROM Supply" +
            " WHERE spid = :productGiven ")
    public Integer getSupplyTotalAmmount(int productGiven);
}

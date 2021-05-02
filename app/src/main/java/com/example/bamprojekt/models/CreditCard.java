package com.example.bamprojekt.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "creditCards")
public class CreditCard {
    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "number")
    String number;

    @ColumnInfo(name = "valid_date")
    String validDate;

    @ColumnInfo(name = "ccv")
    String ccv;

    @ColumnInfo(name = "owner")
    String owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}

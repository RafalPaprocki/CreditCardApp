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
}

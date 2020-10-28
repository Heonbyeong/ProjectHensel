package com.example.projecthensel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity
public class LocalData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public String year, month, date, dataString, memo, address, startTime, endTime;

}

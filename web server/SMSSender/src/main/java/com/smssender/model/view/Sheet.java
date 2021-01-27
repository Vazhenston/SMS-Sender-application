package com.smssender.model.view;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.util.Date;

public class Sheet {
    private String sheetUrl;
    private String listName;
    private String pointAddress;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date currentDate;

    public Date getCurrentDate() throws ParseException {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getPointAddress() {
        return pointAddress;
    }

    public void setPointAddress(String pointAddress) {
        this.pointAddress = pointAddress;
    }

    public String getSheetUrl() {
        return sheetUrl;
    }

    public void setSheetUrl(String sheetUrl) {
        this.sheetUrl = sheetUrl;
    }
}

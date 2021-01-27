package com.smssender.services;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.smssender.model.view.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class SheetService extends BasicService {
    @Value("${google.app.name}")
    private String applicationName;

    @Value("${google.spreadsheet.api.key}")
    private String keyAPI;

    private String spreadSheetID;

    private String sheetName;

    private Date currentDate;

    private String range;

    private String addressPoint;

    private Sheets getSheetsService() throws GeneralSecurityException, IOException {
        return new Sheets.Builder(getNetHttpTransport(), getJsonFactory(), AuthorizationService.getCredential())
                .setApplicationName(applicationName)
                .build();
    }

    private ValueRange getValueRange() throws GeneralSecurityException, IOException {
        return getSheetsService().spreadsheets().values()
                .get(getSpreadSheetID(), getRange())
                .setKey(keyAPI)
                .execute();
    }

    public List<List<Object>> readTable() throws GeneralSecurityException, IOException {
        return getValueRange().getValues();
    }

    public void exchangeUrl(Sheet sheet) throws ParseException {
        setCurrentDate(sheet.getCurrentDate());
        setSpreadSheetID(sheet.getSheetUrl().substring(39).replaceFirst("/edit.*", ""));
        setSheetName(sheet.getListName());
        setAddressPoint(sheet.getPointAddress());
    }

    public String getSpreadSheetID() {
        return spreadSheetID;
    }

    public void setSpreadSheetID(String spreadSheetID) {
        this.spreadSheetID = spreadSheetID;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public String getRange() {
        range = getSheetName() + "!A4:F";
        return range;
    }

    public String getAddressPoint() {
        return addressPoint;
    }

    public void setAddressPoint(String addressPoint) {
        this.addressPoint = addressPoint;
    }
}

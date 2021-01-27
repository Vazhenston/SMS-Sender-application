package com.smssender.controllers;

import com.smssender.model.Message;
import com.smssender.model.view.Sheet;
import com.smssender.services.MessageService;
import com.smssender.services.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

@Controller
public class SheetController {
    private SheetService sheetService;

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setSheetService(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    @GetMapping("/home_page")
    public String getUrlForm(@ModelAttribute("sheet") Sheet sheet) {
        return "home_page";
    }

    @PostMapping("/home_page")
    public String addUrl(@ModelAttribute("sheet") Sheet sheet) throws ParseException {
        sheetService.exchangeUrl(sheet);
        return "redirect:/read";
    }

    @GetMapping("/read")
    public String read() throws GeneralSecurityException, IOException, ParseException {
        messageService.saveMessagesToDB(sheetService.readTable());
        return "redirect:/messages";
    }
}

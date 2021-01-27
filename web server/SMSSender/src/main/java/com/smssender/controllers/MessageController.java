package com.smssender.controllers;

import com.smssender.model.Message;
import com.smssender.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {
    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public String getAllMessages(Model model) {
        model.addAttribute("messages", messageService.getAllMessages());
        return "messages";
    }

    @GetMapping("/messages/edit/{id}")
    public String editMessage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("message", messageService.getByID(id));
        return "edit";
    }

    @PostMapping("/messages/edit/{id}")
    public String updateMessage(@ModelAttribute("message") Message message, @PathVariable("id") Long id) {
        messageService.updateMessage(id, message);
        return "redirect:/messages";
    }

    @GetMapping("/messages/delete/{id}")
    public String deleteMessageByID(@PathVariable("id") Long id) {
        messageService.deleteMessageByID(id);
        return "redirect:/messages";
    }

    @GetMapping("/messages/new")
    public String newMessage(@ModelAttribute("message") Message message) {
        return "new";
    }

    @PostMapping("/messages/new")
    public String addMessage(@ModelAttribute("message") Message message) {
        messageService.addMessage(message);
        return "redirect:/messages";
    }
}

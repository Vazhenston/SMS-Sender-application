package com.smssender.services;

import com.smssender.model.Message;
import com.smssender.model.User;
import com.smssender.repositories.MessageRepository;
import com.smssender.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    private SheetService sheetService;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setSheetService(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    public void saveMessagesToDB(List<List<Object>> persons) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (List row : persons) {
            if (row.get(2).equals(dateFormat.format(sheetService.getCurrentDate()))) {
                Message message = new Message();
                message.setId(Long.parseLong((String) row.get(0)));
                message.setPhoneNumber(Long.parseLong((String) row.get(5)));
                message.setMessageText(getResultMessage(row.get(4), row.get(0), row.get(2)));
                message.setUser(getUser());
                if (!messageRepository.existsById(message.getId())) {
                    messageRepository.save(message);
                }
            }
        }
    }

    public String getResultMessage(Object name, Object orderId, Object date) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy (EEEE)", Locale.ENGLISH);
        return String.format("Hello, %s. Your order №%d is waiting for you at %s. " +
                        "Storage period until %s. Hurry to pick up your order, we are waiting for you! \"KazanExpress\"", name,
                Long.parseLong((String) orderId), sheetService.getAddressPoint(), df.format(getCalendar(date).getTime()));
    }

    public Calendar getCalendar(Object date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateFormat.parse((String) date));
        calendar.roll(Calendar.DAY_OF_MONTH, 6);
        return calendar;
    }

    public List<Message> getMessagesOfUser(User user) {
        return messageRepository.findMessageByUser(user);
    }

    public void deleteMessageByID(Long id) {
        messageRepository.deleteById(id);
    }

    public void updateMessage(Long id, Message message) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        Message existingMessage = optionalMessage.get();
        if (message.getPhoneNumber() != null) {
            existingMessage.setPhoneNumber(message.getPhoneNumber());
        }
        if (message.getMessageText() != null) {
            existingMessage.setMessageText(message.getMessageText());
        }
        messageRepository.saveAndFlush(existingMessage);
    }

    public Message getByID(Long id) {
        return messageRepository.getOne(id);
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }
}

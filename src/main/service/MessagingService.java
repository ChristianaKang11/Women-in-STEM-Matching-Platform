package main.service;

import main.entity.Message;
import main.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getConversation(Long senderId, Long receiverId) {
        return messageRepository.findConversation(senderId, receiverId);
    }

    public Message getLatestMessage(Long senderId, Long receiverId) {
        return messageRepository.findLatestMessage(senderId, receiverId);
    }

    public List<Message> getSentMessages(Long senderId) {
        return messageRepository.findBySenderId(senderId);
    }

    public List<Message> getReceivedMessages(Long receiverId) {
        return messageRepository.findByReceiverId(receiverId);
    }

    public Message sendMessage(Long senderId, Long receiverId, String content) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }
}

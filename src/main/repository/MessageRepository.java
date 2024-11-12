package main.repository;

import main.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    /**
     * Retrieve all messages exchanged between a sender and receiver.
     * @param senderId ID of the sender user.
     * @param receiverId ID of the receiver user.
     * @return List of messages exchanged between sender and receiver.
     */
    @Query("SELECT m FROM Message m WHERE (m.senderId = :senderId AND m.receiverId = :receiverId) " +
            "OR (m.senderId = :receiverId AND m.receiverId = :senderId) ORDER BY m.timestamp ASC")
    List<Message> findConversation(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

    /**
     * Retrieve all messages sent by a specific user.
     * @param senderId ID of the user who sent the messages.
     * @return List of messages sent by the specified user.
     */
    List<Message> findBySenderId(Long senderId);

    /**
     * Retrieve all messages received by a specific user.
     * @param receiverId ID of the user who received the messages.
     * @return List of messages received by the specified user.
     */
    List<Message> findByReceiverId(Long receiverId);

    /**
     * Retrieve the latest message in a conversation between a sender and receiver.
     * This can be useful for displaying a preview of the most recent message in a chat list.
     * @param senderId ID of the sender user.
     * @param receiverId ID of the receiver user.
     * @return The latest message between sender and receiver, or null if none exists.
     */
    @Query("SELECT m FROM Message m WHERE (m.senderId = :senderId AND m.receiverId = :receiverId) " +
            "OR (m.senderId = :receiverId AND m.receiverId = :senderId) ORDER BY m.timestamp DESC")
    Message findLatestMessage(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
package ask.Chat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO CHATROOM(pr_id, sellerId, buyerId, fileName, createdDate, pr_title) VALUES ( :#{#chatRoom.pr_id}, :#{#chatRoom.sellerId}, :#{#chatRoom.buyerId}, :#{#chatRoom.fileName}, :#{#chatRoom.createdDate}, :#{#chatRoom.pr_title})", nativeQuery = true)
	public void addChatRoom (@Param("chatRoom") ChatRoom chatRoom) throws IOException;
	
	//String chatId, String pr_id, String senderId, String recipientId

	@Query(value = "SELECT * FROM CHATROOM AS c JOIN BOARD as b ON c.pr_id = b.id WHERE c.sellerid =: #{#userId} OR c.buyerid =: #{#userId}", nativeQuery = true)
	public List<ChatList> findByUserId(@Param("userId") String userId);

	@Query(value = "SELECT COUNT(*) FROM CHATROOM WHERE PR_ID = #{pr_id} AND buyerid = #{buyerId};\n", nativeQuery = true)
	public int countByChatId(int pr_id, String buyerId);

	@Query(value = "", nativeQuery = true)
	public ChatRoom findByChatId(int pr_id, String buyerId);

	@Query(value = "", nativeQuery = true)
	public void appendMessage(ChatRoom chatRoom) throws FileNotFoundException, IOException;

	@Query(value = "", nativeQuery = true)
	public int getId(int pr_id, String buyerId);

	@Query(value = "", nativeQuery = true)
	public void updateFileName(int id, String fileName);

	@Query(value = "", nativeQuery = true)
	public void updateChatReadBuy(int id, int chatReadBuy);

	@Query(value = "", nativeQuery = true)
	public void updateChatReadSell(int id, int chatReadSell);

	@Query(value = "", nativeQuery = true)
	public int getUnreadMessages(String userId);

	@Query(value = "", nativeQuery = true)
	public List<Integer> getUnreadChatRoom(String userId);

	
}
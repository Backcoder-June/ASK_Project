package ask.Chat;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {
    
    @Autowired
	ChatRoomRepository chatRoomRepository;
    
//    String fileUploadPath = "/upload/";
    String fileUploadPath = "c:/chatupload/";
 
    public void addChatRoom(ChatRoom chatRoom) throws IOException {
        Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());
        chatRoom.setCreatedDate(createdDate);
        chatRoomRepository.addChatRoom(chatRoom);
    }
    
    //no connection with DB
    public List<ChatRoom> readChatHistory(ChatRoom chatRoom) throws IOException {
        
        String pathName = fileUploadPath + chatRoom.getFileName();
        
        //DB에 저장된 chat.txt 파일을 읽어옴 
        BufferedReader br = new BufferedReader(new FileReader(pathName));
        //View에 ChatRoom 객체로 전달
        ChatRoom chatRoomLines = new ChatRoom();
        List<ChatRoom> chatHistory = new ArrayList<ChatRoom>();
 
        String chatLine;
        int idx = 1;
        
        while ((chatLine = br.readLine()) != null) {
            
            //1개 메시지는 3줄(보낸사람,메시지내용,보낸시간)로 구성돼있음
            int answer = idx % 3;
            if (answer == 1) {
                //보낸사람
                chatRoomLines.setSenderId(chatLine);
                idx++;
            } else if (answer == 2) {
                //메시지내용
                chatRoomLines.setContent(chatLine);
                idx++;
            } else {
                //보낸시간
                chatRoomLines.setSendTime(chatLine);
                //메시지 담긴 ChatRoom 객체 List에 저장
                chatHistory.add(chatRoomLines);
                //객체 초기화, 줄(row)인덱스 초기화
                chatRoomLines = new ChatRoom();
                idx = 1;
            }            
        }
        
        return chatHistory;
    }
    
    public void updateFileName(int id, String fileName) {
 
        chatRoomRepository.updateFileName(id, fileName);
    }
    
    public void createFile(int pr_id, int id) throws IOException {
        System.out.println("상품아이디 : "+pr_id + ", 채팅방아이디 : " + id);
        String fileName = pr_id + "_" + id + ".txt";
        String pathName = fileUploadPath + fileName;
        //File 클래스에 pathName 할당
        File txtFile = new File(pathName);
        //로컬경로에 파일 생성
        txtFile.createNewFile();
        
        chatRoomRepository.updateFileName(id, fileName);
    }
    
    
    
	public List<ChatRoom> findByUserId(String UserId) {
		return chatRoomRepository.findByUserId(UserId);
	}

	public int countByChatId(int pr_id, String buyerId) {
		
		return chatRoomRepository.countByChatId(pr_id, buyerId);
	}


	//no connection with DB
	public void appendMessage(ChatRoom chatRoom) throws IOException {
		int pr_id = chatRoom.getPr_id();
		String buyerId = chatRoom.getBuyerId();

		System.out.println("pr id : " + pr_id);
		System.out.println("buyerid : " + buyerId);
		System.out.println("파인드파이챗아이디 전");
		ChatRoom chatRoomAppend = chatRoomRepository.findByChatId(pr_id, buyerId);
		System.out.println("파인드바이아이디 성공");
		System.out.println("어펜드 : "+chatRoomAppend.toString());
		String pathName = fileUploadPath + chatRoomAppend.getFileName();
		
		FileOutputStream fos = new FileOutputStream(pathName, true);
		String content = chatRoom.getContent();
		String senderId = chatRoom.getSenderId();
		String sendTime = chatRoom.getSendTime();
//		System.out.println("print:" + content);
		
		String writeContent = senderId + "\n" + content + "\n" + "[" +  sendTime + "]" + "\n";
		
		byte[] b = writeContent.getBytes();
		
		fos.write(b);
		fos.close();
		
		System.out.println("senderId: "+ senderId);
		System.out.println("sellerId: "+ chatRoom.getSellerId());
		System.out.println(senderId.equals(chatRoom.getSellerId()));
		
		//알림 
		if (senderId.equals(chatRoom.getSellerId())) {
			updateChatReadBuy(chatRoom.getId(), 0);
		} else {
			updateChatReadSell(chatRoom.getId(), 0);
		}
		
	}
	
	// 알림 

	public ChatRoom findByChatId(int pr_id, String buyerId) {
		return chatRoomRepository.findByChatId(pr_id, buyerId);
	}
	
	public int getId(int pr_id, String buyerId) {
		
		return chatRoomRepository.getId(pr_id, buyerId);
	}

	    
    
	public void updateChatReadBuy(int id, int chatReadBuy) {
		
		chatRoomRepository.updateChatReadBuy(id, chatReadBuy);
		
	}

	public void updateChatReadSell(int id, int chatReadSell) {
		
		chatRoomRepository.updateChatReadSell(id, chatReadSell);
		
	}
	
	public int getUnreadMessages(String userId) {
		
		return chatRoomRepository.getUnreadMessages(userId);
	}

	public List<Integer> getUnreadChatRoom(String userId) {
		
		List<Integer> unread = chatRoomRepository.getUnreadChatRoom(userId);
		return unread;
	}
    
    
    
    
    
}

package ask.Chat;

import javax.persistence.*;
import java.sql.Timestamp;

public class ChatList {
	private int id;
	private int pr_id;
	private String seller_id;
	private String buyer_id;
	private String file_name;
	private Timestamp created_date;
	private String pr_title;
	private int chat_read_buy;
	private int chat_read_sell;

	//not in DB
	private String content;
	private String sendTime;
	private String senderId;






	@Override
	public String toString() {
		return "ChatRoom [id=" + id + ", pr_id=" + pr_id + ", sellerId=" + seller_id + ", buyerId=" + buyer_id
				+ ", fileName=" + file_name + ", createdDate=" + created_date + ", content=" + content + ", sendTime="
				+ sendTime + ", senderId=" + senderId + ", pr_title=" + pr_title + "]";
	}


	public ChatList(int id, int pr_id, String sellerId, String buyerId, String fileName,
					Timestamp createdDate, String pr_title, int chatReadBuy, int chatReadSell) {
		super();
		this.id = id;
		this.pr_id = pr_id;
		this.seller_id = sellerId;
		this.buyer_id = buyerId;
		this.file_name = fileName;
		this.created_date = createdDate;
		this.pr_title = pr_title;
		this.chat_read_buy = chatReadBuy;
		this.chat_read_sell = chatReadSell;
	}





	public int getChatReadBuy() {
		return chat_read_buy;
	}

	public void setChatReadBuy(int chatReadBuy) {
		this.chat_read_buy = chatReadBuy;
	}

	public int getChatReadSell() {
		return chat_read_sell;
	}

	public void setChatReadSell(int chatReadSell) {
		this.chat_read_sell = chatReadSell;
	}

	public ChatList() {
		// TODO Auto-generated constructor stub
	}

	public ChatList(String content, String sendTime, String senderId) {
		this.content = content;
		this.sendTime = sendTime;
		this.senderId = senderId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPr_id() {
		return pr_id;
	}

	public void setPr_id(int pr_id) {
		this.pr_id = pr_id;
	}

	public String getSellerId() {
		return seller_id;
	}

	public void setSellerId(String sellerId) {
		this.seller_id = sellerId;
	}

	public String getBuyerId() {
		return buyer_id;
	}

	public void setBuyerId(String buyerId) {
		this.buyer_id = buyerId;
	}

	public String getFileName() {
		return file_name;
	}

	public void setFileName(String fileName) {
		this.file_name = fileName;
	}

	public Timestamp getCreatedDate() {
		return created_date;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.created_date = createdDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getPr_title() {
		return pr_title;
	}

	public void setPr_title(String pr_title) {
		this.pr_title = pr_title;
	}

}



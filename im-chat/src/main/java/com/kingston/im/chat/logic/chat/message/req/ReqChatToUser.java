package com.kingston.im.chat.logic.chat.message.req;

import com.kingston.im.chat.base.SpringContext;
import com.kingston.im.chat.net.IoSession;
import com.kingston.im.chat.net.message.AbstractPacket;
import com.kingston.im.chat.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ReqChatToUser extends AbstractPacket {

	private long toUserId;

	private String content;

	public long getToUserId() {
		return toUserId;
	}

	public void setToUserId(long toUserId) {
		this.toUserId = toUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeLong(this.toUserId);
		writeUTF8(buf, content);

	}

	@Override
	public void readBody(ByteBuf buf) {
		this.toUserId = buf.readLong();
		this.content = readUTF8(buf);

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ReqChatToUser;
	}

	@Override
	public void execPacket(IoSession session) {
		SpringContext.getChatService().chat(session, toUserId, content);
	}

}

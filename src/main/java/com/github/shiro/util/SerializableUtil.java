package com.github.shiro.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

/**
 * 序列化工具
 * 
 * @author jiangyf
 * @date 2017年8月10日 下午6:04:18
 */
public class SerializableUtil {

	/**
	 * 序列化会话
	 * 
	 * @param session
	 * @return
	 */
	public static String serialize(Session session) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(session);
			return Base64.encodeToString(bos.toByteArray());
		} catch (Exception e) {
			throw new RuntimeException("serialize session error", e);
		}
	}

	/**
	 * 反序列化会话
	 * 
	 * @param sessionStr
	 * @return
	 */
	public static Session deserialize(String sessionStr) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
			ObjectInputStream ois = new ObjectInputStream(bis);
			return (Session) ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException("deserialize session error", e);
		}
	}

}

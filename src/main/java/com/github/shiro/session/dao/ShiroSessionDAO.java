package com.github.shiro.session.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import com.github.shiro.util.SerializableUtil;
import com.github.util.JdbcTemplateUtil;

/**
 * shiro 会话（crud）:通过把会话序列化后存储到数据库实现
 * 
 * CachingSessionDAO 提供了对开发者透明的会话缓存的功能，只需要设置相应的 CacheManager 即可
 * 
 * @author jiangyf
 * @date 2017年10月10日 下午2:58:33
 */
public class ShiroSessionDAO extends CachingSessionDAO {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.jdbcTemplate();

	@Override
	protected Serializable doCreate(Session session) {
		if (session == null) {
			return null;
		}
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		String sql = "insert into shiro_session(id, session) values(?,?)";
		jdbcTemplate.update(sql, sessionId, SerializableUtil.serialize(session));
		return session.getId();
	}

	@Override
	protected void doUpdate(Session session) {
		if (session == null) {
			return;
		}
		// 如果会话过期/停止 没必要再更新了
		if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
			return;
		}
		String sql = "update shiro_session set session=? where id=?";
		jdbcTemplate.update(sql, SerializableUtil.serialize(session), session.getId());
	}

	@Override
	protected void doDelete(Session session) {
		if (session == null) {
			return;
		}
		String sql = "delete from shiro_session where id=?";
		jdbcTemplate.update(sql, session.getId());
	}

	// 所有在读取时会先查缓存中是否存在，如果找不到才到数据库中查找
	@Override
	protected Session doReadSession(Serializable sessionId) {
		String sql = "select session from shiro_session where id=?";
		List<String> sessionStrList = jdbcTemplate.queryForList(sql, String.class, sessionId);
		if (sessionStrList.size() == 0)
			return null;
		return SerializableUtil.deserialize(sessionStrList.get(0));
	}

}

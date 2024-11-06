package org.jeecg.config.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;


/**
 * @description 基于redis实现共享的session
 * */
public class RedisSessionDAO extends AbstractSessionDAO  {

    private long expireTime;

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisSessionDAO(long expireTime){
        this.expireTime = expireTime;
    }


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.SECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return sessionId == null ? null : (Session) redisTemplate.opsForValue().get(sessionId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(Session session) throws UnknownSessionException {
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.SECONDS);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(Session session) {
        if (session != null && session.getId() != null) {
            redisTemplate.opsForValue().getOperations().delete(session.getId());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Session> getActiveSessions() {
        return redisTemplate.keys("*");
    }


}

package org.jeecg.config.shiro.session;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


/**
 * @description 将redis中的Session存储在内存缓存中，以提高性能和减少数据库/redis/?的访问次数
 * */
public class LocalRedisSessionDAO extends CachingSessionDAO {

    @Autowired
    private RedisSessionDAO redisSessionDao;

    @Override
    protected void doUpdate(Session session) {
        redisSessionDao.update(session);
        cache(session,session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        redisSessionDao.delete(session);
        uncache(session);
    }
    @Override
    protected Session doReadSession(Serializable sessionId) {
//		return redisSessionDao.readSession(sessionId);

        Session session = getCachedSession(sessionId);
        if (session == null) {
            session = redisSessionDao.readSession(sessionId);
            if (session != null) {
                cache(session, session.getId());
            }
        }
        return session;

    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);
        cache(session, sessionId);
        return sessionId;
    }

}

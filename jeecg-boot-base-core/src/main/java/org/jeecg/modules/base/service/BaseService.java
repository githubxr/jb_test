package org.jeecg.modules.base.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 *
 * @author xr
 * @since 2024-11-12
 * @description 通用方法（待完成）
 * */
public abstract class BaseService<T> {
    public abstract T queryByName(String name);
    public abstract String getCode(T entity);
    public abstract void save(T entity);

    /*
    public String saveIfNotExist(String name, String prefix, EntityFactory<T> factory) {
        T exist = queryByName(name);
        if (exist == null) {
            String code = prefix + "_" + IdWorker.getId(); // 生成雪花ID
            T newEntity = factory.createEntity(code, name);
            save(newEntity);
            return code;
        }
        return getCode(exist);
    }
    */
}
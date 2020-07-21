package com.example.smallexease.utils;

import com.example.smallexease.bean.CollectionBean;
import com.example.smallexease.dao.CollectionBeanDao;
import com.example.smallexease.dao.DaoMaster;
import com.example.smallexease.dao.DaoSession;

import java.util.List;

public class DbHelpter {
    private static volatile DbHelpter instance;
    private final CollectionBeanDao collectionBeanDao;

    public static DbHelpter getInstance() {
        if (instance == null) {
            synchronized (DbHelpter.class) {
                if (instance == null) {
                    instance = new DbHelpter();
                }
            }
        }
        return instance;
    }

    public DbHelpter() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(GreenDaoApplication.getApp(), "collection.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        collectionBeanDao = daoSession.getCollectionBeanDao();
    }

    //判断是否存在该对象
    private boolean isHased(CollectionBean collectionBean) {
        List<CollectionBean> list = collectionBeanDao.queryBuilder().where(CollectionBeanDao.Properties.Id.eq(collectionBean.getId())).list();
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //增
    public long insert(CollectionBean collectionBean) {
        if (!isHased(collectionBean)) {
            long l = collectionBeanDao.insertOrReplace(collectionBean);
            return l;
        }
        return -1;
    }

    //删
    public boolean delete(CollectionBean collectionBean) {
        if (isHased(collectionBean)) {
            collectionBeanDao.delete(collectionBean);
            return true;
        }
        return false;
    }

    //改
    public boolean update(CollectionBean collectionBean) {
        if (isHased(collectionBean)) {
            collectionBeanDao.update(collectionBean);
            return true;
        }
        return false;
    }

    //查
    public List<CollectionBean> query() {
        List<CollectionBean> list = collectionBeanDao.queryBuilder().list();
        return list;
    }
}

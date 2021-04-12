package com.parsdeveloper.shopping.model.dao;

import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class PersistEntityListener implements PreInsertEventListener, PostInsertEventListener, PostUpdateEventListener, PreUpdateEventListener, Serializable {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onPostInsert(PostInsertEvent event) {
        logger.info(event.getId().toString());
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        System.out.println(1);
    }

    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return true;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        boolean notPersistable = PreInsertHelper.onPreInsert(event);
        return notPersistable;
        /*if (!notPersistable) {

        }*/
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        System.out.println(2);
        return false;
    }
}


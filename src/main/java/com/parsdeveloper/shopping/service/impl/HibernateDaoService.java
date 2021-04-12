package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.model.dao.BaseModel;
import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.dao.StringUtils;
import com.parsdeveloper.shopping.service.api.DaoService;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class HibernateDaoService implements DaoService, Serializable {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateDaoService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int executeUpdate(String queryString, Map<String, Object> params) {
        Query query = getSession().createQuery(queryString);
        setQueryParameters(query, params);
        return query.executeUpdate();
    }

    public Query createQuery(String query) {
        return getSession().createQuery(query);
    }

    public List createNativeQuery(String query, Map<String, Object> params) {
        NativeQuery nativeQuery = getSession().createNativeQuery(query);
        setQueryParameters(nativeQuery, params);
        return nativeQuery.getResultList();
    }

    public ProcedureCall createStoredProcedureCall(String storedProcedureName, Class... resultClasses) {
        return getSession().createStoredProcedureCall(storedProcedureName, resultClasses);
    }

    public void refresh(Object entity) {
        getSession().refresh(entity);
    }

    public void flush() {
        getSession().flush();
    }

    public <E> E loadById(Class<E> clazz, Serializable Id) {
        return getSession().load(clazz, Id);
    }

    public <E> E loadById(Class<E> clazz, Serializable Id, LockOptions LockOptions) {
        return getSession().load(clazz, Id, LockOptions);
    }

    @Override
    public <E> E getById(Class<E> clazz, Serializable Id) {
        return getSession().get(clazz, Id);
    }

    @Override
    public <E> E bySimpleNaturalId(Class<E> clazz, Serializable naturalId) {
        E load = getSession().bySimpleNaturalId(clazz).load(naturalId);
        if (load == null) {
            throw new IllegalStateException("no record with given naturalId was found");
        }
        return load;
    }

    @Override
    public <E> Optional<E> tryBySimpleNaturalId(Class<E> clazz, Serializable naturalId) {
        return getSession().bySimpleNaturalId(clazz).loadOptional(naturalId);
    }

    @Override
    public <E extends CodeEnabled> E getByCode(Class<E> clazz, Serializable code) {
        Optional<E> e = tryGetByCode(clazz, code);
        if (!e.isPresent()) {
            throw new IllegalStateException("at least one instance must be exist, or use tryGetByCode");
        }
        return e.get();
    }

    @Override
    public <E extends CodeEnabled> Optional<E> tryGetByCode(Class<E> clazz, Serializable code) {
        List byCode = this.createQuery("from " + getMetaModel(clazz).getName() + " e where e.code = :code").setParameter("code", code).getResultList();
        if (byCode.size() == 1) {
            return Optional.of((E) byCode.get(0));
        } else if (byCode.size() > 1) {
            throw new IllegalStateException("search by code returned more than one instance, programmer error, try to enable an UK");
        }
        return Optional.empty();
    }


    public <E> E findById(Class<? extends E> clazz, Serializable id) {
        return getSession().get(clazz, id);
    }

    public Object findSingularByNamedQuery(String namedQuery, Map<String, Object> params) {
        return createNamedQuery(namedQuery, 0, 1, params).getSingleResult();
    }

    public <E> List<E> findByNamedQuery(String namedQuery, Map<String, Object> params) {
        return createNamedQuery(namedQuery, 0, Byte.MAX_VALUE, params).getResultList();//TODO ,prevent fetch all records
    }

    public <E> List<E> findByNamedQuery(String namedQuery, int firstResult, int maxResult, Map<String, Object> params) {
        return createNamedQuery(namedQuery, firstResult, maxResult, params).getResultList();
    }

    public Query createNamedQuery(String namedQuery, Map<String, Object> params) {
        return createNamedQuery(namedQuery, 0, Byte.MAX_VALUE, params);
    }

    @Override
    public <E> Stream<E> createNamedQueryAsStream(String namedQuery, Map<String, Object> params) {
        Query query = getSession().getNamedQuery(namedQuery);
        setQueryParameters(query, params);
        return ((org.hibernate.query.Query) query).stream();
    }

    @Override
    public <E> Stream<E> createNamedQueryAsStream(String namedQuery, int firstResult, int maxResult, Map<String, Object> params) {
        Query query = getSession().getNamedQuery(namedQuery);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        setQueryParameters(query, params);
        return ((org.hibernate.query.Query) query).stream();
    }

    public Query createNamedQuery(String namedQuery, int firstResult, int maxResult, Map<String, Object> params) {
        Query query = getSession().getNamedQuery(namedQuery);
        setQueryParameters(query, params);
        query.setFirstResult(firstResult).setMaxResults(maxResult);
        return query;
    }

    @Deprecated
    public <E> List<E> findByNamedQuery(String namedQuery, Object[] params) {
        return findByNamedQuery(namedQuery, 0, Byte.MAX_VALUE, params);//TODO ,prevent fetch all records
    }

    private void setQueryParameters(Query query, Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            params.forEach((key, value) -> {
                if (value != null && String.class.isAssignableFrom(value.getClass())) {
                    value = StringUtils.correctInvalidCharacters((String) value);
                }
                query.setParameter(key, value);
            });
        }
    }

    @Deprecated
    public <E> List<E> findByNamedQuery(String namedQuery, int firstResult, int maxResult, Object[] params) {
        Query query = getSession().getNamedQuery(namedQuery);
        if (params != null && params.length > 0) {
            int i = 0;
            for (Object param : params) {
                query.setParameter(i++, param);
            }
        }
        query.setFirstResult(firstResult).setMaxResults(maxResult);
        return query.getResultList();
    }


    public <E> List<E> find(String query) {
        return find(query, 0, Byte.MAX_VALUE, null);
    }

    public <E> List<E> find(String query, Map<String, Object> params) {
        return find(query, 0, Byte.MAX_VALUE, params);
    }

    public <E> List<E> find(String queryString, int firstResult, int maxResult, Map<String, Object> params) {
        Query query = createQuery(queryString);
        if (params != null && params.size() > 0) {
            params.forEach(query::setParameter);
        }
        query.setFirstResult(firstResult).setMaxResults(maxResult);
        return query.getResultList();
    }

    public Object getSingleResult(String queryString, Map<String, Object> params) {
        Query query = createQuery(queryString);
        if (params != null && params.size() > 0) {
            params.forEach(query::setParameter);
        }
        return query.getSingleResult();
    }

    @Deprecated
    public Criteria createCriteria(Class clazz, Example example) {
        return getSession().createCriteria(clazz).add(example);
    }

    @Deprecated
    public <E> Criteria createCriteria(Class<? extends E> clazz) {
        return getSession().createCriteria(clazz);
    }

    public <E> List<E> loadAll(Class<E> clazz, int maxResults) {
        CriteriaQuery<E> criteriaQuery = getSession().getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.from(clazz);
        return getSession().createQuery(criteriaQuery).setMaxResults(Byte.MAX_VALUE).list();
    }

    public <E extends BaseModel> Long countEntity(Class<E> clazz, Serializable id) {
        CriteriaBuilder qb = getSession().getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        Root root = cq.from(clazz);
        cq.select(qb.count(root));
        cq.where(qb.equal(root.get("id"), id));
        return getSession().createQuery(cq).getSingleResult();
    }

    public <E> List<E> loadAll(Class<E> clazz) {
        return loadAll(clazz, Byte.MAX_VALUE);
    }

    public <E> E save(E entity) {
        return (E) getSession().merge(entity);
    }

    public void saveOrUpdate(BaseModel entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(BaseModel entity) {
        Session session = getSession();
        if (session.contains(entity)) {
            session.delete(entity);
        } else {
            entity = session.load(entity.getClass(), entity.getId());
            session.delete(entity);
        }

    }

    public void evict(BaseModel entity) {
        getSession().evict(entity);
    }

    public void enableFetchProfile(String fetchProfile) {
        getSession().enableFetchProfile(fetchProfile);
    }

    public void disableFetchProfile(String fetchProfile) {
        getSession().disableFetchProfile(fetchProfile);
    }

    public <X> EntityType<X> getMetaModel(Class clazz) {
        return getSession().getMetamodel().entity(clazz);
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


}

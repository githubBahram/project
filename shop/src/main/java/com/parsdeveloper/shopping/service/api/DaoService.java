package com.parsdeveloper.shopping.service.api;


import com.parsdeveloper.shopping.model.dao.BaseModel;
import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.procedure.ProcedureCall;

import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface DaoService {

    int executeUpdate(String queryString, Map<String, Object> params);

    @Deprecated
    Query createQuery(String hql);

    List createNativeQuery(String query, Map<String, Object> params);

    ProcedureCall createStoredProcedureCall(String storedProcedureName, Class... resultClasses);

    void refresh(Object entity);

    Query createNamedQuery(String namedQuery, Map<String, Object> params);

    /**
     * This method will create a Stream instead of Query object
     *
     * @param namedQuery namedQuery name
     * @param params     map of parameters to pass for named bind variables
     * @param <E>        The Type of Stream Object
     * @return Stream Object
     * @implNote h.zare 11/29/2016 Default implementation down cast the <b>javax.persistence.Query</b> to <b>org.hibernate.query.Query</b>
     * @see org.hibernate.query.Query#stream()
     * @since 3.0.8-RC2
     */
    <E> Stream<E> createNamedQueryAsStream(String namedQuery, Map<String, Object> params);

    <E> Stream<E> createNamedQueryAsStream(String namedQuery, int firstResult, int maxResult, Map<String, Object> params);

    Query createNamedQuery(String namedQuery, int firstResult, int maxResult, Map<String, Object> params);

    void flush();

    <E> E loadById(Class<E> clazz, Serializable Id);

    <E> E loadById(Class<E> clazz, Serializable Id, LockOptions LockOptions);

    <E> E getById(Class<E> clazz, Serializable Id);

    <E> E bySimpleNaturalId(Class<E> clazz, Serializable naturalId);

    <E> Optional<E> tryBySimpleNaturalId(Class<E> clazz, Serializable naturalId);

    <E extends CodeEnabled> E getByCode(Class<E> clazz, Serializable code);

    <E extends CodeEnabled> Optional<E> tryGetByCode(Class<E> clazz, Serializable code);

    <E> E findById(Class<? extends E> clazz, Serializable id);

    Object findSingularByNamedQuery(String namedQuery, Map<String, Object> params);

    <E> List<E> findByNamedQuery(String namedQuery, Map<String, Object> params);

    <E> List<E> findByNamedQuery(String namedQuery, int firstResult, int maxResult, Map<String, Object> params);

    @Deprecated
    <E> List<E> findByNamedQuery(String namedQuery, int firstResult, int maxResult, Object[] params);

    @Deprecated
    <E> List<E> findByNamedQuery(String namedQuery, Object[] params);

    <E> List<E> find(String query, Map<String, Object> params);

    <E> List<E> find(String query, int firstResult, int maxResult, Map<String, Object> params);

    Object getSingleResult(String queryString, Map<String, Object> params);

    @Deprecated
    Criteria createCriteria(Class clazz, Example example);

    @Deprecated
    <E> Criteria createCriteria(Class<? extends E> clazz);

    <E> List<E> loadAll(Class<E> clazz);

    <E> List<E> loadAll(Class<E> clazz, int maxResults);

    <E> E save(E entity);

    void saveOrUpdate(BaseModel entity);

    void delete(BaseModel entity);

    void evict(BaseModel entity);

    void enableFetchProfile(String fetchProfile);

    void disableFetchProfile(String fetchProfile);

    <E extends BaseModel> Long countEntity(Class<E> clazz, Serializable id);

    <X> EntityType<X> getMetaModel(Class clazz);

    Session getSession();
}

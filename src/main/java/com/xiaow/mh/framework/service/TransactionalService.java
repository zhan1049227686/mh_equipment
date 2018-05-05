package com.xiaow.mh.framework.service;

import com.xiaow.mh.framework.common.exceptions.PersistenceException;
import com.xiaow.mh.framework.domain.AppDO;
import com.xiaow.mh.framework.domain.CRUDRepo;
import org.jodah.typetools.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Created by POJO on 5/29/16.
 */
@Service
@Transactional(rollbackFor = Throwable.class, isolation = Isolation.REPEATABLE_READ)
public abstract class TransactionalService<T extends CRUDRepo, E extends AppDO> {
    @Autowired
    protected T repo;

    @PersistenceContext(unitName = "default")
    protected EntityManager em;

    Class<?>[] typeArgs = TypeResolver.resolveRawArguments(TransactionalService.class, getClass());
    Class<E> classDO = (Class<E>) typeArgs[1];

    @SuppressWarnings(value = "unchecked")
    protected static List<Map<String, Object>> resMap(String[] params, Iterable res) {
        List resultList = new ArrayList<Map<String, Object>>();
        if (params != null && params.length > 0 && res != null) {
            for (Object i : res) {
                int c = 0;
                HashMap resMap = new HashMap();
                for (Object e : (Object[]) i) {
                    resMap.put(params[c++], e);
                }
                resultList.add(resMap);
            }
        }
        return resultList;
    }

    public E save(E entity) throws PersistenceException {
        try {
            return (E) repo.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "更新失败");
        }
    }

    @SuppressWarnings(value = "unchecked")
    public List<E> save(Collection<E> entities) throws PersistenceException {
        try {
            return (List<E>) repo.save(entities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "更新失败");
        }
    }

    @SuppressWarnings(value = "unchecked")
    public boolean delete(Collection<Long> entities) throws PersistenceException {
        try {
            Iterable<E> tmp = repo.findAll(entities);
            repo.delete(tmp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "删除失败");
        }
        return true;
    }

    @SuppressWarnings(value = "unchecked")
    public boolean deleteByEntities(Collection<E> entities) throws PersistenceException {
        try {
            repo.delete(entities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "删除失败");
        }
        return true;
    }

    @SuppressWarnings(value = "unchecked")
    public boolean delete(Long id) throws PersistenceException {
        try {
            repo.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "删除失败");
        }
        return true;
    }

    @SuppressWarnings(value = "unchecked")
    public boolean delete(E entity) throws PersistenceException {
        try {
            repo.delete(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "删除失败");
        }
        return true;
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public E findOne(Long id) throws PersistenceException {
        try {
            E obj = (E)repo.findOne(id);
            em.detach(obj);
            return obj ;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "查找失败");
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public E get(Long id) throws PersistenceException {
        return findOne(id);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<E> findAll(List<Long> ids) throws PersistenceException {
        try {
            return (List<E>) repo.findAll(ids);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "查找失败");
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<E> findAll() throws PersistenceException {
        try {
            return (List<E>) repo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "查找失败");
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<E> findAll(Pageable p) throws PersistenceException {
        try {
            return repo.findAll(p);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "查找失败");
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<E> findAll(Sort s) throws PersistenceException {
        try {
            return (List<E>) repo.findAll(s);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(classDO.getName() + "查找失败");
        }
    }


}

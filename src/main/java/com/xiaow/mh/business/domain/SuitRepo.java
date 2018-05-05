package com.xiaow.mh.business.domain;

import com.xiaow.mh.framework.domain.CRUDRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangnengwen on 17/11/19.
 */
@Repository
public interface SuitRepo extends CRUDRepo<Suit> {

    @Query(value = "SELECT * FROM d_suit s where s.NAME =:name and s.COUNT =:num", nativeQuery = true)
    List<Suit> findByNameAndCount(@Param("name") String name, @Param("num") Integer num);
}

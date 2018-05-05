package com.xiaow.mh.framework.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 不用缓存的Repo
 */
public interface CRUDRepo<T extends AppDO> extends PagingAndSortingRepository<T, Long> {

}

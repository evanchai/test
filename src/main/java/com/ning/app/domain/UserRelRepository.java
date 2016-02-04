package com.ning.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by evanchai on 2/4/16.
 */
public interface UserRelRepository extends JpaRepository<UserRel, Long> {
    List<UserRel> findByFromId(long fromId);
    List<UserRel> findByToId(long toId);
    UserRel findByFromIdAndToId(long fromId,long toId);
}

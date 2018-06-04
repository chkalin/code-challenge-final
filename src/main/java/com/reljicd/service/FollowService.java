package com.reljicd.service;

import com.reljicd.model.Follow;


import java.util.Optional;
import java.util.Set;

public interface FollowService {

    Set<Follow> findAllByUsername(String username);

    Optional<Follow> findByUsernameAndFollowusername(String username, String followusername);

    Follow save(Follow follow);

    void delete(Follow follow);
}

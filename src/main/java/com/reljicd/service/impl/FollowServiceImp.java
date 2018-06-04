package com.reljicd.service.impl;

import com.reljicd.model.Follow;
import com.reljicd.model.User;
import com.reljicd.repository.FollowRepository;
import com.reljicd.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class FollowServiceImp implements FollowService {

    private final FollowRepository followRepository;

    @Autowired
    public FollowServiceImp(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public Set<Follow> findAllByUsername(String username) {
        return followRepository.findAllByUsername(username);
    }

    @Override
    public Optional<Follow> findByUsernameAndFollowusername(String username, String followusername) {
        return followRepository.findByUsernameAndFollowusername(username, followusername);
    }

    @Override
    public Follow save(Follow follow) {
        return followRepository.saveAndFlush(follow);
    }

    @Override
    public void delete(Follow follow) {
        followRepository.delete(follow);
    }
}

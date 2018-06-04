package com.reljicd.controller;

import com.reljicd.model.Follow;
import com.reljicd.model.Post;
import com.reljicd.model.User;
import com.reljicd.service.FollowService;
import com.reljicd.service.PostService;
import com.reljicd.service.UserService;
import com.reljicd.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Controller
public class FollowController {

    private final PostService postService;
    private final UserService userService;
    private final FollowService followService;

    @Autowired
    public FollowController(UserService userService, PostService postService, FollowService followService) {
        this.postService = postService;
        this.userService = userService;
        this.followService = followService;
    }

    @RequestMapping(value = "/follow/{username}", method = RequestMethod.GET)
    public String followForUsername(@PathVariable String username,
                                  @RequestParam(defaultValue = "0") int page,
                                  Model model)  {
        Set<Follow> followSet = followService.findAllByUsername(username);
        Set<User> follernames = new HashSet();
        Optional<User> currentUser = userService.findByUsername(username);
        User user = currentUser.get();
//        if (!followSet.isEmpty()){
//            Iterator<Follow> iter = followSet.iterator();
//            while (iter.hasNext()){
//                Optional<User> optionalUser = userService.findByUsername(iter.next().getFollowUsername());
//                User followeduser = optionalUser.get();
//                follernames.add(followeduser);
//            }
//            Page<Post> posts = postService.findAllByUserInOrderedByDatePageable(follernames, page);
//            Pager pager = new Pager(posts);
//
//            model.addAttribute("pager", pager);
//            model.addAttribute("user", user);
//
//            return "/follow";
//        }
//        else{return "/nofollow"; }

        if (!followSet.isEmpty()){
            Iterator<Follow> iter = followSet.iterator();
            while (iter.hasNext()){
                Optional<User> optionalUser = userService.findByUsername(iter.next().getFollowUsername());
                User followeduser = optionalUser.get();
                follernames.add(followeduser);
            }
            Page<Post> posts = postService.findAllByUserInOrderedByDatePageable(follernames, page);
            Pager pager = new Pager(posts);

            model.addAttribute("pager", pager);
            model.addAttribute("user", user);

            return "/follow";
        }
        else{
            model.addAttribute("user", user);
            return "/nofollow"; }
    }
}

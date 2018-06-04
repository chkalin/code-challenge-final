package com.reljicd.controller;

import com.reljicd.model.Follow;
import com.reljicd.model.FollowStatus;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BlogController {

    private final UserService userService;

    private final PostService postService;

    private final FollowService followService;

   @Autowired
   public BlogController(UserService userService, PostService postService, FollowService followService) {
       this.userService = userService;
       this.postService = postService;
       this.followService = followService;
   }

    @RequestMapping(value = "/blog/{username}-{currentuser}", method = RequestMethod.GET)
    public String blogForUsernameCurrent(@PathVariable String username,
                                  @RequestParam(defaultValue = "0") int page,
                                  @PathVariable String currentuser,
                                  Model model) {
        Optional<User> optionalUser = userService.findByUsername(username);
        String followStatus;
        Optional<Follow> optionalFollow = followService.findByUsernameAndFollowusername(currentuser, username);
        if(optionalFollow.isPresent()){
            followStatus = "UnFollow";
        }else{
            followStatus = "Follow Me";
        }
        FollowStatus status = new FollowStatus();
        status.setValue(followStatus);
        status.setCurrent(currentuser);
        status.setWatch(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Page<Post> posts = postService.findByUserOrderedByDatePageable(user, page);
            Pager pager = new Pager(posts);

            model.addAttribute("pager", pager);
            model.addAttribute("user", user);
            model.addAttribute("followstatus", status);

            return "/posts";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/blog/delete-{username}-{currentuser}", method = RequestMethod.GET)
    public String toDeleteFollow(@PathVariable String username,
                                         @PathVariable String currentuser){
       Follow follow = new Follow();
       follow.setUsername(currentuser);
       follow.setFollowUsername(username);
       Optional<Follow> optionalFollow = followService.findByUsernameAndFollowusername(currentuser, username);
       Follow exitFollow = optionalFollow.get();
       follow.setSortid(exitFollow.getSortid());
       followService.delete(follow);
       return "redirect:/blog/" + username + "-" + currentuser;
    }

    @RequestMapping(value = "/blog/add-{username}-{currentuser}", method = RequestMethod.GET)
    public String toAddFollow(@PathVariable String username,
                                 @PathVariable String currentuser){
        Follow follow = new Follow();
        follow.setUsername(currentuser);
        follow.setFollowUsername(username);
        followService.save(follow);
        return "redirect:/blog/" + username + "-" + currentuser;

    }
}

package com.reljicd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sort_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "follow_username")
    private String followusername;

    public Long getSortid() {
        return id;
    }

    public void setSortid(Long id) {
        this.id = id;
    }

    public String getFollowUsername() {
        return followusername;
    }

    public void setFollowUsername(String followusername) {
        this.followusername = followusername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

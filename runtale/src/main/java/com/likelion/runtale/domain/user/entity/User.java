package com.likelion.runtale.domain.user.entity;

import com.likelion.runtale.domain.running.entity.Running;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Running> runnings;

    public User() {
    }

    public User(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

    public User(Long id, String loginId, String password, String nickname) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

    public void addOrUpdateRunning(Running running) {
        for (int i = 0; i < runnings.size(); i++) {
            if (runnings.get(i).getId().equals(running.getId())) {
                runnings.set(i, running);
                return;
            }
        }
        this.runnings.add(running);
        running.setUser(this);
    }
}

package com.sparta.newsfeed.user.entity;

import com.sparta.newsfeed.board.entity.Timestamped;
import com.sparta.newsfeed.friend.entity.Friend;
import com.sparta.newsfeed.friend.entity.FriendRequest;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")  // 앞에 U -> u 소문자로 변경
public class UserEntity extends Timestamped {  // timestamp 추가하면 아래 코드 필요없음

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(nullable = false, name = "username")
    private String username;
    @Column(nullable = false, unique = true, name = "email" )
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(nullable = false, name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = true, name = "introduce")
    private String introduce;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    // 친구관련 추가(진호)
    @OneToMany (mappedBy = "requestedUserId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FriendRequest> friendRequests = new ArrayList<>();
    @OneToMany (mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> friends = new ArrayList<>();

    public UserEntity(String username, String email, String password, Gender gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = Status.ACTIVE;
    }

    public UserEntity(String email) {
        this.email = email;
    }

    public enum Gender {
        MALE, FEMALE
    }

    public enum Status{
        ACTIVE, WITHDRAWN
    }

}

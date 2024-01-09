package team2.bookbridge.domain.User.domain;


import lombok.NoArgsConstructor;
import  team2.bookbridge.domain.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column(nullable = true)
    private  String registeration_number;

    @Column
    private Role role;
    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;


}

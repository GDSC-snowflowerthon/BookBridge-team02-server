package team2.bookbridge.domain.User.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import  team2.bookbridge.domain.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private  String registration_number;

    @Column(nullable = false)
    private Role role;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;


}

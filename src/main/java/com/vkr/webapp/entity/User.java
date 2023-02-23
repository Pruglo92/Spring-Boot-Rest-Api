package com.vkr.webapp.entity;

import com.sun.istack.NotNull;
import com.vkr.webapp.entity.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(exclude = {"savedResults"}, callSuper = true)
@ToString(exclude = {"savedResults"}, callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    @Column
    private String username;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SavedResult> savedResults;

    @NotNull
    @CreatedDate
    @Column
    private LocalDateTime dateOfCreated;

    @NotNull
    @LastModifiedDate
    @Column
    private LocalDateTime dateOfLastVisit;
}

package com.epam.esm.entity;

import com.epam.esm.Role;
import com.epam.esm.Status;
import com.epam.esm.listener.GeneralEntityListener;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.Instant;

@javax.persistence.Entity
@Table(name = "users", schema="mjs")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@EntityListeners(GeneralEntityListener.class)
@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email=:email")
public class User extends com.epam.esm.entity.Entity<Long> {

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "create_date", columnDefinition = "TIMESTAMP")
    private Instant createDate;
    @Column(name = "last_update_date", columnDefinition = "TIMESTAMP")
    private Instant lastUpdateDate;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

}

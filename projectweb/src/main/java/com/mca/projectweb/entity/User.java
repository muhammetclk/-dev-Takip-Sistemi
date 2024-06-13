package com.mca.projectweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_email"),
        @UniqueConstraint(columnNames = "user_number")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    long userId;


    @Column(name="user_firstname",nullable = false)
    String userFirstname;


    @Column(name="user_lastname",nullable = false)
    String userLastname;

    @Column(name="user_number",nullable = false)
    String userNumber;


    @Column(name="user_email",nullable = false)
    String userEmail;


    @Column(name="user_password",nullable = false)
    String userPassword;
    @Column(name="user_faculty",nullable = false)
    String userFaculty;

   /* @Column(name="user_role",nullable = false)
    String userRole;*/

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade =
            CascadeType.ALL, orphanRemoval = true)
    private List<File> files;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade =
            CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments;


    @ManyToOne
    @JoinColumn(name = "role_Id", referencedColumnName = "role_Id")
    private Role role;

}

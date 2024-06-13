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
@Table(name = "roles" )
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    long roleId;


    @Column(name="role_name",nullable = false)
    String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade =
            CascadeType.ALL, orphanRemoval = true)
    private List<User> user;





}

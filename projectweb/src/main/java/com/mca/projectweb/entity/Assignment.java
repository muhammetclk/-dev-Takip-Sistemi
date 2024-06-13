package com.mca.projectweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assignments")
public class Assignment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="assignment_id")
    long assignmentId;


    @Column(name="name")
    String name;


    @Column(name="description")
    String description;
    @Column(name="deadline")
    Date deadline;


    @Column(name="created_at")
    String createdAt;


    @Column(name="active")
    Boolean active;





    /*@Column(name="user_id")
    Long userId;*/

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}

package com.mca.projectweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    long fileId;


    @Column(name="file_data")
    byte[] fileData;

  /*  @Column(name="user_id")
    long userId;*/

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "assignment_Id", referencedColumnName = "assignment_Id")
    private Assignment assignment;


    /*@Column(name="assignment_id")
    long assignmentId;*/






}

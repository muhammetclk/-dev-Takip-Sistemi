package com.mca.projectweb.dao;

import com.mca.projectweb.dto.AssignmentDTO;
import com.mca.projectweb.entity.Assignment;
import com.mca.projectweb.entity.File;

import com.mca.projectweb.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class AdminDAO {




    private final SessionFactory sessionFactory;

    @Autowired
    public AdminDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public Object loadObject(Class clazz, Serializable id) {
        return getSession().get(clazz, id);
    }



    public void createUser(User user) {
        System.err.println("saveFile dao" + user);
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // User'ı veritabanına kaydet
            session.save(user);

            // İşlemi commit et
            transaction.commit();

            System.err.println("User successfully saved: " + user);
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("Error saving user: " + e.getMessage());
        } finally {
            // Session'ı kapat
            session.close();
        }
    }

    public void addTeacherWork(AssignmentDTO assignmentDTO) {
        System.err.println("addTeacherWork dao");
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Assignment assignment = new Assignment();
            assignment.setName(assignmentDTO.getName());
            assignment.setDescription(assignmentDTO.getDescription());
            assignment.setCreatedAt(assignmentDTO.getCreatedAt());
            assignment.setDeadline(assignmentDTO.getDeadline());
            assignment.setActive(assignmentDTO.getActive());

            // User entity'sini bul ve ata
            User user = session.get(User.class, assignmentDTO.getUserId());
            if (user != null) {
                assignment.setUser(user);

                // Assignment'i veritabanına kaydet
                session.save(assignment);

                // İşlemi commit et
                transaction.commit();

                System.err.println("saveFile dao sonu");
            } else {
                System.err.println("User bulunamadı.");
                transaction.rollback();
            }
        } finally {
            // Session'ı kapat
            System.err.println("saveFile dao finally");
            session.close();
        }
    }

}

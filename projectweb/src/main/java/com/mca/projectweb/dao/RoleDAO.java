package com.mca.projectweb.dao;
/*
import com.mca.projectweb.entity.Assignment;
import com.mca.projectweb.entity.File;
import com.mca.projectweb.entity.Role;
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

@Repository
public class RoleDAO {




    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public Object loadObject(Class clazz, Serializable id) {
        return getSession().get(clazz, id);
    }

    public Role findById(long id) {
        System.err.println("saveFile dao" + file);
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {

            Role role = new Role();

            role.setRoleId(id);
            // Assignment ve User entity'lerini bulun ve ata
            Assignment assignment = session.get(Assignment.class, assignmentId);
            User user = session.get(User.class, userId);

            if (assignment != null && user != null) {
                filee.setAssignment(assignment);
                filee.setUser(user);

                // File'ı veritabanına kaydet
                session.save(filee);

                // İşlemi commit et
                transaction.commit();

                System.err.println("saveFile dao sonu" + file);
            } else {
                // Assignment veya User bulunamazsa, işlemi geri al
                transaction.rollback();
                System.err.println("Assignment veya User bulunamadı.");
            }
        } finally {
            // Session'ı kapat
            System.err.println("saveFile dao finally" + file);
            session.close();
        }


    }*/
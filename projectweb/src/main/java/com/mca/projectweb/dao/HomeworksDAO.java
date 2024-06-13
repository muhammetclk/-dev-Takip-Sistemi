package com.mca.projectweb.dao;


import com.mca.projectweb.entity.File;

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
public class HomeworksDAO {




    private final SessionFactory sessionFactory;

    @Autowired
    public HomeworksDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public Object loadObject(Class clazz, Serializable id) {
        return getSession().get(clazz, id);
    }



    public List<File> getHomeworks(Long userId) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<File> criteriaQuery = criteriaBuilder.createQuery(File.class);
        Root<File> root = criteriaQuery.from(File.class);

        // Öğretmen nesnesine göre filtrele
        //Predicate teacherPredicate = criteriaBuilder.equal(root.get("teacher").get("teacherId"), teacherId);
        Predicate userPredicate = criteriaBuilder.equal(root.get("user").get("userId"), userId);

        // Filtreyi sorguya ekle
        criteriaQuery.select(root).where(userPredicate);

        // Sorguyu oluştur
        Query<File> query = session.createQuery(criteriaQuery);

        List<File> files = query.getResultList();
        transaction.commit();
        return files;
    }

    public List<File> getSubmissions(Long assignmentId) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<File> criteriaQuery = criteriaBuilder.createQuery(File.class);
        Root<File> root = criteriaQuery.from(File.class);

        // Öğretmen nesnesine göre filtrele
        //Predicate teacherPredicate = criteriaBuilder.equal(root.get("teacher").get("teacherId"), teacherId);
        Predicate userPredicate = criteriaBuilder.equal(root.get("assignment").get("assignmentId"), assignmentId);
       // Predicate studentPredicate = criteriaBuilder.equal(root.get("assignmentId"), assignmentId);

        // Filtreyi sorguya ekle
        criteriaQuery.select(root).where(userPredicate);

        // Sorguyu oluştur
        Query<File> query = session.createQuery(criteriaQuery);

        List<File> files = query.getResultList();
        transaction.commit();
        return files;
    }







}

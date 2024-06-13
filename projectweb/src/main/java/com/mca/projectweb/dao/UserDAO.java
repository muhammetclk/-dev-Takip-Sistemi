package com.mca.projectweb.dao;


import com.mca.projectweb.entity.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
public class UserDAO {




    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public Object loadObject(Class clazz, Serializable id) {
        return getSession().get(clazz, id);
    }

    public User findUserByEmail(String userEmail) {
        System.err.println("dao katmanı findUserByEmail metodu içinde öğrenci için");
        Session session = getSession();
        Transaction transaction = session.beginTransaction(); // İşlem başlat
        System.err.println("dao katmanı session olusturuldu" + session);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        System.err.println("dao katmanı criteriabulder olusturuldu ");
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        // E-postaya göre bir filtre oluştur
        Predicate emailPredicate = criteriaBuilder.equal(root.get("userEmail"), userEmail);

        // Filtreyi sorguya ekle
        criteriaQuery.where(emailPredicate);

        // Sorguyu oluştur
        Query<User> query = session.createQuery(criteriaQuery);

        System.err.println("dao içinde findStudentByEmail metodu sonunda öğrenci için");
        // Sonuçları al
        User User = query.getSingleResultOrNull();
        transaction.commit(); // İşlemi sonlandır
        return User;
    }







    public User getUserProfile(String teacher) {

        System.err.println("dao katmanı getTeachersWithSearch metodu içinde");
        Session session = getSession();
        Transaction transaction = session.beginTransaction(); // İşlem başlat
        System.err.println("dao katmanı session olusturuldu" + session);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        System.err.println("dao katmanı criteriabulder olusturuldu ");
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        String email=teacher+"@mail.com";


        // ÖğretmenId'ye göre filtrele
        Predicate idPredicate = criteriaBuilder.equal(root.get("userEmail"), email);

        // Filtreyi sorguya ekle
        criteriaQuery.select(root).where(idPredicate);// Filtreyi sorguya ekle



        // Sorguyu oluştur
        Query<User> query = session.createQuery(criteriaQuery);

        System.err.println("dao içinde getTeachersWithSearch metodu sonunda");
        // Sonuçları al
        User user = query.getSingleResultOrNull();
        transaction.commit(); // İşlemi sonlandır
        return user;





    }


    public User findById(Long id) {

        System.err.println("dao katmanı getTeachersWithSearch metodu içinde");
        Session session = getSession();
        Transaction transaction = session.beginTransaction(); // İşlem başlat
        System.err.println("dao katmanı session olusturuldu" + session);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        System.err.println("dao katmanı criteriabulder olusturuldu ");
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);



        // ÖğretmenId'ye göre filtrele
        Predicate idPredicate = criteriaBuilder.equal(root.get("userId"), id);

        // Filtreyi sorguya ekle
        criteriaQuery.select(root).where(idPredicate);// Filtreyi sorguya ekle



        // Sorguyu oluştur
        Query<User> query = session.createQuery(criteriaQuery);

        System.err.println("dao içinde getTeachersWithSearch metodu sonunda");
        // Sonuçları al
        User student = query.getSingleResultOrNull();
        transaction.commit(); // İşlemi sonlandır
        return student;





    }




}

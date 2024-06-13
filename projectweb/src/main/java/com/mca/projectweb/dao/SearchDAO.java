package com.mca.projectweb.dao;


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
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
public class SearchDAO {




    private final SessionFactory sessionFactory;

    @Autowired
    public SearchDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public Object loadObject(Class clazz, Serializable id) {
        return getSession().get(clazz, id);
    }



    public List<User> getTeachersWithSearch(String search) {
        System.err.println("dao katmanı getTeachersWithSearch metodu içinde");
        Session session = getSession();
        Transaction transaction = session.beginTransaction(); // İşlem başlat
        System.err.println("dao katmanı session olusturuldu" + session);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        System.err.println("dao katmanı criteriabulder olusturuldu ");
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        // Mevcut arama filtreleri
        Predicate firstNamePredicate = criteriaBuilder.like(root.get("userFirstname"), "%" + search + "%");
        Predicate lastNamePredicate = criteriaBuilder.like(root.get("userLastname"), "%" + search + "%");
        Predicate firstNameOrLastNamePredicate = criteriaBuilder.or(firstNamePredicate, lastNamePredicate);

        // user_role = ROLE_TEACHER koşulu
        Predicate rolePredicate = criteriaBuilder.equal(root.get("role").get("roleName"), "ROLE_TEACHER");

        // Tüm filtreleri birleştirme
        Predicate finalPredicate = criteriaBuilder.and(firstNameOrLastNamePredicate, rolePredicate);
        criteriaQuery.where(finalPredicate);

        // Sorguyu oluştur
        Query<User> query = session.createQuery(criteriaQuery);
        System.err.println("dao içinde getTeachersWithSearch metodu sonunda");

        // Sonuçları al
        List<User> teachers = query.getResultList();
        transaction.commit(); // İşlemi sonlandır
        return teachers;
    }



}

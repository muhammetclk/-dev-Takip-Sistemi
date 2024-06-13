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
public class AssignmentDAO {




    private final SessionFactory sessionFactory;

    @Autowired
    public AssignmentDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public Object loadObject(Class clazz, Serializable id) {
        return getSession().get(clazz, id);
    }



    public List<Assignment> getTeachersWorks(String email) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Assignment> criteriaQuery = criteriaBuilder.createQuery(Assignment.class);
        Root<Assignment> root = criteriaQuery.from(Assignment.class);


        Predicate userPredicate = criteriaBuilder.equal(root.get("user").get("userEmail"), email);

        // Filtreyi sorguya ekle
        criteriaQuery.select(root).where(userPredicate);

        // Sorguyu oluştur
        Query<Assignment> query = session.createQuery(criteriaQuery);

        List<Assignment> assignments = query.getResultList();
        transaction.commit();
        return assignments;
    }
    public Assignment getProjectDetail(Long assignmentId) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Assignment> criteriaQuery = criteriaBuilder.createQuery(Assignment.class);
        Root<Assignment> root = criteriaQuery.from(Assignment.class);

        // Öğretmen nesnesine göre filtrele
       // Predicate userPredicate = criteriaBuilder.equal(root.get("user").get("assignmentId"), assignmentId);
        Predicate userPredicate = criteriaBuilder.equal(root.get("assignmentId"), assignmentId);

        // Filtreyi sorguya ekle
        criteriaQuery.select(root).where(userPredicate);

        // Sorguyu oluştur
        Query<Assignment> query = session.createQuery(criteriaQuery);

        Assignment assignment = query.getSingleResultOrNull();
        transaction.commit();
        return assignment;
    }

    public void saveFile(byte[] file, long assignmentId, long userId) {
        System.err.println("saveFile dao" + file);
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Dosya verisini byte dizisine dönüştür

            // Yeni bir File oluştur ve dosya verisini ata
            File filee = new File();
            filee.setFileData(file);

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

package com.hibernate.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.OneToOne.model.Student;
import com.hibernate.OneToOne.model.University;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            University newUniversity = new University();
            Student newStudent = new Student();

            newUniversity.setUniversityName("Osmania");
            newUniversity.setLocation("HYD");
            newUniversity.setStudent(newStudent);

            newStudent.setName("Upendar");
            newStudent.setUniversity(newUniversity);

            session.save(newUniversity);
            session.save(newStudent);

            session.getTransaction().commit();

            // Clear the session after committing
            session.clear();

            // Retrieve data from the database
            University retrievedUniversity = session.get(University.class, newUniversity.getId());
            Student retrievedStudent = session.get(Student.class, newStudent.getStudentId());

            System.out.println("Retrieved Student: " + retrievedStudent.getName());
            System.out.println("Student's University: " + retrievedStudent.getUniversity().getUniversityName());
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}

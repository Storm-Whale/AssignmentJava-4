package com.whale.buoi_n_1.repositories;

import com.whale.buoi_n_1.Utils.HibernateUtil;
import com.whale.buoi_n_1.entities.DanhMuc;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DanhMuc_Repository {
    private Session session;

    public List<DanhMuc> getAll() {
        List<DanhMuc> listDanhMuc;
        session = HibernateUtil.getFACTORY().openSession();
        listDanhMuc = (List<DanhMuc>) session.createQuery("FROM  DanhMuc order by ngayTao asc ").list();
        session.close();
        return listDanhMuc;
    }

    public DanhMuc findById(int id) {
        session = HibernateUtil.getFACTORY().openSession();
        DanhMuc danhMuc = session.find(DanhMuc.class, id);
        return danhMuc;
    }

    public void createDanhMuc(DanhMuc danhMuc) {
        session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(danhMuc);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
    }
    public void updateDanhMuc(DanhMuc newDanhMuc) {
        session = HibernateUtil.getFACTORY().openSession();
        try {
            session.getTransaction().begin();
            session.update(newDanhMuc);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    public void deleteDanhMuc(DanhMuc oldDanhMuc) {
        session = HibernateUtil.getFACTORY().openSession();
        try {
            session.getTransaction().begin();
            session.remove(oldDanhMuc);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}

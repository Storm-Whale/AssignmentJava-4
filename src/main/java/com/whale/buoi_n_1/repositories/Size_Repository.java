package com.whale.buoi_n_1.repositories;

import com.whale.buoi_n_1.Utils.HibernateUtil;
import com.whale.buoi_n_1.entities.Size;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Size_Repository {
    private Session session;

    public List<Size> getAll() {
        List<Size> listMauSac;
        String hql = "Select entity From Size entity";
        session = HibernateUtil.getFACTORY().openSession();
        listMauSac = session.createQuery(hql, Size.class).list();
        session.close();
        return listMauSac;
    }

    public Size findById(int id) {
        session = HibernateUtil.getFACTORY().openSession();
        Size size = session.find(Size.class, id);
        return size;
    }

    public void createSize(Size size) {
        session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(size);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
    }

    public void updateSize(Size size) {
        session = HibernateUtil.getFACTORY().openSession();
        try {
            session.getTransaction().begin();
            session.update(size);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void deleteSize(Size oldSize) {
        session = HibernateUtil.getFACTORY().openSession();
        try {
            session.getTransaction().begin();
            session.remove(oldSize);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Size> listMauSac = new Size_Repository().getAll();
        for (Size size : listMauSac) {
            System.out.println(listMauSac.toString());
        }
    }
}

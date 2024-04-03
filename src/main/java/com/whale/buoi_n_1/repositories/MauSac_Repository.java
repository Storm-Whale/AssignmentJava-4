package com.whale.buoi_n_1.repositories;

import com.whale.buoi_n_1.Utils.HibernateUtil;
import com.whale.buoi_n_1.entities.MauSac;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MauSac_Repository {
    private Session session;

    public List<MauSac> getAll() {
        List<MauSac> listMauSac;
        session = HibernateUtil.getFACTORY().openSession();
        listMauSac = session.createQuery("from MauSac").list();
        session.close();
        return listMauSac;
    }

    public MauSac findById(int id) {
        session = HibernateUtil.getFACTORY().openSession();
        MauSac mauSac = session.find(MauSac.class, id);
        return mauSac;
    }

    public void createMauSac(MauSac mauSac) {
        session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(mauSac);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
    }

    public void updateMauSac(MauSac newMauSac) {
        session = HibernateUtil.getFACTORY().openSession();
        try {
            session.getTransaction().begin();
            session.update(newMauSac);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void deleteMauSac(MauSac oldMauSac) {
        session = HibernateUtil.getFACTORY().openSession();
        try {
            session.getTransaction().begin();
            session.remove(oldMauSac);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<MauSac> listMauSac = new MauSac_Repository().getAll();
        for (MauSac ms : listMauSac) {
            System.out.println(ms.toString());
        }
    }
}

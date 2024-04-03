package com.whale.buoi_n_1.repositories;

import com.whale.buoi_n_1.Utils.HibernateUtil;
import com.whale.buoi_n_1.entities.SanPham;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

public class SanPham_Repository {
    private Session session;

    public SanPham_Repository(){
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<SanPham> getAll(){
        String hql = "Select entity FROM SanPham entity";
        Query query = session.createQuery(hql, SanPham.class);
        return query.getResultList();
    }

    public SanPham findById(int id){
        return session.find(SanPham.class, id);
    }

    public void createSp (SanPham newSanPham){
        try {
            session.getTransaction().begin();
            session.persist(newSanPham);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void updateSP(SanPham newSanPham){
        try {
            session.getTransaction().begin();
            session.merge(newSanPham);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void deleteSP(SanPham oldSanPham){
        try {
            session.getTransaction().begin();
            session.remove(oldSanPham);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public static void main(String[] args) {
        List<SanPham> listSanPham = new SanPham_Repository().getAll();
        for (SanPham sp : listSanPham){
            System.out.println(sp.toString());
        }
    }
}

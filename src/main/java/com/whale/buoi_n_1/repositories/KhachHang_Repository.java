package com.whale.buoi_n_1.repositories;

import com.whale.buoi_n_1.Utils.HibernateUtil;
import com.whale.buoi_n_1.entities.KhachHang;
import org.hibernate.Session;

import java.util.List;

public class KhachHang_Repository {
    Session session;

    public KhachHang_Repository() {
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<KhachHang> findAll() {
        String query = "From KhachHang";
        List<KhachHang> khachHangList = (List<KhachHang>)
                session.createQuery(query).list();
        return khachHangList;
    }

    public KhachHang findById(int id){
        String query = "from KhachHang Where id = :id_1";
        KhachHang value = (KhachHang) session.createQuery(query).setParameter("id_1", id).getSingleResult();
        return value;
    }

    public KhachHang findBySDT(String sdt){
        String query = "from KhachHang Where sdt = :sdt";
        KhachHang value = (KhachHang) session.createQuery(query).setParameter("sdt", sdt).getSingleResult();
        return value;
    }

    public void createKhachHang(KhachHang newKhachHang) {
        try {
            session.getTransaction().begin();
            session.persist(newKhachHang);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void updateKhachHang(KhachHang newKhachHang) {
        try {
            session.getTransaction().begin();
            session.merge(newKhachHang);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void deleteKhachHang(KhachHang oldKhachHang){
        try {
            session.getTransaction().begin();
            session.remove(oldKhachHang);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}

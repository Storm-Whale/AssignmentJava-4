package com.whale.buoi_n_1.repositories;

import com.whale.buoi_n_1.Utils.HibernateUtil;
import com.whale.buoi_n_1.entities.HoaDonChiTiet;
import org.hibernate.Session;

import java.util.List;

public class HoaDonChiTiet_Repository {
    Session session;

    public HoaDonChiTiet_Repository() {
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<HoaDonChiTiet> findAll() {
        String query = "From HoaDonChiTiet";
        List<HoaDonChiTiet> listHoaDonCT = (List<HoaDonChiTiet>) session.createQuery(query).list();
        return listHoaDonCT;
    }

    public List<HoaDonChiTiet> findByIdHD(int idHD) {
        String query = "From HoaDonChiTiet WHERE hoaDon.id = :idHD";
        List<HoaDonChiTiet> listHoaDonCT = (List<HoaDonChiTiet>) session.createQuery(query)
                .setParameter("idHD", idHD).list();
        return listHoaDonCT;
    }

    public HoaDonChiTiet findByIdSpct(int idSpct, int idHD) {
        String query = "From HoaDonChiTiet WHERE sanPhamChiTiet.id = :idSpct AND hoaDon.id = :idHD";
        HoaDonChiTiet listHoaDonCT = (HoaDonChiTiet) session.createQuery(query)
                .setParameter("idSpct", idSpct)
                .setParameter("idHD", idHD)
                .getSingleResult();
        return listHoaDonCT;
    }

    public HoaDonChiTiet findById(int id) {
        String query = "From HoaDonChiTiet WHERE id = :id";
        HoaDonChiTiet listHoaDonCT = (HoaDonChiTiet) session.createQuery(query)
                .setParameter("id", id)
                .getSingleResult();
        return listHoaDonCT;
    }

    public void createHdct(HoaDonChiTiet newHdct){
        try {
            session.getTransaction().begin();
            session.persist(newHdct);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void updateHdct(HoaDonChiTiet newHdct){
        try {
            session.getTransaction().begin();
            session.merge(newHdct);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void deleteHdct(HoaDonChiTiet oldHdct){
        try {
            session.getTransaction().begin();
            session.remove(oldHdct);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}

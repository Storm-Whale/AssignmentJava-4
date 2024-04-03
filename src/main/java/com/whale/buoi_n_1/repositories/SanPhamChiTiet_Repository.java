package com.whale.buoi_n_1.repositories;

import com.whale.buoi_n_1.Utils.HibernateUtil;
import com.whale.buoi_n_1.entities.SanPhamChiTiet;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

public class SanPhamChiTiet_Repository {
    private Session session;

    public SanPhamChiTiet_Repository() {
        this.session = HibernateUtil.getFACTORY().openSession();
        ;
    }

    public List<SanPhamChiTiet> getAll() {
        String hql = "SELECT entity FROM SanPhamChiTiet entity";
        Query query = this.session.createQuery(hql, SanPhamChiTiet.class);
        return query.getResultList();
    }

    public List<SanPhamChiTiet> findByIdSPAndIdHD(int id) {
        List<SanPhamChiTiet> listSpCTById = (List<SanPhamChiTiet>)
                session.createQuery("from SanPhamChiTiet where sanPham.id = :id_1")
                        .setParameter("id_1", id).list();

        return listSpCTById;
    }

    public SanPhamChiTiet findById(int id) {
        SanPhamChiTiet value = (SanPhamChiTiet) session.createQuery("from SanPhamChiTiet where id = :id_1")
                .setParameter("id_1", id).getSingleResult();
        return value;
    }

    public List<SanPhamChiTiet> findByTrangThai() {
        List<SanPhamChiTiet> listSpCTById = (List<SanPhamChiTiet>)
                session.createQuery("from SanPhamChiTiet where trangThai = 'Active'").list();
        return listSpCTById;
    }

    public void updateSpct(SanPhamChiTiet newSpct) {
        try {
            session.getTransaction().begin();
            session.merge(newSpct);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void createSpct(SanPhamChiTiet newSpct) {
        try {
            session.getTransaction().begin();
            session.persist(newSpct);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void deleteSpct(SanPhamChiTiet oldSpct) {
        try {
            session.getTransaction().begin();
            session.remove(oldSpct);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}

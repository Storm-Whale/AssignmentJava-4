package com.whale.buoi_n_1.repositories;

import com.whale.buoi_n_1.Utils.HibernateUtil;
import com.whale.buoi_n_1.entities.HoaDon;
import org.hibernate.Session;

import java.util.List;

public class HoaDon_Repository {
    Session session;
    public HoaDon_Repository(){
        session = HibernateUtil.getFACTORY().openSession();
    }
    public List<HoaDon> findAll() {
        String query = "From HoaDon";
        List<HoaDon> listHoaDon = (List<HoaDon>)
                session.createQuery(query).list();
        return listHoaDon;
    }

    public HoaDon findById(int id){
        String query = "from HoaDon Where id = :id_1";
        HoaDon value = (HoaDon) session.createQuery(query).setParameter("id_1", id).getSingleResult();
        return value;
    }


    public HoaDon findBySdt(String sdt){
        String query = "from HoaDon Where soDienThoai = :sdt";
        HoaDon value = (HoaDon) session.createQuery(query).setParameter("sdt", sdt).getSingleResult();
        return value;
    }

    public List<HoaDon> findByTrangThai() {
        List<HoaDon> listHD = (List<HoaDon>)
                session.createQuery("from HoaDon where trangThai = 'Inactive'").list();
        return listHD;
    }

    public void createHoaDon(HoaDon newHoaDon) {
        try {
            session.getTransaction().begin();
            session.persist(newHoaDon);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void updateHoaDon(HoaDon newHoaDon) {
        try {
            session.getTransaction().begin();
            session.merge(newHoaDon);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void deleteHoaDon(HoaDon oldHoaDon){
        try {
            session.getTransaction().begin();
            session.remove(oldHoaDon);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}

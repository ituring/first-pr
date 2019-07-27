package xyz.xlong99.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import xyz.xlong99.domain.AttentionEntity;
import xyz.xlong99.domain.WiKiEntity;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

import static xyz.xlong99.domain.Main.getSession;

@Repository
public class AttentionDao {
    public void send(AttentionEntity attentionEntity){
        attentionEntity.setAttentionDate(new Timestamp(System.currentTimeMillis()));
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.save(attentionEntity);
        tran.commit();
        session.close();
    }

    public void delete(AttentionEntity attentionEntity){
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.delete(attentionEntity);
        tran.commit();
        session.close();
    }

    public List<AttentionEntity> getAttentionByReceiver(AttentionEntity attentionEntity){
        final Session session = getSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("from AttentionEntity where receiver = :id order by attentionDate desc ").setParameter("id",attentionEntity.getReceiver());
        List list = ((org.hibernate.query.Query) query).list();
        t.commit();
        session.close();
        return list;
    }



}

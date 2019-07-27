package xyz.xlong99.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import xyz.xlong99.domain.CommentEntity;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

import static xyz.xlong99.domain.Main.getSession;

@Repository
public class CommentDao {

    public void upload(CommentEntity commentEntity){
        commentEntity.setCommentDate(new Timestamp(System.currentTimeMillis()));
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.save(commentEntity);
        tran.commit();
        session.close();
    }

    public List<CommentEntity> getCommentByCid(CommentEntity commentEntity){

        final Session session = getSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery
                ("from CommentEntity where cid = :cid  and wid = :wid order by commentDate desc ")
                .setParameter("cid",commentEntity.getCid()).setParameter("wid",commentEntity.getWid());
        List list = ((org.hibernate.query.Query) query).list();
        t.commit();
        session.close();
        return list;
    }
    public List<CommentEntity> getCommentByWid(CommentEntity commentEntity){

        final Session session = getSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery
                ("from CommentEntity where cid = 0 and wid =:wid order by commentDate desc ")
                .setParameter("wid",commentEntity.getWid());
        List list = ((org.hibernate.query.Query) query).list();
        t.commit();
        session.close();
        return list;
    }

    public void delete(CommentEntity commentEntity){
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.delete(commentEntity);
        tran.commit();
        session.close();
    }

}

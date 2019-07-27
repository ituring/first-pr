package xyz.xlong99.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import xyz.xlong99.domain.DraftEntity;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

import static xyz.xlong99.domain.Main.getSession;

@Repository
public class DraftDao {
    public String upload(DraftEntity draftEntity){
        if (draftEntity.getImages()!=null) {
            draftEntity.setImagesTemp(formatString(draftEntity.getImages()));
        }
        if (draftEntity.getTag()!=null) {
            draftEntity.setTagTemp(formatString(draftEntity.getTag()));
        }
        draftEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.save(draftEntity);
        tran.commit();
        session.close();
        return null;
    }

    public String delete(DraftEntity draftEntity){
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.delete(draftEntity);
        tran.commit();
        session.close();
        return null;
    }

    public List<DraftEntity> getDraftsByAuthorId(DraftEntity draftEntity1){
        final Session session = getSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("from DraftEntity where authorId = :id order by createTime desc ").setParameter("id",draftEntity1.getAuthorId());
        List<DraftEntity> list = ((org.hibernate.query.Query) query).list();
        t.commit();
        for (DraftEntity draftEntity:list){
            if (draftEntity.getImagesTemp()!=null){
                draftEntity.setImages(draftEntity.getImagesTemp().split("#"));
                draftEntity.setImagesTemp(null);
            }
            if (draftEntity.getTagTemp()!=null){
                draftEntity.setTag(draftEntity.getTagTemp().split("#"));
                draftEntity.setTagTemp(null);
            }
        }
        session.close();
        return list;
    }

    public String update(DraftEntity draftEntity){
        if (draftEntity.getImages()!=null) {
            draftEntity.setImagesTemp(formatString(draftEntity.getImages()));
        }
        if (draftEntity.getTag()!=null) {
            draftEntity.setTagTemp(formatString(draftEntity.getTag()));
        }
        draftEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.update(draftEntity);
        tran.commit();
        session.close();
        return null;
    }

    public Object getDraftById(DraftEntity draftEntity){

        final Session session = getSession();
//        Transaction tx = session.beginTransaction();

        //1.创建CriteriaBuilder对象
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        //2.获取CriteriaQuery对象
        CriteriaQuery<DraftEntity> createQuery = criteriaBuilder.createQuery(DraftEntity.class);

        //3.指定根条件
        Root<DraftEntity> root = createQuery.from(DraftEntity.class);

        //获取用户id

        createQuery.where((criteriaBuilder.equal(root.get("wid"), draftEntity.getWid())));

        //4执行查询
        List<DraftEntity> List = session.createQuery(createQuery).getResultList();
        session.close();
        if (List.get(0)!=null) {
            if (List.get(0).getTagTemp()!=null){
                List.get(0).setTag(List.get(0).getTagTemp().split("#"));
                List.get(0).setTagTemp(null);
            }
            if (List.get(0).getImagesTemp()!=null){
                List.get(0).setImages(List.get(0).getImagesTemp().split("#"));
                List.get(0).setImagesTemp(null);
            }
            return List.get(0);
        }
        else{
            return "未查询到";
        }
    }

    public String formatString(String[] strings){
        String temp=strings[0];
        for (int i=1;i<strings.length;i++){
            temp=temp+"#"+strings[i];
        }
        return temp;
    }
}

package xyz.xlong99.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import xyz.xlong99.domain.QueryWiki;
import xyz.xlong99.domain.WiKiEntity;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

import static xyz.xlong99.domain.Main.getSession;

@Repository
public class WiKiDao {

    public String upload(WiKiEntity wiKiEntity){
        if (wiKiEntity.getImages()!=null) {
            wiKiEntity.setImagesTemp(formatString(wiKiEntity.getImages()));
        }
        if (wiKiEntity.getTag()!=null) {
            wiKiEntity.setTagTemp(formatString(wiKiEntity.getTag()));
        }
        wiKiEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.save(wiKiEntity);
        tran.commit();
        session.close();
        return null;
    }

    public String addLike(QueryWiki queryWiki){
        WiKiEntity wiKiEntity = (WiKiEntity) getWiki(queryWiki);
        wiKiEntity.setLikeCount(wiKiEntity.getLikeCount()+1);
        if (wiKiEntity.getLikePersonTemp()!=null) {

            String string =wiKiEntity.getLikePersonTemp()+"#"+queryWiki.getUid();

            wiKiEntity.setLikePersonTemp(string);
        }else {
            wiKiEntity.setLikePersonTemp(queryWiki.getUid());
        }
        update(wiKiEntity);
        return null;
    }


    public String delete(QueryWiki queryWiki){
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.delete(getWiki(queryWiki));
        tran.commit();
        session.close();
        return null;
    }

    public String update(WiKiEntity wiKiEntity){
        if (wiKiEntity.getImages()!=null) {
            wiKiEntity.setImagesTemp(formatString(wiKiEntity.getImages()));
        }
        if (wiKiEntity.getTag()!=null) {
            wiKiEntity.setTagTemp(formatString(wiKiEntity.getTag()));
        }
        wiKiEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        final Session session=getSession();
        Transaction tran =session.beginTransaction();
        session.update(wiKiEntity);
        tran.commit();
        session.close();
        return null;
    }

    public Object getWiki(QueryWiki queryWiki){

        final Session session = getSession();
//        Transaction tx = session.beginTransaction();

        //1.创建CriteriaBuilder对象
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        //2.获取CriteriaQuery对象
        CriteriaQuery<WiKiEntity> createQuery = criteriaBuilder.createQuery(WiKiEntity.class);

        //3.指定根条件
        Root<WiKiEntity> root = createQuery.from(WiKiEntity.class);

        //获取用户id

            createQuery.where((criteriaBuilder.equal(root.get("wid"), queryWiki.getWid())));

        //4执行查询
        List<WiKiEntity> List = session.createQuery(createQuery).getResultList();
        session.close();
        if (List.get(0).isIfPublic()||List.get(0).getAuthorId().equals(queryWiki.getUid())) {
            if (List.get(0) != null) {
                if (List.get(0).getTagTemp() != null) {
                    List.get(0).setTag(List.get(0).getTagTemp().split("#"));
                }
                if (List.get(0).getImagesTemp() != null) {
                    List.get(0).setImages(List.get(0).getImagesTemp().split("#"));
                }
                if (List.get(0).getLikePersonTemp() != null) {
                    List.get(0).setLikePerson(List.get(0).getLikePersonTemp().split("#"));
                }
                if (queryWiki.getUid() != null && List.get(0).getLikePersonTemp() != null) {
                    List.get(0).setLikePerson(List.get(0).getLikePersonTemp().split("#"));
                    for (String i : List.get(0).getLikePerson()) {
                        if (i.equals(queryWiki.getUid())) {
                            List.get(0).setIfAddLike(true);
                        }
                    }
                }
                return List.get(0);
            } else {
                return "未查询到";
            }
        }
        else {
            return "对方设置为私密";
        }
    }

    public List<WiKiEntity> getWikisByAuthorId(QueryWiki queryWiki){
        final Session session = getSession();
//        Transaction tx = session.beginTransaction();

        //1.创建CriteriaBuilder对象
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        //2.获取CriteriaQuery对象
        CriteriaQuery<WiKiEntity> createQuery = criteriaBuilder.createQuery(WiKiEntity.class);

        //3.指定根条件
        Root<WiKiEntity> root = createQuery.from(WiKiEntity.class);

        //获取用户id

        createQuery.where((criteriaBuilder.equal(root.get("authorId"), queryWiki.getAuthorId())));
        boolean b;
        if (!queryWiki.getAuthorId().equals(queryWiki.getUid())){
            b=true;
            createQuery.where((criteriaBuilder.equal(root.get("ifPublic"), b)));
        }

        //4执行查询
        List<WiKiEntity> List = session.createQuery(createQuery).getResultList();
        for (WiKiEntity wiKiEntity:List){
            if (wiKiEntity.getLikePersonTemp()!=null){
                wiKiEntity.setLikePerson(wiKiEntity.getLikePersonTemp().split("#"));

            }
            if (wiKiEntity.getImagesTemp()!=null){
               wiKiEntity.setImages(wiKiEntity.getImagesTemp().split("#"));

           }
            if (wiKiEntity.getTagTemp()!=null){
               wiKiEntity.setTag(wiKiEntity.getTagTemp().split("#"));

           }
            if (queryWiki.getUid()!=null&&wiKiEntity.getLikePersonTemp()!=null){
                wiKiEntity.setLikePerson(wiKiEntity.getLikePersonTemp().split("#"));
                for (String i:wiKiEntity.getLikePerson()){
                    if (i.equals(queryWiki.getUid())){
                        wiKiEntity.setIfAddLike(true);
                    }
                }
            }
        }
        session.close();
        return List;
    }

    public List<WiKiEntity> getAllPublicWikis(QueryWiki queryWiki){
        final Session session = getSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("from WiKiEntity where ifPublic = true order by createTime desc  ");
        List<WiKiEntity> list = ((org.hibernate.query.Query) query).list();
        t.commit();
        for (WiKiEntity wiKiEntity:list){
            if (wiKiEntity.getImagesTemp()!=null){
                wiKiEntity.setImages(wiKiEntity.getImagesTemp().split("#"));

            }
            if (wiKiEntity.getTagTemp()!=null){
                wiKiEntity.setTag(wiKiEntity.getTagTemp().split("#"));

            }
            if (wiKiEntity.getLikePersonTemp()!=null){
                wiKiEntity.setLikePerson(wiKiEntity.getLikePersonTemp().split("#"));

            }
            if (queryWiki.getUid()!=null&&wiKiEntity.getLikePersonTemp()!=null){
                wiKiEntity.setLikePerson(wiKiEntity.getLikePersonTemp().split("#"));
                for (String i:wiKiEntity.getLikePerson()){
                    if (i.equals(queryWiki.getUid())){
                        wiKiEntity.setIfAddLike(true);
                    }
                }
            }
        }
        session.close();
        return list;
    }

    public boolean ifPublic(QueryWiki queryWiki){

        final Session session = getSession();
//        Transaction tx = session.beginTransaction();

        //1.创建CriteriaBuilder对象
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        //2.获取CriteriaQuery对象
        CriteriaQuery<WiKiEntity> createQuery = criteriaBuilder.createQuery(WiKiEntity.class);

        //3.指定根条件
        Root<WiKiEntity> root = createQuery.from(WiKiEntity.class);

        //获取用户id

        createQuery.where((criteriaBuilder.equal(root.get("wid"), queryWiki.getWid())));

        //4执行查询
        List<WiKiEntity> List = session.createQuery(createQuery).getResultList();
        session.close();
        return List.get(0).isIfPublic();
    }

    public boolean ifAuthor(QueryWiki queryWiki){

        final Session session = getSession();
//        Transaction tx = session.beginTransaction();

        //1.创建CriteriaBuilder对象
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        //2.获取CriteriaQuery对象
        CriteriaQuery<WiKiEntity> createQuery = criteriaBuilder.createQuery(WiKiEntity.class);

        //3.指定根条件
        Root<WiKiEntity> root = createQuery.from(WiKiEntity.class);

        //获取用户id

        createQuery.where((criteriaBuilder.equal(root.get("wid"), queryWiki.getWid())));

        //4执行查询
        List<WiKiEntity> List = session.createQuery(createQuery).getResultList();
        session.close();
        if (queryWiki.getUid().equals(List.get(0).getAuthorId())){
            return true;

        }else {
            return false;
        }


    }

    private String formatString(String[] strings){
        String temp=strings[0];
        for (int i=1;i<strings.length;i++){
            temp=temp+"#"+strings[i];
        }
        return temp;
    }


}

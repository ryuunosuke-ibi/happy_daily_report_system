package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.BusinessConverter;
import actions.views.BusinessView;
import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import constants.JpaConst;
import models.Business;
import models.validators.BusinessValidator;

/**
 * 商談テーブルの操作に関わる処理を行うクラス
 */
public class BusinessService extends ServiceBase {

    /**
     * 指定した従業員が作成した商談データを、指定されたページ数の一覧画面に表示する分取得しBusinessViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<BusinessView> getMinePerPage(EmployeeView employee, int page) {

        List<Business> businesss = em.createNamedQuery(JpaConst.Q_BUS_GET_ALL_MINE, Business.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return BusinessConverter.toViewList(businesss);
    }

    /**
     * 指定した従業員が作成した商談データの件数を取得し、返却する
     * @param employee
     * @return 商談データの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_BUS_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示する商談データを取得し、BusinessViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<BusinessView> getAllPerPage(int page) {

        List<Business> businesss = em.createNamedQuery(JpaConst.Q_BUS_GET_ALL, Business.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return BusinessConverter.toViewList(businesss);
    }

    /**
     * 商談テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long businesss_count = (long) em.createNamedQuery(JpaConst.Q_BUS_COUNT, Long.class)
                .getSingleResult();
        return businesss_count;
    }

    /**
     * idを条件に取得したデータをBusinessViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public BusinessView findOne(int id) {
        return BusinessConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された商談の登録内容を元にデータを1件作成し、商談テーブルに登録する
     * @param rv 商談の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(BusinessView bv) {
        List<String> errors = BusinessValidator.validate(bv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            bv.setCreatedAt(ldt);
            bv.setUpdatedAt(ldt);
            createInternal(bv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された商談の登録内容を元に、商談データを更新する
     * @param rv 商談の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(BusinessView bv) {

        //バリデーションを行う
        List<String> errors = BusinessValidator.validate(bv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            bv.setUpdatedAt(ldt);

            updateInternal(bv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Business findOneInternal(int id) {
        return em.find(Business.class, id);
    }

    /**
     * 商談データを1件登録する
     * @param rv 商談データ
     */
    private void createInternal(BusinessView bv) {

        em.getTransaction().begin();
        em.persist(BusinessConverter.toModel(bv));
        em.getTransaction().commit();

    }

    /**
     * 商談データを更新する
     * @param rv 商談データ
     */
    private void updateInternal(BusinessView bv) {

        em.getTransaction().begin();
        Business b = findOneInternal(bv.getId());
        BusinessConverter.copyViewToModel(b, bv);
        em.getTransaction().commit();

    }

}
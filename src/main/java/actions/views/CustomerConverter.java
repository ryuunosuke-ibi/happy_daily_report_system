package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Customer;

/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class CustomerConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param ev CustomerViewのインスタンス
     * @return Customerのインスタンス
     */
    public static Customer toModel(CustomerView cv) {

        return new Customer(
                cv.getId(),
                cv.getCode(),
                cv.getName(),
                cv.getPhone_number(),
                cv.getMail_adress(),
                cv.getCreatedAt(),
                cv.getUpdatedAt(),
                cv.getDeleteFlag() == null
                        ? null
                        : cv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                ? JpaConst.CUS_DEL_TRUE
                                : JpaConst.CUS_DEL_FALSE);
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Employeeのインスタンス
     * @return EmployeeViewのインスタンス
     */
    public static CustomerView toView(Customer c) {

        if(c == null) {
            return null;
        }

        return new CustomerView(
                c.getId(),
                c.getCode(),
                c.getName(),
                c.getPhone_number(),
                c.getMail_adress(),

                c.getCreatedAt(),
                c.getUpdatedAt(),
                c.getDeleteFlag() == null
                        ? null
                        : c.getDeleteFlag() == JpaConst.CUS_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<CustomerView> toViewList(List<Customer> list) {
        List<CustomerView> cvs = new ArrayList<>();

        for (Customer c : list) {
            cvs.add(toView(c));
        }

        return cvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(Customer c, CustomerView cv) {
        c.setId(cv.getId());
        c.setCode(cv.getCode());
        c.setName(cv.getName());
        c.setPhone_number(cv.getPhone_number());
        c.setMail_adress(cv.getMail_adress());
        c.setCreatedAt(cv.getCreatedAt());
        c.setUpdatedAt(cv.getUpdatedAt());
        c.setDeleteFlag(cv.getDeleteFlag());

    }

}
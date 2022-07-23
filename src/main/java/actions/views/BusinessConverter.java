package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Business;


/**
 * 日報データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class BusinessConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv BusinesstViewのインスタンス
     * @return Businessのインスタンス
     */
    public static Business toModel(BusinessView bv) {
        return new Business(
                bv.getId(),
                bv.getCustomer(),
                bv.getEmployee(),
                bv.getBusinessDate(),
                bv.getTitle(),
                bv.getContent(),
                bv.getCreatedAt(),
                bv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Businessのインスタンス
     * @return BusinessViewのインスタンス
     */
    public static BusinessView toView(Business b) {

        if (b == null) {
            return null;
        }

        return new BusinessView(
                b.getId(),
                b.getCustomer(),
                b.getEmployee(),
                b.getBusinessDate(),
                b.getTitle(),
                b.getContent(),
                b.getCreatedAt(),
                b.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<BusinessView> toViewList(List<Business> list) {
        List<BusinessView> evs = new ArrayList<>();

        for (Business b : list) {
            evs.add(toView(b));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Business b, BusinessView bv) {
        b.setId(bv.getId());
        b.setCustomer(bv.getCustomer());
        b.setEmployee(bv.getEmployee());
        b.setBusinessDate(bv.getBusinessDate());
        b.setTitle(bv.getTitle());
        b.setContent(bv.getContent());
        b.setCreatedAt(bv.getCreatedAt());
        b.setUpdatedAt(bv.getUpdatedAt());

    }

}
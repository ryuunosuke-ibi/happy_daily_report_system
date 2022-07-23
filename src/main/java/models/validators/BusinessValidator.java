package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.BusinessView;
import constants.MessageConst;

/**
 * 商談インスタンスに設定されている値のバリデーションを行うクラス
 */
public class BusinessValidator {

    /**
     * 商談インスタンスの各項目についてバリデーションを行う
     * @param bv 商談インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(BusinessView bv) {
        List<String> errors = new ArrayList<String>();

        //担当者のチェック
        String employeeError = validateEmployee(bv.getEmployee());
        if (!employeeError.equals("")) {
            errors.add(employeeError);
        }
        //顧客名のチェック
        //String customerError = validateCustomer(bv.getCustomer());
        //if (!customerError.equals("")) {
            //errors.add(customerError);
        //}

        //商談名称のチェック
        String titleError = validateTitle(bv.getTitle());
        if (!titleError.equals("")) {
            errors.add(titleError);
        }

        //内容のチェック
        String contentError = validateContent(bv.getContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }

        return errors;
    }
    /**
     * 担当者に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateEmployee(String employee) {
        if (employee == null || employee.equals("")) {
            return MessageConst.E_NOEMPLOYEE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 顧客名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    //private static String validateCustomer(String customer) {
        //if (customer == null || customer.equals("")) {
            //return MessageConst.E_NONAMECUS.getMessage();
        //}

        //入力値がある場合は空文字を返却
        //return "";
    //}

    /**
     * 商談名称に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateTitle(String title) {
        if (title == null || title.equals("")) {
            return MessageConst.E_NOBUSINESSTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

}
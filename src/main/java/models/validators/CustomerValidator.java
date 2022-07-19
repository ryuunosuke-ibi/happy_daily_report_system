package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.CustomerView;
import constants.MessageConst;
import services.CustomerService;

/**
 * 顧客インスタンスに設定されている値のバリデーションを行うクラス
 *
 */
public class CustomerValidator {

    /**
     * 顧客インスタンスの各項目についてバリデーションを行う
     * @param service 呼び出し元Serviceクラスのインスタンス
     * @param cv CustomerViewのインスタンス
     * @param codeDuplicateCheckFlag 顧客番号の重複チェックを実施するかどうか(実施する:true 実施しない:false)

     * @return エラーのリスト
     */
    public static List<String> validate(
            CustomerService service, CustomerView cv, Boolean codeDuplicateCheckFlag) {
        List<String> errors = new ArrayList<String>();

        //顧客番号のチェック
        String codeError = validateCode(service, cv.getCode(), codeDuplicateCheckFlag);
        if (!codeError.equals("")) {
            errors.add(codeError);
        }

        //顧客名のチェック
        String nameError = validateName(cv.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

        //電話番号のチェック
        String phone_numberError = validatePhone_number(cv.getPhone_number());
        if (!phone_numberError.equals("")) {
            errors.add(phone_numberError);
        }

        //メールアドレスのチェック
        String mail_adressError = validateMail_adress(cv.getMail_adress());
        if (!mail_adressError.equals("")) {
            errors.add(mail_adressError);
        }

        return errors;
    }

    /**
     * 顧客番号の入力チェックを行い、エラーメッセージを返却
     * @param service CustomerServiceのインスタンス
     * @param code 顧客番号
     * @param codeDuplicateCheckFlag 顧客番号の重複チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーメッセージ
     */
    private static String validateCode(CustomerService service, String code, Boolean codeDuplicateCheckFlag) {

        //入力値がなければエラーメッセージを返却
        if (code == null || code.equals("")) {
            return MessageConst.E_NOCUS_CODE.getMessage();
        }

        if (codeDuplicateCheckFlag) {
            //顧客番号の重複チェックを実施

            long customersCount = isDuplicateCustomer(service, code);

            //同一顧客番号が既に登録されている場合はエラーメッセージを返却
            if (customersCount > 0) {
                return MessageConst.E_CUS_CODE_EXIST.getMessage();
            }
        }

        //エラーがない場合は空文字を返却
        return "";
    }

    /**
     * @param service CustomerServiceのインスタンス
     * @param code 顧客番号
     * @return 顧客テーブルに登録されている同一顧客番号のデータの件数
     */
    private static long isDuplicateCustomer(CustomerService service, String code) {

        long customersCount = service.countByCode(code);
        return customersCount;
    }

    /**
     * 顧客名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 顧客名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {

        if (name == null || name.equals("")) {
            return MessageConst.E_NONAMECUS.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 電話番号に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param phone_number 電話番号
     * @return エラーメッセージ
     */
    private static String validatePhone_number(String phone_number) {

        if (phone_number == null || phone_number.equals("")) {
            return MessageConst.E_NOPHONE_CODE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
    /**
     * 電話番号に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param phone_number 電話番号
     * @return エラーメッセージ
     */
    private static String validateMail_adress(String mail_adress) {

        if (mail_adress == null || mail_adress.equals("")) {
            return MessageConst.E_NOMAIL_CODE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

}
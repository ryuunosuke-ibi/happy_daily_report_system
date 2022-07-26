package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.BusinessView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.BusinessService;

/**
 * 商談に関する処理を行うActionクラス
 *
 */
public class BusinessAction extends ActionBase {

    private BusinessService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new BusinessService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示する商談データを取得
        int page = getPage();
        List<BusinessView> businesss = service.getAllPerPage(page);

        //全日報データの件数を取得
        long businesssCount = service.countAll();

        putRequestScope(AttributeConst.BUSINESSS, businesss); //取得した商談データ
        putRequestScope(AttributeConst.BUS_COUNT, businesssCount); //全ての商談データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_BUS_INDEX);
    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

        //商談情報の空インスタンスに、商談の日付＝今日の日付を設定する
        BusinessView bv = new BusinessView();
        bv.setBusinessDate(LocalDate.now());
        putRequestScope(AttributeConst.REPORT, bv); //日付のみ設定済みの日報インスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_BUS_NEW);

    }
    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //商談の日付が入力されていなければ、今日の日付を設定
            LocalDate day = null;
            if (getRequestParam(AttributeConst.BUS_DATE) == null
                    || getRequestParam(AttributeConst.BUS_DATE).equals("")) {
                day = LocalDate.now();
            } else {
                day = LocalDate.parse(getRequestParam(AttributeConst.BUS_DATE));
            }

            //セッションから商談をした顧客情報を取得
            //CustomerView cv = (CustomerView) getSessionScope(AttributeConst.CUSTOMER);

            //パラメータの値をもとに商談情報のインスタンスを作成する
            BusinessView bv = new BusinessView(
                    null,
                    getRequestParam(AttributeConst.BUS_CUSTOMER),
                    getRequestParam(AttributeConst.BUS_EMPLOYEE),
                    day,
                    getRequestParam(AttributeConst.BUS_TITLE),
                    getRequestParam(AttributeConst.BUS_CONTENT),
                    null,
                    null);

            //商談情報登録
            List<String> errors = service.create(bv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.REPORT, bv);//入力された商談情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_BUS_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_BUS, ForwardConst.CMD_INDEX);
            }
        }
    }
    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件に商談データを取得する
        BusinessView bv = service.findOne(toNumber(getRequestParam(AttributeConst.BUS_ID)));

        if (bv == null) {
            //該当の商談データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.BUSINESS, bv); //取得した商談データ

            //詳細画面を表示
            forward(ForwardConst.FW_BUS_SHOW);
        }
    }
    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件に商談データを取得する
        BusinessView bv = service.findOne(toNumber(getRequestParam(AttributeConst.BUS_ID)));

        //セッションから商談をした顧客情報を取得
        //CustomerView cv = (CustomerView) getSessionScope(AttributeConst.CUSTOMER);

        if (bv == null ){//cv.getId() != bv.getCustomer().getId()) {
            //該当の商談データが存在しない、または
            //ログインしている従業員が商談の作成者でない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.BUSINESS, bv); //取得した商談データ

            //編集画面を表示
            forward(ForwardConst.FW_BUS_EDIT);
        }

    }
    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に商談データを取得する
            BusinessView bv = service.findOne(toNumber(getRequestParam(AttributeConst.BUS_ID)));

            //入力された商談内容を設定する
            bv.setCustomer(getRequestParam(AttributeConst.BUS_CUSTOMER));
            bv.setEmployee(getRequestParam(AttributeConst.BUS_EMPLOYEE));
            bv.setBusinessDate(toLocalDate(getRequestParam(AttributeConst.BUS_DATE)));
            bv.setTitle(getRequestParam(AttributeConst.BUS_TITLE));
            bv.setContent(getRequestParam(AttributeConst.BUS_CONTENT));

            //商談データを更新する
            List<String> errors = service.update(bv);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.BUSINESS, bv); //入力された商談情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_BUS_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_BUS, ForwardConst.CMD_INDEX);

            }
        }
    }
}

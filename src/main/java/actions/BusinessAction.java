package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.BusinessView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.BusinessService;

/**
 * 日報に関する処理を行うActionクラス
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

        //指定されたページ数の一覧画面に表示する日報データを取得
        int page = getPage();
        List<BusinessView> businesss = service.getAllPerPage(page);

        //全日報データの件数を取得
        long businesssCount = service.countAll();

        putRequestScope(AttributeConst.BUSINESSS, businesss); //取得した日報データ
        putRequestScope(AttributeConst.BUS_COUNT, businesssCount); //全ての日報データの件数
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

}
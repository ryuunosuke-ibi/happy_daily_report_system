package constants;

/**
 * リクエストパラメーターの変数名、変数値、jspファイルの名前等画面遷移に関わる値を定義するEnumクラス
 *
 */
public enum ForwardConst {

    //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_EMP("Employee"),
    ACT_CUS("Customer"),
    ACT_REP("Report"),
    ACT_BUS("Business"),
    ACT_AUTH("Auth"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),

    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),

    FW_EMP_INDEX("employees/index"),
    FW_REP_INDEX("reports/index"),
    FW_CUS_INDEX("customers/index"),
    FW_BUS_INDEX("businesss/index"),

    FW_EMP_SHOW("employees/show"),
    FW_REP_SHOW("reports/show"),
    FW_CUS_SHOW("customers/show"),
    FW_BUS_SHOW("businesss/show"),

    FW_EMP_NEW("employees/new"),
    FW_REP_NEW("reports/new"),
    FW_CUS_NEW("customers/new"),
    FW_BUS_NEW("businesss/new"),

    FW_EMP_EDIT("employees/edit"),
    FW_REP_EDIT("reports/edit"),
    FW_CUS_EDIT("customers/edit"),
    FW_BUS_EDIT("businesss/edit");


    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}
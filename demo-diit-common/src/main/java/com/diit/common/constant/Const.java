package com.diit.common.constant;

public final class Const {

    /**
     * <B>构造方法</B><BR>
     */
    private Const() {
    }

    /** 判断代码：是 */
    public static final String TRUE = "1";

    /** 判断代码：否 */
    public static final String FALSE = "0";

    /** 通用字符集编码 */
    public static final String CHARSET_UTF8 = "UTF-8";

    /** 中文字符集编码 */
    public static final String CHARSET_CHINESE = "GBK";

    /** 英文字符集编码 */
    public static final String CHARSET_LATIN = "ISO-8859-1";

    /** 根节点ID */
    public static final String ROOT_ID = "root";

    /** NULL字符串 */
    public static final String NULL = "null";

    /** 日期格式 */
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    /** 日期时间格式 */
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /** 时间戳格式 */
    public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
    
    /** JSON成功标记 */
    public static final String JSON_SUCCESS = "success";

    /** JSON数据 */
    public static final String JSON_DATA = "data";

    /** JSON数据列表 */
    public static final String JSON_ROWS = "rows";
    
    /** JSON总数 */
    public static final String JSON_TOTAL = "total";

    /** JSON消息文本 */
    public static final String JSON_MESSAGE = "message";
    
    /**cookie 用户登录token*/
    public static final String TOKEN_LOGIN = "_xlsw";
    
    /**cookie 用户名*/
    public static final String USER_NAME = "_USER_NAME";

}

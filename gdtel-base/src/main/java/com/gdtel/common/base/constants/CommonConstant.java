package com.gdtel.common.base.constants;

public interface CommonConstant {
	/**
	 * token请求头名称
	 */
	String REQ_HEADER = "Authorization";

	/**
	 * token分割符
	 */
	String TOKEN_SPLIT = "Bearer ";

	/**
	 * jwt签名
	 */
	String SIGN_KEY = "gdtel";

	String STATUS_NORMAL = "0";

	String STATUS_LOCK = "9";

	String MENU = "0";

	String BUTTON = "1";

	String DEL_FLAG = "del_flag";

	String UTF8 = "UTF-8";

	String CONTENT_TYPE = "application/json; charset=utf-8";


	/**
	 * 路由信息Redis保存的key
	 */
	String ROUTE_KEY = "_ROUTE_KEY";
}

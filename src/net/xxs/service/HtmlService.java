package net.xxs.service;

import java.util.Map;

/**
 * Service接口 - 生成静态
 */

public interface HtmlService {
	
	/**
	 * 根据Freemarker模板文件路径、Map数据生成HTML
	 * 
	 * @param templatePath
	 *            Freemarker模板文件路径
	 *            
	 * @param htmlPath
	 *            生成HTML路径
	 * 
	 * @param data
	 *            Map数据
	 * 
	 */
	public void buildHtml(String templatePath, String htmlPath, Map<String, Object> data);
	
	/**
	 * 生成ADMIN.JS
	 * 
	 */
	public void buildAdminJs();
	
	
	/**
	 * 生成错误页HTML
	 */
	public void buildErrorPageHtml();
	
	/**
	 * 生成权限错误页HTML
	 */
	public void buildErrorPageAccessDeniedHtml();
	
	/**
	 * 生成错误页500 HTML
	 */
	public void buildErrorPage500Html();
	
	/**
	 * 生成错误页404 HTML
	 */
	public void buildErrorPage404Html();
	
	/**
	 * 生成错误页403 HTML
	 */
	public void buildErrorPage403Html();
	
}
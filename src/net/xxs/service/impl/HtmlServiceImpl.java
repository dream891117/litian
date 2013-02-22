package net.xxs.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import net.xxs.bean.PageTemplateConfig;
import net.xxs.common.FreemarkerManager;
import net.xxs.service.HtmlService;
import net.xxs.util.SettingUtil;
import net.xxs.util.TemplateConfigUtil;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.ResourceBundleModel;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Service实现类 - 生成静态
 */

@Service("htmlServiceImpl")
public class HtmlServiceImpl implements HtmlService, ServletContextAware {

	private ServletContext servletContext;
	@Resource(name = "freemarkerManager")
	private FreemarkerManager freemarkerManager;
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	// 获取公用数据
	public Map<String, Object> getCommonData() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n");
		ResourceBundleModel resourceBundleModel = new ResourceBundleModel(resourceBundle, new BeansWrapper());
		
		Map<String, Object> commonData = new HashMap<String, Object>();
		commonData.put("bundle", resourceBundleModel);
		commonData.put("base", getContextPath());
		commonData.put("setting", SettingUtil.getSetting());
		commonData.put("currencyFormat", SettingUtil.getCurrencyFormat());
		return commonData;
	}
	
	public void buildHtml(String templatePath, String htmlPath, Map<String, Object> data) {
		try {
			Configuration configuration = freemarkerManager.getConfiguration(servletContext);
			Template template = configuration.getTemplate(templatePath);
			File htmlFile = new File(servletContext.getRealPath(htmlPath));
			File htmlDirectory = htmlFile.getParentFile();
			if (!htmlDirectory.exists()) {
				htmlDirectory.mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void buildAdminJs() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ADMIN_JS);
		Map<String, Object> data = getCommonData();
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildErrorPageHtml() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "系统出现异常,请与管理员联系!");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildErrorPageAccessDeniedHtml() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE_ACCESS_DENIED);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "您无此访问权限!");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildErrorPage500Html() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE_500);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "系统出现异常,请与管理员联系!");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildErrorPage404Html() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE_404);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "您访问的页面不存在!准备要出现404了");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildErrorPage403Html() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE_403);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "系统出现异常,请与管理员联系!");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	/**
	 * 获取虚拟路径
	 * 
	 * @return 虚拟路径
	 */
	private String getContextPath() {
		if (servletContext.getMajorVersion() < 2 || (servletContext.getMajorVersion() == 2 && servletContext.getMinorVersion() < 5)) {
			return SettingUtil.getSetting().getContextPath();
		} else {
			return servletContext.getContextPath();
		}
	}

}
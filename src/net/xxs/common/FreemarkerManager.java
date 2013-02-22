package net.xxs.common;

import javax.servlet.ServletContext;

import net.xxs.directive.CheckboxDirective;
import net.xxs.directive.PaginationDirective;
import net.xxs.directive.PaymentResultMethod;
import net.xxs.directive.SubstringMethod;
import net.xxs.util.SpringUtil;
import freemarker.template.TemplateException;

public class FreemarkerManager extends org.apache.struts2.views.freemarker.FreemarkerManager {

	public synchronized freemarker.template.Configuration getConfiguration(ServletContext servletContext) throws TemplateException {
		freemarker.template.Configuration config = (freemarker.template.Configuration) servletContext.getAttribute(CONFIG_SERVLET_CONTEXT_KEY);
		if (config == null) {
			config = createConfiguration(servletContext);
			// config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			
			SubstringMethod substringMethod = (SubstringMethod) SpringUtil.getBean("substringMethod");
			PaymentResultMethod paymentResultMethod = (PaymentResultMethod) SpringUtil.getBean("paymentResultMethod");
			CheckboxDirective checkboxDirective = (CheckboxDirective) SpringUtil.getBean("checkboxDirective");
			PaginationDirective paginationDirective = (PaginationDirective) SpringUtil.getBean("paginationDirective");
			
			config.setSharedVariable(PaymentResultMethod.TAG_NAME, paymentResultMethod);
			config.setSharedVariable(SubstringMethod.TAG_NAME, substringMethod);
			config.setSharedVariable(CheckboxDirective.TAG_NAME, checkboxDirective);
			config.setSharedVariable(PaginationDirective.TAG_NAME, paginationDirective);
			
			servletContext.setAttribute(CONFIG_SERVLET_CONTEXT_KEY, config);
		}
		config.setWhitespaceStripping(true);
		return config;
	}

}
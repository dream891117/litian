package net.xxs.service;

import java.util.List;

import net.xxs.entity.Navigation;
import net.xxs.entity.Navigation.NavigationPosition;

/**
 * Service接口 - 导航
 */

public interface NavigationService extends BaseService<Navigation, String> {

	/**
	 * 获取导航集合（只包含isVisible=true的对象）
	 * 
	 * @param navigationPosition
	 *            导航位置,null表示无限制
	 * 
	 * @param maxResults
	 *            最大结果数,null表示无限制
	 * 
	 * @return 顶级商品分类集合
	 * 
	 */
	public List<Navigation> getNavigationList(NavigationPosition navigationPosition, Integer maxResults);

}
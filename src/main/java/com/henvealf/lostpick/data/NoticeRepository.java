package com.henvealf.lostpick.data;

import java.util.List;

import com.henvealf.lostpick.web.beans.Notice;

/**
 * Notice数据库操作
 * 
 * @author Henvealf
 *
 */
public interface NoticeRepository {
	/**
	 * 
	 * @param pageNumber 页数 
	 * @param count   每一页启事的数量
	 * @param lostOrPick 丢还是捡
	 * @return 
	 */
	public List<Notice> findNoticesByPage(int pageNumber, int count, boolean lostOrPick);

	/**
	 * 
	 * @param NoticeId
	 * @return
	 */
	public Notice findOne(Long noticeId);
	
	public List<Notice> findAllNotice(boolean lostOrPick,int pageNumber); 
	
	/**
	 * 根据物品类型查询启事。
	 * @param goodsType 物品类型
	 * @param lostOrpick 丢还是捡
	 * @param pageNumber 页码数
	 * @return
	 */
	public List<Notice> findByGoodsType(String goodsType,boolean lostOrpick,int pageNumber);
	
	/***
	 * <p>根据用户Id获取用户说发布的启事的信息。<p>
	 * @param userId   用户Id
	 * @param lostOrPick  寻物启事还是失物招领
	 * @return
	 */
	public List<Notice> findNoticesByUserId(String userId, boolean lostOrPick);
	
	public void add(Notice notice);
	
	/**
	 * 修改启事
	 * @param notice 启事
	 * @param isChangeImg 是否修改图片。
	 */
	public void updateInfo(Notice notice,boolean isChangeImg);
	
	public void delete(long id);
	
	public String findNoticeImgUrl(long id);
	
	/**
	 * 根据lop类型获取最大页数
	 * @return
	 */
	public int getMaxPageCountByLopType(boolean lop);
	
	public int getMaxPageCountByLopAndGoodsType(boolean lop, String goodsType);
}

package com.henvealf.lostpick.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.henvealf.lostpick.web.beans.Notice;

@Repository
public class JdbcNoticeRepository implements NoticeRepository{
	
	private static final int PER_PAGE_NUM = 12;
	private static final String BY_PAGE = " order by id desc limit ? , " + PER_PAGE_NUM;
	
	
	private static final String INSERT_NOTICE = "insert into notices "
					+ "( userId , goodsType, noticeTime, lostOrPick, lopTime, exactTime, lopPlace, imgUrl,description) "
					+ "values (  ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	
	private static final String SELECT_ONE_NOTICE = "select * from notices where id = ? ";
	
	private static final String SELECT_BY_GOODSTYPE_LOP_PAGE = " select * from notices "
								+ "where goodsType = ? and lostOrPick = ?  " + BY_PAGE;
	private static final String SELECT_NOTICES_BY_USERID = "select * from notices where userId = ? and lostOrPick = ? ";
	
	//不修改图片
	private static final String UPDATE_INFO_NO_IMG = "update notices set goodsType = ?, lostOrPick = ?, lopTime = ?, exactTime = ?, lopPlace = ?,"
								+" description = ? where id = ?";
	//修改图片
	private static final String UPDATE_INFO_HAS_IMG = "update notices set goodsType = ?, lostOrPick = ?, lopTime = ?, exactTime = ?, lopPlace = ?,"
			+"imgUrl = ?, description = ?  where id = ?";
	
	private static final String DELETE = "delete from notices where id = ?";
	
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcNoticeRepository(){
	}
	
	public JdbcNoticeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Notice> findNoticesByPage(int pageNumber, int count, boolean lostOrPick) {
		return null;
	}
	
	
	@Transactional
	@Override
	public void add(Notice notice) {
		jdbcTemplate.update(INSERT_NOTICE, 
							notice.getUserId(),
							notice.getGoodsType(),
							notice.getNoticeTime(),
							notice.isLostOrPick(),
							notice.getLopTime(),
							notice.getExactTime(),
							notice.getLopPlace(),
							notice.getImgUrl(),
							notice.getDescription());
	}

	@Override
	public void updateInfo(Notice notice,boolean isChangeImg) {
		
		if(isChangeImg){				//修改了图片
			jdbcTemplate.update(UPDATE_INFO_HAS_IMG, 
							notice.getGoodsType(),
							notice.isLostOrPick(),
							notice.getLopTime(),
							notice.getExactTime(),
							notice.getLopPlace(),
							notice.getImgUrl(),
							notice.getDescription(),
							notice.getId());
		}else{					//没有修改图片
			jdbcTemplate.update(UPDATE_INFO_NO_IMG, 
					notice.getGoodsType(),
					notice.isLostOrPick(),
					notice.getLopTime(),
					notice.getExactTime(),
					notice.getLopPlace(),
					notice.getDescription(),
					notice.getId());
		}

		
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update(DELETE,id);
	}

	@Override
	public Notice findOne(Long id) {
		return jdbcTemplate.queryForObject(SELECT_ONE_NOTICE,
						new NoticeRowMapper(), id);
	}
	
	@Override
	public List<Notice> findAllNotice(boolean lostOrPick,int pageNumber) {
		return jdbcTemplate.query(SELECT_ALL_NOTICE_BY_LOP,
									new NoticeRowMapper(),
									lostOrPick,
									(pageNumber-1)*PER_PAGE_NUM);
	}
	
	@Override
	public List<Notice> findByGoodsType(String goodsType,boolean lostOrpick,int pageNumber) {
		return jdbcTemplate.query(SELECT_BY_GOODSTYPE_LOP_PAGE, 
							   	  new NoticeRowMapper(),
								  goodsType,
								  lostOrpick,
								  (pageNumber-1)*PER_PAGE_NUM);
	}
	
	
	@Override
	public List<Notice> findNoticesByUserId(String userId, boolean lostOrPick) {
		return jdbcTemplate.query(SELECT_NOTICES_BY_USERID, 
									new NoticeRowMapper(),
									userId,
									lostOrPick);
	}
	
	private static class NoticeRowMapper implements RowMapper<Notice>{

		@Override
		public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
			Notice notice = new Notice();
			
			notice.setId(rs.getLong("id"));
			notice.setUserId(rs.getString("userId"));
			notice.setImgUrl(rs.getString("imgUrl"));
			notice.setNoticeTime(rs.getString("noticeTime"));
			notice.setLopPlace(rs.getString("lopPlace"));
			notice.setDescription(rs.getString("description"));
			notice.setGoodsType(rs.getString("goodsType"));
			notice.setLostOrPick(rs.getBoolean("lostOrPick"));
			notice.setLopTime(rs.getString("lopTime"));
			notice.setExactTime(rs.getString("exactTime"));
			return notice;
		}
		
	}
	
	@Override
	public String findNoticeImgUrl(long id) {
		//jdbcTemplate.
		return null;
	}
	
	
	
	@Override
	public int getMaxPageCountByLopType(boolean lop) {
		int maxCount = jdbcTemplate.queryForObject(
				SELECT_COUNT_BY_LOPTYPE,new GetCountRowMapper(),lop);

		return (maxCount+PER_PAGE_NUM)/PER_PAGE_NUM;
	}
	
	@Override
	public int getMaxPageCountByLopAndGoodsType(boolean lop, String goodsType) {
		int maxCount = jdbcTemplate.queryForObject(
				SLELECT_COUNT_BY_LOP_GOODSTYPE,new GetCountRowMapper(),lop,goodsType);

		return (maxCount+PER_PAGE_NUM)/PER_PAGE_NUM;
	}
	
	
	private class GetCountRowMapper implements RowMapper<Integer> {
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("count");
		}
	}
	

	
	//根据捡或丢获取记录条数
	private static final String SELECT_COUNT_BY_LOPTYPE = "select count(*) as count from notices where lostOrPick = ?";
	
	private static final String SLELECT_COUNT_BY_LOP_GOODSTYPE = "select count(*) as count from notices where lostOrPick = ? and goodsType = ?";
	
	private static final String SELECT_ALL_NOTICE_BY_LOP = "select * from notices where lostOrPick = ? " + BY_PAGE;

	private static final String SELECT_IMG_BY_ID = "select imgUrl from notices where id = ?";







	
/*	public static void main(String[] args) {
		JdbcNoticeRepository j = new JdbcNoticeRepository(); 
		System.out.println(j.findNotices(1,10,true));
	}*/
}

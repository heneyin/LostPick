package com.henvealf.lostpick.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.henvealf.lostpick.web.beans.DayWord;
@Repository
public class JdbcDayWordRepository implements DayWordRepository{

	private final static String ADD_DAY_WORD = "insert into dayWords (imgUrl, sentence,author) values(?,?,?)";
	
	private final static String FIND_MAX_ID_DAY_WORD = "select * from dayWords where id=(select max(id) from dayWords)";

	private JdbcTemplate jdbcTemplate;

	public JdbcDayWordRepository(){
	}

	public JdbcDayWordRepository(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void add(DayWord dayWord) {
		if(jdbcTemplate == null){
			System.out.println("jdbcTemplate == null");
		}
		jdbcTemplate.update(ADD_DAY_WORD,
							dayWord.getImgUrl(),
							dayWord.getSentence(),
							dayWord.getAuthor());
	}

	@Override
	public DayWord findMaxIdDayWord() {
		return jdbcTemplate.queryForObject(FIND_MAX_ID_DAY_WORD, new DayWordRowMap());
	}
	
	private class DayWordRowMap implements RowMapper<DayWord>{

		@Override
		public DayWord mapRow(ResultSet rs, int rowNum) throws SQLException {
			DayWord dw = new DayWord();
			dw.setImgUrl(rs.getString("imgUrl"));
			dw.setSentence(rs.getString("sentence"));
			dw.setAuthor(rs.getString("author"));
			
			return dw;
		}
		
	}

}

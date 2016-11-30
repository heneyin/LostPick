package com.henvealf.lostpick.data;

import com.henvealf.lostpick.web.beans.DayWord;

public interface DayWordRepository {
	public void add(DayWord dayWord);
	
	public DayWord findMaxIdDayWord();
}

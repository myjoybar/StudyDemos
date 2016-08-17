package com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.pinyin;


import com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.model.MembersEntity;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<MembersEntity> {

	public int compare(MembersEntity o1,MembersEntity o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}

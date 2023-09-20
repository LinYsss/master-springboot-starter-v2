package com.wayakeji.qiniuyun.params;

import java.util.ArrayList;
import java.util.List;

public class QiniuImgDel extends QiniuyunBuffer {
	
	private Integer id;
	
	private List<Integer> ids;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.ids = new ArrayList<>();
		this.ids.add(id);
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(String ids) {
		String[] arr = ids.split(",");
		this.ids = new ArrayList<>();
		for(int i = 0; i < arr.length; i++) {
			this.ids.add(Integer.parseInt(arr[i]));
		}
	}
	
}

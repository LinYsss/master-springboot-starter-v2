package com.wayakeji.common.api.dto;

import org.springframework.format.annotation.NumberFormat;

import java.util.List;

/**
 * 保存角色权限的参数接收类
 * <p>必填: rid -> 角色ID
 * <pre>
 *  选填: 
 *    adds -> 新增的用户id集合
 *    dels -> 删除的用户id集合
 * @author hu trace
 */
public class AuthorityRoleModuleAdd {
	
	@NumberFormat
	private Integer rid;
	
	private List<Integer> adds;
	
	private List<Integer> dels;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public List<Integer> getAdds() {
		return adds;
	}

	public void setAdds(List<Integer> adds) {
		this.adds = adds;
	}

	public List<Integer> getDels() {
		return dels;
	}

	public void setDels(List<Integer> dels) {
		this.dels = dels;
	}
	
}

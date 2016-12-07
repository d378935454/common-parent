package com.bean.model;

import java.io.Serializable;

/**
 * 社区信息Bean
 * 
 * @author zhuhui
 * @since 2015年7月12日 下午3:00:08
 * @version 0.0.1
 */
public class VillageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -620620983232552350L;

	// 社区编号
    private Long villageId;
    // 社区名称
    private String villageName;
    // 街道编号
    private Long streetId;
    
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public Long getStreetId() {
		return streetId;
	}
	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}
}

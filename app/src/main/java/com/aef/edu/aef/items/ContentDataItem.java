package com.aef.edu.aef.items;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContentDataItem {

	private String mainNickName;
	private String subNickName;
	private String itemText;
	private int photoId;

	public ContentDataItem setMainNickName(String mainNickName) {
		this.mainNickName = mainNickName;
		return this;
	}

	public ContentDataItem setSubNickName(String subNickName) {
		this.subNickName = subNickName;
		return this;
	}

	public ContentDataItem setItemText(String itemText) {
		this.itemText = itemText;
		return this;
	}

	public ContentDataItem setPhotoId(int photoId) {
		this.photoId = photoId;
		return this;
	}

	public String getMainNickName() {
		return mainNickName;
	}

	public String getSubNickName() {
		return subNickName;
	}

	public String getItemText() {
		return itemText;
	}

	public int getPhotoId() {
		return photoId;
	}
}

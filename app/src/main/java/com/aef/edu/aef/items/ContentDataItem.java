package com.aef.edu.aef.items;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContentDataItem {

	private String nickName;
	private String itemText;
	private int nickPos;
	private String description;
	private int photoId;
	private int type;

	public ContentDataItem(String nickName, String itemText) {
		this.nickName = nickName;
		this.itemText = itemText;
	}

	public ContentDataItem(int photoId, String description, String nickName, int nickPos) {
		this.photoId = photoId;
		this.description = description;
		this.nickName = nickName;
		this.nickPos = nickPos;
	}

	public String getNickName() {
		return nickName;
	}

	public String getItemText() {
		return itemText;
	}

	public int getNickPos() {
		return nickPos;
	}

	public String getDescription() {
		return description;
	}

	public int getPhotoId() {
		return photoId;
	}

	public int getType() {
		return type;
	}
}

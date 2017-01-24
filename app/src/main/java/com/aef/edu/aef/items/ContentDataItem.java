package com.aef.edu.aef.items;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContentDataItem {

	private String nickName;
	private int nickPos;
	private String text;
	private int photoId;
	private int stringResId;
	private int color;
	private int type;

	public ContentDataItem(int photoId, String text, int stringResId, int type) {
		this.photoId = photoId;
		this.text = text;
		this.stringResId = stringResId;
		this.type = type;
	}

	public ContentDataItem(int photoId, String text, int stringResId, int type, String nickName, int nickPos) {
		this.photoId = photoId;
		this.text = text;
		this.stringResId = stringResId;
		this.type = type;
		this.nickName = nickName;
		this.nickPos = nickPos;
	}

	public String getNickName() {
		return nickName;
	}

	public int getNickPos() {
		return nickPos;
	}

	public String getText() {
		return text;
	}

	public int getPhotoId() {
		return photoId;
	}

	public int getStringResId() {
		return stringResId;
	}

	public int getColor() {
		return color;
	}

	public int getType() {
		return type;
	}
}

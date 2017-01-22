package com.aef.edu.aef.items;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContentDataItem {

	private String text;
	private int photoId;
	private int stringResId;
	private int color;

	public ContentDataItem(String text) {
		this.text = text;
	}

	public ContentDataItem(int photoId, String text, int stringResId) {
		this.photoId = photoId;
		this.text = text;
		this.stringResId = stringResId;
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
}

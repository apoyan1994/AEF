package com.aef.edu.aef.items;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContextDataItem {

	private String text;
	private int photoId;
	private String uri;
	private int color;

	public ContextDataItem(int photoId, String text, String uri) {
		this.photoId = photoId;
		this.text = text;
		this.uri = uri;
	}

	public ContextDataItem(int photoId, String text, String uri, int color) {
		this.photoId = photoId;
		this.text = text;
		this.uri = uri;
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public int getPhotoId() {
		return photoId;
	}

	public String getUri() {
		return uri;
	}

	public int getColor() {
		return color;
	}
}

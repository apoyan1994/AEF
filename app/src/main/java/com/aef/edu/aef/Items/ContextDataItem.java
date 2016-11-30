package com.aef.edu.aef.items;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContextDataItem {

	private String text;
	private int photoId;
	private String uri;

	public ContextDataItem(int photoId, String text, String uri) {
		this.photoId = photoId;
		this.text = text;
		this.uri = uri;
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
}

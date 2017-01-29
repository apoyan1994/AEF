package com.aef.edu.aef.items;

/**
 * Created by Hovo on 13/10/2016.
 */

public class TextImageItem {

	private int itemTextId;
	private int photoId;

	public TextImageItem setItemText(int itemTextId) {
		this.itemTextId = itemTextId;
		return this;
	}

	public TextImageItem setPhotoId(int photoId) {
		this.photoId = photoId;
		return this;
	}

	public int getItemTextId() {
		return itemTextId;
	}

	public int getPhotoId() {
		return photoId;
	}
}

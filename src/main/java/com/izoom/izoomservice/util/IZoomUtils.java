package com.izoom.izoomservice.util;

public class IZoomUtils {
	public static String getImageType(String contentType) {		
		return contentType.split("/")[1];		
	}
}

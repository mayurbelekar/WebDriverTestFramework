package com.framework;

import java.io.File;
import java.io.IOException;

public class ProjectFolder {

	public static String getProjectFolder(){
		File file = new File(".");
		String path = null;
		try {
			path = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String actualPath = path.substring(0, path.lastIndexOf("\\")+1);
		actualPath = actualPath + "TestFramework";
		return actualPath;
	}

}

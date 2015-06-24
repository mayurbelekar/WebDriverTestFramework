package com.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

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
	
	public static String getProjectName(){
		String filename = System.getProperty("user.dir") + "//pom.xml";
		File file = new File(filename);
		FileReader fileReader = null;
		MavenXpp3Reader mavenreader = new MavenXpp3Reader();
		Model model = null;
		try {
			fileReader = new FileReader(file);
			model = mavenreader.read(fileReader);
			model.setPomFile(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MavenProject project = new MavenProject(model);
		return project.getName();
	}

}

package com.my;

import static com.my.DataNodeConfig.DATA_DIR;

import java.io.File;

public class FileUtils {

	/**
	 * 获取文件在本地磁盘上的绝对路径名
	 * @param relativeFilename
	 * @return
	 * @throws Exception
	 */
	public static String getAbsoluteFilename(String relativeFilename, String dirPath) throws Exception {
		String[] relativeFilenameSplited = relativeFilename.split("/"); 
    	
    	for(int i = 0; i < relativeFilenameSplited.length - 1; i++) {
    		if(i == 0) {
    			continue;
    		}
    		dirPath += "\\" + relativeFilenameSplited[i];
    	}
    	
    	File dir = new File(dirPath);
    	if(!dir.exists()) {
    		dir.mkdirs();
    	}
    	
    	String absoluteFilename = dirPath + "\\" + 
    			relativeFilenameSplited[relativeFilenameSplited.length - 1];
    	return absoluteFilename;
	}
	
}

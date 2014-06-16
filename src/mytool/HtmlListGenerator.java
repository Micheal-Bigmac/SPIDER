package mytool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HtmlListGenerator {
	private static FileWriter fw = null;
	private static ArrayList<String> filelist = new ArrayList<String>();
	public static String fileroot;
	
	public static ArrayList<String> run(){
		try {
			fw = new FileWriter(new File(fileroot.substring(0, fileroot.lastIndexOf("\\")) + "/FileList.txt"));
			refreshFileList(fileroot);
	        fw.close();
		} catch (IOException e) {
			// TODO 自动生成�?catch �?
			e.printStackTrace();
		}
		//refreshFileList(fileroot);
		
		return filelist;
	}
	//刷新文件列表，是�?��递归的函�?
	public static void refreshFileList(String strPath) { 
        File dir = new File(strPath); 
        File[] files = dir.listFiles(); 
        
        if (files == null) 
            return; 
        for (int i = 0; i < files.length; i++) { 
        	//如果仍有子目录，继续�?��遍历
            if (files[i].isDirectory()) { 
                refreshFileList(files[i].getAbsolutePath()); 
            } else { 
                String strFileName = files[i].getAbsolutePath().toLowerCase();
                String type = strFileName.substring(strFileName.lastIndexOf(".") + 1, strFileName.length());
                /*System.out.println(strFileName);
                System.out.println(type);*/
                if (type.equals("shtml")){
                	File f1 = new File(strFileName);
                	File f2 = new File(strFileName);
                	f2.renameTo(new File(strFileName + ".htm"));
                	f1.delete();
                	filelist.add(strFileName + ".htm");
                	try {
						//fw.write(URL);
						//fw.write("\t");
						//fw.write(localURL);
						//fw.write("\n");
                    	fw.write(strFileName + ".htm\n");
					} catch (IOException e) {
						// TODO 自动生成�?catch �?
						e.printStackTrace();
					}
                }
                if (type.equals("html") || type.equals("htm")){
                	/*System.out.println(URL);
                    System.out.println(localURL);
                    System.out.println("---"+strFileName);*/
                    filelist.add(strFileName);
                    try {
						//fw.write(URL);
						//fw.write("\t");
						//fw.write(localURL);
						//fw.write("\n");
                    	fw.write(strFileName + "\n");
					} catch (IOException e) {
						// TODO 自动生成�?catch �?
						e.printStackTrace();
					}
                }              
            } 
        } 
    }
}

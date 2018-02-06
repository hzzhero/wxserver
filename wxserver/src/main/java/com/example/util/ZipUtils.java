package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
	
	/** 
     * ��ѹ��ָ��Ŀ¼ 
     * @param zipPath 
     * @param descDir 
     * @author ��־��
     */  
    public static void unZipFiles(String zipPath,String descDir)throws IOException{  
        unZipFiles(new File(zipPath), descDir);  
    }  
    /** 
     * ��ѹ�ļ���ָ��Ŀ¼ 
     * @param zipFile 
     * @param descDir 
     * @author hzz 
     */  
    @SuppressWarnings("rawtypes")  
    public static void unZipFiles(File zipFile,String descDir)throws IOException{  
        File pathFile = new File(descDir);  
        if(!pathFile.exists()){  
            pathFile.mkdirs();  
        }  
        ZipFile zip = new ZipFile(zipFile);  
        for(Enumeration entries = zip.entries();entries.hasMoreElements();){  
            ZipEntry entry = (ZipEntry)entries.nextElement();  
            String zipEntryName = entry.getName();  
            InputStream in = zip.getInputStream(entry);  
            String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;  
            //�ж�·���Ƿ����,�������򴴽��ļ�·��  
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
            if(!file.exists()){  
                file.mkdirs();  
            }  
            //�ж��ļ�ȫ·���Ƿ�Ϊ�ļ���,����������Ѿ��ϴ�,����Ҫ��ѹ  
            if(new File(outPath).isDirectory()){  
                continue;  
            }  
            //����ļ�·����Ϣ  
            System.out.println(outPath);  
              
            OutputStream out = new FileOutputStream(outPath);  
            byte[] buf1 = new byte[1024];  
            int len;  
            while((len=in.read(buf1))>0){  
                out.write(buf1,0,len);  
            }  
            in.close();  
            out.close();  
            }  
        System.out.println("******************��ѹ���********************");  
    } 
    
    
    /** 
     * ѹ���ļ�-����outҪ�ڵݹ������,���Է�װһ���������� 
     * ����ZipFiles(ZipOutputStream out,String path,File... srcFiles) 
     * @param zip 
     * @param path 
     * @param srcFiles 
     * @throws IOException 
     * @author hzz 
     */  
    public static void ZipFiles(File zip,String path,File... srcFiles) throws IOException{  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));  
        ZipUtils.ZipFiles(out,path,srcFiles);  
        out.close();  
        System.out.println("*****************ѹ�����*******************");  
    }  
    /** 
     * ѹ���ļ�-File 
     * @param zipFile  zip�ļ� 
     * @param srcFiles ��ѹ��Դ�ļ� 
     * @author hzz 
     */  
    public static void ZipFiles(ZipOutputStream out,String path,File... srcFiles){  
        path = path.replaceAll("\\*", "/");  
        if(!path.endsWith("/")){  
            path+="/";  
        }  
        byte[] buf = new byte[1024];  
        try {  
            for(int i=0;i<srcFiles.length;i++){  
                if(srcFiles[i].isDirectory()){  
                    File[] files = srcFiles[i].listFiles();  
                    String srcPath = srcFiles[i].getName();  
                    srcPath = srcPath.replaceAll("\\*", "/");  
                    if(!srcPath.endsWith("/")){  
                        srcPath+="/";  
                    }  
                    out.putNextEntry(new ZipEntry(path+srcPath));  
                    ZipFiles(out,path+srcPath,files);  
                }  
                else{  
                    FileInputStream in = new FileInputStream(srcFiles[i]);  
                    System.out.println(path + srcFiles[i].getName());  
                    out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));  
                    int len;  
                    while((len=in.read(buf))>0){  
                        out.write(buf,0,len);  
                    }  
                    out.closeEntry();  
                    in.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }

}

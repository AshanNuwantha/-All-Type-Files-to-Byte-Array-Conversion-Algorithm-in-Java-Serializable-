/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Process;

import java.io.*;

/**
 *
 * @author Ashan Dassanayake
 */
public class DataProcess {
    private final int COPY_PASTE_CODE=20;
    public DataProcess() {
    }

    public int getCOPY_PASTE_CODE() {
        return COPY_PASTE_CODE;
    }
    
    public boolean createFilesToByte(String openfilePathWithFileType,int counter_code,String savefilePathWithFileType) throws FileNotFoundException, IOException{
         File file=new File(openfilePathWithFileType);
         
         FileInputStream fis=new FileInputStream(file);
         
         ByteArrayOutputStream baos=new ByteArrayOutputStream();
         byte []bytes=null;
         
         byte []arrayByte=new byte[2048];
         
         int counter=0;
         for(int readNum;(readNum=fis.read(arrayByte))!= -1;){
                baos.write(arrayByte,0, readNum);
                counter++;
                if(counter==counter_code){
                    bytes=baos.toByteArray();
                    if(bytes!=null){
                         byteArrayToSaveFile(arrayByte, savefilePathWithFileType);
                         bytes=null;
                    }
                    counter=0;
                }
         }
        
        bytes=baos.toByteArray();
        if(bytes!=null){
             byteArrayToSaveFile(arrayByte, savefilePathWithFileType);
        }
        baos.flush();
        baos.close();
        return true;
    }
    
    public boolean byteArrayToSaveFile(byte []getByteArray,String filePathWithFileType) throws FileNotFoundException, IOException{
        File file=new File(filePathWithFileType);
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        fileOutputStream.write(getByteArray);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.isFile();
    }
}

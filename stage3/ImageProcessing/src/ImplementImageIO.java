import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO {
    
    @Override
    public Image myRead(String filePath) throws IOException {
        // 通过bmp文件地址创建文件输入流对象  
        java.io.FileInputStream fin = new java.io.FileInputStream(filePath);
        
        // 根据文件输入流对象创建原始数据输入对象
        java.io.BufferedInputStream bis = new java.io.BufferedInputStream(fin);
        
        // 位图文件头分4部分，共14字节 
        byte[] bitMapFileHeader = new byte[14];  
        bis.read(bitMapFileHeader, 0, 14);  
  
        // 位图信息头共40字节
        byte[] bitMapInfoHeader = new byte[40];  
        bis.read(bitMapInfoHeader, 0, 40);
        
        // 位图信息头的第4到第7个字节（由0开始数）为位图的宽度，单位为像素
        // 调用以下函数，将bitMapInfoHeader里面的该部分转换为整形
        int biWidth = convertByteArrayToInt(bitMapInfoHeader, 4);
        
        // 位图信息头的第8到第11个字节（由0开始数）为位图的高度，单位为像素
        // 调用以下函数，将bitMapInfoHeader里面的该部分转换为整形
        int biHeight = convertByteArrayToInt(bitMapInfoHeader, 8);
        
        // 位图信息头的第14和第15个字节（由0开始数）为每个像素的位数
        int biBitCount = (int) (bitMapInfoHeader[14] & 0xff) + 
                          (int) ((bitMapInfoHeader[15] & 0xff) << 8);

        // 所用的重要颜色数目
        int biClrImportant = convertByteArrayToInt(bitMapInfoHeader, 36);
        byte[] biImportant = new byte[biClrImportant * 4];
        bis.read(biImportant, 0, biClrImportant * 4);

        /* 
         * 因为32位的Windows操作系统处理4个字节(32位)的速度比较快，
         * 所以BMP的每一行颜色占用的字节数规定为4的整数倍
         * 补位的公式为：widthBytes = (width*biBitCount+31)/32*4
         */
        int widthBytes = (biWidth * biBitCount + 31) / 32 * 4;
        
        // 读取颜色点阵数据
        byte[] rgbData = new byte[widthBytes * biHeight];
        bis.read(rgbData, 0, widthBytes * biHeight);
        
        // 位图大小，单位像素
        int bitMapSize = biWidth * biHeight;
        
        // 每个像素点的rgb值，为一维数组，由以适应下面方法的参数
        int[] pix = new int[bitMapSize];
        
        int pixOffset = 0;

        if (biBitCount == 24) {
            // 将byte数组转化为Int
            for (int i = biHeight - 1 ; i >= 0 ; i--) {
                for (int j = 0 ; j < biWidth ; j++) {
                    byte[] rgbByte = {rgbData[i * widthBytes + j * 3], 
                               rgbData[i * widthBytes + j * 3 + 1],
                               rgbData[i * widthBytes + j * 3 + 2],
                               (byte) 0xff};
                    pix[pixOffset++] = convertByteArrayToInt(rgbByte, 0);
                }
            }
        } 
        else if (biBitCount == 8) {  
            // 将byte数组转化为Int
            for (int i = biHeight - 1 ; i >= 0 ; i--) {
                for (int j = 0 ; j < biWidth ; j++) {
                    byte rgbByte = rgbData[i * widthBytes + j];
                    pix[pixOffset++] = 0xff000000 + (rgbByte << 16) + (rgbByte << 8) + rgbByte;
                }
            }
        }
     
        /*
         * MemoryImageSource类是 ImageProducer 接口的一个实现，
         * 该接口使用一个数组为 Image 生成像素值
         */
        ImageProducer producer = new MemoryImageSource(biWidth, biHeight, pix, 0, biWidth);
        
        /*
         * 调用以下方法：Toolkit.createImage(ImageProducer producer) 
         * 使用指定的图像生成器（ImageProducer）创建一幅图像。
         */
        Image img = Toolkit.getDefaultToolkit().createImage(producer);
        
        fin.close();  
        bis.close();
        
        return img;
    }

    /**
     * 此方法用于将byteBuffer里面的某个数据段（共4bytes）转化int类型
     * @param startPos 需要读取bitMapInfoHeader数组的初始位置
     * @return          由startPos到endPos这段byte转化成的int型
     */
    public int convertByteArrayToInt(byte[] byteBuffer, int startPos) {
        // 注意数据是小端存储的方式，故startPos + 3才是最高位
        // &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位 
        int byte0 = (byteBuffer[startPos] & 0xff);
        int byte1 = (byteBuffer[startPos + 1] & 0xff) << 8;
        int byte2 = (byteBuffer[startPos + 2] & 0xff) << 16;
        int byte3 = (byteBuffer[startPos + 3] & 0xff) << 24;
        return byte0 + byte1 + byte2 + byte3; 
    }
    
    @Override
    public Image myWrite(Image image, String filePath) throws IOException {
        try {
            
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            
            // BufferedImage实现了RenderedImage接口，故先将Image类型转化为BufferedImage类型
            BufferedImage buffImage = 
                    new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            
            // 将图像画到buffImage中
            Graphics2D bGr = buffImage.createGraphics();
            bGr.drawImage(image, 0, 0, width, height, null);
            bGr.dispose();
            
            // 创建一个输出文件
            File output= new File(filePath + ".bmp");
            ImageIO.write(buffImage, "bmp", output);

        } catch (Exception e) {
            return null;
        }
        return image;
    }

}
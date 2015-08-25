import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import imagereader.IImageProcessor;

public class ImplementImageProcesser implements IImageProcessor {

    private enum CHANEL_TYPE {RED, GREEN, BLUE, GRAY} 
    
    /**
     * 将Image对象转化为BufferedImage对象
     * @param image 想要转换的对象
     * @return      转换结果
     */
    private Image showChanel(CHANEL_TYPE c, Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        
        // BufferedImage实现了RenderedImage接口，故先将Image类型转化为BufferedImage类型
        BufferedImage buffImage = 
                new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        
        // 将图像画到buffImage中
        Graphics2D bGr = buffImage.createGraphics();
        bGr.drawImage(image, 0, 0, width, height, null);
        bGr.dispose();
        
        int[] rgbArray = new int[width * height];
        buffImage.getRGB(0, 0, width, height, rgbArray, 0, width);

        switch (c) {
        case RED:
            for (int i = 0 ; i < width * height ; i++) {
                rgbArray[i] = 0xffff0000 & rgbArray[i];
            }
            break;
        case GREEN:
            for (int i = 0 ; i < width * height ; i++) {
                rgbArray[i] = 0xff00ff00 & rgbArray[i];
            }
            break;
        case BLUE:
            for (int i = 0 ; i < width * height ; i++) {
                rgbArray[i] = 0xff0000ff & rgbArray[i];
            }
            break;
        case GRAY:
            /* 采用NTSC推荐的彩色图到灰度图的转换公式：
                I = 0.299 * R + 0.587 * G + 0.114 * B，
                其中R,G,B分别为红、绿、蓝通道的颜色值。
            */
            for (int i = 0 ; i < width * height ; i++) {
                int r = (0x00ff0000 & rgbArray[i]) >> 16;
                int g = (0x0000ff00 & rgbArray[i]) >> 8;
                int b = 0x000000ff & rgbArray[i];
                int n = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                rgbArray[i] = 0xff000000 + (n << 16) + (n << 8) + n;
            }
            break;
        default:
            break;
        }
        
        // 把图像设置为新的RGB值
        buffImage.setRGB(0, 0, width, height, rgbArray, 0, width);

        return (Image) buffImage;
    }
    
    /**
     * 提取蓝色通道
     */
    @Override
    public Image showChanelB(Image image) {
        return showChanel(CHANEL_TYPE.BLUE, image);
    }

    /**
     * 提取绿色通道
     */
    @Override
    public Image showChanelG(Image image) {
        return showChanel(CHANEL_TYPE.GREEN, image);
    }

    /**
     * 提取红色通道
     */
    @Override
    public Image showChanelR(Image image) {
        return showChanel(CHANEL_TYPE.RED, image);
    }

    /**
     * 转化成灰度图像
     */
    @Override
    public Image showGray(Image image) {
        return showChanel(CHANEL_TYPE.GRAY, image);
    }

}
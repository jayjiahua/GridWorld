import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

/**
 * Test two bmp pictures. Each picture has four color, red, green, blue
 * and gray.
 * @author Jiahua Wu
 */

public class ImplementImageProcesserTest {
    /*  The test file path  */
    private static final String GOAL_RED_PATH1 = "bmptest/goal/1_red_goal.bmp";
    private static final String GOAL_GREEN_PATH_1 = "bmptest/goal/1_green_goal.bmp";
    private static final String GOAL_BLUE_PATH_1 = "bmptest/goal/1_blue_goal.bmp";
    private static final String GOAL_GRAY_PATH_1 = "bmptest/goal/1_gray_goal.bmp";
    
    private static final String GOAL_RED_PATH2 = "bmptest/goal/2_red_goal.bmp";
    private static final String GOAL_GREEN_PATH_2 = "bmptest/goal/2_green_goal.bmp";
    private static final String GOAL_BLUE_PATH_2 = "bmptest/goal/2_blue_goal.bmp";
    private static final String GOAL_GRAY_PATH_2 = "bmptest/goal/2_gray_goal.bmp";
    
    private static final String MY_RED_PATH_1 = "myresult/1_red.bmp";
    private static final String MY_GREEN_PATH_1 = "myresult/1_green.bmp";
    private static final String MY_BULE_PATH_1 = "myresult/1_blue.bmp";
    private static final String MY_GRAY_PATH_1 = "myresult/1_gray.bmp";
    
    private static final String MY_RED_PATH_2 = "myresult/2_red.bmp";
    private static final String MY_GREEN_PATH_2 = "myresult/2_green.bmp";
    private static final String MY_BULE_PATH_2 = "myresult/2_blue.bmp";
    private static final String MY_GRAY_PATH_2 = "myresult/2_gray.bmp";

    
    
    @Before
    public void setUp() throws Exception {
        
    }

    /**
     * Test height, width and RGB between goal and my result
     * @throws IOException
     */
    @Test
    public void testShowChanelB1() throws IOException {
        TestRunner runner = new TestRunner(GOAL_BLUE_PATH_1, MY_BULE_PATH_1);
        runner.testHeight();
        runner.testWidth();
        runner.testRGB();
    }

    @Test
    public void testShowChanelB2() throws IOException {
        TestRunner runner = new TestRunner(GOAL_BLUE_PATH_2, MY_BULE_PATH_2);
        runner.testHeight();
        runner.testWidth();
        runner.testRGB();
    }

    @Test
    public void testShowChanelG1() throws IOException {
        TestRunner runner = new TestRunner(GOAL_GREEN_PATH_1, MY_GREEN_PATH_1);
        runner.testHeight();
        runner.testWidth();
        runner.testRGB();
    }

    @Test
    public void testShowChanelG2() throws IOException {
        TestRunner runner = new TestRunner(GOAL_GREEN_PATH_2, MY_GREEN_PATH_2);
        runner.testHeight();
        runner.testWidth();
        runner.testRGB();
    }

    
    @Test
    public void testShowChanelR1() throws IOException {
        TestRunner runner = new TestRunner(GOAL_RED_PATH1, MY_RED_PATH_1);
        runner.testHeight();
        runner.testWidth();
        runner.testRGB();
    }
    
    @Test
    public void testShowChanelR2() throws IOException {
        TestRunner runner = new TestRunner(GOAL_RED_PATH2, MY_RED_PATH_2);
        runner.testHeight();
        runner.testWidth();
        runner.testRGB();   
    }

    @Test
    public void testShowGray1() throws IOException {
        TestRunner runner = new TestRunner(GOAL_GRAY_PATH_1, MY_GRAY_PATH_1);
        runner.testHeight();
        runner.testWidth();
        runner.testRGB();   
    }

    @Test
    public void testShowGray2() throws IOException {
        TestRunner runner = new TestRunner(GOAL_GRAY_PATH_2, MY_GRAY_PATH_2);
        runner.testHeight();
        runner.testWidth();
        runner.testRGB();   
    }
    
    
}

/**
 * Avoiding redundance, so write this class.
 * @author Jiahua Wu
 *
 */

class TestRunner 
{
    private BufferedImage goalBufferedImage;
    private BufferedImage myBufferedImage;
    private int goalHeight;
    private int goalWidth;
    private int myHeight;
    private int myWidth;
    
    public TestRunner(String goalPath, String myPath) throws IOException {
        // get current path to find my picture
        String currentPath = this.getClass().getResource("").getPath();
        currentPath = currentPath.substring(0, currentPath.length() - 4);
        File goalImage = new File(currentPath + goalPath);
        File myImage = new File(currentPath + myPath);
        
        goalBufferedImage = ImageIO.read(goalImage);
        myBufferedImage = ImageIO.read(myImage);
        
        goalHeight = goalBufferedImage.getHeight();
        goalWidth = goalBufferedImage.getWidth();

        myWidth = myBufferedImage.getWidth();
        myHeight = myBufferedImage.getHeight();
    }
    
    public void testWidth() {
        assertEquals(goalWidth, myWidth);
    }
    
    public void testHeight() {
        assertEquals(goalHeight, myHeight);
    }
    
    public void testRGB() {
        int[] goalArray = new int[goalWidth * goalHeight];
        int[] myArray = new int[goalWidth * goalHeight];
        
        goalBufferedImage.getRGB(0, 0, goalWidth, goalHeight, goalArray, 0, goalWidth);
        myBufferedImage.getRGB(0, 0, myWidth, myHeight, myArray, 0, myWidth);
        assertArrayEquals(goalArray, myArray);
    }
    
}

// ImagaReaderRunner.java
import imagereader.Runner;
 
public class ImageReaderRunner {
    public static void main(String[] args) {
        ImplementImageIO imageioer = new ImplementImageIO();
        ImplementImageProcesser processor = new ImplementImageProcesser();
        Runner.run(imageioer, processor);
    }
}
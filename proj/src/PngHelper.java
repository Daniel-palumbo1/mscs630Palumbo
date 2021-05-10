import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class PngHelper {
    public BufferedImage img;
    public ByteArrayOutputStream stream;
    public String[] arr;


    PngHelper(String path, String key) {
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Danie\\Desktop\\CSMS\\Security Algorithms\\repo\\proj\\src\\images\\Beaver (82).png"));
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(img,"png",stream);
            //Signed 32 bit int -->
            byte[] hex = stream.toByteArray();
            this.img = img;
            this.stream = stream;

            AES aes = new AES(key, byteToHex(hex));


        }catch(Exception e){
            System.out.println(e);
        }
    }

    //converts a byte array to an array of hex Strings
    public static String[] byteToHex(byte[] bytes){
        String[] out = new String[bytes.length];
        for(int i = 0; i < bytes.length; i++){
            out[i] = hexAsByte(bytes[i]);
        }
        return out;
    }

    //converts an array of Hex strings to an array of bytes
    public static byte[] hexToByte(String[] hex){
        byte[] out = new byte[hex.length];
        for(int i = 0; i < hex.length; i++){
            out[i] = byteAsHex(hex[i]);
        }
        return out;
    }


    // Converts hex to bytes
    public static byte byteAsHex(String hex){
        return (byte) ((Character.digit(hex.charAt(0),16) << 4) + Character.digit(hex.charAt(1),16));

    }

    //Converts bytes to Hex
    public static String hexAsByte(byte bytes){
        String out = Integer.toHexString(bytes & 0xFF);
        return out.length() == 1 ? "0" + out.toUpperCase() : out.toUpperCase();
    }


}

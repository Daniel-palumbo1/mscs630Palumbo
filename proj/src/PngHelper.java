import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;

public class PngHelper {
    public BufferedImage img;
    public ByteArrayOutputStream stream;
    public String[] arr;
    public AES aes;
    public String pngHeader = "89504E470D0A1A0A";


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
            this.aes = aes;


        }catch(Exception e){
            System.out.println(e);
        }
    }

    public String getEncryption(){
        String hexString = aes.encrypt();
        return hexString;
    }

    public String getDecryption(){
        aes.decrypt();
        return null;
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

    public static String[] hexArray(String str){
        String[] out = new String[str.length()/2];
        System.out.println(str.length());
        int arrIndex = 0;
        for(int i = 0; i < str.length()-2; i+=2){
            String val = "" +  str.charAt(i) + str.charAt(i+1);
            out[arrIndex] = val;
            arrIndex++;
        }
        out[arrIndex] = "" + str.charAt(str.length()-2) + str.charAt(str.length()-1);
        System.out.println(out.length);
        System.out.println(Arrays.toString(out));
        return out;
    }


}

public class Driver {

    public static void main(String[] arg){
        PngHelper img = new PngHelper("C:\\Users\\Danie\\Desktop\\CSMS\\Security Algorithms\\repo\\proj\\src\\images\\Beaver (82).png", "BECE93DD29721D8627D7611886510F69");
        String encrypted = img.getEncryption();
        String decrypted = img.getDecryption();
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}

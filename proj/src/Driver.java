public class Driver {

    public static void main(String[] arg){
        PngHelper img = new PngHelper("C:\\Users\\Danie\\Desktop\\CSMS\\Security Algorithms\\repo\\proj\\src\\images\\Beaver (82).png", "BECE93DD29721D8627D7611886510F69");
        String s = img.getEncryption();
        String j = img.getDecryption();
        System.out.println(s);
        System.out.println(j);
    }
}

public class Driver {

    public static void main(String[] arg){
        PngHelper img = new PngHelper("C:\\Users\\Danie\\Desktop\\CSMS\\Security Algorithms\\repo\\proj\\src\\images\\Beaver (82).png", "BECE93DD29721D8627D7611886510F69");
        img.getEncryption();
        img.getDecryption();
//        System.out.println(Arrays.toString(img.arr));
//        String[] hex = PngHelper.byteToHex(img.arr);
//        System.out.println(Arrays.toString(hex));
//        byte[] backToByte = PngHelper.hexToByte(hex);
//        System.out.println(hex.length);
//        System.out.println(img.arr.length);
//        System.out.println(Arrays.toString(backToByte));
//        System.out.println(backToByte.length);
//
//        SecureRandom ran = new SecureRandom();
//        byte bytes[] = new byte[32];
//        ran.nextBytes(bytes);
//        String[] IV = PngHelper.byteToHex(bytes);
//        System.out.println(Arrays.toString(bytes));
//        System.out.println(Arrays.toString(IV));

    }
}

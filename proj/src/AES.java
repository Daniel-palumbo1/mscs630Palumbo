import java.security.SecureRandom;
import java.util.*;

public class AES {
    private static String key;
    private static String IV;
    private static int padding;
    private String last;


    private static String[][] GF256 = {{"02","03","01","01"},
            {"01","02","03","01"},
            {"01","01","02","03"},
            {"03","01","01","02"}
    };

    //Sbox implementation
    private static int[] sBox = new int[] {
            0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
            0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0,
            0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15,
            0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
            0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
            0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF,
            0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8,
            0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
            0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73,
            0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
            0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79,
            0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
            0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A,
            0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E,
            0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
            0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
    };

    private static final int[] rCon = {
            0x8D,0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80,0x1B,0x36,0x6C,0xD8,0xAB,0x4D,0x9A,
            0x2F,0x5E,0xBC,0x63,0xC6,0x97,0x35,0x6A,0xD4,0xB3,0x7D,0xFA,0xEF,0xC5,0x91,0x39,
            0x72,0xE4,0xD3,0xBD,0x61,0xC2,0x9F,0x25,0x4A,0x94,0x33,0x66,0xCC,0x83,0x1D,0x3A,
            0x74,0xE8,0xCB,0x8D,0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80,0x1B,0x36,0x6C,0xD8,
            0xAB,0x4D,0x9A,0x2F,0x5E,0xBC,0x63,0xC6,0x97,0x35,0x6A,0xD4,0xB3,0x7D,0xFA,0xEF,
            0xC5,0x91,0x39,0x72,0xE4,0xD3,0xBD,0x61,0xC2,0x9F,0x25,0x4A,0x94,0x33,0x66,0xCC,
            0x83,0x1D,0x3A,0x74,0xE8,0xCB,0x8D,0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80,0x1B,
            0x36,0x6C,0xD8,0xAB,0x4D,0x9A,0x2F,0x5E,0xBC,0x63,0xC6,0x97,0x35,0x6A,0xD4,0xB3,
            0x7D,0xFA,0xEF,0xC5,0x91,0x39,0x72,0xE4,0xD3,0xBD,0x61,0xC2,0x9F,0x25,0x4A,0x94,
            0x33,0x66,0xCC,0x83,0x1D,0x3A,0x74,0xE8,0xCB,0x8D,0x01,0x02,0x04,0x08,0x10,0x20,
            0x40,0x80,0x1B,0x36,0x6C,0xD8,0xAB,0x4D,0x9A,0x2F,0x5E,0xBC,0x63,0xC6,0x97,0x35,
            0x6A,0xD4,0xB3,0x7D,0xFA,0xEF,0xC5,0x91,0x39,0x72,0xE4,0xD3,0xBD,0x61,0xC2,0x9F,
            0x25,0x4A,0x94,0x33,0x66,0xCC,0x83,0x1D,0x3A,0x74,0xE8,0xCB,0x8D,0x01,0x02,0x04,
            0x08,0x10,0x20,0x40,0x80,0x1B,0x36,0x6C,0xD8,0xAB,0x4D,0x9A,0x2F,0x5E,0xBC,0x63,
            0xC6,0x97,0x35,0x6A,0xD4,0xB3,0x7D,0xFA,0xEF,0xC5,0x91,0x39,0x72,0xE4,0xD3,0xBD,
            0x61,0xC2,0x9F,0x25,0x4A,0x94,0x33,0x66,0xCC,0x83,0x1D,0x3A,0x74,0xE8,0xCB,0x8D
    };

    private static final int[] MULT_TWO = {
            0x00,0x02,0x04,0x06,0x08,0x0a,0x0c,0x0e,0x10,0x12,0x14,0x16,0x18,0x1a,0x1c,0x1e,
            0x20,0x22,0x24,0x26,0x28,0x2a,0x2c,0x2e,0x30,0x32,0x34,0x36,0x38,0x3a,0x3c,0x3e,
            0x40,0x42,0x44,0x46,0x48,0x4a,0x4c,0x4e,0x50,0x52,0x54,0x56,0x58,0x5a,0x5c,0x5e,
            0x60,0x62,0x64,0x66,0x68,0x6a,0x6c,0x6e,0x70,0x72,0x74,0x76,0x78,0x7a,0x7c,0x7e,
            0x80,0x82,0x84,0x86,0x88,0x8a,0x8c,0x8e,0x90,0x92,0x94,0x96,0x98,0x9a,0x9c,0x9e,
            0xa0,0xa2,0xa4,0xa6,0xa8,0xaa,0xac,0xae,0xb0,0xb2,0xb4,0xb6,0xb8,0xba,0xbc,0xbe,
            0xc0,0xc2,0xc4,0xc6,0xc8,0xca,0xcc,0xce,0xd0,0xd2,0xd4,0xd6,0xd8,0xda,0xdc,0xde,
            0xe0,0xe2,0xe4,0xe6,0xe8,0xea,0xec,0xee,0xf0,0xf2,0xf4,0xf6,0xf8,0xfa,0xfc,0xfe,
            0x1b,0x19,0x1f,0x1d,0x13,0x11,0x17,0x15,0x0b,0x09,0x0f,0x0d,0x03,0x01,0x07,0x05,
            0x3b,0x39,0x3f,0x3d,0x33,0x31,0x37,0x35,0x2b,0x29,0x2f,0x2d,0x23,0x21,0x27,0x25,
            0x5b,0x59,0x5f,0x5d,0x53,0x51,0x57,0x55,0x4b,0x49,0x4f,0x4d,0x43,0x41,0x47,0x45,
            0x7b,0x79,0x7f,0x7d,0x73,0x71,0x77,0x75,0x6b,0x69,0x6f,0x6d,0x63,0x61,0x67,0x65,
            0x9b,0x99,0x9f,0x9d,0x93,0x91,0x97,0x95,0x8b,0x89,0x8f,0x8d,0x83,0x81,0x87,0x85,
            0xbb,0xb9,0xbf,0xbd,0xb3,0xb1,0xb7,0xb5,0xab,0xa9,0xaf,0xad,0xa3,0xa1,0xa7,0xa5,
            0xdb,0xd9,0xdf,0xdd,0xd3,0xd1,0xd7,0xd5,0xcb,0xc9,0xcf,0xcd,0xc3,0xc1,0xc7,0xc5,
            0xfb,0xf9,0xff,0xfd,0xf3,0xf1,0xf7,0xf5,0xeb,0xe9,0xef,0xed,0xe3,0xe1,0xe7,0xe5
    };

    private static final int[] MULT_THREE = {
            0x00,0x03,0x06,0x05,0x0c,0x0f,0x0a,0x09,0x18,0x1b,0x1e,0x1d,0x14,0x17,0x12,0x11,
            0x30,0x33,0x36,0x35,0x3c,0x3f,0x3a,0x39,0x28,0x2b,0x2e,0x2d,0x24,0x27,0x22,0x21,
            0x60,0x63,0x66,0x65,0x6c,0x6f,0x6a,0x69,0x78,0x7b,0x7e,0x7d,0x74,0x77,0x72,0x71,
            0x50,0x53,0x56,0x55,0x5c,0x5f,0x5a,0x59,0x48,0x4b,0x4e,0x4d,0x44,0x47,0x42,0x41,
            0xc0,0xc3,0xc6,0xc5,0xcc,0xcf,0xca,0xc9,0xd8,0xdb,0xde,0xdd,0xd4,0xd7,0xd2,0xd1,
            0xf0,0xf3,0xf6,0xf5,0xfc,0xff,0xfa,0xf9,0xe8,0xeb,0xee,0xed,0xe4,0xe7,0xe2,0xe1,
            0xa0,0xa3,0xa6,0xa5,0xac,0xaf,0xaa,0xa9,0xb8,0xbb,0xbe,0xbd,0xb4,0xb7,0xb2,0xb1,
            0x90,0x93,0x96,0x95,0x9c,0x9f,0x9a,0x99,0x88,0x8b,0x8e,0x8d,0x84,0x87,0x82,0x81,
            0x9b,0x98,0x9d,0x9e,0x97,0x94,0x91,0x92,0x83,0x80,0x85,0x86,0x8f,0x8c,0x89,0x8a,
            0xab,0xa8,0xad,0xae,0xa7,0xa4,0xa1,0xa2,0xb3,0xb0,0xb5,0xb6,0xbf,0xbc,0xb9,0xba,
            0xfb,0xf8,0xfd,0xfe,0xf7,0xf4,0xf1,0xf2,0xe3,0xe0,0xe5,0xe6,0xef,0xec,0xe9,0xea,
            0xcb,0xc8,0xcd,0xce,0xc7,0xc4,0xc1,0xc2,0xd3,0xd0,0xd5,0xd6,0xdf,0xdc,0xd9,0xda,
            0x5b,0x58,0x5d,0x5e,0x57,0x54,0x51,0x52,0x43,0x40,0x45,0x46,0x4f,0x4c,0x49,0x4a,
            0x6b,0x68,0x6d,0x6e,0x67,0x64,0x61,0x62,0x73,0x70,0x75,0x76,0x7f,0x7c,0x79,0x7a,
            0x3b,0x38,0x3d,0x3e,0x37,0x34,0x31,0x32,0x23,0x20,0x25,0x26,0x2f,0x2c,0x29,0x2a,
            0x0b,0x08,0x0d,0x0e,0x07,0x04,0x01,0x02,0x13,0x10,0x15,0x16,0x1f,0x1c,0x19,0x1a
    };


    public AES(String key,String[] arr){
       generateIV(key.length());
       setKey(key);
       String data = stringMaker(arr);
       String[] dataArray = new String[data.length()/32];
       System.out.println(this.key);
       int index = 0;
       for(int i = 0; i < dataArray.length; i++){
           dataArray[i] = data.substring(index,index+32);
           index += 32;
       }
       IVxor(getIV(),dataArray[0]);
       String[] encrypted = new String[dataArray.length];
       encrypted[0] = AESEncryption(last);
       System.out.println("encrypted: " + encrypted[0]);
       System.out.println(last);


    }

    public static String AESEncryption(String pTextHex){

        //adding round keys from keyHex
        String[] roundKeysHex = aesRoundKeys(getKey());
        String[][] outStateHex = AESStateXOR(pTextHex,roundKeysHex[0]);

        for(int i = 0; i < 9; i++){
            outStateHex = AESNibbleSub(outStateHex);
            outStateHex = AESShiftRow(outStateHex);
            outStateHex = AESMixColumn(outStateHex);
            outStateHex = AESStateXOR(stringify(outStateHex),roundKeysHex[i+1]);
        }

        outStateHex = AESNibbleSub(outStateHex);
        outStateHex = AESShiftRow(outStateHex);
        outStateHex = AESStateXOR(stringify(outStateHex),roundKeysHex[10]);
        return stringify(outStateHex);
    }

    public static String[] aesRoundKeys(String KeyHex){
        //Section1: adding each Hex code to first the initial 4x4 matrix and then to W the 4x44 matrix.
        String[] output = new String[11];
        Queue<String> queue = new LinkedList<>();

        for(int index = 0; index < KeyHex.length(); index+=2){
            queue.add(KeyHex.substring(index,index+2));
        }

        String[][] Ke = new String[4][4];
        for(int i = 0; i < Ke.length; i++){
            for(int j = 0; j < Ke[0].length; j++){
                Ke[j][i] = queue.remove();
            }
        }

        String[][] W = new String[4][44];
        for(int i = 0; i < Ke.length; i++){
            for(int j = 0; j < Ke[0].length; j++){
                W[j][i] = Ke[j][i];
            }
        }

        /*Section2: If the column number is divisible by 4, we shift the temp array
         *then take the SBox value of each entry in the temp array. We then XOR index 0 against the Rcon round and then
         *XOR each entry against the entries in the column-4.
         */
        for(int col = 4; col < W[0].length; col++){
            String[] tempW = {W[0][col-1], W[1][col-1], W[2][col-1], W[3][col-1]};
            if(col % 4 == 0){
                tempW = shiftUp(tempW);

                for(int x = 0; x < tempW.length; x++){
                    tempW[x] = Integer.toHexString(aesSBox(tempW[x]));
                }

                tempW[0] = XOR(aesRcon(getRound(col)),tempW[0]).toUpperCase();

                for(int x = 0; x < tempW.length; x++){
                    tempW[x] = XOR(W[x][col-4],tempW[x]);
                    W[x][col] = tempW[x].length() == 2 ? tempW[x].toUpperCase() : "0" + tempW[x].toUpperCase();
                }
            }
            /*Section3: If the column number is not divisible by 4, we just XOR each entry against the entries in the
             *column-4.
             */
            else{
                for(int x = 0; x < tempW.length; x++){
                    tempW[x] = XOR(W[x][col-4],tempW[x]);
                    W[x][col] = tempW[x].length() == 2 ? tempW[x].toUpperCase() : "0" + tempW[x].toUpperCase();
                }
            }
        }
        //Section 4: I use a StringBuffer to iterate through W and create 11 strings to then load into the output array
        int strIndex = 0;
        int colNum = 1;
        StringBuffer ans = new StringBuffer();

        for(int col = 0; col < W[0].length; col++, colNum++){
            for(int row = 0; row < W.length; row++){
                ans.append(W[row][col]);
            }
            if(colNum % 4 == 0){
                output[strIndex] = ans.toString();
                strIndex++;
                ans.delete(0,ans.length());
            }
        }
        return output;
    }

    /**
     * aesStateXOR
     *
     * This method takes in 2 32 bit strings and returns an array of Strings that represent each the xor of the
     * 4 x 4 matrices.
     *
     * @param sHex String, String 32 bit string
     * @param keyHex String, String 32 bit string
     * @return String[][] a 4 x 4 Matrix of Strings pertaining to the XOR of each input as a 4 x 4 matrix.
     */
    public static String[][] AESStateXOR(String sHex, String keyHex){
        if(sHex.length() != keyHex.length() ){
            System.out.println("Error has occured in AESStateXOR. Array lengths do not match.");
        }

        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        for(int index = 0; index < sHex.length(); index+=2){
            queue1.add(sHex.substring(index,index+2));
        }

        for(int index = 0; index < keyHex.length(); index+=2){
            queue2.add(keyHex.substring(index,index+2));
        }

        String[][] ptHex = new String[4][4];
        for(int i = 0; i < ptHex.length; i++){
            for(int j = 0; j < ptHex[0].length; j++){
                ptHex[j][i] = queue1.remove();
            }
        }
        String[][] keysHex = new String[4][4];
        for(int i = 0; i < keysHex.length; i++){
            for(int j = 0; j < keysHex[0].length; j++){
                keysHex[j][i] = queue2.remove();
            }
        }

        String[][] output = new String[ptHex.length][ptHex.length];
        for(int i = 0; i < ptHex.length; i++){
            for(int j = 0; j < ptHex[0].length; j++){
                output[i][j] = XOR(ptHex[i][j],keysHex[i][j]);
            }
        }
        return output;
    }



    public static String[] shiftUp(String[] str){
        String[] shifted = {str[1],str[2],str[3],str[0]};
        return shifted;
    }

    public static String[][] AESMixColumn(String[][] inStateHex){
        String[][] output = new String[inStateHex.length][inStateHex.length];
        for(int i = 0; i < inStateHex.length; i++){
            for(int j = 0; j < inStateHex.length; j++){
                int num = 0;
                for(int x = 0; x < inStateHex.length; x++){
                    num ^= evaluateGF(GF256[i][x],inStateHex[x][j]);
                }
                output[i][j] = Integer.toHexString(num);
            }
        }
        return output;
    }

    public static String[][] AESNibbleSub(String[][] inStateHex){
        String[][] output = new String[inStateHex.length][inStateHex.length];
        for(int i = 0; i < inStateHex.length; i++){
            for(int j = 0; j < inStateHex[0].length; j++){
                output[i][j] = Integer.toHexString(aesSBox(inStateHex[i][j]));
            }
        }
        return output;
    }

    public static String[][] AESShiftRow(String[][] inStateHex){
        String[][] output = new String[inStateHex.length][inStateHex.length];
        for(int i = 0; i < inStateHex.length; i++){
            for(int j = 0; j < inStateHex.length; j++) {
                if(i < 1){
                    output[i][j] = inStateHex[i][j];
                }
                else if(i == 1){
                    if(j == 3)
                        output[i][j] = inStateHex[i][j-3];
                    else
                        output[i][j] = inStateHex[i][j+1];
                }
                else if(i == 2){
                    if(j >= 2)
                        output[i][j] = inStateHex[i][j-2];
                    else
                        output[i][j] = inStateHex[i][j+2];
                }
                else{
                    if(j == 0)
                        output[i][j] = inStateHex[i][j+3];
                    else
                        output[i][j] = inStateHex[i][j-1];

                }
            }
        }
        return output;
    }

    public static int evaluateGF(String gf, String hex){
        if(gf.equals("02"))
            return MULT_TWO[Integer.parseInt(hex,16)];
        else if(gf.equals("03"))
            return MULT_THREE[Integer.parseInt(hex,16)];
        else
            return Integer.parseInt(hex,16);
    }

    public static String stringify(String[][] hex){
        String str = "";
        for(int i = 0; i < hex.length; i++){
            for(int j = 0; j < hex[0].length; j++){
                if(hex[j][i].length() < 2)
                    str += "0" + hex[j][i].toUpperCase();
                else
                    str += hex[j][i].toUpperCase();
            }
        }
        return str;
    }

    public static int aesSBox(String inHex){
        return sBox[Integer.parseInt(inHex,16)];
    }

    public static String aesRcon(int round){
        return Integer.toHexString(rCon[round]);
    }

    public static int getRound(int column){
        return (int) Math.floor(column/4);
    }

    public static String XOR(String left, String right){
        return Integer.toHexString(Integer.parseInt(left,16) ^ Integer.parseInt(right,16));
    }

    public void generateIV(int keyLength){
        SecureRandom ran = new SecureRandom();
        byte[] bytes = ran.generateSeed(16);
        String[] hex = byteToHex(bytes);
        StringBuffer str = new StringBuffer();
        for(String s: hex){
            str.append(s);
        }
        this.IV = str.toString();
    }

    public static String getIV(){
        return IV;
    }

    public static String[] byteToHex(byte[] bytes){
        String[] out = new String[bytes.length];
        for(int i = 0; i < bytes.length; i++){
            out[i] = hexAsByte(bytes[i]);
        }
        return out;
    }

    public static String hexAsByte(byte bytes){
        String out = Integer.toHexString(bytes & 0xFF);
        return out.length() == 1 ? "0" + out.toUpperCase() : out.toUpperCase();
    }

    public String IVxor(String IV, String plaintext){
        StringBuffer str = new StringBuffer();
        for(int i = 0; i < IV.length(); i+=2){
            String xor = XOR(IV.substring(i,i+2),plaintext.substring(i,i+2));
            if(xor.length() > 1){
                str.append(xor);
            }
            else{
                str.append("0" + xor);
            }
        }
        this.last = str.toString().toUpperCase();
        return str.toString().toUpperCase();
    }

    public String stringMaker(String[] arr){
        StringBuffer str = new StringBuffer();
        for(String s: arr){
            str.append(s);
        }
        int padding = 0;
        while(str.length() % 32 != 0){
            Random ran  = new Random();
            int val = ran.nextInt(256);
            str.append(Integer.toHexString(val).toUpperCase());
            padding++;
        }
        this.padding = padding;
        return str.toString();
    }

    public void setKey(String key){
        this.key = key;
    }

    public static String getKey(){
        return key;
    }
    public String getLast(){
        return this.last;
    }


}

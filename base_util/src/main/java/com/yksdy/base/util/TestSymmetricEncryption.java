package com.yksdy.base.util;

public class TestSymmetricEncryption {
    private static final String DATA = "asiatravel";

    public static void main(String[] args) {
        testDES();
        test3DES();
        testAES();
    }

    /**
     * 测试AES加密-->对称加密
     */
    public static void testAES() {
        try {
            /**
             * 密钥
             */
            byte[] aesKey = AESUtil.initKey();
            System.out.println(DATA + " AES key: " + ByteToHexUtil.fromByteToHex(aesKey));
            /**
             * 加密后的数据
             */
            byte[] encryptResult = AESUtil.encrypt(DATA.getBytes(), aesKey);
            System.out.println(DATA + " AES 加密: " + ByteToHexUtil.fromByteToHex(encryptResult));
            /**
             * 解密后的数据
             */
            byte[] decryptResult = AESUtil.decrypt(encryptResult, aesKey);
            System.out.println(DATA + " AES 解密: " + new String(decryptResult));
            System.out.println();
        } catch (Exception e) {
        }
    }

    /**
     * 测试3DES加密-->对称加密
     */
    public static void test3DES() {
        try {
            /**
             * 密钥
             */
            byte[] tripleKey = TripleDESUtil.initKey();
            System.out.println(DATA + " 3DES key: " + ByteToHexUtil.fromByteToHex(tripleKey));
            /**
             * 加密后的数据
             */
            byte[] encryptResult = TripleDESUtil.encrypt(DATA.getBytes(), tripleKey);
            System.out.println(DATA + " 3DES 加密: " + ByteToHexUtil.fromByteToHex(encryptResult));
            /**
             * 解密后的数据
             */
            byte[] decryptResult = TripleDESUtil.decrypt(encryptResult, tripleKey);
            System.out.println(DATA + " 3DES 解密: " + new String(decryptResult));
            System.out.println();
        } catch (Exception e) {
        }
    }

    /**
     * 测试DES加密-->对称加密
     */
    public static void testDES() {
        /**
         * DES 加密
         */
        byte[] desKey = DESUtil.initKey();
        System.out.println(DATA +" DES key: " + ByteToHexUtil.fromByteToHex(desKey));
        /**
         * 加密后的数据
         */
        byte[] desResult = DESUtil.encrypt(DATA.getBytes(), desKey);
        System.out.println(DATA + " DES 加密: " + ByteToHexUtil.fromByteToHex(desResult));

        /**
         * DES 解密
         */
        byte[] decryptResult = DESUtil.decrypt(desResult, desKey);
        System.out.println(DATA + " DES 解密" + new String(decryptResult));
        System.out.println();
    }
}

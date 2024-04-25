package com.cnsbd.aptitudo.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.Base64;

public class CipherUtils implements Serializable {

    // secret key
    // private final static byte[] KEY = {49, 122, 65, 71, 114, 83, 35, 118, 53, 65, 50, 51, 64, 118, 86, 100, 73, 104, 37, 83, 82, 36, 69, 83, 41, 51, 86, 71, 86, 101, 87, 50};
    // private static final String KEY = "1zAGrS#v5A23@vVdIh%SR$ES)3VGVeW2";
    private static final byte[] KEY = {0x14, 0x68, 0x63, 0x73, 0x49, 0x53, 0x21, 0x53, 0x65, 0x4a, 0x7c, 0x65, 0x71, 0x4b, 0x65, 0x59};

    public static String encrypt(String strToEncrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(KEY, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final String encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes()));
            return encryptedString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(KEY, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final String decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            return decryptedString;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /*public static void main(String[] args) {
        CommandLineParser parser = new PosixParser();
        Options options = new Options();
        Option help = new Option("help", "Display help");
        Option encrypt = new Option("encrypt", true, " - string to encrypt");
        Option decrypt = new Option("decrypt", true, " - string to decrypt");
        options.addOption(help);
        options.addOption(encrypt);
        options.addOption(decrypt);
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("encrypt")) {
                final String strToEncrypt = cmd.getOptionValue("encrypt");
                final String encryptedStr = CipherUtils.encrypt(strToEncrypt.trim());
                System.out.println("String to Encrypt : " + strToEncrypt);
                System.out.println("Encrypted : " + encryptedStr);
            } else if (cmd.hasOption("decrypt")) {
                final String strToDecrypt = cmd.getOptionValue("decrypt");
                final String decryptedStr = CipherUtils.decrypt(strToDecrypt.trim());
                System.out.println("String To Decrypt : " + strToDecrypt);
                System.out.println("Decrypted : " + decryptedStr);
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("[-h] [-encrypt ]", options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}

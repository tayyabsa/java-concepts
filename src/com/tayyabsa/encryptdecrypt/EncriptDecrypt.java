package com.tayyabsa.encryptdecrypt;

import co.uk.xplora.common.adapter.DependencyResolver;
import co.uk.xplora.common.enums.XploraPayErrors;
import co.uk.xplora.common.exception.ApplicationException;
import co.uk.xplora.processor.dto.NotificationResponseDTO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.json.JSONObject;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

public class EncriptDecrypt {
    private static PublicKey tribePublicKey;
    private static PrivateKey xploraPrivateKey;
    private static PublicKey xploraPublicKey;

    public static final String tribePublicKeyRSA = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAk/iYGsBebqMnqGmR0NGTK5Z4E/vF6RsGsGR4viTnxbxtrW37sgx+Z/VeAnOZm5xi3ee2kHSM7qXmX89Mw4wopYtsxLkonefDMGDbHd//Tnw+KSD5mSiwkuRfB3IxZGq3qpSW2Uj9EEN+2aWJhMkz2SlR84KcAx2QhFy+ZqC/LsPzVopMlF/8mzqo0M/GxUxbjCvupcjkb/W8GeV5CsM0n0xi0XtviygduUuhkvurhIxYmNDbu3JK8uNM7juAHT1ivP3f2NAfuudADf2qVBor2cZ1UmC/uds5yB2GrysybB8Uz5EZMZZfbkyx3oruwNybtgJMmqiO6KaPktHscvwjsZortYZbp3of8TjwYnZoeEfuAe32uVWd35QHpUuhiMKzbxP7VjpG6+NOYDUqTGQj4LG/XImuU+RyvV3bJ2gu7utyJpmV3bIrsSFrJ5G7L1Fnkj2KHTe3TwaXX8zjzdNOCu8n5k6OzD2JuNqSM7sy+lLmdBaR5gwrSwbFYG0XEaUZXHXz+nzR6led9R1RPAxy/tseqDB4yhFLo7TfCF2NfIRk/6NV6trEe87sJzKSM4HqxqTDHLGyLATH+22ydA7Vs2pjPndvTpaV/QyLKxPKRylTwOyavyGSxv0IumDgYNzgfMLKBs2wEhRklsjFizYunK4TMK+hiiKUZjiTp8REBqcCAwEAAQ==";
    public static final String xploraPrivateKeyRSA = "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";

    public static final String xploraPublicKeyRSA = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAs+xKVA6tesSqmSyAjJ0QCFp1XPHcP64ZozKshxGTSXcBn32Cl+2Vsuj5cWNHykxcjNb2DuR2GbpuuhBv59PH4/3yjiSv7bvNSaR6Dalri4EITGEl4LIAHSRcNmo4HRstXOyGG6uc8oZi/DURh1ZDxewGkaPBJQT7aQZ+Md6ccMwQuqEv+5sk4wZs3dfdTn0nJLhZ++76y8PfNJIaHNP5nCWHD7+WIoOdhgWBcVm7pstCkZB3B7Oc8ktwzsMhLki0+tekp/1LxPuR4V0Drqve94PbqTX1BPWxd51+0mwzbA6H/yhSf31cSoQohKQTuexM8l5+2Zn7Qng4/Ix+ZSkrEy7sggEIbwd79e23bscdVLj9mwLtFlSsLaj9rs/RAH/VL0OsDquioNkMlxOQ/w3uiY4DsI0Ym9VimveEINtC/lfVk+PXoiPcWssWGJxQudfCtZpNnbAC028w/L+kOeN7GPSNA8GOKp3WrLsp7F1uA6YEkZchuGsOcT13Jo5rS8fIPNMAvVLAMi5BnZqyhGS/tpQXuaIxJVI36LVcmdgPawr+XEIcGQ1BKvh9g55pBdQ3UXG2H13gMLMT2qfdBZ7rch3PlbO5xV7mEbj1qKL6OyT66vXiOdDmWTSvb+hK7u3v0VQvONc0qusSunEaSOXg6ehM0pZ5FEhtkWx8lE2v1D0CAwEAAQ==";


    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {








        Integer i1 = 100;
        Integer i2 = 100;

        Integer j1 = 200;
        Integer j2 = 200;

        System.out.println("i1: " + (i1.hashCode()));
        System.out.println("i2: " + (i2.hashCode()));
        System.out.println("j1: " + (j1.hashCode()));
        System.out.println("j2: " + (j2.hashCode()));

        System.out.println("i1 == i2: " + (i1 == i2));
        System.out.println("j1 == j2: " + (j1 == j2));










































/*
        System.out.println(decrypt3DES("NkY0K+u72JRFnzAjRmpNgg==",
                "6093ff68cd0c6b287e41e11f2d32f9df"));*/

//        System.out.println(decryptTribeRequest("974d61aa2f696c07GUuL6qXWeAeIAsMtb5pYqi056o2V9+uFVfmXA48p0R6vz4vtzSk9uXGQma0cUoLMZW8T479qyhocnoqDCiHTcTmd5YG88XhQep6nEvwlEJVmszLDrFjw/hPd1pNyR+mgdb3m+/VgZX9ixbf++Cu4nU+ba+UFuuVNt2vUDcvUG4w=",
//                "FqgJIedXIQ2r7XTPz68NTM4xpaDK0VQwsv5u62Mhudu2O9euUlvWbly996/w3mTjB27RsWwcObuaJPz64mLUH/ZzF0m7Nc5SqaKBC0QT6CdQ1gB1N4RjF9h26UDTR2odQBNDfYZzTt4iM1AH+pSWzvVh0+NxUS/wLkWoLSQGPMZ44be1/CMCoCrcVPJTG30zeirhqEwGt8ys1X57NP3d3XTzyBwj2TxVApbDqwlKZ/O7UkW4qBYSu9EZskEvuGFyjk+iGrTwZplGmI7OTIlrCfCD8VoyyH4wY/A9U+j/14OXpuOZ8Kk0+WR3fNcVVTFdoBY3CN08Q2QknO+q9TeINwws9yA5jcjUFAMBpkGftZrZK40CUs9bu378F4hGE/HiUXvB34PaVFVO51mYXWwUeY/zCFbvhml27XgmnkTZyQbSWXMfUqmxF9vT42MqtdWBWSGzZtoUyLkLvVx+Edid/Zf44tyXQy0q7pygtAtkvHDYxp+SdSKxZBjcG0rGuEuKwXjYbqvfhCasYjtTFWse8wiXPR5H6YHEnaj2NaWuj3tJpHOsASgAd8KNk5ogYjS2gBaxK4bKt00mCtgo1PB9iH7x1n6qAWAIHOsg0+lOBBw81+CKTDPjl3WWpx4Q2iSwVl955Zi2PmYif6R6kMhmXjQhLRijNL6IIw9askEUdvE="));

    }


    public static String decryptFieldData(String data, String dataEncryptionKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {

        byte[] decodedData = Base64.decode(data);
//        String hex2bin = hex2bin(decodedData);
//        String substring = hex2bin.substring(0, 8);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, xploraPrivateKey);
        return "";
    }


    public static NotificationResponseDTO encrypt(Object object) {

        String secret = UUID.randomUUID().toString().replaceAll("-", "");
        Cipher cipher;
        try {
            if (tribePublicKey == null)
                setPublicKey();

            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, tribePublicKey);
            byte[] secretBytes = cipher.doFinal(secret.getBytes());
            String x_sign = Base64.encodeAsString(secretBytes);

            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = generateIv();
            String bin2Hex = bin2hex(ivParameterSpec.getIV());
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES"),
                    new IvParameterSpec(bin2Hex.getBytes()));
            JSONObject jsonObject = new JSONObject(object);
            return new NotificationResponseDTO().setObject(bin2Hex.concat(Base64.encodeAsString(cipher
                    .doFinal(jsonObject.toString().getBytes())))).setX_sign(x_sign);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException
                | InvalidKeyException | InvalidAlgorithmParameterException e) {
            DependencyResolver.getLog().debug("Exception Occurred : {}", e.getMessage());
            throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
        }


    }

    public static String decrypt(String data, String x_sign) {
        DependencyResolver.getLog().debug("Going to decrypt response");
        Cipher cipher;
        try {
            if (xploraPrivateKey == null)
                setPrivateKey();

            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, xploraPrivateKey);
            byte[] secretBytes = cipher.doFinal(Base64.decode(x_sign));
            String secret = new String(secretBytes, StandardCharsets.UTF_8);

            return secret;
        /*    if (data.length() % 4 != 0)
                data = data.replaceAll("=", "");
            var iv = data.substring(0, 16);

            byte[] origData = Base64.decode(data.substring(16));
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secret.getBytes(), "AES"),
                    new IvParameterSpec(iv.getBytes()));
            return new String(cipher.doFinal(origData));*/

        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            DependencyResolver.getLog().debug("Exception Occurred : {}", e.getMessage());
            throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
        }
    }

    public static String decryptTribeRequest(String data, String x_sign) {
        DependencyResolver.getLog().debug("Going to decrypt response");
        Cipher cipher;
        try {
            if (xploraPrivateKey == null) {
                try {
                    DependencyResolver.getLog().debug("Going to set Private Key");
                    byte[] pkcs8EncodedBytes = Base64.decode(xploraPrivateKeyRSA);
                    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
                    KeyFactory kf = KeyFactory.getInstance("RSA");
                    xploraPrivateKey = kf.generatePrivate(keySpec);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
                }
            }

            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, xploraPrivateKey);
            byte[] secretBytes = cipher.doFinal(Base64.decode(x_sign));
            String secret = new String(secretBytes, StandardCharsets.UTF_8);

            if (data.length() % 4 != 0)
                data = data.replaceAll("=", "");
            var iv = data.substring(0, 16);

            byte[] origData = Base64.decode(data.substring(16));
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secret.getBytes(), "AES"),
                    new IvParameterSpec(iv.getBytes()));
            return new String(cipher.doFinal(origData));

        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException
                | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            DependencyResolver.getLog().debug("Exception Occurred : {}", e.getMessage());
            throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
        }
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[8];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private static void setPublicKey() {
        try {
            DependencyResolver.getLog().debug("Going to set Public Key");
            byte[] decodePublicKey = Base64.decode(tribePublicKeyRSA);
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodePublicKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            tribePublicKey = kf.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
        }
    }

    public static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
    }


    public static byte[] hex2binByte(String data) {
        byte[] bytes = new byte[data.length() / 2];

        int counter = 0;
        for (int i = 0; i < data.length(); i = i + 2) {

            String substring = data.substring(i, i + 2);
            bytes[counter++] = Integer.decode("0x" + substring).byteValue();
        }
        return bytes;
    }

    private static void setPrivateKey() {
        try {
            DependencyResolver.getLog().debug("Going to set Private Key");
            byte[] pkcs8EncodedBytes = Base64.decode(xploraPrivateKeyRSA);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            xploraPrivateKey = kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
        }
    }

    private static void setPrivateKeyField() {
        try {
            DependencyResolver.getLog().debug("Going to set Private Key");
            byte[] pkcs8EncodedBytes = Base64.decode(xploraPrivateKeyRSA);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            xploraPrivateKey = kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
        }
    }

    public static String decryptTribeFieldData(String data, String x_sign) {
        DependencyResolver.getLog().debug("Going to decrypt response");
        Cipher cipher;
        try {
            if (xploraPrivateKey == null) {
                try {
                    DependencyResolver.getLog().debug("Going to set Private Key");
                    byte[] pkcs8EncodedBytes = Base64.decode(xploraPrivateKeyRSA);
                    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
                    KeyFactory kf = KeyFactory.getInstance("RSA");
                    xploraPrivateKey = kf.generatePrivate(keySpec);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
                }
            }

            String secret = x_sign;

            if (data.length() % 4 != 0)
                data = data.replaceAll("=", "");
            var iv = data.substring(0, 16);

            byte[] origData = Base64.decode(data);
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secret.getBytes(), "AES"),
                    new IvParameterSpec(iv.getBytes()));
            return new String(cipher.doFinal(origData));

        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException
                | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            DependencyResolver.getLog().debug("Exception Occurred : {}", e.getMessage());
            throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
        }
    }


    public static String decrypt3DES(String src, String key) {
        try {
            byte[] hex2bin = hex2binByte(key);
            byte[] bytes = new byte[24];

            System.arraycopy(hex2bin, 0, bytes, 0, 16);
            System.arraycopy(hex2bin, 0, bytes, 16, 8);

            SecretKeySpec desKey = new SecretKeySpec(bytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

            byte[] iv = new byte[8];
            for (int i = 0; i < 8; i++) {
                iv[i] = 0;
            }
            cipher.init(Cipher.DECRYPT_MODE, desKey, new IvParameterSpec(iv));

            byte[] bytesrc = Base64.decode(src);
            byte[] retByte = cipher.doFinal(bytesrc);

            String out = new String(retByte, StandardCharsets.UTF_8);
            int packing = out.charAt(out.length() - 1);

            if (packing < 8) {
                for (int i = out.length() - 1; i >= out.length() - packing; i--) {
                    if (out.charAt(i) != packing) {
                        packing = 0;
                    }
                }
                out = out.substring(0, out.length() - packing);
            }
            return out;
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException
                | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            DependencyResolver.getLog().debug("Exception Occurred : {}", e.getMessage());
            throw new ApplicationException(XploraPayErrors.INTERNAL_SERVER_ERROR);
        }
    }

    public static byte[] hexDecode(String hex) {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        for (int i = 0; i < hex.length(); i += 2) {
            int b = Integer.parseInt(hex.substring(i, i + 2), 16);
            bas.write(b);
        }
        return bas.toByteArray();
    }

}

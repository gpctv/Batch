public class Decode {

  

    

     public static void main(String[] args) throws Exception

     {

       //當然啦，這當中我只有做解密。

         String decryptedString = Decode.decrypt("9Tkt7Wr8bhmBHMMw5Mf7yQTV6Sa1taV68sujQUwoHoA=") ;

            

         System.out.println(decryptedString);

        

     }

     /**

      * 寫了一個加密的方法

      * @param strToEncrypt

      * @return

      */

     public static String encrypt(String strToEncrypt)

     {

         try

         {

             byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

             IvParameterSpec ivspec = new IvParameterSpec(iv);

              //做一把鑰匙加密

             SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

             KeySpec spec = new PBEKeySpec("test".toCharArray(), "test2".getBytes(), 65536, 256);

             SecretKey tmp = factory.generateSecret(spec);

             SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            

             Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

             cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

             return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));

         }

         catch (Exception e)

         {

             System.out.println("Error while encrypting: " + e.toString());

         }

         return null;

     }

     /**

      * 寫了一個解密的方法

      * @param strToDecrypt

      * @return

      */

     public static String decrypt(String strToDecrypt) {

         try

         {

             byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

             IvParameterSpec ivspec = new IvParameterSpec(iv);

              //同一把鑰匙解密

             SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

             KeySpec spec = new PBEKeySpec("test".toCharArray(), "test2".getBytes(), 65536, 256);

             SecretKey tmp = factory.generateSecret(spec);

             SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            

             Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

             cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

             return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

         }

         catch (Exception e) {

             System.out.println("Error while decrypting: " + e.toString());

         }

         return null;

     }

}

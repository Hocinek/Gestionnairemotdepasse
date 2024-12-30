import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class FileEncryptionUtil {

    // Retourne une clé fixe (ou pré-générée)
    public static SecretKey getFixedKey() throws Exception {
        byte[] keyBytes = "1234567890abcdef".getBytes();  // Clé de 16 octets (128 bits)
        return new SecretKeySpec(keyBytes, "AES");
    }

    // Crypte les données avec la clé et l'IV spécifiés
    public static byte[] encrypt(String data, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);   // Assure-toi que l'IV est de 16 octets
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher.doFinal(data.getBytes());
    }

    // Décrypte les données avec la clé et l'IV spécifiés
    public static String decrypt(byte[] encryptedData, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);   // Assure-toi que l'IV est de 16 octets
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        return new String(decryptedData);
    }

    // Sauvegarde les données cryptées dans un fichier
    public static void saveEncryptedFile(String fileName, byte[] encryptedData, byte[] iv) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(fileName, true); // Append mode
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            // Sauvegarder l'IV et les données cryptées dans le fichier pour le décryptage
            out.writeObject(iv);      // Enregistrer l'IV
            out.writeObject(encryptedData); // Enregistrer les données cryptées
        }
    }

    // Lire le fichier crypté et retourner les données cryptées et IV
    public static byte[][] readEncryptedFile(String fileName) throws Exception {
        byte[] iv = null;
        byte[] encryptedData = null;

        // Lire le fichier crypté
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            iv = (byte[]) in.readObject();    // Lire l'IV
            encryptedData = (byte[]) in.readObject(); // Lire les données cryptées
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Erreur lors de la lecture du fichier crypté.", e);
        }

        return new byte[][]{iv, encryptedData}; // Retourne l'IV et les données cryptées
    }
}

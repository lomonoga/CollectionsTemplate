package ru.naumen.collection.task1;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class to instance a User
 */
public class User {
    private String username;
    private String email;
    private byte[] passwordHash;

    public User(String username, String email, String password) throws Exception {
        this.username = username;
        this.email = email;
        this.passwordHash = hashPassword(password);
    }

    /**
     * Hashing password
     *
     * @param password The password to be encoded
     * @return Hashed password
     */
    private static byte[] hashPassword(String password) throws Exception {
        byte[] salt = "solt_My_life_)))0".getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65551, 128);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashPassword = keyFactory.generateSecret(spec).getEncoded();
        return hashPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username)
                && Objects.equals(email, user.email)
                && Arrays.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        int result = 28 * Objects.hash(username, email);
        result = (31_181 * result + Arrays.hashCode(passwordHash) + 10_001) % 11_897;
        return result;
    }
}

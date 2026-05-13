import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public final class PasswordHasher {
    private static final SecureRandom RNG = new SecureRandom();

    private static final int SALT_BYTES = 16;
    private static final int ITERATIONS = 120_000;
    private static final int KEY_LENGTH_BITS = 256;

    private PasswordHasher() {}

    /**
     * Returns an encoded string with algorithm + parameters + salt + derived key.
     * Format: PBKDF2$sha256$<iterations>$<salt_b64>$<hash_b64>
     */
    public static String hash(char[] password) {
        byte[] salt = new byte[SALT_BYTES];
        RNG.nextBytes(salt);

        byte[] dk = pbkdf2Sha256(password, salt, ITERATIONS, KEY_LENGTH_BITS);
        return "PBKDF2$sha256$" + ITERATIONS + "$" + b64(salt) + "$" + b64(dk);
    }

    public static boolean verify(char[] password, String stored) {
        if (stored == null) return false;
        String[] parts = stored.split("\\$");
        if (parts.length != 5) return false;
        if (!"PBKDF2".equals(parts[0])) return false;
        if (!"sha256".equals(parts[1])) return false;

        int iterations;
        try {
            iterations = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        byte[] salt;
        byte[] expected;
        try {
            salt = Base64.getDecoder().decode(parts[3]);
            expected = Base64.getDecoder().decode(parts[4]);
        } catch (IllegalArgumentException e) {
            return false;
        }

        byte[] actual = pbkdf2Sha256(password, salt, iterations, expected.length * 8);
        return MessageDigest.isEqual(expected, actual);
    }

    private static byte[] pbkdf2Sha256(char[] password, byte[] salt, int iterations, int keyLengthBits) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLengthBits);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException("Password hashing failed.", e);
        }
    }

    private static String b64(byte[] bytes) {
        return Base64.getEncoder().withoutPadding().encodeToString(bytes);
    }
}

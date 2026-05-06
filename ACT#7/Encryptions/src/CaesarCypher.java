public class CaesarCypher {
    static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int index = 0; index < text.length(); index++) {
            char currentChar = text.charAt(index);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int encryptedOffset = Math.floorMod(currentChar - base + shift, 26);
                result.append((char) (encryptedOffset + base));
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
}

class VigenereCypher {
    static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        String normalizedKey = key.toUpperCase();
        int keyIndex = 0;

        for (int index = 0; index < text.length(); index++) {
            char currentChar = text.charAt(index);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int shift = normalizedKey.charAt(keyIndex % normalizedKey.length()) - 'A';
                int encryptedOffset = (currentChar - base + shift) % 26;
                result.append((char) (encryptedOffset + base));
                keyIndex++;
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
}
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String fullName = sc.nextLine();
        System.out.println("Enter a number for shifting: ");
        int cKey = sc.nextInt();
        System.out.println("Enter a KEY for VigenereCypher: ");
        String vKey = sc.next();

        System.out.println("Original Name: " + fullName);
        System.out.println("Caesar Cipher: " + CaesarCypher.encrypt(fullName, cKey));
        System.out.println("Vigenere Cipher: " + VigenereCypher.encrypt(fullName, vKey));

        sc.close();
    }
}

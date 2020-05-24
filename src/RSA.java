import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSA {
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();

    public static void main(String[] args) throws Exception {
        String message = "COMP4450 Assignment 4 " +
                "Question 1 (10 marks): Please describe your research question. What is it,  " +
                "you are trying to do? Please give enough details so that any of the tutors can  " +
                "understand it. (Max 250 words) " +
                "Asymmetric cryptography background: Asymmetric cryptography consists by  " +
                "a public key (for encryption) and a private key (for decryption). The main idea  " +
                "of such cryptographic algorithm is based on mathematical problems to  " +
                "generate one-way functions. In such algorithm, everyone knows the receiver’s  " +
                "public key can use to encrypt a message and send to receiver; and the  " +
                "message can only be decrypted by receiver’s private key. " +
                "Digital Certificate Background: Digital signature is a message digest algorithm  " +
                "with a key. This key includes a public key and a private key. It is used to verify  " +
                "data integrity, authenticate data sources, and resist denial. It follows the OSI  " +
                "reference model, private key signature, and public key verification. The  " +
                "common digital signature algorithms are mainly RSA, DSA, ECDSA. " +
                "Our research problem is about finding a new kind of asymmetric cryptography  " +
                "algorithm to reduce the amount of calculation in the process of encryption,  " +
                "decryption and digital signature, make the calculation faster and information  " +
                "transmission more secure. The main idea of this algorithm comes from RSA,  " +
                "which based on the practical difficulty of factoring the product of two large  " +
                "prime number. With larger prime numbers, the complexity of the algorithm  " +
                "increases exponentially. This kind of property is similar to one-way function,  " +
                "which is easy to compute on every input, but extremely hard to get solution  " +
                "reversely. We called this novel asymmetric algorithm is HCA (half cut algorithm).  " +
                "Question 2 (10 marks): Please motivate your research question: Why did  " +
                "you think this question is interesting and important? (max 500 words) " +
                "In the present, the current mainstream of computer science is about AI, data  " +
                "mining and so on. But we believe that encryption algorithms are the most  " +
                "important part of network security but also the most easily neglected part. For  " +
                "netizens, they lack the corresponding security awareness, ‘encryption  " +
                "algorithms’, ‘RSA’, ‘MD5’ these professional words are difficult for them to  " +
                "understand. For professional technicians, when they found the current  " +
                "encryption algorithm they used exist vulnerabilities. The first thing they will do  " +
                "is to calculate the cost of upgrading the safer encryption algorithm and the  " +
                "incomes that the upgrade can bring. However, if the cost of upgrading is much  " +
                "more that income, even if users’ private has potential risks of leakage,  " +
                "technicians will remain the same encryption algorithm. (Ref: CISSP) We think  " +
                "the rapid development of AI, data mining just like a person owns a huge  " +
                "amount of property in house but did not do any protective measures.  " +
                "Therefore, in a very short period, the demand for professional technicians in  " +
                "the security field will increase rapidly. This research project is a practice and  " +
                "prepare for security field. That’s the reason for why we choose topic about  " +
                "encryption algorithm. " +
                "With the rapid development of technology, the emergence of supercomputers  " +
                "and the increasing performance of personal computers, algorithms that used  " +
                "to require brute-force computer cracking for more than 50 years may now only  " +
                "take a few hours or even a few minutes. The algorithm proposed as early as  " +
                "20 years ago is not enough to meet the current security requirements of  " +
                "encryption algorithms. " +
                "In technical perspective: Many encryption algorithms like MD4, MD5, SHA1  " +
                "considered harmful today. In 2005, Xiaoyun Wang and Hongbo Yu had  " +
                "successfully break MD5 (Wang & Yun, 2005). And MD5 has already been  " +
                "cracked within 5 hours using a PC with Pentium 4 1.70GHz CPU (Jie Liang,  " +
                "2007). Through MD5 hash collision can generate a fake digital certificate that  " +
                "is the same as the real digital certificate. Therefore, hackers use this method  " +
                "to generate phishing websites to steal users’ information. In 1994 Peter Shor  " +
                "proved that Quantum computer can factorization done in polynomial time.  " +
                "With the development of Quantum computer technology, RSA will gradually  " +
                "become insecure. " +
                "For personal reason: Lack of security will directly make users’ personal  " +
                "information loss, for example: bank account, private medical record, address  " +
                "and so on. " +
                "For business: Security issues for a company means that the company’s  " +
                "reputation, credibility, and pecuniary will suffer significant loss. " +
                "Question 3 (20 marks): What is the current state of the art regarding your  " +
                "research question. Please give an overall summary (max 750 words, 10  " +
                "marks) and describe the 2-3 (depending on the size of the team) most  " +
                "relevant state of the art papers in detail (max 500 words per paper, 10 marks).  " +
                "Please specify who summarised which paper, as the 10 marks per paper are  " +
                "for the person summarising it. " +
                "Quantum cryptography " +
                "ECC " +
                "Question 4 (20 marks): Please report in detail what you have done to solve  " +
                "your research question in chronological order. Please describe what worked,  " +
                "but also what didn’t work, as well as why you decided to do the steps you  " +
                "described. (Max 2000 words) " +
                "Question 5 (10 marks): How did you distribute the work among your team?  " +
                "Who did what? Why did you distribute the work in that way? (Max 500 words,  " +
                "ideally tabular form) " +
                "Question 6 (10 marks): Please describe the outcome of your research?  " +
                "What positive or negative results have you achieved? Please note that we do " +
                " not expect positive outcomes. (Max 500 words) " +
                "Question 7 (10 marks): What would be the next steps, either to answer your  " +
                "research question, or if answered, to extend it? (Max 500 words) " +
                "Question 8 (10 marks): What have you learned from this research project?  " +
                "What would you do differently in hindsight? (Max 500 words) ";
        String[] messages = message.split(" ");
        long start = System.currentTimeMillis();
        genKeyPair();
        System.out.println("Public key:" + keyMap.get(0));
        System.out.println("Private key:" + keyMap.get(1));
        for(String s : messages){
            String messageEn = encrypt(s, keyMap.get(0));
            System.out.println(s + "\tenc:" + messageEn);
            String messageDe = decrypt(messageEn, keyMap.get(1));
            System.out.println("dec:" + messageDe);
            System.out.println(s.equals(messageDe)?"The result is correct":"The result not correct");
        }

        long end = System.currentTimeMillis();
        System.out.println("total use time: " + (end - start)+"ms.");

    }

    public static void genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(512, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        keyMap.put(0, publicKeyString);
        keyMap.put(1, privateKeyString);
    }

    public static String encrypt(String str, String publicKey) throws Exception {
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.US_ASCII)));
        return outStr;
    }

    public static String decrypt(String str, String privateKey) throws Exception {
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.US_ASCII));
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;

    }
}

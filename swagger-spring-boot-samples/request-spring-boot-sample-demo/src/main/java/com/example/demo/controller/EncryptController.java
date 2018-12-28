package com.example.demo.controller;

import com.battcn.boot.request.configuration.encrypt.annotation.Decrypt;
import com.battcn.boot.request.configuration.encrypt.annotation.Encrypt;
import com.battcn.boot.request.utils.encrypt.CryptoType;
import com.battcn.boot.request.utils.encrypt.CryptoUtils;
import com.example.demo.entity.TestBody;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

import static com.battcn.boot.request.utils.encrypt.CryptoUtils.decryptToString;
import static com.battcn.boot.request.utils.encrypt.CryptoUtils.encryptToString;

/**
 * @author Levin
 * @since 2018/12/27 0027
 */

@RestController
public class EncryptController {

    private static final Logger log = LoggerFactory.getLogger(EncryptController.class);

    @Encrypt(type = CryptoType.AES, key = "12345678", description = "采用 AES 进行解密处理")
    @Decrypt(type = CryptoType.AES, key = "12345678", description = "采用 AES 进行解密处理")
    @PostMapping("/aes")
    public String aes(@RequestBody String content) {
        log.info("解密消息" + content);
        return content;
    }

    @Encrypt
    @Decrypt
    @PostMapping("/aes1")
    public TestBody aes1(@RequestBody TestBody content) {
        log.info("解密消息" + content.toString());
        TestBody testBody = new TestBody();
        testBody.setId(1L);
        testBody.setName("哈哈");
        testBody.setTime(LocalDateTime.now());
        return testBody;
    }

    @Encrypt(type = CryptoType.DES, key = "12345678", description = "采用 DES 进行解密处理")
    @Decrypt(type = CryptoType.DES, key = "12345678", description = "采用 DES 进行解密处理")
    @PostMapping("/des")
    public String des(@RequestBody String content) {
        log.info("解密消息" + content);
        return content;
    }

    @Encrypt(type = CryptoType.RSA, key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAviSuCu4Yg/WAyjp06qiaE/ioI2M/ACT9UTUVxWtM7IZlXMQZPjLn0H1x0zmJ/VLIhnBliyb06QLvtrrBFRt4jnOJR5LjoTg/g8XYdVXN6a+XFjqFvOUPgzZ7OdywOoXxiO+M7WrvT0XgqyBqCnDADpY1eucDqfIDYYOBHKbtMkh0N4ZVBcfULb1Sm+Q7ed+jUa8eXPQPhMrWvhQkIeZJh+hCIrNjXUxyfZPh1tSvqoJYArbyHZs8LnbUtjIQCx9OlR9+xJTx3L9h89I4D+hqA4CZqxUzfibsu5XgYKnoSri2OCR2FefSfYlCd8Fysp0wET/r1L141qnhoMQtrUs8jwIDAQAB", description = "采用 DES 进行解密处理")
    @Decrypt(type = CryptoType.RSA, key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC+JK4K7hiD9YDKOnTqqJoT+KgjYz8AJP1RNRXFa0zshmVcxBk+MufQfXHTOYn9UsiGcGWLJvTpAu+2usEVG3iOc4lHkuOhOD+Dxdh1Vc3pr5cWOoW85Q+DNns53LA6hfGI74ztau9PReCrIGoKcMAOljV65wOp8gNhg4Ecpu0ySHQ3hlUFx9QtvVKb5Dt536NRrx5c9A+Eyta+FCQh5kmH6EIis2NdTHJ9k+HW1K+qglgCtvIdmzwudtS2MhALH06VH37ElPHcv2Hz0jgP6GoDgJmrFTN+Juy7leBgqehKuLY4JHYV59J9iUJ3wXKynTARP+vUvXjWqeGgxC2tSzyPAgMBAAECggEAMhFkhtpFOFIoFJgp+zRkRgf+9jqG91nGHmEVF4P2oH2PKUs1vmwXII43r8AB9uOai9QC2Q5sBQNR7dLlTtKJ/zCrIF6sc+JkzyUEp3jtnLAw35iPaLsER6/L6OOUwARPIpi5ijbTRxOGYmlJovAnkm+5K2CzVUe13jKLh+joool/ReZk0Rsr4tVLSLmvzDA/sRwYun0x0+jl5EZSQfwsVyN9bD5rY/In/EuvH9yj5R4lPe+mimF4Os6IgTsP5LzqDTAiFx5NNioFRJ2SkcTmM0CZQeMIBuvvF2HCtJlDEfCytD7wYup3GBvar2ccOe9T3YhJdsj5bfAJHVJtamxQwQKBgQDnYReMMzqAh2HOFL8QymzOjImsrOz6NCZatq38TU1hSe9PK+C0sFGhkd788y4AuURS1Btu4i7F+hOYcj1z3L+NSPGE3yLHVjakMrrNbA9rwG/t7oU0cG7d0WWM9bcTQiCSNcUyt69BGH3dZdqee1tITzqghE7+gh9RYiVcI6/8LwKBgQDSYEuWFLMUsR/s7unSHCucuEXjwbYrvknv8Y81sjvrWktNXrJoYlbGy/7HYA6lxzchtSxhPuUSjopwQ5scgMhqf8Gxz7jsDN9ak2dErF7cWRFYfh6aKhkbEw9oG01jIX15MK0TbMafoJslDhPQF1cP9i0+ZGg+gPbASdeUVRTNoQKBgQCOjwDOLgYeiMtXCOtL8hymCmsNDCKaaiUzgRijuhEyHzamJhe13Gj/TnwAh+hRI9UX333jjNJawqDuLXz1dQ5Eg6vjPQQVo2XZNzRnOuwpbJDKHUrPK3Lzkn+qIP6ii/y7eQu+GvSM/AUYsxfGy6RLYh1yJvLw1sVrBDiWk5prmwKBgFvgrmI3XBa3XKgPl5KptupVGEDmAveLvaLLLq5WzxB0eNqrduNbv2ZHBVhxvTPtk0hnZaB65XR7SD7LZ9zE6cKJVUCg5bRB0vIt2jYFydAWHhs1yYuuwxQt+NaQxfV7VN8uwQfww7ZHYDqIsWJ6Lw3Lh+rt0xEpJZrJJRulJNbBAoGBAK1OEnfBpSB99N8gdhp+ZGLsDfwFCQ2Cd4Jpsd4hxdwbXevNuA1OiE20sHPuKqEqfOKocgTMobCwbSfnymatRydVoeUumkEc4Ja+XDgH+P1eXQLdIuRCwh0AXl+vkuOCBDMw367Zp/j6vwPlNKh9ZmOBPwhV0Syv2Z8uGkTZ6g+f", description = "采用 DES 进行解密处理")
    @PostMapping("/rsa")
    public String rsa(@RequestBody String content) {
        log.info("解密消息" + content);
        return content;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("========================================AES========================================");
        String key = "12345678";
        String content = "哈哈";
        Charset charset = Charset.forName("UTF-8");
        final String encrypt = CryptoUtils.encryptToString(CryptoType.AES, key, content, charset);
        System.out.println("加密" + encrypt);
        System.out.println("解密" + CryptoUtils.decryptToString(CryptoType.AES, key, encrypt, charset));
        System.out.println("================================================================================");
        final InputStream inputStream = CryptoUtils.encrypt(CryptoType.AES, key, IOUtils.toInputStream(content, charset));
        final InputStream stream = CryptoUtils.decrypt(CryptoType.AES, key, inputStream);
        System.out.println(IOUtils.toString(stream, charset));

        System.out.println("========================================DES========================================");
        final String desEncrypt = encryptToString(CryptoType.DES, key, content, charset);
        System.out.println("加密" + desEncrypt);
        System.out.println("解密" + decryptToString(CryptoType.DES, key, desEncrypt, charset));

        System.out.println("========================================RSA========================================");
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAviSuCu4Yg/WAyjp06qiaE/ioI2M/ACT9UTUVxWtM7IZlXMQZPjLn0H1x0zmJ/VLIhnBliyb06QLvtrrBFRt4jnOJR5LjoTg/g8XYdVXN6a+XFjqFvOUPgzZ7OdywOoXxiO+M7WrvT0XgqyBqCnDADpY1eucDqfIDYYOBHKbtMkh0N4ZVBcfULb1Sm+Q7ed+jUa8eXPQPhMrWvhQkIeZJh+hCIrNjXUxyfZPh1tSvqoJYArbyHZs8LnbUtjIQCx9OlR9+xJTx3L9h89I4D+hqA4CZqxUzfibsu5XgYKnoSri2OCR2FefSfYlCd8Fysp0wET/r1L141qnhoMQtrUs8jwIDAQAB";
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC+JK4K7hiD9YDKOnTqqJoT+KgjYz8AJP1RNRXFa0zshmVcxBk+MufQfXHTOYn9UsiGcGWLJvTpAu+2usEVG3iOc4lHkuOhOD+Dxdh1Vc3pr5cWOoW85Q+DNns53LA6hfGI74ztau9PReCrIGoKcMAOljV65wOp8gNhg4Ecpu0ySHQ3hlUFx9QtvVKb5Dt536NRrx5c9A+Eyta+FCQh5kmH6EIis2NdTHJ9k+HW1K+qglgCtvIdmzwudtS2MhALH06VH37ElPHcv2Hz0jgP6GoDgJmrFTN+Juy7leBgqehKuLY4JHYV59J9iUJ3wXKynTARP+vUvXjWqeGgxC2tSzyPAgMBAAECggEAMhFkhtpFOFIoFJgp+zRkRgf+9jqG91nGHmEVF4P2oH2PKUs1vmwXII43r8AB9uOai9QC2Q5sBQNR7dLlTtKJ/zCrIF6sc+JkzyUEp3jtnLAw35iPaLsER6/L6OOUwARPIpi5ijbTRxOGYmlJovAnkm+5K2CzVUe13jKLh+joool/ReZk0Rsr4tVLSLmvzDA/sRwYun0x0+jl5EZSQfwsVyN9bD5rY/In/EuvH9yj5R4lPe+mimF4Os6IgTsP5LzqDTAiFx5NNioFRJ2SkcTmM0CZQeMIBuvvF2HCtJlDEfCytD7wYup3GBvar2ccOe9T3YhJdsj5bfAJHVJtamxQwQKBgQDnYReMMzqAh2HOFL8QymzOjImsrOz6NCZatq38TU1hSe9PK+C0sFGhkd788y4AuURS1Btu4i7F+hOYcj1z3L+NSPGE3yLHVjakMrrNbA9rwG/t7oU0cG7d0WWM9bcTQiCSNcUyt69BGH3dZdqee1tITzqghE7+gh9RYiVcI6/8LwKBgQDSYEuWFLMUsR/s7unSHCucuEXjwbYrvknv8Y81sjvrWktNXrJoYlbGy/7HYA6lxzchtSxhPuUSjopwQ5scgMhqf8Gxz7jsDN9ak2dErF7cWRFYfh6aKhkbEw9oG01jIX15MK0TbMafoJslDhPQF1cP9i0+ZGg+gPbASdeUVRTNoQKBgQCOjwDOLgYeiMtXCOtL8hymCmsNDCKaaiUzgRijuhEyHzamJhe13Gj/TnwAh+hRI9UX333jjNJawqDuLXz1dQ5Eg6vjPQQVo2XZNzRnOuwpbJDKHUrPK3Lzkn+qIP6ii/y7eQu+GvSM/AUYsxfGy6RLYh1yJvLw1sVrBDiWk5prmwKBgFvgrmI3XBa3XKgPl5KptupVGEDmAveLvaLLLq5WzxB0eNqrduNbv2ZHBVhxvTPtk0hnZaB65XR7SD7LZ9zE6cKJVUCg5bRB0vIt2jYFydAWHhs1yYuuwxQt+NaQxfV7VN8uwQfww7ZHYDqIsWJ6Lw3Lh+rt0xEpJZrJJRulJNbBAoGBAK1OEnfBpSB99N8gdhp+ZGLsDfwFCQ2Cd4Jpsd4hxdwbXevNuA1OiE20sHPuKqEqfOKocgTMobCwbSfnymatRydVoeUumkEc4Ja+XDgH+P1eXQLdIuRCwh0AXl+vkuOCBDMw367Zp/j6vwPlNKh9ZmOBPwhV0Syv2Z8uGkTZ6g+f";
        String myMessage = "Secret Message";
        final String string = CryptoUtils.encryptToString(CryptoType.RSA, publicKey, myMessage, charset);
        System.out.println(string);
        System.out.println("foo = " + decryptToString(CryptoType.RSA, privateKey, string, charset));
    }
}

package rsa;


import java.util.Map;
import static rsa.CreateSecretKey.*;

/**
 * @description: 非对称加密测试
 * @author: xutao
 * @create: 2019-06-10 15:19
 **/
public class Test {

    public static void main(String[] args) throws Exception {
        //生成秘钥
        Map<String, Object> keyMap=initKey();
        String publicKey = getPublicKeyStr(keyMap);
        String privateKey= getPrivateKeyStr(keyMap);
        //读取文件内容
        byte[] content=FileUtils.getContent("C:\\Users\\51196\\Downloads\\rsa\\转正相关附件.docx");
        //公钥加密内容
        byte[] encryptContent=encrypt(content,publicKey);
        //公钥加密文件名
        String filename="转正相关附件.docx";
        String encryptName= encryptBASE64(encrypt(filename.getBytes("UTF-8"),publicKey));
        System.out.println("加密后文件名：\r\n"+encryptName);
        //生成加密文件
        FileUtils.saveFile("C:\\Users\\51196\\Downloads\\rsa\\转正相关附件2.docx",encryptContent);
        //私钥解密文件
        //读取加密文件内容
        byte[] encryptByte=FileUtils.getContent("C:\\Users\\51196\\Downloads\\rsa\\转正相关附件2.docx");
        byte[] plainText= decrypt(encryptByte,privateKey);
        //写入新文件
        FileUtils.saveFile("C:\\Users\\51196\\Downloads\\rsa\\转正相关附件3.docx",plainText);
    }
}

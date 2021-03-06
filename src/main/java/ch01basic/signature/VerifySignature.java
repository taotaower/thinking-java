package ch01basic.signature;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-7-23
 * Time: 上午8:58
 * To change this template use File | Settings | File Templates
 */

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

import static ch01basic.signature.KeyUtil.hexStrToBytes;

public class VerifySignature {

    public void run() {
        try {
            String pubkeyvalue = "30819f300d06092a864886f70d010101050003818d003081890281810093dbe94b47911b429ae9cbb7181651492a29532a74caef2f15385334d168d75d236b4752ccf24d29ed7f3fa5d52dc18d106f8f1d7fcafac546f9de5bb96633dc3e7b92cf527221aa736b97669e8a8389587cfe05aeaaad15f3f2409fea47ec00c1b81f6036c4c71bdba66230f67114a1e29ee29c5bb6d3f9b5d3171ad0b11fc90203010001";//这是GenerateKeyPair输出的公钥编码
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(hexStrToBytes(pubkeyvalue));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);

            String info = "orderId=10dkfadsfksdkssdkd&amount=80&orderTime=20060509";
            byte[] signed = hexStrToBytes("3e5739184a60196ae0a74730489ef511c86f6556ef1a25dbddca96c860d27074f8942bf5e551ba75c97b4f0286359a3133cadaef746c33b5b97472d94373a789116deb6aeff2d9f428b5264f0e83c4a8c64ec750c086143edc9f9c4ac6eef7b338a46fa07801b4bcf27cdc0cab32bb11fca5f993c644dd071de98f8072590937");//这是SignatureData输出的数字签名
            Signature signetcheck = Signature.getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(info.getBytes());
            if (signetcheck.verify(signed)) {
                System.out.println("info=" + info);
                System.out.println("签名正常");
            } else System.out.println("非签名正常");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new VerifySignature().run();
    }
}

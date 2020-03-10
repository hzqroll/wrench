package com.roll.wrench.web.bloom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

/**
 * @author zongqiang.hao
 * created on 2020-03-09 21:12.
 */
@RunWith(JUnit4.class)
public class MessageDigestTest {
    @Test
    public void simpleTest() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        String value = "肖敏";
        byte[] bytes = value.getBytes(Charset.forName("utf-8"));
        messageDigest.update(bytes);
        byte[] resultBytes = messageDigest.digest(bytes);
        BitSet bitSet = BitSet.valueOf(resultBytes);

        StringBuffer buffer = new StringBuffer();
        int i;
        for (int offset = 0; offset < resultBytes.length; offset++) {
            i = resultBytes[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buffer.append("0");
            }
            buffer.append(Integer.toHexString(i));
        }
        System.out.println(bitSet.toString());
        System.out.println(buffer.toString());
        System.out.println(Integer.numberOfLeadingZeros(10));
    }
}

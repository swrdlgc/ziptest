package org.example;

import org.junit.Test;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * @author swrd
 * @version 1.0
 * @date 2023/5/9
 */
public class WinZipUtilsTest {
    String password = "password";

    @Test
    public void test0() throws IOException {
        String output = "output.winzip0";

        CommonZipUtils.doZip("doc", output + "/all.tmp.zip");
        WinZipUtils.encrypt(output + "/all.tmp.zip", output + "/all.zip", password);
        Zip4jUtils.doUnzip(output + "/all.zip", output, password);
    }

    @Test
    public void test1() throws IOException, DataFormatException {
        String output = "output.winzip1";
        WinZipUtils.doZip("doc", output + "/all.zip", password);
        WinZipUtils.doUnzip(output + "/all.zip", output, password);
    }
}

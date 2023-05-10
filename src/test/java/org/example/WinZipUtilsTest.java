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
    @Test
    public void test0() throws IOException, DataFormatException {
        String password = "password";
        WinZipUtils.doZip("doc", "output4/all.zip", password);
        WinZipUtils.doUnzip("output4/all.zip", "output4", password);
        Zip4jUtils.doUnzip("output4/all.zip", "output4", password);
    }
}

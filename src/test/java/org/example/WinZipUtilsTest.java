package org.example;

import org.junit.Test;

import java.io.IOException;

/**
 * @author swrd
 * @version 1.0
 * @date 2023/5/9
 */
public class WinZipUtilsTest {
    @Test
    public void test0() throws IOException {
        String password = "password";
        WinZipUtils.doZip("doc", "output4/all.zip", password);
        WinZipUtils.doUnzip("output4/all.zip", "output4", password);
    }
}

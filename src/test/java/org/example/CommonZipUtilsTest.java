package org.example;

import org.junit.Test;

import java.io.IOException;

/**
 * @author swrd
 * @version 1.0
 * @date 2023/5/9
 */
public class CommonZipUtilsTest {
    @Test
    public void test0() throws IOException {
        String output = "output.comzip0";
        CommonZipUtils.doZip("doc/test1.txt", output + "/test1.zip");
        CommonZipUtils.doUnzip(output + "/test1.zip", output);
    }

    @Test
    public void test1() throws IOException {
        String output = "output.comzip1";
        CommonZipUtils.doZip("doc", output + "/all.zip");
        CommonZipUtils.doUnzip(output + "/all.zip", output);
    }
}

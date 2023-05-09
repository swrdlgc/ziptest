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
        CommonZipUtils.doZip("doc/test1.txt", "output1/test1.zip");
        CommonZipUtils.doUnzip("output1/test1.zip", "output1");
    }

    @Test
    public void test1() throws IOException {
        CommonZipUtils.doZip("doc", "output2/all.zip");
        CommonZipUtils.doUnzip("output2/all.zip", "output2");
    }
}

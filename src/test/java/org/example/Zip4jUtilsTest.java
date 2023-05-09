package org.example;

import org.junit.Test;

import java.io.IOException;

/**
 * @author swrd
 * @version 1.0
 * @date 2023/5/9
 */
public class Zip4jUtilsTest {
    @Test
    public void test0() throws IOException {
        String password = "password";
        Zip4jUtils.doZip("doc", "output3/all.zip", password);
        Zip4jUtils.doUnzip("output3/all.zip", "output3", password);
        //CommonZipUtils.doUnzip("output3/all.zip", "output3"); // Exception
    }
}

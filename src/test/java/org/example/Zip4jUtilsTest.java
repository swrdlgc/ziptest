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
        String output = "output.zip4j0";

        Zip4jUtils.doZip("doc", output + "/all.zip");
        Zip4jUtils.doUnzip(output + "/all.zip", output);
        CommonZipUtils.doUnzip(output + "/all.zip", output);
    }

    @Test
    public void test1() throws IOException {
        String password = "password";
        String output = "output.zip4j1";

        Zip4jUtils.doZip("doc", output + "/all.zip", password);
        Zip4jUtils.doUnzip(output + "/all.zip", output, password);
        //CommonZipUtils.doUnzip(output + "/all.zip", output); // Exception
    }
}

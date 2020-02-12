package example;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringOperationsTestHomework1 {

    @Test
    public void testContainsC_Positive() {
        StringOperations stringOperations = new StringOperations();
        boolean actualResult = stringOperations.containsC("aabbccddeeff", "bb");
        Assert.assertTrue(actualResult);
    }

    @Test
    public void testContainsC_Negative() {
        StringOperations stringOperations = new StringOperations();
        boolean actualResult = stringOperations.containsC("aabbccddeeff", "zz");
        Assert.assertFalse(actualResult);
    }

    @Test
    public void testContentEqualsC_Positive() {
        StringOperations stringOperations = new StringOperations();
        boolean actualResult = stringOperations.contentEqualsC("aabbccddeeff", "aabbccddeeff");
        Assert.assertTrue(actualResult);
    }

    @Test
    public void testContentEqualsC_Negative() {
        StringOperations stringOperations = new StringOperations();
        boolean actualResult = stringOperations.contentEqualsC("aabbccddeeff", "aabbccddeef");
        Assert.assertFalse(actualResult);
    }

    @Test
    public void testIsEmptyC_Positive() {
        StringOperations stringOperations = new StringOperations();
        boolean actualResult = stringOperations.isEmptyC("");
        Assert.assertTrue(actualResult);
    }

    @Test
    public void testIsEmptyC_Negative() {
        StringOperations stringOperations = new StringOperations();
        boolean actualResult = stringOperations.isEmptyC(" ");
        Assert.assertFalse(actualResult);
    }

    @Test
    public void testLastIndexOfC_Positive() {
        StringOperations stringOperations = new StringOperations();
        int actualResult = stringOperations.lastIndexOfC("abcdefg", "e");
        int expectedResult = 4;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(enabled = false)
    public void testLastIndexOfC_Negative() {
        StringOperations stringOperations = new StringOperations();
        int actualResult = stringOperations.lastIndexOfC("abcdefg", "cg");
        int expectedResult = 5;
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void testReplaceC_Positive() throws InputValueIsNullException {
        StringOperations stringOperations = new StringOperations();
        String actualResult = stringOperations.replaceC("Major Briggs was a United States Air Force officer and the overseer of Listening Post Alpha, a top-secret installation in northeastern Washington devoted to studying paranormal activity both local and extraterrestrial", "Major Briggs", "THAT'S CLASSIFIED!");
        String expectedResult = "THAT'S CLASSIFIED! was a United States Air Force officer and the overseer of Listening Post Alpha, a top-secret installation in northeastern Washington devoted to studying paranormal activity both local and extraterrestrial";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testReplaceC_Negative() throws InputValueIsNullException {
        StringOperations stringOperations = new StringOperations();
        String actualResult = stringOperations.replaceC("aabbcc", "bb", "X X");
        String expectedResult = "aaXXcc";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @DataProvider
    public Object[][] getNullStrings() {
        return new Object[][]{
                {"a", null, "c"},
                {null, "b", "c"},
                {"a", "b", null}
        };
    }

    @Test(dataProvider = "getNullStrings")
    public void testReplaceC_Exception(String str1,String str2, String str3) throws InputValueIsNullException {
        StringOperations stringOperations = new StringOperations();
        try {
            stringOperations.replaceC(str1,str2,str3);
        } catch (Exception e) {
            Assert.assertEquals("Вы не ввели значение!", e.getMessage());
        }
    }
}


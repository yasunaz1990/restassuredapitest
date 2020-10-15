package utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Steps {

    //
    private static ExtentTest eachTestCaseSection;


    /**
     *  This method assigns ExtentTest object created on TestExecutionListener class
     *  to the global eachTestCaseSection variables, so that we can use it
     *  in subsequent methods for report editing.
     *
     * @param tcSection  ExtentTest object
     */
    public static void init(ExtentTest tcSection) {
        eachTestCaseSection = tcSection;
    }

    /**
     * Use this method to log a test step in the report for
     * each test case section.
     *
     * @param stepInfo  String message that represents test step
     */
    public static void log(String stepInfo) {
        eachTestCaseSection.info(stepInfo);
    }


    /**
     * Use this method to log JSON format to the test case section as a step
     *
     * @param content String content that represent JSON data
     */
    public static void logJson(String content){
        eachTestCaseSection.info(MarkupHelper.createCodeBlock(content, CodeLanguage.JSON));
    }


    /**
     * Use this method to attache screenshot for each test case section as a step
     *
     * @param message String that represent image title
     */
    public static void imgLog(String message){
        // To Do later
    }
}

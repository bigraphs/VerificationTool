package it.uniud.mads.jlibbig.proverif.networkservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniud.mads.jlibbig.proverif.exceptions.GeneralException;
import it.uniud.mads.jlibbig.proverif.exceptions.InternalServerError500Exception;
import it.uniud.mads.jlibbig.proverif.exceptions.NotFound404Exception;

@DisplayName("When running proverif requests")
public class ProVerifRequestsTest {

    @Nested
    @DisplayName("proverifRest method")
    public class ProverifRest {

        @BeforeEach
        void beforeEach() {
        }

        @AfterEach
        void afterEach() {
            try {
                HttpRequests.delete("models/Test");
            } catch (NotFound404Exception | InternalServerError500Exception | GeneralException e) {
            }
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Get Models request")
        void testProverifGetModels() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Models\": [] }";
            // Actual
            String actualResult = ProVerifRequests.getModels().replaceAll("\n", " ").replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Get Model request")
        void testProverifGetModel() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Model\": \"Test\", \"Input\": [], \"OutputText\": [], \"OutputHtml\": [ \"cssproverif.css\" ] }";
            // Actual
            ProVerifRequests.postModel("Test");
            String actualResult = ProVerifRequests.getModel("Test").replaceAll("\n", " ").replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Post Model request")
        void testProverifPostModel() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"Model Test has been created\" }";
            // Actual
            String actualResult = ProVerifRequests.postModel("Test").replaceAll("\n", " ").replaceAll(" +", " ")
                    .strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Delete Model request")
        void testProverifDeleteModel() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"Model Test has been deleted\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            String actualResult = ProVerifRequests.deleteModel("Test").replaceAll("\n", " ").replaceAll(" +", " ")
                    .strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Get Input File request")
        void testProverifGetInputFile() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Model\": \"Test\", \"File\": \"FileTest\", \"Result\": \"Content of test file\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "Content of test file");
            String actualResult = ProVerifRequests.getInputFile("Test", "FileTest").replaceAll("\n", " ")
                    .replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Post Input File request")
        void testProverifPostInputFile() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"File FileTest of the model Test has been created\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            String actualResult = ProVerifRequests.postInputFile("Test", "FileTest", "Content of test file")
                    .replaceAll("\n", " ").replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Put Input File request")
        void testProverifPutInputFile() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"File FileTest of the model Test has been modified\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "Content of test file");
            String actualResult = ProVerifRequests.putInputFile("Test", "FileTest", "Content of test file")
                    .replaceAll("\n", " ").replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Delete Input File request")
        void testProverifDeleteInputFile() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"File FileTest of the model Test has been deleted\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "Content of test file");
            String actualResult = ProVerifRequests.deleteInputFile("Test", "FileTest").replaceAll("\n", " ")
                    .replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Post Verify Text request")
        void testProverifPostVerifyText() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"The script finish\", \"Log\": \"b'Proverif executed on file /models/Test/input/text/FileTest.pv, result can be found in /models/Test/output/text/FileTest.txt\\\\n'\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "");
            String actualResult = ProVerifRequests.postVerifyText("Test", "FileTest").replaceAll("\n", " ")
                    .replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Post Verify Html request")
        void testProverifPostVerifyHtml() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"The script finish\", \"Log\": \"b'File \\\"/models/Test/input/text/FileTest.pv\\\", line 1, character 1:\\\\nError: Syntax error.\\\\nProverif executed on file /models/Test/input/text/FileTest.pv, result can be found in /models/Test/output/html/\\\\n'\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "");
            String actualResult = ProVerifRequests.postVerifyHtml("Test", "FileTest").replaceAll("\n", " ")
                    .replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Put Verify Text request")
        void testProverifPutVerifyText() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"The script finish\", \"Log\": \"b'Proverif executed on file /models/Test/input/text/FileTest.pv, result can be found in /models/Test/output/text/FileTest.txt\\\\n'\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "");
            ProVerifRequests.postVerifyText("Test", "FileTest");
            String actualResult = ProVerifRequests.putVerifyText("Test", "FileTest").replaceAll("\n", " ")
                    .replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Put Verify Html request")
        void testProverifPutVerifyHtml() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"The script finish\", \"Log\": \"b'File \\\"/models/Test/input/text/FileTest.pv\\\", line 1, character 1:\\\\nError: Syntax error.\\\\nProverif executed on file /models/Test/input/text/FileTest.pv, result can be found in /models/Test/output/html/\\\\n'\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "");
            ProVerifRequests.postVerifyHtml("Test", "FileTest");
            String actualResult = ProVerifRequests.putVerifyHtml("Test", "FileTest").replaceAll("\n", " ")
                    .replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Get Output Text request")
        void testProverifGetOutputText() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Model\": \"Test\", \"File\": \"FileTest\", \"Result\": \"File \\\"/models/Test/input/text/FileTest.pv\\\", line 1, character 1:\\nError: Syntax error.\\n\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "");
            ProVerifRequests.postVerifyText("Test", "FileTest");
            String actualResult = ProVerifRequests.getOutputText("Test", "FileTest").replaceAll("\n", " ")
                    .replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Delete Output Text request")
        void testProverifDeleteOutputText() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"File FileTest of the model Test has been deleted\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "");
            ProVerifRequests.postVerifyText("Test", "FileTest");
            String actualResult = ProVerifRequests.deleteOutputText("Test", "FileTest").replaceAll("\n", " ")
                    .replaceAll(" +", " ").strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Get Output Html request")
        void testProverifGetOutputHtml() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"output\": \"<!DOCTYPE html PUBLIC \\\"- W3C DTD HTML 4.01 Transitional EN\\\"> <html> <head> <meta http-equiv=\\\"Content-Type\\\" content=\\\"text/html; charset=utf-8\\\"> <title>ProVerif: main result page<\\/title> <link rel=\\\"stylesheet\\\" href=\\\"cssproverif.css\\\"> <\\/head> <body> <H1>ProVerif results<\\/H1>\", \"url\": \"http: localhost:5000/models/Test/output/html/index.html\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "");
            ProVerifRequests.postVerifyHtml("Test", "FileTest");
            String actualResult = ProVerifRequests.getOutputHtml("Test").replaceAll("\n", " ").replaceAll(" +", " ")
                    .strip().replaceAll("//", " ");
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when making proverif Delete Output Html request")
        void testProverifDeleteOutputHtml() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedResult = "{ \"Result\": \"All output html files of the model Test has been deleted\" }";
            // Actual
            ProVerifRequests.postModel("Test");
            ProVerifRequests.postInputFile("Test", "FileTest", "");
            ProVerifRequests.postVerifyHtml("Test", "FileTest");
            String actualResult = ProVerifRequests.deleteOutputHtml("Test").replaceAll("\n", " ").replaceAll(" +", " ")
                    .strip();
            // Test
            assertEquals(expectedResult, actualResult, "should return the same result");
        }

        private boolean checkServerUp() {
            try {
                HttpRequests.get("");
                return true;
            } catch (NotFound404Exception | InternalServerError500Exception | GeneralException e) {
                return true;
            } catch (Exception e) {
                System.err.println("Server DOWN");
                return false;
            }
        }

        private boolean checkServerEmpty() {
            String expectedResult = "{ \"Models\": [] }";
            try {
                String results = HttpRequests.get("models").replaceAll("\n", " ").replaceAll(" +", " ").strip();
                if (expectedResult.equals(results)) {
                    return true;
                } else {
                    System.err.println("Server is not Empty");
                    return false;
                }
            } catch (NotFound404Exception | InternalServerError500Exception | GeneralException e) {
                System.err.println("Server is not Empty");
                return false;
            } catch (Exception e) {
                System.err.println("Server is not Empty");
                return false;
            }
        }
    }
}

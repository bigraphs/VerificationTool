package it.uniud.mads.jlibbig.proverif.networkservices;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniud.mads.jlibbig.proverif.constants.Constants;
import it.uniud.mads.jlibbig.proverif.exceptions.Conflict409Exception;
import it.uniud.mads.jlibbig.proverif.exceptions.GeneralException;
import it.uniud.mads.jlibbig.proverif.exceptions.InternalServerError500Exception;
import it.uniud.mads.jlibbig.proverif.exceptions.NotFound404Exception;
import it.uniud.mads.jlibbig.proverif.exceptions.UnsupportedMediaType415Exception;

@DisplayName("When running http requests")
public class HttpRequestsTest {

    @Nested
    @DisplayName("httpRest method")
    public class Http {

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
        @DisplayName("when making http Get request")
        void testHttpGet() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedGetResult = "{ \"Models\": [] }";
            // Actual
            String actualGetResult = null;
            try {
                actualGetResult = HttpRequests.get("models").replaceAll("\n", " ").replaceAll(" +", " ").strip();
            } catch (NotFound404Exception | InternalServerError500Exception | GeneralException e) {
                System.err.println(e);
                assertFalse(true);
            }
            assertEquals(expectedGetResult, actualGetResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when testing http Get exception 404")
        void testHttpGetException404() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Test
            assertThrows(NotFound404Exception.class, () -> HttpRequests.get("mod"));
        }

        @Test
        @Tag("networking")
        @DisplayName("when making http Post request")
        void testHttpPost() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedPostResult = "{ \"Result\": \"Model Test has been created\" }";
            // Actual
            String actualPostResult = null;
            try {
                actualPostResult = HttpRequests.post(Constants.getHttppost(), "models/Test", null, null)
                        .replaceAll("\n", " ").replaceAll(" +", " ").strip();
            } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                    | InternalServerError500Exception | GeneralException e) {
                assertFalse(true);
            }
            assertEquals(expectedPostResult, actualPostResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when testing http Post exception 404")
        void testHttpPostException404() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Test
            assertThrows(NotFound404Exception.class,
                    () -> HttpRequests.post(Constants.getHttppost(), "mod/Test", null, null));
        }

        @Test
        @Tag("networking")
        @DisplayName("when testing http Post exception 409")
        void testHttpPostException409() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Test
            try {
                HttpRequests.post(Constants.getHttppost(), "models/Test", null, null);
            } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                    | InternalServerError500Exception | GeneralException e) {
                assertFalse(true);
            }
            assertThrows(Conflict409Exception.class,
                    () -> HttpRequests.post(Constants.getHttppost(), "models/Test", null, null));
        }

        @Test
        @Tag("networking")
        @DisplayName("when testing http Post exception 415")
        void testHttpPostException415() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Test
            try {
                HttpRequests.post(Constants.getHttppost(), "models/Test", null, null);
                HttpRequests.post(Constants.getHttppost(), "models/Test/input/text/TestFile", "data", "prova");
            } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                    | InternalServerError500Exception | GeneralException e) {
                assertFalse(true);
            }
            assertThrows(UnsupportedMediaType415Exception.class,
                    () -> HttpRequests.post(Constants.getHttppost(), "models/Test/verify/TestFile", "WrongType", ""));
        }

        @Test
        @Tag("networking")
        @DisplayName("when making http Put request")
        void testHttpPut() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedPostResult = "{ \"Result\": \"File TestFile of the model Test has been modified\" }";
            // Actual
            String actualPostResult = null;
            try {
                HttpRequests.post(Constants.getHttppost(), "models/Test", null, null);
                HttpRequests.post(Constants.getHttppost(), "models/Test/input/text/TestFile", "data", "content");
                actualPostResult = HttpRequests
                        .post(Constants.getHttpput(), "models/Test/input/text/TestFile", "data", "changedContent")
                        .replaceAll("\n", " ").replaceAll(" +", " ").strip();
            } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                    | InternalServerError500Exception | GeneralException e) {
                assertFalse(true);
            }
            assertEquals(expectedPostResult, actualPostResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when testing http Put exception 404")
        void testHttpPutException404() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Test
            assertThrows(NotFound404Exception.class, () -> HttpRequests.post(Constants.getHttpput(),
                    "mod/Test/input/text/TestFile", "data", "changedContent"));
        }

        @Test
        @Tag("networking")
        @DisplayName("when testing http Put exception 409")
        void testHttpPutException409() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Test
            try {
                HttpRequests.post(Constants.getHttppost(), "models/Test", null, null);
                HttpRequests.post(Constants.getHttppost(), "models/Test/input/text/TestFile", "data", "content");
            } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                    | InternalServerError500Exception | GeneralException e) {
                assertFalse(true);
            }
            assertDoesNotThrow(() -> HttpRequests.post(Constants.getHttpput(), "models/Test/input/text/TestFile",
                    "data", "contentChanged"), "should not thrown exception");
        }

        @Test
        @Tag("networking")
        @DisplayName("when testing http Put exception 415")
        void testHttpPutException415() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Test
            try {
                HttpRequests.post(Constants.getHttppost(), "models/Test", null, null);
                HttpRequests.post(Constants.getHttppost(), "models/Test/input/text/TestFile", "data", "prova");
            } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                    | InternalServerError500Exception | GeneralException e) {
                assertFalse(true);
            }
            assertThrows(UnsupportedMediaType415Exception.class,
                    () -> HttpRequests.post(Constants.getHttpput(), "models/Test/verify/TestFile", "WrongType", ""));
        }

        @Test
        @Tag("networking")
        @DisplayName("when making http Delete request")
        void testHttpDelete() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Expected
            String expectedPostResult = "{ \"Result\": \"Model Test has been deleted\" }";
            // Actual
            String actualPostResult = null;
            try {
                HttpRequests.post(Constants.getHttppost(), "models/Test", null, null);
                actualPostResult = HttpRequests.delete("models/Test").replaceAll("\n", " ").replaceAll(" +", " ")
                        .strip();
            } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                    | InternalServerError500Exception | GeneralException e) {
                assertFalse(true);
            }
            assertEquals(expectedPostResult, actualPostResult, "should return the same result");
        }

        @Test
        @Tag("networking")
        @DisplayName("when testing http Delete exception 404")
        void testHttpDeleteException404() {
            // Assume server Up
            assumeTrue(checkServerUp());
            assumeTrue(checkServerEmpty());
            // Test
            assertThrows(NotFound404Exception.class, () -> HttpRequests.delete("model/Test"));
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

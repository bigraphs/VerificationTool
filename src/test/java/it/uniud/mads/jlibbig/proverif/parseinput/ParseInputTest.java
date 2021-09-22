package it.uniud.mads.jlibbig.proverif.parseinput;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniud.mads.jlibbig.core.imports.ImportDirectedBigraph;
import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;

@DisplayName("When running parse input")
public class ParseInputTest {

    @Nested
    @DisplayName("parse method")
    public class ParseNodes {

        String directedBigraph = "{ \"graph\": { \"nodes\": { \"root1\": { \"metadata\": { \"type\": \"root\", \"location\": 1 }, \"label\": \"root1\" }, \"nodeA\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"properties\": { \"params\": [ \"x:bitstring\" ], \"behaviour\": \"!( out(#0+,x) ).\", \"events\": [\"BigEventTest()\"], \"attribute\": \"\" } }, \"label\": \"nodeA\" }, \"nodeB\": { \"metadata\": { \"type\": \"node\", \"control\": \"2on0\", \"properties\": { \"params\": [], \"behaviour\": \"!( in(#0+,x:bitstring); out(#1+, x) ).\", \"attribute\": \"\" } }, \"label\": \"nodeB\" }, \"c\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"c\" }, \"d\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"d\" } }, \"edges\": [ { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"nodeA\", \"metadata\": {} }, { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"nodeB\", \"metadata\": {} }, { \"source\": \"nodeA\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"nodeB\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"nodeB\", \"relation\": \"linkedTo\", \"target\": \"d\", \"metadata\": { \"portFrom\": \"1\" } } ], \"type\": \"ldb\", \"metadata\": { \"signature\": { \"1on0\": { \"active\": true, \"arityOut\": 1, \"arityIn\": 0 }, \"2on0\": { \"active\": true, \"arityOut\": 2, \"arityIn\": 0 } } } } }";
        String integrativeJson = "{ \"bigraphInfo\": { \"id\": \"testVerify\" }, \"types\": [\"key\"], \"variables\": { \"public\": [], \"private\": [\"mex:bitstring\"] }, \"functions\": { \"senc\": \"fun senc(bitstring, key) : bitstring. reduc forall m : bitstring, k : key; sdec(senc(m, k), k) = m.\" }, \"queries\": { \"query1\": { \"attacker\": \"mex\" }, \"query2\": { \"query\": \"testing\" } }, \"instantiations\": { \"nodeA\": { \"x\": \"mex\" } }, \"prologue\": [] }";
        ImportDirectedBigraph im;
        DirectedBigraph bigraph;
        ParseInput parseInput;

        @BeforeEach
        void beforeEach() {
            im = new ImportDirectedBigraph();
            bigraph = im.doImport(directedBigraph);
            parseInput = new ParseInput();
            parseInput.parse(bigraph, integrativeJson);
        }

        @Test
        @Tag("parsing")
        @DisplayName("when parsing bigraphInfo")
        void testParsingBigraphInfo() {
            // Expected
            String expectedBigraphInfo = "testVerify";
            // Actual
            String actualBigraphInfo = parseInput.getBigraphInfo().getId();
            // Test
            assertEquals(expectedBigraphInfo, actualBigraphInfo, "should return the same bigraph info");
        }

        @Test
        @Tag("parsing")
        @DisplayName("when parsing types")
        void testParsingTypes() {
            // Expected
            String expectedTypeKey = "key";
            String expectedTypeContract = "type key.";
            // Actual
            String actualTypeKey = parseInput.getTypes().get(0).getId();
            String actualTypeContract = parseInput.getTypes().get(0).getContract();
            // Test
            assertAll(() -> {
                assertEquals(expectedTypeKey, actualTypeKey, "should return the same type key");
                assertEquals(expectedTypeContract, actualTypeContract, "should return the same type contract");
            });
        }

        @Test
        @Tag("parsing")
        @DisplayName("when parsing channels")
        void testParsingChannels() {
            // Expected
            String expectedChannelKey1 = "c";
            String expectedChannelContract1 = "free c : channel.";
            String expectedChannelKey2 = "d";
            String expectedChannelContract2 = "free d : channel.";
            // Actual
            String actualChannelKey1 = parseInput.getChannels().get(0).getId();
            String actualChannelContract1 = parseInput.getChannels().get(0).getContract();
            String actualChannelKey2 = parseInput.getChannels().get(1).getId();
            String actualChannelContract2 = parseInput.getChannels().get(1).getContract();
            // Test
            assertAll(() -> {
                assertEquals(expectedChannelKey1, actualChannelKey1, "should return the same channel key");
                assertEquals(expectedChannelContract1, actualChannelContract1,
                        "should return the same channel contract");
                assertEquals(expectedChannelKey2, actualChannelKey2, "should return the same channel key");
                assertEquals(expectedChannelContract2, actualChannelContract2,
                        "should return the same channel contract");
            });
        }

        @Test
        @Tag("parsing")
        @DisplayName("when parsing variables")
        void testParsingVariables() {
            // Expected
            String expectedVariableKey = "mex:bitstring";
            String expectedVariableContract = "free mex:bitstring [private].";
            // Actual
            String actualVariableKey = parseInput.getVariables().get(0).getId();
            String actualVariableContract = parseInput.getVariables().get(0).getContract();
            // Test
            assertAll(() -> {
                assertEquals(expectedVariableKey, actualVariableKey, "should return the same variable key");
                assertEquals(expectedVariableContract, actualVariableContract,
                        "should return the same variable contract");
            });
        }

        @Test
        @Tag("parsing")
        @DisplayName("when parsing functions")
        void testParsingFunctions() {
            // Expected
            String expectedFunctionKey = "senc";
            String expectedFunctionContract = "fun senc(bitstring, key) : bitstring. reduc forall m : bitstring, k : key; sdec(senc(m, k), k) = m.";
            // Actual
            String actualFunctionKey = parseInput.getFunctions().get(0).getId();
            String actualFunctionContract = parseInput.getFunctions().get(0).getContract();
            // Test
            assertAll(() -> {
                assertEquals(expectedFunctionKey, actualFunctionKey, "should return the same function key");
                assertEquals(expectedFunctionContract, actualFunctionContract,
                        "should return the same function contract");
            });
        }

        @Test
        @Tag("parsing")
        @DisplayName("when parsing events")
        void testParsingEvents() {
            // Expected
            String expectedEventKey = "BigEventTest()";
            String expectedEventContract = "event BigEventTest().";
            // Actual
            String actualEventKey = parseInput.getEvents().get(0).getId();
            String actualEventContract = parseInput.getEvents().get(0).getContract();
            // Test
            assertAll(() -> {
                assertEquals(expectedEventKey, actualEventKey, "should return the same event key");
                assertEquals(expectedEventContract, actualEventContract, "should return the same event contract");
            });
        }

        @Test
        @Tag("parsing")
        @DisplayName("when parsing queries")
        void testParsingQueries() {
            // Expected
            String expectedQueryKey = "query1";
            String expectedQueryContract = "query attacker(mex).";
            String expectedQuery2Key = "query2";
            String expectedQuery2Contract = "query testing.";
            // Actual
            String actualQueryKey = parseInput.getQueries().get(0).getId();
            String actualQueryContract = parseInput.getQueries().get(0).getContract();
            String actualQuery2Key = parseInput.getQueries().get(1).getId();
            String actualQuery2Contract = parseInput.getQueries().get(1).getContract();
            // Test
            assertAll(() -> {
                assertEquals(expectedQueryKey, actualQueryKey, "should return the same query key");
                assertEquals(expectedQueryContract, actualQueryContract, "should return the same query contract");
                assertEquals(expectedQuery2Key, actualQuery2Key, "should return the same query key");
                assertEquals(expectedQuery2Contract, actualQuery2Contract, "should return the same query contract");
            });
        }

        @Test
        @Tag("parsing")
        @DisplayName("when parsing contracts")
        void testParsingContracts() {
            // Expected
            String expectedContract1 = "let behaviour_nodeA(x:bitstring) = !( out(c,x) ).";
            String expectedContract2 = "let behaviour_nodeB() = !( in(c,x:bitstring); out(d, x) ).";
            // Actual
            String actualContract1 = parseInput.getContracts().get(0);
            String actualContract2 = parseInput.getContracts().get(1);
            // Test
            assertAll(() -> {
                assertEquals(expectedContract1, actualContract1, "should return the same contract");
                assertEquals(expectedContract2, actualContract2, "should return the same contract");
            });
        }

        @Test
        @Tag("parsing")
        @DisplayName("when parsing process")
        void testParsingProcess() {
            // Expected
            String expectedProcessKey = "mainProcess";
            String expectedProcessContract = "( behaviour_nodeA(mex) | behaviour_nodeB() )";
            // String expectedProcessContract = "( behaviour_nodeB() | behaviour_nodeA(mex) )";
            // Actual
            String actualProcessKey = parseInput.getProcess().getId();
            String actualProcessContract = parseInput.getProcess().getContract();
            // Test
            assertAll(() -> {
                assertEquals(expectedProcessKey, actualProcessKey, "should return the same process key");
                assertEquals(expectedProcessContract, actualProcessContract, "should return the same process contract");
            });
        }
    }
}
package it.uniud.mads.jlibbig.proverif.createinput;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniud.mads.jlibbig.core.imports.ImportDirectedBigraph;
import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;
import it.uniud.mads.jlibbig.proverif.parseinput.ParseInput;

@DisplayName("When running create input")
public class CreateInputTest {

    @Nested
    @DisplayName("create method")
    public class Create {

        String directedBigraph = "{ \"graph\": { \"nodes\": { \"root1\": { \"metadata\": { \"type\": \"root\", \"location\": 1 }, \"label\": \"root1\" }, \"nodeA\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"properties\": { \"params\": [ \"x:bitstring\" ], \"behaviour\": \"!( out(#0+,x) ).\", \"events\": [], \"attribute\": \"\" } }, \"label\": \"nodeA\" }, \"nodeB\": { \"metadata\": { \"type\": \"node\", \"control\": \"2on0\", \"properties\": { \"params\": [], \"behaviour\": \"!( in(#0+,x:bitstring); out(#1+, x) ).\", \"events\": [], \"attribute\": \"\" } }, \"label\": \"nodeB\" }, \"c\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"c\" }, \"d\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"d\" } }, \"edges\": [ { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"nodeA\", \"metadata\": {} }, { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"nodeB\", \"metadata\": {} }, { \"source\": \"nodeA\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"nodeB\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"nodeB\", \"relation\": \"linkedTo\", \"target\": \"d\", \"metadata\": { \"portFrom\": \"1\" } } ], \"type\": \"ldb\", \"metadata\": { \"signature\": { \"1on0\": { \"active\": true, \"arityOut\": 1, \"arityIn\": 0 }, \"2on0\": { \"active\": true, \"arityOut\": 2, \"arityIn\": 0 } } } } }";
        String integrativeJson = "{ \"bigraphInfo\": { \"id\": \"testVerify\" }, \"types\": [\"key\"], \"variables\": { \"public\": [], \"private\": [\"mex:bitstring\"] }, \"functions\": { \"senc\": \"fun senc(bitstring, key) : bitstring. reduc forall m : bitstring, k : key; sdec(senc(m, k), k) = m.\" }, \"queries\": { \"query1\": { \"attacker\": \"mex\" } }, \"instantiations\": { \"nodeA\": { \"x\": \"mex\" } }, \"prologue\": [] }";
        ImportDirectedBigraph im;
        DirectedBigraph bigraph;
        ParseInput parseInput;
        CreateInput create;

        @BeforeEach
        void beforeEach() {
            im = new ImportDirectedBigraph();
            bigraph = im.doImport(directedBigraph);
            parseInput = new ParseInput();
            parseInput.parse(bigraph, integrativeJson);
            create = new CreateInput(parseInput);
            create.create();
        }

        @Test
        @Tag("creatingInput")
        @DisplayName("when creating input")
        void testCreate() {
            // Expected
            // String expectedInput = "type key. free c : channel. free d : channel. free mex:bitstring [private]. fun senc(bitstring, key) : bitstring. reduc forall m : bitstring, k : key; sdec(senc(m, k), k) = m. query attacker(mex). let behaviour_nodeB() = !( in(c,x:bitstring); out(d, x) ). let behaviour_nodeA(x:bitstring) = !( out(c,x) ). process ( behaviour_nodeB() | behaviour_nodeA(mex) )";
            String expectedInput = "type key. free c : channel. free d : channel. free mex:bitstring [private]. fun senc(bitstring, key) : bitstring. reduc forall m : bitstring, k : key; sdec(senc(m, k), k) = m. query attacker(mex). let behaviour_nodeA(x:bitstring) = !( out(c,x) ). let behaviour_nodeB() = !( in(c,x:bitstring); out(d, x) ). process ( behaviour_nodeA(mex) | behaviour_nodeB() )";
            // Actual
            String actualInput = create.getProverifInput().substring(47);
            // Test
            assertEquals(expectedInput, actualInput, "should return the same input file");
        }
    }
}

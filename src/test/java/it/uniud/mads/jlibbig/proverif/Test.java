package it.uniud.mads.jlibbig.proverif;

import it.uniud.mads.jlibbig.core.imports.ImportDirectedBigraph;
import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;

public class Test {

    private static String json = "{ \"graph\": { \"nodes\": { \"root1\": { \"metadata\": { \"type\": \"root\", \"location\": 1 }, \"label\": \"root1\" }, \"nodeA\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"params\": [ \"x:bitstring\" ], \"behaviour\": \"!( out(#0+,x) ).\", \"attribute\": \"\" }, \"label\": \"nodeA\" }, \"nodeB\": { \"metadata\": { \"type\": \"node\", \"control\": \"2on0\", \"params\": [], \"behaviour\": \"!( in(#0+,x:bitstring); out(#1+, x) ).\", \"attribute\": \"\" }, \"label\": \"nodeB\" }, \"c\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"c\" }, \"d\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"d\" } }, \"edges\": [ { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"nodeA\", \"metadata\": {} }, { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"nodeB\", \"metadata\": {} }, { \"source\": \"nodeA\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"nodeB\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"nodeB\", \"relation\": \"linkedTo\", \"target\": \"d\", \"metadata\": { \"portFrom\": \"1\" } } ], \"type\": \"ldb\", \"metadata\": { \"signature\": { \"1on0\": { \"active\": true, \"arityOut\": 1, \"arityIn\": 0 }, \"2on0\": { \"active\": true, \"arityOut\": 2, \"arityIn\": 0 } } } } }";
    private static String integrativeJson = "{ \"bigraphInfo\": { \"id\": \"testVerify\" }, \"types\": [\"key\"], \"variables\": { \"public\": [], \"private\": [\"mex:bitstring\"] }, \"functions\": { \"senc\": \"fun senc(bitstring, key) : bitstring. reduc forall m : bitstring, k : key; sdec(senc(m, k), k) = m.\" }, \"queries\": { \"query1\": { \"attacker\": \"mex\" }, \"query2\": { \"event\": \"\" } }, \"instantiations\": { \"nodeA\": { \"x\": \"mex\" } } }";

    public static void main(String[] args) {
        
        ImportDirectedBigraph im = new ImportDirectedBigraph();
        DirectedBigraph bigraph = im.doImport(json);
        Verify verify = new Verify();
        String outputText = verify.postVerify(bigraph, integrativeJson, "Test", "inputTest");
        System.out.println(outputText);
    }

}
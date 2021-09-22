package it.uniud.mads.jlibbig.proverif.testproverif;

import it.uniud.mads.jlibbig.core.imports.ImportDirectedBigraph;
import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;
import it.uniud.mads.jlibbig.proverif.Verify;

public class ExApproxRepetPrivate2 {

    private static String json = "{ \"graph\": { \"nodes\": { \"root1\": { \"metadata\": { \"type\": \"root\", \"location\": 1 }, \"label\": \"root1\" }, \"processA\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"params\": [], \"behaviour\": \"out(#0+,M).\", \"attribute\": \"\" }, \"label\": \"processA\" }, \"processB\": { \"metadata\": { \"type\": \"node\", \"control\": \"2on0\", \"params\": [], \"behaviour\": \"in(#0+,x:t); in(#0+,y:t); out(#1+,s).\", \"attribute\": \"\" }, \"label\": \"processB\" }, \"c\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"c\" }, \"d\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"d\" } }, \"edges\": [ { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"processA\", \"metadata\": {} }, { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"processB\", \"metadata\": {} }, { \"source\": \"processA\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"processB\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"processB\", \"relation\": \"linkedTo\", \"target\": \"d\", \"metadata\": { \"portFrom\": \"1\" } } ], \"type\": \"ldb\", \"metadata\": { \"signature\": { \"1on0\": { \"active\": true, \"arityOut\": 1, \"arityIn\": 0 }, \"2on0\": { \"active\": true, \"arityOut\": 2, \"arityIn\": 0 } } } } }";
    private static String integrativeJson = "{ \"bigraphInfo\": { \"id\": \"Ex Approx Repet Private 2\" }, \"types\": [ \"t\" ], \"variables\": { \"public\": [\"M:t\"], \"private\": [\"s:bitstring\"] }, \"functions\": {}, \"queries\": { \"attack\": { \"attacker\": \"s\" } }, \"instantiations\": {}, \"prologue\": [ \"new c:channel;\" ] }";

    public static void main(String[] args) {

        ImportDirectedBigraph im = new ImportDirectedBigraph();
        DirectedBigraph bigraph = im.doImport(json);
        Verify verify = new Verify();
        String outputText = verify.postVerify(bigraph, integrativeJson, "ExApproxRepetPrivate2", "exApproxRepetPrivate2");
        System.out.println(outputText);
    }
    
}

package it.uniud.mads.jlibbig.proverif.testproverif;

import it.uniud.mads.jlibbig.core.imports.ImportDirectedBigraph;
import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;
import it.uniud.mads.jlibbig.proverif.Verify;

public class DhFs {

    private static String json = "{ \"graph\": { \"nodes\": { \"root1\": { \"metadata\": { \"type\": \"root\", \"location\": 1 }, \"label\": \"root1\" }, \"processA\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"params\": [], \"behaviour\": \"new a: exponent; new b: exponent; new c: exponent; out(#0+, (exp(g,a), exp(g,b), choice[exp(exp(g,a),b), exp(g,c)])).\", \"attribute\": \"\" }, \"label\": \"processA\" }, \"d\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"d\" } }, \"edges\": [ { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"processA\", \"metadata\": {} }, { \"source\": \"processA\", \"relation\": \"linkedTo\", \"target\": \"d\", \"metadata\": { \"portFrom\": \"0\" } } ], \"type\": \"ldb\", \"metadata\": { \"signature\": { \"1on0\": { \"active\": true, \"arityOut\": 1, \"arityIn\": 0 } } } } }";
    private static String integrativeJson = "{ \"bigraphInfo\": { \"id\": \"dh-fs\" }, \"types\": [ \"G\", \"exponent\" ], \"variables\": { \"public\": [], \"private\": [] }, \"functions\": { \"constG\": \"const g: G [data].\", \"exp\": \"fun exp(G, exponent): G.\" }, \"queries\": { \"equation1\": { \"equation\": \"forall x: exponent, y: exponent; exp(exp(g,x),y) = exp(exp(g,y),x)\" } }, \"instantiations\": {}, \"prologue\": [] }";

    public static void main(String[] args) {

        ImportDirectedBigraph im = new ImportDirectedBigraph();
        DirectedBigraph bigraph = im.doImport(json);
        Verify verify = new Verify();
        String outputText = verify.postVerify(bigraph, integrativeJson, "DhFs", "DhFs");
        System.out.println(outputText);
    }
    
}

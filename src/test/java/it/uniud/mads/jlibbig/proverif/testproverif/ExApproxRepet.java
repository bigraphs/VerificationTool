package it.uniud.mads.jlibbig.proverif.testproverif;

import it.uniud.mads.jlibbig.core.imports.ImportDirectedBigraph;
import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;
import it.uniud.mads.jlibbig.proverif.Verify;

public class ExApproxRepet {

    private static String json = "{ \"graph\": { \"nodes\": { \"root1\": { \"metadata\": { \"type\": \"root\", \"location\": 1 }, \"label\": \"root1\" }, \"processA\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"params\": [], \"behaviour\": \"!( new k:key; out(#0+, senc(senc(s,k),k)); in(#0+, x:bitstring); out(#0+, sdec(x,k)) ).\", \"attribute\": \"\" }, \"label\": \"processA\" }, \"c\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"c\" } }, \"edges\": [ { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"processA\", \"metadata\": {} }, { \"source\": \"processA\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } } ], \"type\": \"ldb\", \"metadata\": { \"signature\": { \"1on0\": { \"active\": true, \"arityOut\": 1, \"arityIn\": 0 } } } } }";
    private static String integrativeJson = "{ \"bigraphInfo\": { \"id\": \"ex_approx_repet\" }, \"types\": [ \"key\" ], \"variables\": { \"public\": [], \"private\": [\"s:bitstring\"] }, \"functions\": { \"senc\": \"fun senc(bitstring, key): bitstring. reduc forall m: bitstring, k: key; sdec(senc(m,k),k) = m.\" }, \"queries\": { \"query1\": { \"attacker\": \"s\" } }, \"instantiations\": {}, \"prologue\": [] }";

    public static void main(String[] args) {

        ImportDirectedBigraph im = new ImportDirectedBigraph();
        DirectedBigraph bigraph = im.doImport(json);
        Verify verify = new Verify();
        String outputText = verify.postVerify(bigraph, integrativeJson, "ExApproxRepet", "exApproxRepet");
        System.out.println(outputText);
    }
}

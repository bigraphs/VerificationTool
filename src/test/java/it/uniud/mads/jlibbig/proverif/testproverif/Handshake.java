package it.uniud.mads.jlibbig.proverif.testproverif;

import it.uniud.mads.jlibbig.core.imports.ImportDirectedBigraph;
import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;
import it.uniud.mads.jlibbig.proverif.Verify;

public class Handshake {

    private static String json = "{ \"graph\": { \"nodes\": { \"root1\": { \"metadata\": { \"type\": \"root\", \"location\": 1 }, \"label\": \"root1\" }, \"clientA\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"params\": [ \"pkA : pkey\", \"skA : skey\", \"pkB : spkey\" ], \"behaviour\": \"!( out (#0+, pkA); in (#0+, x : bitstring); let y = adec(x, skA) in let (=pkB, k : key) = checksign(y, pkB) in out (#0+, senc(s, k)) ).\", \"attribute\": \"\" }, \"label\": \"clientA\" }, \"serverB\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"params\": [ \"pkB : spkey\", \"skB : sskey\" ], \"behaviour\": \"!( in(#0+, pkX : pkey); new k : key; out(#0+, aenc(sign((pkB, k), skB), pkX)); in(#0+, x : bitstring); let z = sdec(x, k) in 0 ).\", \"attribute\": \"\" }, \"label\": \"serverB\" }, \"c\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"c\" } }, \"edges\": [ { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"clientA\", \"metadata\": {} }, { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"serverB\", \"metadata\": {} }, { \"source\": \"clientA\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"serverB\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } } ], \"type\": \"ldb\", \"metadata\": { \"signature\": { \"1on0\": { \"active\": true, \"arityOut\": 1, \"arityIn\": 0 } } } } }";
    private static String integrativeJson = "{ \"bigraphInfo\": { \"id\": \"handshake\" }, \"types\": [ \"skey\", \"pkey\", \"sskey\", \"spkey\", \"key\" ], \"variables\": { \"public\": [], \"private\": [\"s:bitstring\"] }, \"functions\": { \"senc\": \"fun senc(bitstring, key) : bitstring. reduc forall m : bitstring, k : key; sdec(senc(m, k), k) = m.\", \"aenc\": \"fun pk(skey): pkey. fun aenc(bitstring, pkey) : bitstring. reduc forall m : bitstring, k : skey; adec(aenc(m, pk(k)), k) = m.\", \"sign\": \"fun spk(sskey) : spkey. fun sign(bitstring, sskey) : bitstring. reduc forall m : bitstring, k : sskey; getmess(sign(m, k)) = m. reduc forall m : bitstring, k : sskey; checksign(sign(m, k), spk(k)) = m.\" }, \"queries\": { \"query1\": { \"attacker\": \"s\" } }, \"instantiations\": { \"clientA\": { \"pkA\": \"pkA\", \"skA\": \"skA\", \"pkB\": \"pkB\" }, \"serverB\": { \"pkB\": \"pkB\", \"skB\": \"skB\" } }, \"prologue\": [ \"new skA : skey;\", \"new skB : sskey;\", \"let pkA = pk(skA) in out(c, pkA);\", \"let pkB = spk(skB) in out(c, pkB);\" ] }";

    public static void main(String[] args) {
        
        ImportDirectedBigraph im = new ImportDirectedBigraph();
        DirectedBigraph bigraph = im.doImport(json);
        Verify verify = new Verify();
        String outputText = verify.postVerify(bigraph, integrativeJson, "TestHandshake", "handshake");
        System.out.println(outputText);
    }
    
}

package it.uniud.mads.jlibbig.proverif.testproverif;

import it.uniud.mads.jlibbig.core.imports.ImportDirectedBigraph;
import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;
import it.uniud.mads.jlibbig.proverif.Verify;

public class NeedhamSchroederPKVar1 {

    private static String json = "{ \"graph\": { \"nodes\": { \"root1\": { \"metadata\": { \"type\": \"root\", \"location\": 1 }, \"label\": \"root1\" }, \"alice\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"params\": [ \"pkB: pkey\", \"skA: skey\" ], \"behaviour\": \"!( in(#0+, pkX: pkey); event beginBparam(pkX); new Na: bitstring; out(#0+, aenc((Na, pk(skA)), pkX)); in(#0+, m: bitstring); let (=Na, NX: bitstring) = adec(m, skA) in out(#0+, aenc(NX, pkX)); if pkX = pkB  then event endAparam(pk(skA)); out(#0+, senc(secretANa, Na)); out(#0+, senc(secretANb, NX)) ).\", \"events\": [ \"beginBparam(pkey)\", \"endAparam(pkey)\" ], \"attribute\": \"\" }, \"label\": \"alice\" }, \"bob\": { \"metadata\": { \"type\": \"node\", \"control\": \"1on0\", \"params\": [ \"pkA: pkey\", \"skB: skey\" ], \"behaviour\": \"!( in(#0+, m: bitstring); let (NY: bitstring, pkY: pkey) = adec(m, skB) in event beginAparam(pkY); new Nb: bitstring; out(#0+, aenc((NY, Nb), pkY)); in(#0+, m3: bitstring); if Nb = adec(m3, skB) then if pkY = pkA then event endBparam(pk(skB)); out(#0+, senc(secretBNa, NY)); out(#0+, senc(secretBNb, Nb)) ).\", \"events\": [ \"beginAparam(pkey)\", \"endBparam(pkey)\" ], \"attribute\": \"\" }, \"label\": \"bob\" }, \"c\": { \"metadata\": { \"type\": \"name\", \"interface\": \"outer\", \"locality\": 1, \"polarity\": \"+\" }, \"label\": \"c\" } }, \"edges\": [ { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"alice\", \"metadata\": {} }, { \"source\": \"root1\", \"relation\": \"place\", \"target\": \"bob\", \"metadata\": {} }, { \"source\": \"alice\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } }, { \"source\": \"bob\", \"relation\": \"linkedTo\", \"target\": \"c\", \"metadata\": { \"portFrom\": \"0\" } } ], \"type\": \"ldb\", \"metadata\": { \"signature\": { \"1on0\": { \"active\": true, \"arityOut\": 1, \"arityIn\": 0 } } } } }";
    private static String integrativeJson = "{ \"bigraphInfo\": { \"id\": \"Needham Schroeder Pk Var1\" }, \"types\": [ \"pkey\", \"skey\", \"spkey\", \"sskey\" ], \"variables\": { \"public\": [], \"private\": [ \"secretANa:bitstring\", \"secretANb:bitstring\", \"secretBNa:bitstring\", \"secretBNb:bitstring\" ] }, \"functions\": { \"aenc\": \"fun pk(skey): pkey. fun aenc(bitstring, pkey): bitstring. reduc forall x: bitstring, y: skey; adec(aenc(x, pk(y)),y) = x.\", \"sign\": \"fun spk(sskey): spkey. fun sign(bitstring, sskey): bitstring. reduc forall x: bitstring, y: sskey; getmess(sign(x,y)) = x. reduc forall x: bitstring, y: sskey; checksign(sign(x,y), spk(y)) = x.\", \"sharedEnc\": \"fun senc(bitstring,bitstring): bitstring. reduc forall x: bitstring, y: bitstring; sdec(senc(x,y),y) = x.\" }, \"queries\": { \"correspondence1\": { \"query\": \"x: pkey; inj-event(endBparam(x)) ==> inj-event(beginBparam(x))\" }, \"correspondence2\": { \"query\": \"x: pkey; inj-event(endAparam(x)) ==> inj-event(beginAparam(x))\" }, \"attack1\": { \"attacker\": \"secretANa\" }, \"attack2\": { \"attacker\": \"secretANb\" }, \"attack3\": { \"attacker\": \"secretBNa\" }, \"attack4\": { \"attacker\": \"secretBNb\" } }, \"instantiations\": { \"alice\": { \"pkB\": \"pkB\", \"skA\": \"skA\" }, \"bob\": { \"pkA\": \"pkA\", \"skB\": \"skB\" } }, \"prologue\": [ \"new skA: skey; let pkA = pk(skA) in out(c, pkA);\", \"new skB: skey; let pkB = pk(skB) in out(c, pkB);\" ] }";

    public static void main(String[] args) {
        
        ImportDirectedBigraph im = new ImportDirectedBigraph();
        DirectedBigraph bigraph = im.doImport(json);
        Verify verify = new Verify();
        String outputText = verify.postVerify(bigraph, integrativeJson, "NeedhamSchroederPKVar1", "NeedhamSchroederPKVar1");
        System.out.println(outputText);
    }
    
}

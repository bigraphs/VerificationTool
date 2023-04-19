package it.uniud.mads.jlibbig.proverif;

import it.uniud.mads.jlibbig.core.imports.ImportDirectedBigraph;

import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;
import it.uniud.mads.jlibbig.proverif.constants.Constants;
import it.uniud.mads.jlibbig.proverif.createinput.CreateInput;
import it.uniud.mads.jlibbig.proverif.networkservices.ProVerifRequests;
import it.uniud.mads.jlibbig.proverif.parseinput.ParseInput;

public class Main {

    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "PostVerify":
                    ImportDirectedBigraph im = new ImportDirectedBigraph();
                    try {
                        // System.out.println(args[1]);
                        // args[1] = "{\"graph\":{\"nodes\":{\"root\":{\"metadata\":{\"type\":\"root\",\"location\":1},\"label\":\"root\"},\"containerA\":{\"metadata\":{\"type\":\"node\",\"control\":\"1on0\",\"properties\":{\"params\":[\"data: bitstring\",\"sessionKey: key\"],\"behaviour\":\"!(event startCommunication(data, sessionKey); out(#0+, senc(data, sessionKey))).\",\"events\":[\"startCommunication(data, sessionKey)\"],\"attribute\":\"\"}},\"label\":\"containerA\"},\"containerB\":{\"metadata\":{\"type\":\"node\",\"control\":\"1on0\",\"properties\":{\"params\":[\"sessionKey: key\"],\"behaviour\":\"!(in(#0+, e_data:bitstring); let data_received = sdec(e_data, sessionKey) in event termCommunication(data_received, sessionKey)).\",\"events\":[\"termCommunication(data_received, sessionKey)\", \"termCommunication(data_received, sessionKey)\"],\"attribute\":\"\"}},\"label\":\"containerB\"},\"publicNetwork\":{\"metadata\":{\"type\":\"name\",\"interface\":\"outer\",\"locality\":1,\"polarity\":\"+\"},\"label\":\"publicNetwork\"}},\"edges\":[{\"source\":\"root\",\"relation\":\"place\",\"target\":\"containerA\",\"metadata\":{}},{\"source\":\"root\",\"relation\":\"place\",\"target\":\"containerB\",\"metadata\":{}},{\"source\":\"containerA\",\"relation\":\"linkedTo\",\"target\":\"publicNetwork\",\"metadata\":{\"portFrom\":\"0\"}},{\"source\":\"containerB\",\"relation\":\"linkedTo\",\"target\":\"publicNetwork\",\"metadata\":{\"portFrom\":\"0\"}}],\"type\":\"ldb\",\"metadata\":{\"signature\":{\"1on0\":{\"active\":true,\"arityOut\":1,\"arityIn\":0}}}}}";
                        // args[2] = "{\"bigraphInfo\":{\"id\":\"basicReplyAttack\"},\"types\":[\"key\"],\"variables\":{\"public\":[],\"private\":[\"sessionKey:key\",\"data:bitstring\"]},\"functions\":{\"senc\":\"fun senc(bitstring, key):bitstring. reduc forall m:bitstring, k:key; sdec(senc(m,k),k) = m.\"},\"queries\":{\"attack1\":{\"attacker\":\"data\"},\"correspondence\":{\"query\":\"c:bitstring, k:key; inj-event(termCommunication(c, k)) ==> inj-event(startCommunication(c, k)).\"}},\"instantiations\":{\"containerA\":{\"data\":\"data\",\"sessionKey\":\"sessionKey\"},\"containerB\":{\"sessionKey\":\"sessionKey\"}},\"prologue\":[]}";
                        DirectedBigraph bigraph = im.doImport(args[1]);
                        Verify verify = new Verify();
                        try {
                            String outputText = verify.postVerify(bigraph, args[2], args[3], args[4]);
                            System.out.println(outputText);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.err.println(
                                    "Please insert in arg2 a valid integrativeJson, in arg3 a model name, in arg4 a file name");
                            System.exit(1);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a valid jsonBigraph");
                        System.exit(1);
                    }
                    break;
                case "PostVerifyText":
                    ImportDirectedBigraph im2 = new ImportDirectedBigraph();
                    try {
                        DirectedBigraph bigraph2 = im2.doImport(args[1]);
                        Verify verify2 = new Verify();
                        try {
                            String outputText2 = verify2.postVerifyText(bigraph2, args[2], args[3], args[4]);
                            System.out.println(outputText2);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.err.println(
                                    "Please insert in arg2 a valid integrativeJson, in arg3 a model name, in arg4 a file name");
                            System.exit(1);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a valid jsonBigraph");
                        System.exit(1);
                    }
                    break;
                case "PostVerifyHtml":
                    ImportDirectedBigraph im3 = new ImportDirectedBigraph();
                    try {
                        DirectedBigraph bigraph3 = im3.doImport(args[1]);
                        Verify verify3 = new Verify();
                        try {
                            String outputText3 = verify3.postVerifyHtml(bigraph3, args[2], args[3], args[4]);
                            System.out.println(outputText3);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.err.println(
                                    "Please insert in arg2 a valid integrativeJson, in arg3 a model name, in arg4 a file name");
                            System.exit(1);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a valid jsonBigraph");
                        System.exit(1);
                    }
                    break;
                case "PutVerify":
                    ImportDirectedBigraph im4 = new ImportDirectedBigraph();
                    try {
                        DirectedBigraph bigraph4 = im4.doImport(args[1]);
                        Verify verify4 = new Verify();
                        try {
                            String outputText4 = verify4.putVerify(bigraph4, args[2], args[3], args[4]);
                            System.out.println(outputText4);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.err.println(
                                    "Please insert in arg2 a valid integrativeJson, in arg3 a model name, in arg4 a file name");
                            System.exit(1);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a valid jsonBigraph");
                        System.exit(1);
                    }
                    break;
                case "PutVerifyText":
                    ImportDirectedBigraph im5 = new ImportDirectedBigraph();
                    try {
                        DirectedBigraph bigraph5 = im5.doImport(args[1]);
                        Verify verify5 = new Verify();
                        try {
                            String outputText5 = verify5.putVerifyText(bigraph5, args[2], args[3], args[4]);
                            System.out.println(outputText5);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.err.println(
                                    "Please insert in arg2 a valid integrativeJson, in arg3 a model name, in arg4 a file name");
                            System.exit(1);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a valid jsonBigraph");
                        System.exit(1);
                    }
                    break;
                case "PutVerifyHtml":
                    ImportDirectedBigraph im6 = new ImportDirectedBigraph();
                    try {
                        DirectedBigraph bigraph6 = im6.doImport(args[1]);
                        Verify verify6 = new Verify();
                        try {
                            String outputText6 = verify6.putVerifyHtml(bigraph6, args[2], args[3], args[4]);
                            System.out.println(outputText6);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.err.println(
                                    "Please insert in arg2 a valid integrativeJson, in arg3 a model name, in arg4 a file name");
                            System.exit(1);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a valid jsonBigraph");
                        System.exit(1);
                    }
                    break;
                case "GetModels":
                    System.out.println(ProVerifRequests.getModels());
                    break;
                case "GetModel":
                    try {
                        System.out.println(ProVerifRequests.getModel(args[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name");
                        System.exit(1);
                    }
                    break;
                case "PostModel":
                    try {
                        System.out.println(ProVerifRequests.postModel(args[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name");
                        System.exit(1);
                    }
                    break;
                case "DeleteModel":
                    try {
                        System.out.println(ProVerifRequests.deleteModel(args[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name");
                        System.exit(1);
                    }
                    break;
                case "GetInput":
                    try {
                        System.out.println(ProVerifRequests.getInputFile(args[1], args[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name, in arg2 a filename");
                        System.exit(1);
                    }
                    break;
                case "PostInputRaw":
                    try {
                        System.out.println(ProVerifRequests.postInputFile(args[1], args[2], args[3]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println(
                                "Please insert in arg1 a model name, in arg2 a filename, in arg3 a filebody [Proverif Format]");
                        System.exit(1);
                    }
                    break;
                case "PostInput":
                    ImportDirectedBigraph im7 = new ImportDirectedBigraph();
                    try {
                        DirectedBigraph bigraph7 = im7.doImport(args[3]);
                        ParseInput parsedInput7 = new ParseInput();
                        parsedInput7.parse(bigraph7, args[4]);
                        CreateInput createdInput7 = new CreateInput(parsedInput7);
                        createdInput7.create();
                        String inputProverif7 = createdInput7.getProverifInput();
                        System.out.println(ProVerifRequests.postInputFile(args[1], args[2], inputProverif7));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println(
                                "Please insert in arg1 a valid model, in arg2 a valid file name, in arg3 a valid jsonBigraph, in arg4 a valid integrativeBigraph");
                        System.exit(1);
                    }
                    break;
                case "PutInputRaw":
                    try {
                        System.out.println(ProVerifRequests.putInputFile(args[1], args[2], args[3]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println(
                                "Please insert in arg1 a model name, in arg2 a filename, in arg3 a filebody [Proverif Format]");
                        System.exit(1);
                    }
                    break;
                case "PutInput":
                    ImportDirectedBigraph im8 = new ImportDirectedBigraph();
                    try {
                        DirectedBigraph bigraph8 = im8.doImport(args[3]);
                        ParseInput parsedInput8 = new ParseInput();
                        parsedInput8.parse(bigraph8, args[4]);
                        CreateInput createdInput8 = new CreateInput(parsedInput8);
                        createdInput8.create();
                        String inputProverif8 = createdInput8.getProverifInput();
                        System.out.println(ProVerifRequests.putInputFile(args[1], args[2], inputProverif8));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println(
                                "Please insert in arg1 a valid model, in arg2 a valid file name, in arg3 a valid jsonBigraph, in arg4 a valid integrativeBigraph");
                        System.exit(1);
                    }
                    break;
                case "DeleteInput":
                    try {
                        System.out.println(ProVerifRequests.deleteInputFile(args[1], args[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name, in arg2 a filename");
                        System.exit(1);
                    }
                    break;
                case "PostVerText":
                    try {
                        System.out.println(ProVerifRequests.postVerifyText(args[1], args[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name, in arg2 a filename");
                        System.exit(1);
                    }
                    break;
                case "PostVerHtml":
                    try {
                        System.out.println(ProVerifRequests.postVerifyHtml(args[1], args[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name, in arg2 a filename");
                        System.exit(1);
                    }
                    break;
                case "PutVerText":
                    try {
                        System.out.println(ProVerifRequests.putVerifyText(args[1], args[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name, in arg2 a filename");
                        System.exit(1);
                    }
                    break;
                case "PutVerHtml":
                    try {
                        System.out.println(ProVerifRequests.putVerifyHtml(args[1], args[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name, in arg2 a filename");
                        System.exit(1);
                    }
                    break;
                case "GetOutputText":
                    try {
                        System.out.println(ProVerifRequests.getOutputText(args[1], args[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name, in arg2 a filename");
                        System.exit(1);
                    }
                    break;
                case "DeleteOutputText":
                    try {
                        System.out.println(ProVerifRequests.deleteOutputText(args[1], args[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name, in arg2 a filename");
                        System.exit(1);
                    }
                    break;
                case "GetOutputHtml":
                    try {
                        System.out.println(ProVerifRequests.getOutputHtml(args[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name");
                        System.exit(1);
                    }
                    break;
                case "DeleteOutputHtml":
                    try {
                        System.out.println(ProVerifRequests.deleteOutputHtml(args[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Please insert in arg1 a model name");
                        System.exit(1);
                    }
                    break;
                case "Help":
                    System.out.println(Constants.getHelp());
                    break;
                default:
                    System.err.println("No valid method, please insert in arg0 one of the implemented methods");
                    System.err.println("\n");
                    System.err.println(Constants.getHelp());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Please insert in arg0 a valid method");
            System.err.println("\n");
            System.err.println(Constants.getHelp());
        }
    }
}

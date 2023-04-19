package it.uniud.mads.jlibbig.proverif.constants;

public class Constants {

    private static final String process = "process";
    private static final String openComment = "(* ";
    private static final String closeComment = " *)";
    private static final String dot = ".";
    private static final String colon = ":";
    private static final String free = "free";
    private static final String privateDeclaration = "[private]";

    private static final String jsonIdKey = "id";

    private static final String typeDeclaration = "type";
    private static final String channelDeclaration = "channel";
    private static final String queryDeclaration = "query";
    private static final String eventDeclaration = "event";
    private static final String equationDeclaration = "equation";
    private static final String queryAttacker = "attacker";


    // private static final String httpBaseUrl = "http://localhost:5001/";
    private static final String httpBaseUrl = "http://172.19.0.2:5001/";
    // private static final String httpGetOutputUrl = "http://localhost:8081/"; // Utilizzo direttamente su Host
    private static final String httpGetOutputUrl = "http://172.19.0.2:5001/"; // Utilizzo con container GUI
    private static final String httpGet = "GET";
    private static final String httpPost = "POST";
    private static final String httpPut = "PUT";
    private static final String httpDelete = "DELETE";
    private static final String httpData = "data";
    private static final String httpType = "type";

    private static final String help = "List of available methods: \n 1)  PostVerify [bigraph integrativeJson modelName fileName] \n 2)  PostVerifyText [bigraph integrativeJson modelName fileName] \n 3)  PostVerifyHtml [bigraph integrativeJson modelName fileName] \n 4)  PutVerify [bigraph integrativeJson modelName fileName] \n 5)  PutVerifyText [bigraph integrativeJson modelName fileName] \n 6)  PutVerifyHtml [bigraph integrativeJson modelName fileName] \n 7)  GetModels \n 8)  GetModel [modelName] \n 9)  PostModel [modelName] \n 10) DeleteModel [modelName] \n 11) GetInput [modelName fileName] \n 12) PostInputRaw [modelName fileName fileBodyProverif] \n 13) PostInput [modelName fileName bigraphJson integrativeJson] \n 14) PutInputRaw [modelName fileName fileBodyProverif] \n 15) PutInput [modelName fileName bigraphJson integrativeJson] \n 16) DeleteInput [modelName fileName] \n 17) PostVerText [modelName fileName] \n 18) PostVerHtml [modelName fileName] \n 19) PutVerText [modelName fileName] \n 20) PutVerHtml [modelName fileName] \n 21) GetOutputText [modelName fileName] \n 22) DeleteOutputText [modelName fileName] \n 23) GetOutputHtml [modelName] \n 24) DeleteOutputHtml [modelName] \n 25) Help \n";
    
    /**
     * @return the process
     */
    public static String getProcess() {
        return process;
    }

    /**
     * @return the opencomment
     */
    public static String getOpencomment() {
        return openComment;
    }

    /**
     * @return the closecomment
     */
    public static String getClosecomment() {
        return closeComment;
    }

    /**
     * @return the jsonidkey
     */
    public static String getJsonidkey() {
        return jsonIdKey;
    }

    /**
     * @return the dot
     */
    public static String getDot() {
        return dot;
    }

    /**
     * @return the typedeclaration
     */
    public static String getTypedeclaration() {
        return typeDeclaration;
    }

    /**
     * @return the free
     */
    public static String getFree() {
        return free;
    }

    /**
     * @return the channeldeclaration
     */
    public static String getChanneldeclaration() {
        return channelDeclaration;
    }

    /**
     * @return the colon
     */
    public static String getColon() {
        return colon;
    }

    /**
     * @return the privatedeclaration
     */
    public static String getPrivatedeclaration() {
        return privateDeclaration;
    }

    /**
     * @return the querydeclaration
     */
    public static String getQuerydeclaration() {
        return queryDeclaration;
    }

    /**
     * @return the queryattacker
     */
    public static String getQueryattacker() {
        return queryAttacker;
    }

    /**
     * @return the httpbaseurl
     */
    public static String getHttpbaseurl() {
        return httpBaseUrl;
    }

    /**
     * @return the httpget
     */
    public static String getHttpget() {
        return httpGet;
    }

    /**
     * @return the httppost
     */
    public static String getHttppost() {
        return httpPost;
    }

    /**
     * @return the httpput
     */
    public static String getHttpput() {
        return httpPut;
    }

    /**
     * @return the httpdelete
     */
    public static String getHttpdelete() {
        return httpDelete;
    }

    /**
     * @return the httpdata
     */
    public static String getHttpdata() {
        return httpData;
    }

    /**
     * @return the httptype
     */
    public static String getHttptype() {
        return httpType;
    }

    /**
     * @return the eventdeclaration
     */
    public static String getEventdeclaration() {
        return eventDeclaration;
    }

    /**
     * @return the equationdeclaration
     */
    public static String getEquationdeclaration() {
        return equationDeclaration;
    }

    /**
     * @return the help
     */
    public static String getHelp() {
        return help;
    }

    public static String getHttpgetoutputurl() {
        return httpGetOutputUrl;
    }  
}
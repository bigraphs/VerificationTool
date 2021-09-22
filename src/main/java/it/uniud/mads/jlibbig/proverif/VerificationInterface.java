package it.uniud.mads.jlibbig.proverif;

import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;

public interface VerificationInterface {

        /**
         * Parse input, create Proverif file, create model, send input file to the
         * server and make a POST-REST request to verify proverif file both in text-mode
         * and html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @return the proverif's result.
         */
        public String postVerify(DirectedBigraph bigraph, String integrativeJson);

        /**
         * Parse input, create Proverif file, create model, send input file to the
         * server and make a POST-REST request to verify proverif file both in text-mode
         * and html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @return the proverif's result.
         */
        public String postVerify(DirectedBigraph bigraph, String integrativeJson, String modelName);

        /**
         * Parse input, create Proverif file, create model, send input file to the
         * server and make a POST-REST request to verify proverif file both in text-mode
         * and html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @param fileName        the File's name
         * @return the proverif's result.
         */
        public String postVerify(DirectedBigraph bigraph, String integrativeJson, String modelName, String fileName);

        /**
         * Parse input, create Proverif file, create model, send input file to the
         * server and make a POST-REST request to verify proverif file in text-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @return the proverif's result.
         */
        public String postVerifyText(DirectedBigraph bigraph, String integrativeJson);

        /**
         * Parse input, create Proverif file, create model, send input file to the
         * server and make a POST-REST request to verify proverif file in text-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @return the proverif's result.
         */
        public String postVerifyText(DirectedBigraph bigraph, String integrativeJson, String modelName);

        /**
         * Parse input, create Proverif file, create model, send input file to the
         * server and make a POST-REST request to verify proverif file in text-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @param fileName        the File's name
         * @return the proverif's result.
         */
        public String postVerifyText(DirectedBigraph bigraph, String integrativeJson, String modelName,
                        String fileName);

        /**
         * Parse input, create Proverif file, create model, send input file to the
         * server and make a POST-REST request to verify proverif file in html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @return the proverif's result.
         */
        public String postVerifyHtml(DirectedBigraph bigraph, String integrativeJson);

        /**
         * Parse input, create Proverif file, create model, send input file to the
         * server and make a POST-REST request to verify proverif file in html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @return the proverif's result.
         */
        public String postVerifyHtml(DirectedBigraph bigraph, String integrativeJson, String modelName);

        /**
         * Parse input, create Proverif file, create model, send input file to the
         * server and make a POST-REST request to verify proverif file in html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @param fileName        the File's name
         * @return the proverif's result.
         */
        public String postVerifyHtml(DirectedBigraph bigraph, String integrativeJson, String modelName,
                        String fileName);

        /**
         * Parse input, create Proverif file, send and replace input file to the server
         * and make a PUT-REST request to verify proverif file both in text-mode and
         * html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @return the proverif's result.
         */
        public String putVerify(DirectedBigraph bigraph, String integrativeJson);

        /**
         * Parse input, create Proverif file, send and replace input file to the server
         * and make a PUT-REST request to verify proverif file both in text-mode and
         * html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @return the proverif's result.
         */
        public String putVerify(DirectedBigraph bigraph, String integrativeJson, String modelName);

        /**
         * Parse input, create Proverif file, send and replace input file to the server
         * and make a PUT-REST request to verify proverif file both in text-mode and
         * html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @param fileName        the File's name
         * @return the proverif's result.
         */
        public String putVerify(DirectedBigraph bigraph, String integrativeJson, String modelName, String fileName);

        /**
         * Parse input, create Proverif file, send and replace input file to the server
         * and make a PUT-REST request to verify proverif file in text-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @return the proverif's result.
         */
        public String putVerifyText(DirectedBigraph bigraph, String integrativeJson);

        /**
         * Parse input, create Proverif file, send and replace input file to the server
         * and make a PUT-REST request to verify proverif file in text-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @return the proverif's result.
         */
        public String putVerifyText(DirectedBigraph bigraph, String integrativeJson, String modelName);

        /**
         * Parse input, create Proverif file, send and replace input file to the server
         * and make a PUT-REST request to verify proverif file in text-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @param fileName        the File's name
         * @return the proverif's result.
         */
        public String putVerifyText(DirectedBigraph bigraph, String integrativeJson, String modelName, String fileName);

        /**
         * Parse input, create Proverif file, send and replace input file to the server
         * and make a PUT-REST request to verify proverif file in html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @return the proverif's result.
         */
        public String putVerifyHtml(DirectedBigraph bigraph, String integrativeJson);

        /**
         * Parse input, create Proverif file, send and replace input file to the server
         * and make a PUT-REST request to verify proverif file in html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @return the proverif's result.
         */
        public String putVerifyHtml(DirectedBigraph bigraph, String integrativeJson, String modelName);

        /**
         * Parse input, create Proverif file, send and replace input file to the server
         * and make a PUT-REST request to verify proverif file in html-mode.
         * 
         * @param bigraph         the Directed Bigraph
         * @param integrativeJson the Bigraph's integrative json.
         * @param modelName       the Model's name
         * @param fileName        the File's name
         * @return the proverif's result.
         */
        public String putVerifyHtml(DirectedBigraph bigraph, String integrativeJson, String modelName, String fileName);

}
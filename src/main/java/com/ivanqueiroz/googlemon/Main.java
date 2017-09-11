package com.ivanqueiroz.googlemon;

import com.ivanqueiroz.googlemon.logic.GoogleSearch;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ivan Queiroz <ivanqueiroz@gmail.com>
 * 
 * Fazer combinações de palavras no addwords
 * 
 * Title do Site 
 * - colocar o termo em primeiro, depois o titulo da empresa)
 * - colocar entre 60˜70 caracteres
 * -keyword density
 */
public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        String search = "curso salvador web design";
        
        GoogleSearch.searchRank(search, 10);
        
    }

}

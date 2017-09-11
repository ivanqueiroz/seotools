package com.ivanqueiroz.googlemon.logic;

import com.ivanqueiroz.googlemon.Site;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.experimental.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ivan Queiroz <ivanqueiroz@gmail.com>
 */
@UtilityClass
public class GoogleSearch {

    private final String google = "http://www.google.com/search?q=";

    public List<Site> searchRank(String search, int resultSize) {
        String charset = "UTF-8";
        String userAgent = "GoogleMonBot 1.0 (+http://www.ivanqueiroz.com/)";

        List<Site> resultado = new ArrayList<>();

        Elements links = null;
        try {
            links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GoogleSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GoogleSearch.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (links == null) {
            return resultado;
        }

        for (Element link : links) {
            String title = link.text();
            String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
            try {
                url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GoogleSearch.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!url.startsWith("http")) {
                continue; // Ads/news/etc.
            }

            resultado.add(new Site(url, title));
        }
        
        return resultado;
    }

}

package com.zerobase.dividendproject.scraping;

import com.zerobase.dividendproject.model.Company;
import com.zerobase.dividendproject.model.Dividend;
import com.zerobase.dividendproject.model.Month;
import com.zerobase.dividendproject.model.ScrapedResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class YahooFinanceScrap {

    public static final String YAHOO_DIVIDEND_URL = "https://finance.yahoo.com/quote/%s/history/?period1=%d&period2=%d&interval=1mo";
    private static final String YAHOO_NAME_URL = "https://finance.yahoo.com/quote/%s/?p=%s";
    private static final long START_TIME = 86400; // 60 * 60 * 24

    public ScrapedResult scrapDividends(Company company){

        var scrapedResult = new ScrapedResult();
        scrapedResult.setCompany(company);

        try {

            long now = System.currentTimeMillis() / 1000;

            String url = String.format(YAHOO_DIVIDEND_URL, company.getTicker(), START_TIME, now);
            Document document = Jsoup.connect(url).get();

            Elements parsingDivs = document.getElementsByClass("table svelte-ewueuo");
            Element tableEle = parsingDivs.get(0);
            Element tbody = tableEle.children().get(1);

            List<Dividend> dividends = new ArrayList<>();
            for (Element e : tbody.children()) {
                String txt = e.text();
                if (!txt.endsWith("Dividend")){
                    continue;
                }
                String[] splits = txt.split(" ");
                try {
                    int month = Month.monthStrToInt(splits[0]);
                    int day = Integer.parseInt(splits[1].replace(",", ""));
                    int year = Integer.parseInt(splits[2]);
                    String dividend = splits[3];
                    dividends.add(new Dividend(LocalDate.of(year, month, day), dividend));
                }catch (NumberFormatException ex){
                    ex.printStackTrace();
                }

            }
            scrapedResult.setDividends(dividends);

        }catch (IOException e) {
            e.printStackTrace();
        }

        return scrapedResult;
    }

    public Company scrapCompanyByTicker(String ticker){

        String url =  YAHOO_NAME_URL.formatted(ticker, ticker);

        try {
            Document document = Jsoup.connect(url).get();
            Element NameEle = document.select(".svelte-3a2v0c h1").get(0);
            String companyName = NameEle.text().split("\\(")[0].trim();
            return new Company(companyName, ticker);
        } catch (IOException e){
            e.printStackTrace();

        }
        return null;
    }
}

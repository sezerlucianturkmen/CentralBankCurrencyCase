package com.xinerji.service;

import com.xinerji.dto.request.CurrencyRequestDto;
import com.xinerji.dto.response.CurrencyResponseDto;
import com.xinerji.exception.ErrorType;
import com.xinerji.exception.ManagerException;
import com.xinerji.mapper.ICurrencyMapper;
import com.xinerji.mapper.IDateStampMapper;
import com.xinerji.repository.ICurrencyRepository;
import com.xinerji.repository.entity.Currency;
import com.xinerji.repository.entity.DateStamp;
import com.xinerji.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService extends ServiceManager<Currency,Long> {
    private final ICurrencyRepository repository;
    private final DateStampService dateStampService;

    public CurrencyService(ICurrencyRepository repository,DateStampService dateStampService) {
        super(repository);
        this.repository = repository;
        this.dateStampService = dateStampService;
    }

    public List<CurrencyResponseDto> getCurrencies(CurrencyRequestDto dto) {
        Optional<List<Currency>> currencyList = repository.findAllOptionalByDateStamp(dateStampService.findOptionalByDate(dto.getDate()).get());
            if(currencyList.isPresent() && currencyList.get().size()!=0){
                    return currencyList.get().stream().map(x-> ICurrencyMapper.INSTANCE.toCurrencyResponseDto(x)).collect(Collectors.toList());
            }else{
                 throw  new ManagerException(ErrorType.CURRENCY_NOT_EXIST);
            }
    }

    /**
     * It creates Currency objects using by xml link from central bank
     * @param dto
     * @return
     */
    public boolean saveCurrencies(CurrencyRequestDto dto){

        String[] parts = dto.getDate().split("-");
        String yyyy = parts[0];
        String mm = parts[1];
        String dd = parts[2];
        DateStamp dateStamp = dateStampService.save(DateStamp.builder()
                        .date(dto.getDate())
                        .ddMMyyyy(dd+mm+yyyy)
                        .yyyyMM(yyyy+mm)
                        .build());
        try {
            NodeList nodeList = provideNodeList(dateStamp);

            for(int i = 0; i<nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                Currency currency = Currency.builder()
                        .currencyCode(element.getAttribute("CurrencyCode"))
                        .unit(Integer.parseInt(element.getElementsByTagName("Unit").item(0).getTextContent()))
                        .currencyName(element.getElementsByTagName("CurrencyName").item(0).getTextContent())
                        .forexBuying(Double.parseDouble(element.getElementsByTagName("ForexBuying").item(0).getTextContent()))
                        .forexSelling(Double.parseDouble(element.getElementsByTagName("ForexSelling").item(0).getTextContent()))
                        .banknoteBuying(Double.parseDouble(element.getElementsByTagName("BanknoteBuying").item(0).getTextContent()))
                        .banknoteSelling(Double.parseDouble(element.getElementsByTagName("BanknoteSelling").item(0).getTextContent()))
                        .dateStamp(dateStamp)
                        .build();
                save(currency);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * based on currencies, nodeList is provided.
     * @param dateStamp includes required date String
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public NodeList provideNodeList(DateStamp dateStamp) throws ParserConfigurationException, IOException, SAXException {


        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        URL url = new URL("https://www.tcmb.gov.tr/kurlar/"+dateStamp.getYyyyMM()+"/"+dateStamp.getDdMMyyyy()+".xml");
        Document document = builder.parse(url.openStream());
        return document.getElementsByTagName("Currency");
    }



}

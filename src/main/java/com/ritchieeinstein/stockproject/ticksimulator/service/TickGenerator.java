package com.ritchieeinstein.stockproject.ticksimulator.service;

import com.ritchieeinstein.stockproject.ticksimulator.model.FilteredTick;
import com.ritchieeinstein.stockproject.ticksimulator.model.Instrument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.ritchieeinstein.stockproject.ticksimulator.model.Instrument.round;

@Service
public class TickGenerator {

    @Value("instrumentList.csv")
    private ClassPathResource instrumentFileResource;
    private static final int RANGE_START = 50;
    private static final int RANGE_END = 100;
    private static final Random random = new Random();
    private static final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));

    public void run() throws IOException, InterruptedException {

        List<String[]> incomingTokenList = readData(instrumentFileResource);
        List<Instrument> instrumentList = incomingTokenList.stream()
                .map(strings -> new Instrument(Long.parseLong(strings[0]), strings[1])).collect(Collectors.toList());
        Map<Long,Double> instrumentPriceMap = instrumentList.stream().collect(Collectors.toMap(Instrument::getInstrumentToken, instrument -> instrument.getBasePrice()));
        calendar.set(2020,new Date().getMonth(),new Date().getDate(),9,15);
        Instant instant = calendar.toInstant();
        Instant now = Instant.now();
        long diffInMilli = Duration.between(instant,now).toMillis();
        while(true){
            Instant startOp = Instant.now();
            int activeStocksThisTurn = random.nextInt(RANGE_END - RANGE_START) + RANGE_START;
            List<FilteredTick> tickList = new LinkedList<>();
            for(int i=0;i<activeStocksThisTurn;i++){
                Instrument instrument = instrumentList.get(random.nextInt(instrumentList.size()));
                boolean isUpTrend = random.nextBoolean();
                double actionPercentage = random.nextInt(10) * 0.1;
                double lastTradedPrice = instrumentPriceMap.get(instrument.getInstrumentToken());
                double currentTradedPrice = round(isUpTrend? lastTradedPrice + lastTradedPrice * actionPercentage
                        : lastTradedPrice - lastTradedPrice * actionPercentage,2);
                FilteredTick tick = new FilteredTick();
                tick.setId(UUID.randomUUID());
                tick.setInstrumentToken(instrument.getInstrumentToken());
                tick.setTickTimestamp(Date.from(Instant.now().minusMillis(diffInMilli)));
                tick.setLastTradedTimestamp(Date.from(Instant.now().minusMillis(diffInMilli)));
                tick.setLastTradedPrice(currentTradedPrice);
                tick.setLastTradedQuantity(random.nextInt(300));
                instrumentPriceMap.put(instrument.getInstrumentToken(),currentTradedPrice);
                tickList.add(tick);
            }
            System.out.println("\n\n\n\n\n\n STOCKS THIS TURN : " + activeStocksThisTurn);
            for(FilteredTick tick : tickList) System.out.println(tick.toString());
            Instant endOp = Instant.now();
            long diff = Duration.between(startOp,endOp).toMillis();
            Thread.sleep(1000 - diff);
        }

    }

    public static List<String[]> readData(ClassPathResource resource) throws IOException {
        List<String[]> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
        }
        return content;
    }
}

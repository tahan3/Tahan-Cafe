package by.epam.mtlcwtchr.ecafe.config;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public enum ReservationConfig {

    INSTANCE;

    private Date cafeWorkDayBegin;
    private Date cafeWorkDayEnd;
    private int maxDaysForwardCanBeReserved;

    ReservationConfig(){
        loadProperties();
    }

    private void loadProperties() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("reservationProperties.properties")){
            Properties properties = new Properties();
            properties.load(in);
            cafeWorkDayBegin = Objects.nonNull(properties.getProperty("cafeWorkDayBegin")) ?
                    simpleDateFormat.parse(properties.getProperty("cafeWorkDayBegin")) : simpleDateFormat.parse("08:00");
            cafeWorkDayEnd = Objects.nonNull(properties.getProperty("cafeWorkDayEnd")) ?
                    simpleDateFormat.parse(properties.getProperty("cafeWorkDayEnd")) : simpleDateFormat.parse("18:00");
            maxDaysForwardCanBeReserved = Objects.nonNull(properties.getProperty("maxDaysForwardCanBeReserved")) ?
                    Integer.parseInt(properties.getProperty("maxDaysForwardCanBeReserved")) : 30;
        } catch (IOException | ParseException ex){
            try {
                cafeWorkDayBegin = simpleDateFormat.parse("08:00");
            } catch (ParseException ignored) { }
            try {
                cafeWorkDayEnd = simpleDateFormat.parse("18:00");
            } catch (ParseException ignored) { }
            maxDaysForwardCanBeReserved = 30;
        }
    }

    public Date getCafeWorkDayBegin() {
        return cafeWorkDayBegin;
    }

    public Date getCafeWorkDayEnd() {
        return cafeWorkDayEnd;
    }

    public int getMaxDaysForwardCanBeReserved() {
        return maxDaysForwardCanBeReserved;
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "cafeWorkDayBegin=" + cafeWorkDayBegin +
                ", cafeWorkDayEnd=" + cafeWorkDayEnd +
                ", maxDayForwardCanBeReserved" + maxDaysForwardCanBeReserved +
                '}';
    }
}
